import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, catchError, map, of, switchMap, tap } from 'rxjs';
import { UserProfile } from '../models';
import { ApiService } from './api.service';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly tokenKey = 'jwt_token';
  private readonly userSubject = new BehaviorSubject<UserProfile | null>(null);
  private readonly authenticatedSubject = new BehaviorSubject<boolean>(this.hasStoredToken());

  readonly user$ = this.userSubject.asObservable();
  readonly authenticated$ = this.authenticatedSubject.asObservable();

  constructor(private api: ApiService) {
    if (this.hasStoredToken()) {
      this.loadProfile().subscribe();
    }
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  isAuthenticated(): boolean {
    return this.authenticatedSubject.getValue();
  }

  login(credentials: { username: string; password: string }): Observable<boolean> {
    return this.api.login(credentials).pipe(
      map((token) => {
        const normalized = token?.trim() ?? '';
        if (!normalized || normalized.toLowerCase().includes('invalid')) {
          return false;
        }
        this.storeToken(normalized);
        this.authenticatedSubject.next(true);
        return true;
      }),
      switchMap((success) => (success ? this.loadProfile().pipe(map(() => true)) : of(false)))
    );
  }

  signup(payload: {
    username: string;
    password: string;
    firstname: string;
    lastname: string;
    email: string;
    phoneNumber: string;
  }): Observable<boolean> {
    return this.api.signup(payload).pipe(
      map((message) => {
        const normalized = message?.trim() ?? '';
        return normalized.toLowerCase().includes('success');
      })
    );
  }

  loadProfile(): Observable<boolean> {
    if (!this.hasStoredToken()) {
      this.userSubject.next(null);
      this.authenticatedSubject.next(false);
      return of(false);
    }

    return this.api.getUserDetails().pipe(
      tap((user) => this.userSubject.next(user)),
      map(() => true),
      catchError(() => {
        this.clearSession();
        return of(false);
      })
    );
  }

  logout(): void {
    this.clearSession();
  }

  private storeToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  private hasStoredToken(): boolean {
    return !!this.getToken();
  }

  private clearSession(): void {
    localStorage.removeItem(this.tokenKey);
    this.userSubject.next(null);
    this.authenticatedSubject.next(false);
  }
}

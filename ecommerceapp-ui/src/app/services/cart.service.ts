import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, catchError, map, of, tap } from 'rxjs';
import { CartItem, Product } from '../models';
import { ApiService } from './api.service';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class CartService {
  private readonly itemsSubject = new BehaviorSubject<CartItem[]>([]);
  readonly items$ = this.itemsSubject.asObservable();

  constructor(private api: ApiService, private authService: AuthService) {}

  addToCart(product: Product, quantity = 1): Observable<string> {
    const current = this.itemsSubject.getValue();
    const existing = current.find((item) => item.product.id === product.id);

    const nextItems = existing
      ? current.map((item) =>
          item.product.id === product.id ? { ...item, quantity: item.quantity + quantity } : item
        )
      : [...current, { product, quantity }];

    this.itemsSubject.next(nextItems);

    if (!this.authService.isAuthenticated()) {
      return of('Added locally');
    }

    return this.api.addToCart(product.id, quantity).pipe(
      tap(() => this.loadFromServer().subscribe()),
      catchError(() => of('Saved locally'))
    );
  }

  loadFromServer(): Observable<CartItem[]> {
    if (!this.authService.isAuthenticated()) {
      return of(this.itemsSubject.getValue());
    }

    return this.api.getCart().pipe(
      map((products) => products.map((product) => ({ product, quantity: 1 }))),
      tap((items) => this.itemsSubject.next(items)),
      catchError(() => of(this.itemsSubject.getValue()))
    );
  }

  updateQuantity(productId: number, quantity: number): void {
    const current = this.itemsSubject.getValue();
    const existing = current.find((item) => item.product.id === productId);

    if (!existing) {
      return;
    }

    const nextItems = quantity <= 0
      ? current.filter((item) => item.product.id !== productId)
      : current.map((item) => (item.product.id === productId ? { ...item, quantity } : item));

    this.itemsSubject.next(nextItems);
  }

  removeItem(productId: number): void {
    const nextItems = this.itemsSubject.getValue().filter((item) => item.product.id !== productId);
    this.itemsSubject.next(nextItems);
  }

  clear(): void {
    this.itemsSubject.next([]);
  }

  getCount(): number {
    return this.itemsSubject.getValue().reduce((sum, item) => sum + item.quantity, 0);
  }
}

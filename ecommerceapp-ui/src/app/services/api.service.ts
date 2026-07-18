import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OrderSummary, Product, UserProfile } from '../models';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  private authHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwt_token') ?? '';
    return new HttpHeaders({ Authorization: `Bearer ${token}` });
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}/product/getAllProductDetails`);
  }

  login(credentials: { username: string; password: string }): Observable<string> {
    return this.http.post(`${this.baseUrl}/user/public/login`, credentials, {
      responseType: 'text'
    });
  }

  signup(payload: {
    username: string;
    password: string;
    firstname: string;
    lastname: string;
    email: string;
    phoneNumber: string;
  }): Observable<string> {
    return this.http.post(`${this.baseUrl}/user/public/endUser/signup`, payload, {
      responseType: 'text'
    });
  }

  getUserDetails(): Observable<UserProfile> {
    return this.http.get<UserProfile>(`${this.baseUrl}/user/details`, {
      headers: this.authHeaders()
    });
  }

  addToCart(productId: number, quantity: number): Observable<string> {
    return this.http.get(`${this.baseUrl}/cart/addItem/${productId}/${quantity}`, {
      headers: this.authHeaders(),
      responseType: 'text'
    });
  }

  getCart(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}/cart/products`, {
      headers: this.authHeaders()
    });
  }

  createOrder(): Observable<string> {
    return this.http.post(`${this.baseUrl}/order/createOrder`, {}, {
      headers: this.authHeaders(),
      responseType: 'text'
    });
  }

  getOrders(): Observable<OrderSummary[]> {
    return this.http.get<OrderSummary[]>(`${this.baseUrl}/order/history`, {
      headers: this.authHeaders()
    });
  }
}

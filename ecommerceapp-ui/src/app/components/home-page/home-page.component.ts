import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Product } from '../../models';
import { ApiService } from '../../services/api.service';
import { AuthService } from '../../services/auth.service';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent implements OnInit {
  products: Product[] = [];
  loading = true;
  errorMessage = '';
  quantities: Record<number, number> = {};

  constructor(
    private apiService: ApiService,
    public authService: AuthService,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.loading = true;
    this.errorMessage = '';
    this.apiService.getProducts().subscribe({
      next: (products) => {
        this.products = products;
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'We could not reach the product catalog right now.';
        this.loading = false;
      }
    });
  }

  getQuantity(productId: number): number {
    return this.quantities[productId] ?? 1;
  }

  incrementQuantity(product: Product): void {
    this.quantities[product.id] = (this.quantities[product.id] ?? 1) + 1;
  }

  decrementQuantity(product: Product): void {
    const current = this.quantities[product.id] ?? 1;
    this.quantities[product.id] = current > 1 ? current - 1 : 1;
  }

  addToCart(product: Product): void {
    const quantity = this.getQuantity(product.id);
    this.cartService.addToCart(product, quantity).subscribe();
    this.quantities[product.id] = 1;
  }
}

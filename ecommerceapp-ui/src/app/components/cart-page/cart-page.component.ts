import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { ApiService } from '../../services/api.service';
import { CartItem } from '../../models';

@Component({
  selector: 'app-cart-page',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './cart-page.component.html',
  styleUrl: './cart-page.component.css'
})
export class CartPageComponent implements OnInit {
  items: CartItem[] = [];
  message = '';

  constructor(private cartService: CartService, private apiService: ApiService) {}

  ngOnInit(): void {
    this.cartService.items$.subscribe((items) => (this.items = items));
    this.cartService.loadFromServer().subscribe();
  }

  total(): number {
    return this.items.reduce((sum, item) => sum + item.product.price * item.quantity, 0);
  }

  changeQuantity(item: CartItem, delta: number): void {
    const nextQuantity = item.quantity + delta;
    this.cartService.updateQuantity(item.product.id, nextQuantity);
  }

  remove(productId: number): void {
    this.cartService.removeItem(productId);
  }

  checkout(): void {
    this.apiService.createOrder().subscribe({
      next: (response) => {
        this.message = response;
        this.cartService.clear();
      },
      error: () => {
        this.message = 'Unable to place order right now.';
      }
    });
  }
}

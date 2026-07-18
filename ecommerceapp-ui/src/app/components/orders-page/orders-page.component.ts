import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';
import { OrderSummary } from '../../models';

@Component({
  selector: 'app-orders-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './orders-page.component.html',
  styleUrl: './orders-page.component.css'
})
export class OrdersPageComponent implements OnInit {
  orders: OrderSummary[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.getOrders().subscribe({
      next: (orders) => (this.orders = orders),
      error: () => (this.orders = [])
    });
  }
}

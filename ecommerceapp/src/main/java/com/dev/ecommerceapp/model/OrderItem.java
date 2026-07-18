package com.dev.ecommerceapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_items")
public class OrderItem {
	
	@EmbeddedId
	private OrderItemId id;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price")
	private int price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("orderId")
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Product product;
	
}

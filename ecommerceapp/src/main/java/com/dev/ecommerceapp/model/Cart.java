package com.dev.ecommerceapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
	
	@EmbeddedId
	private CartItemId id;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price")
	private long price;
	
	public Cart(String username, int productId, int quantity, long price) {
		this.id = new CartItemId(username, productId);
		this.quantity = quantity;
		this.price = price;
	}
	
	

}

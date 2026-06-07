package com.dev.ecommerceapp.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderItemId {
	
	@Column(name = "order_id")
	private long orderId;
	
	@Column(name = "product_id")
	private int productId;

	@Override
	public int hashCode() {
		return Objects.hash(orderId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
				return true;
		if (obj==null || this.getClass()!=obj.getClass())
				return false;
		OrderItemId that = (OrderItemId) obj;
		return this.orderId==that.orderId && this.productId==that.productId;
	}
	
}

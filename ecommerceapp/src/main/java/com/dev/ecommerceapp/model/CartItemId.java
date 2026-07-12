package com.dev.ecommerceapp.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CartItemId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "username")
	private String username;
	
	@Column(name = "product_id")
	private int productId;

	@Override
	public int hashCode() {
		return Objects.hash(username, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
				return true;
		if (obj==null || this.getClass()!=obj.getClass())
				return false;
		CartItemId that = (CartItemId) obj;
		return this.username.equals(that.username) && this.productId==that.productId;
	}


}

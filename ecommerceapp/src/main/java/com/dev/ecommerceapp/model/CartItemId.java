package com.dev.ecommerceapp.model;

import java.io.Serializable;
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
public class CartItemId implements Serializable{

	@Column(name = "username")
	private String username;
	
	@Column(name = "product_id")
	private int product_id;

	@Override
	public int hashCode() {
		return Objects.hash(username, product_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
				return true;
		if (obj==null || this.getClass()!=obj.getClass())
				return false;
		CartItemId that = (CartItemId) obj;
		return this.username.equals(that.username) && this.product_id==that.product_id;
	}


}

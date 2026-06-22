package com.dev.ecommerceapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
	
	@JsonIgnore
	private String username;
	
	@Positive(message = "Product Id cannot be empty nor Negative")
	private int productId;
	
	@Positive(message = "Quantity cannot be zero or less")
	private int quantity;
	
	@JsonIgnore
	private float price;
}

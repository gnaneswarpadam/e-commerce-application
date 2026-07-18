package com.dev.ecommerceapp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemDTO {
	
	private long orderId;
		
	private int quantity;
	
	private int price;
	
	private ProductDetailsDTO productDetailsDTO;
	
}

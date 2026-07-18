package com.dev.ecommerceapp.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderDTO {
	
	private long orderId;
	
	private String status;
	
	private double amount;
	
	List<OrderItemDTO> orderItems;
}

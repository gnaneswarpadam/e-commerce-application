package com.dev.ecommerceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ecommerceapp.model.Order;
import com.dev.ecommerceapp.service.OrderServiceImpl;

@RestController
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@PostMapping("/createOrder")
	public String createOrder(@RequestBody Order order) {
		return orderServiceImpl.createOrder(order);
	}
}

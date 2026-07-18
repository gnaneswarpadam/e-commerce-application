package com.dev.ecommerceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ecommerceapp.model.OrderDTO;
import com.dev.ecommerceapp.service.OrderServiceImpl;

@RestController
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@PostMapping("/createOrder")
	public String createOrder(Authentication authentication) {
		String username = authentication.getName();
		return orderServiceImpl.createOrder(username);
	}
	
	@GetMapping("/history")
	public List<OrderDTO> getAllOrders(Authentication authentication) {
		String username = authentication.getName();
		return orderServiceImpl.getAllOrders(username);
	}
}

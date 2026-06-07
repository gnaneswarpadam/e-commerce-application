package com.dev.ecommerceapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ecommerceapp.model.Cart;
import com.dev.ecommerceapp.repository.CartRepository;

@Service
public class CartServiceImpl {
	
//	@Autowired
//	private CartRepositoryImpl cartRepositoryImpl;
	
	@Autowired
	private CartRepository cartRepository;
	
	public String addItem(Cart cart) {
		cartRepository.save(cart);
		return "Updated Cart Successfully";
	}
}

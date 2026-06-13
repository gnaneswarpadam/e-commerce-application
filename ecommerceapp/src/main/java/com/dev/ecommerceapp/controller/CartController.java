package com.dev.ecommerceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ecommerceapp.model.Cart;
import com.dev.ecommerceapp.service.CartServiceImpl;

@RestController
@RequestMapping("cart")
public class CartController {
	
	@Autowired
	private CartServiceImpl cartServiceImpl;
	
	@PostMapping("addItem")
	public String addItem(Authentication authentication, @RequestBody Cart cart) {
		cart.getId().setUsername(authentication.getName());
		return cartServiceImpl.addItem(cart);
	}

}

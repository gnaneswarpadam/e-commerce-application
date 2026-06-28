package com.dev.ecommerceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ecommerceapp.model.ProductDetailsDTO;
import com.dev.ecommerceapp.service.CartServiceImpl;

@RestController
@RequestMapping("cart")
public class CartController {
	
	@Autowired
	private CartServiceImpl cartServiceImpl;
	
	@GetMapping("addItem/{productId}/{quantity}")
	public String addItem(Authentication authentication, @PathVariable("productId") int productId, @PathVariable("quantity") int quantity) {
		String username = authentication.getName();
		return cartServiceImpl.addItem(username, productId, quantity);
	}
	
	@GetMapping("/products")
	public List<ProductDetailsDTO> getProducts(Authentication authentication){
		String username = authentication.getName();
		return cartServiceImpl.getProducts(username);
	}

}

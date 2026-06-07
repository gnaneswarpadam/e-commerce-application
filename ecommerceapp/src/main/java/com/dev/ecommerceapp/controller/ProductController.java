package com.dev.ecommerceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ecommerceapp.model.Product;
import com.dev.ecommerceapp.model.ProductDetailsDTO;
import com.dev.ecommerceapp.service.ProductServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@GetMapping(value = "/getProductDetails") 
	public List<Product> getProductDetails() {
		return productServiceImpl.getProductDetails();
	}
	
	@GetMapping(value = "/getAllProductDetails")
	public List<ProductDetailsDTO> getAllProductDetails() {
		return productServiceImpl.getAllProductDetails();
	}
	
	@PostMapping(value = "/addProduct")
	public String addProduct(@RequestBody ProductDetailsDTO dto) {
		if(dto==null) {
			return "Product Details aren't received";
		}
		return productServiceImpl.addProduct(dto);
	}
	
	@PostMapping(value = "/updateProduct")
	public String updateProduct(@RequestBody ProductDetailsDTO dto) {
		if(dto==null) {
			return "Product Details aren't received";
		}
		return productServiceImpl.updateProduct(dto);
	}

}
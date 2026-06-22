package com.dev.ecommerceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ecommerceapp.exception.AppException;
import com.dev.ecommerceapp.marker.UpdateProductMarker;
import com.dev.ecommerceapp.model.Product;
import com.dev.ecommerceapp.model.ProductDetailsDTO;
import com.dev.ecommerceapp.service.ProductServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	public String addProduct(@Valid @RequestBody ProductDetailsDTO dto) {
		if(dto==null) {
			throw new AppException("Product Details aren't received");
		}
		log.info(dto.toString());
		return productServiceImpl.addProduct(dto);
	}
	
	@PostMapping(value = "/updateProduct")
	public String updateProduct(@Validated(UpdateProductMarker.class) @RequestBody ProductDetailsDTO dto) {
		if(dto==null) {
			throw new AppException("Product Details aren't received");
		}
		log.info(dto.toString());
		return productServiceImpl.updateProduct(dto);
	}

}
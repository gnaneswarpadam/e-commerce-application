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
import com.dev.ecommerceapp.model.ProductImage;
import com.dev.ecommerceapp.repository.ProductImageRepository;
import com.dev.ecommerceapp.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductImageRepository productImageRepository;
	
	@GetMapping(value = "/getProductDetails") 
	public List<Product> getProductDetails() {
		return productRepository.findAll();
	}
	
	@GetMapping(value = "/getAllProductDetails")
	public List<ProductDetailsDTO> getAllProductDetails() {
		return productRepository.getAllProductDetails();
	}
	
	@PostMapping(value = "/addProduct")
	public String addProduct(@RequestBody ProductDetailsDTO dto) {
		if(dto==null) {
			return "Product Details aren't received";
		}
		Product product = new Product(dto.getName(),dto.getPrice(),dto.getDescription(),dto.getCount());
		product = productRepository.save(product);
		productImageRepository.save(new ProductImage(product.getId(),dto.getImageUrl()));
		return "Details added successfully";
	}
	
	@PostMapping(value = "/updateProduct")
	public String updateProduct(@RequestBody ProductDetailsDTO dto) {
		if(dto==null) {
			return "Product Details aren't received";
		}
		Product product = new Product(dto.getId(),dto.getName(),dto.getPrice(),dto.getDescription(),dto.getCount());
		product = productRepository.save(product);
		productImageRepository.save(new ProductImage(dto.getId(),dto.getImageUrl()));
		return "Details updated successfully";
	}

}
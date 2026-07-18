package com.dev.ecommerceapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.ecommerceapp.model.Product;
import com.dev.ecommerceapp.model.ProductDetailsDTO;
import com.dev.ecommerceapp.model.ProductImage;
import com.dev.ecommerceapp.repository.ProductRepository;

@Service
public class ProductServiceImpl {

	private final ProductRepository productRepository;
		
	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	public List<Product> getProductDetails() {
		return productRepository.findAll();
	}
	
	public List<ProductDetailsDTO> getAllProductDetails() {
		return productRepository.getAllProductDetails();
	}
	
	public String addProduct(ProductDetailsDTO dto) {
		ProductImage productImage = ProductImage.builder().id(dto.getId()).imageUrl(dto.getImageUrl()).build();
		Product product = Product.builder().name(dto.getName()).price(dto.getPrice()).description(dto.getDescription()).count(dto.getCount()).build();
		product.setProductImage(productImage);
		productImage.setProduct(product);
		product = productRepository.save(product);
		return "Details added successfully";
	}
	
	public String updateProduct(ProductDetailsDTO dto) {
		ProductImage productImage = ProductImage.builder().id(dto.getId()).imageUrl(dto.getImageUrl()).build();
		Product product = Product.builder().id(dto.getId()).name(dto.getName()).price(dto.getPrice()).description(dto.getDescription()).count(dto.getCount()).build();
		product.setProductImage(productImage);
		productImage.setProduct(product);
		product = productRepository.save(product);
		return "Details updated successfully";
	}
}

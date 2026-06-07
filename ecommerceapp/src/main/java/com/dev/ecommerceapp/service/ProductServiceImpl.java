package com.dev.ecommerceapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.ecommerceapp.model.Product;
import com.dev.ecommerceapp.model.ProductDetailsDTO;
import com.dev.ecommerceapp.model.ProductImage;
import com.dev.ecommerceapp.repository.ProductImageRepository;
import com.dev.ecommerceapp.repository.ProductRepository;

@Service
public class ProductServiceImpl {

	private final ProductRepository productRepository;
	
	private final ProductImageRepository productImageRepository;
	
	public ProductServiceImpl(ProductRepository productRepository, ProductImageRepository productImageRepository) {
		super();
		this.productRepository = productRepository;
		this.productImageRepository = productImageRepository;
	}

	public List<Product> getProductDetails() {
		return productRepository.findAll();
	}
	
	public List<ProductDetailsDTO> getAllProductDetails() {
		return productRepository.getAllProductDetails();
	}
	
	public String addProduct(ProductDetailsDTO dto) {
		Product product = new Product(dto.getName(),dto.getPrice(),dto.getDescription(),dto.getCount());
		product = productRepository.save(product);
		productImageRepository.save(new ProductImage(product.getId(),dto.getImageUrl()));
		return "Details added successfully";
	}
	
	public String updateProduct(ProductDetailsDTO dto) {
		if(dto==null) {
			return "Product Details aren't received";
		}
		Product product = new Product(dto.getId(),dto.getName(),dto.getPrice(),dto.getDescription(),dto.getCount());
		product = productRepository.save(product);
		productImageRepository.save(new ProductImage(dto.getId(),dto.getImageUrl()));
		return "Details updated successfully";
	}
}

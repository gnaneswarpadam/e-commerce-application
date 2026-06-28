package com.dev.ecommerceapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.ecommerceapp.model.Product;
import com.dev.ecommerceapp.model.ProductDetailsDTO;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query("SELECT new com.dev.ecommerceapp.model.ProductDetailsDTO(P.id, P.name, P.price, P.description, P.count, PI.imageUrl) FROM Product P LEFT JOIN ProductImage PI ON P.id=PI.id")
	public List<ProductDetailsDTO> getAllProductDetails();
	
	@Query("SELECT new com.dev.ecommerceapp.model.ProductDetailsDTO(P.id, P.name, P.price, P.description, P.count, PI.imageUrl) FROM Product P LEFT JOIN ProductImage PI ON P.id=PI.id WHERE P.id in (:productIds)")
	public List<ProductDetailsDTO> getSpecificProductDetails(@Param("productIds") List<Integer> productIds);
}
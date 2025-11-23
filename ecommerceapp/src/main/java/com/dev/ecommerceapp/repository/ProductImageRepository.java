package com.dev.ecommerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ecommerceapp.model.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer>{

}

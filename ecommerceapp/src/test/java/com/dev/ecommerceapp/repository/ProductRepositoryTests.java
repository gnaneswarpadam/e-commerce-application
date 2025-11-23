package com.dev.ecommerceapp.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dev.ecommerceapp.model.ProductDetailsDTO;

@SpringBootTest
public class ProductRepositoryTests {
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void getAllProductDetailsTest() {
		List<ProductDetailsDTO> list = productRepository.getAllProductDetails();
		Assertions.assertEquals(true, list.size()>0);
	}

}

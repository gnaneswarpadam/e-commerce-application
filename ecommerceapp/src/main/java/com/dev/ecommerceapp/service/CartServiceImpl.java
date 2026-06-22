package com.dev.ecommerceapp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.ecommerceapp.exception.AppException;
import com.dev.ecommerceapp.model.Cart;
import com.dev.ecommerceapp.model.CartDTO;
import com.dev.ecommerceapp.model.Product;
import com.dev.ecommerceapp.repository.CartRepository;
import com.dev.ecommerceapp.repository.ProductRepository;

@Service
public class CartServiceImpl {
	
	private final CartRepository cartRepository;
	
	private final ProductRepository productRepository;
	
	/**
	 * @param cartRepository
	 * @param productRepository
	 */
	public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository) {
		super();
		this.cartRepository = cartRepository;
		this.productRepository = productRepository;
	}

	
	public String addItem(CartDTO cartDTO) {
		Optional<Product> optionalProduct = productRepository.findById(cartDTO.getProductId());
		Product product = optionalProduct.orElseThrow(() -> new AppException("Product Not Found"));
		if(cartDTO.getQuantity()>product.getCount()) {
			throw new AppException("Stock Present : "+product.getCount());
		}
		Cart cart = new Cart(cartDTO.getUsername(), cartDTO.getProductId(), cartDTO.getQuantity(), product.getPrice());
		cartRepository.save(cart);
		return "Updated Cart Successfully";
	}
}

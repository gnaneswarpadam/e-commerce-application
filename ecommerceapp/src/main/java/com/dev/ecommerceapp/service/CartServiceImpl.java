package com.dev.ecommerceapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.ecommerceapp.exception.AppException;
import com.dev.ecommerceapp.model.Cart;
import com.dev.ecommerceapp.model.Product;
import com.dev.ecommerceapp.model.ProductDetailsDTO;
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

	
	public String addItem(String username, int productId, int quantity) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		Product product = optionalProduct.orElseThrow(() -> new AppException("Product Not Found"));
		if(quantity>product.getCount()) {
			throw new AppException("Stock Present : "+product.getCount());
		}
		Cart cart = new Cart(username, productId, quantity, product.getPrice());
		cartRepository.save(cart);
		return "Updated Cart Successfully";
	}
	
	public List<ProductDetailsDTO> getProducts(String username) {
		List<Cart> cartDetails = cartRepository.findByUsername(username);
		List<Integer> productIds = cartDetails.stream().map((cart) -> cart.getId().getProductId()).toList();
		List<ProductDetailsDTO> productDetailsList =  productRepository.getSpecificProductDetails(productIds);
		
		Map<Integer, Integer> productIdQuantityReqMap = new HashMap<Integer, Integer>();
		for(Cart cart : cartDetails) {
			productIdQuantityReqMap.put(cart.getId().getProductId(), cart.getQuantity());
		}
		for(ProductDetailsDTO productDetail : productDetailsList) {
			productDetail.setCount(productIdQuantityReqMap.get(productDetail.getId()));
		}
		return productDetailsList;
	}
}

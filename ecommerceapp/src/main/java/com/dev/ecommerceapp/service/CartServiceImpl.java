package com.dev.ecommerceapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.ecommerceapp.exception.AppException;
import com.dev.ecommerceapp.model.CartItem;
import com.dev.ecommerceapp.model.Product;
import com.dev.ecommerceapp.model.ProductDetailsDTO;
import com.dev.ecommerceapp.model.User;
import com.dev.ecommerceapp.repository.CartRepository;
import com.dev.ecommerceapp.repository.ProductRepository;
import com.dev.ecommerceapp.repository.UserRepository;

@Service
public class CartServiceImpl {
	
	private final CartRepository cartRepository;
	
	private final ProductRepository productRepository;
	
	private final UserRepository userRepository;
	
	/**
	 * @param cartRepository
	 * @param productRepository
	 */
	public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
		super();
		this.cartRepository = cartRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}

	
	public String addItem(String username, int productId, int quantity) {
		Optional<User> userOpt = userRepository.findById(username);
		User user = userOpt.orElseThrow(() -> new AppException("User Not Found"));
		Optional<Product> optionalProduct = productRepository.findById(productId);
		Product product = optionalProduct.orElseThrow(() -> new AppException("Product Not Found"));
		if(quantity>product.getCount()) {
			throw new AppException("Stock Present : "+product.getCount());
		}
		CartItem cart = new CartItem(username, productId, quantity, product.getPrice());
		cart.setProduct(product);
		cart.setUser(user);
		cartRepository.save(cart);
		return "Updated Cart Successfully";
	}
	
	public List<ProductDetailsDTO> getProducts(String username) {
		List<CartItem> cartDetails = cartRepository.findByUsername(username);
		List<Integer> productIds = cartDetails.stream().map((cart) -> cart.getId().getProductId()).toList();
		List<ProductDetailsDTO> productDetailsList =  productRepository.getSpecificProductDetails(productIds);
		
		Map<Integer, Integer> productIdQuantityReqMap = new HashMap<Integer, Integer>();
		for(CartItem cart : cartDetails) {
			productIdQuantityReqMap.put(cart.getId().getProductId(), cart.getQuantity());
		}
		for(ProductDetailsDTO productDetail : productDetailsList) {
			productDetail.setCount(productIdQuantityReqMap.get(productDetail.getId()));
		}
		return productDetailsList;
	}
}

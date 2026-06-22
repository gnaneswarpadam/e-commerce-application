package com.dev.ecommerceapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.ecommerceapp.model.Cart;
import com.dev.ecommerceapp.model.Order;
import com.dev.ecommerceapp.model.OrderItem;
import com.dev.ecommerceapp.model.OrderItemId;
import com.dev.ecommerceapp.repository.CartRepository;
import com.dev.ecommerceapp.repository.OrderItemRepository;
import com.dev.ecommerceapp.repository.OrderRepository;

@Service
public class OrderServiceImpl {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private CartRepository cartRepository;

	@Transactional
	public String createOrder(String username) {
		List<Cart> cartList = cartRepository.findByUsername(username);
		double amount = 0;
		for(Cart cart : cartList) {
			amount += (cart.getQuantity() * cart.getPrice());
		}
		Order order = new Order(0, username, LocalDateTime.now(), "CREATED", amount);
		final Order orderCopy = orderRepository.save(order);
		List<OrderItem> orderItems = cartList.stream()
				.map((cart) -> new OrderItem( 
						new OrderItemId(orderCopy.getOrderId(), cart.getId().getProductId()), cart.getQuantity(), (int)cart.getPrice()))
				.toList();
		orderItemRepository.saveAll(orderItems);
		cartRepository.deleteAll(cartList);
		return "Order Placed Successfully";
	}
}

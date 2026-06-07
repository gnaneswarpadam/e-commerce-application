package com.dev.ecommerceapp.service;

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
	public String createOrder(Order order) {
		Order orderCopy = orderRepository.save(order);
		System.out.println("--------"+orderCopy.getOrderId());
		List<Cart> cartList = cartRepository.findByUsername(order.getUsername());
		List<OrderItem> orderItems = cartList.stream()
				.map((cart) -> new OrderItem( 
						new OrderItemId(orderCopy.getOrderId(), cart.getId().getProduct_id()), cart.getQuantity(), (int)cart.getPrice()))
				.toList();
		
		orderItemRepository.saveAll(orderItems);
		cartRepository.deleteAll(cartList);
		return "Order Placed Successfully";
	}
}

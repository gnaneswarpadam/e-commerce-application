package com.dev.ecommerceapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.ecommerceapp.exception.AppException;
import com.dev.ecommerceapp.model.CartItem;
import com.dev.ecommerceapp.model.Order;
import com.dev.ecommerceapp.model.OrderDTO;
import com.dev.ecommerceapp.model.OrderItem;
import com.dev.ecommerceapp.model.OrderItemDTO;
import com.dev.ecommerceapp.model.OrderItemId;
import com.dev.ecommerceapp.model.Product;
import com.dev.ecommerceapp.model.ProductDetailsDTO;
import com.dev.ecommerceapp.model.User;
import com.dev.ecommerceapp.repository.CartRepository;
import com.dev.ecommerceapp.repository.OrderRepository;
import com.dev.ecommerceapp.repository.UserRepository;

@Service
public class OrderServiceImpl {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public String createOrder(String username) {
		Optional<User> userOpt = userRepository.findById(username);
		User user = userOpt.orElseThrow(() -> new AppException("User Not Found"));
		List<CartItem> cartList = cartRepository.findByUsername(username);
		double amount = 0;
		for(CartItem cart : cartList) {
			amount += (cart.getQuantity() * cart.getPrice());
		}
		Order order = Order.builder().user(user).orderTs(CommonServiceImpl.getCurrentDateTime()).status("CREATED").amount(amount).build();
		List<OrderItem> orderItems = cartList.stream()
				.map((cart) -> OrderItem.builder().id(new OrderItemId()).order(order).product(cart.getProduct()).quantity(cart.getQuantity()).price((int)cart.getPrice()).build())
				.toList();
		
		order.setOrderItems(orderItems);
		orderRepository.save(order);
		cartRepository.deleteAll(cartList);
		return "Order Placed Successfully";
	}
	
	
	public List<OrderDTO> getAllOrders(String username){
		Optional<User> userOpt = userRepository.findById(username);
		User user = userOpt.orElseThrow(() -> new AppException("User Not Found"));
		List<Order> orders = user.getOrders();
		
		List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
		
		
		for(Order orderTemp : orders) {
			OrderDTO orderDTO = OrderDTO.builder().orderId(orderTemp.getOrderId()).status(orderTemp.getStatus()).amount(orderTemp.getAmount()).build();
			List<OrderItemDTO> orderItemDTOs = new ArrayList<OrderItemDTO>();
			for(OrderItem orderItem : orderTemp.getOrderItems()) {
				
				OrderItemDTO orderItemDTO = OrderItemDTO.builder().orderId(orderDTO.getOrderId())
				.quantity(orderItem.getQuantity()).price(orderItem.getPrice()).build();
				
				Product product = orderItem.getProduct();
				ProductDetailsDTO productDetailsDTO = ProductDetailsDTO.builder().id(product.getId()).name(product.getName()).price(product.getPrice()).description(product.getDescription()).count(product.getCount()).imageUrl(product.getProductImage().getImageUrl()).build();
				orderItemDTO.setProductDetailsDTO(productDetailsDTO);
				
				orderItemDTOs.add(orderItemDTO);	
			}
			orderDTO.setOrderItems(orderItemDTOs);
			orderDTOs.add(orderDTO);
		}
		
		return orderDTOs;
	}
}

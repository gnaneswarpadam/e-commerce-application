package com.dev.ecommerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ecommerceapp.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}

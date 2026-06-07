package com.dev.ecommerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ecommerceapp.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}

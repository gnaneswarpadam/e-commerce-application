package com.dev.ecommerceapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.ecommerceapp.model.Cart;
import com.dev.ecommerceapp.model.CartItemId;

public interface CartRepository extends JpaRepository<Cart, CartItemId>{
	
	@Query("SELECT c FROM Cart c WHERE c.id.username=:username")
	public List<Cart> findByUsername(@Param("username") String username);
}

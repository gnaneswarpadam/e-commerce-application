package com.dev.ecommerceapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.ecommerceapp.model.CartItem;
import com.dev.ecommerceapp.model.CartItemId;

public interface CartRepository extends JpaRepository<CartItem, CartItemId>{
	
	@Query("SELECT ci FROM CartItem ci WHERE ci.id.username=:username")
	public List<CartItem> findByUsername(@Param("username") String username);
}

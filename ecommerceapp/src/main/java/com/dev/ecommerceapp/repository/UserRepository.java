package com.dev.ecommerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ecommerceapp.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	
}

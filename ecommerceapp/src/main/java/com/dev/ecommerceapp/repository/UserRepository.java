package com.dev.ecommerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.ecommerceapp.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	@Query("SELECT u.role FROM User u WHERE u.username=:username")
	public String getRole(@Param("username") String username);
}

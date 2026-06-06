package com.dev.ecommerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ecommerceapp.model.UserSecret;

public interface UserSecretRepository extends JpaRepository<UserSecret, String> {

}

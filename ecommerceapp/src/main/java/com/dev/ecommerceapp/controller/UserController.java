package com.dev.ecommerceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ecommerceapp.model.UserDetailsDTO;
import com.dev.ecommerceapp.service.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping(value = "/endUser/signup")
	public String signup(@Valid @RequestBody UserDetailsDTO userDetails) {
		return userServiceImpl.signup(userDetails);
	}
	
	@PostMapping(value = "/login")
	public String login(@RequestBody UserDetailsDTO userCredentials) {
		return userServiceImpl.login(userCredentials);
	}
	
	@GetMapping(value = "/details")
	public UserDetailsDTO getUserDetails(Authentication authentication) {
		String username = authentication.getName();
		return userServiceImpl.getUserDetails(username);
	}
	
}

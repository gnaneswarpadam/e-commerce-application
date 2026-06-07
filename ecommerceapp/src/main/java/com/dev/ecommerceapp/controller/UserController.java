package com.dev.ecommerceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ecommerceapp.model.UserSignUpDTO;
import com.dev.ecommerceapp.service.UserServiceImpl;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping(value = "/signup")
	public String signup(@RequestBody UserSignUpDTO userDetails) {
		return userServiceImpl.signup(userDetails);
	}
	
	@PostMapping(value = "/login")
	public String login(@RequestBody UserSignUpDTO userCredentials) {
		return userServiceImpl.login(userCredentials);
	}
	
}

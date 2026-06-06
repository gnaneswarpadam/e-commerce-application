package com.dev.ecommerceapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.ecommerceapp.model.User;
import com.dev.ecommerceapp.model.UserSecret;
import com.dev.ecommerceapp.model.UserSignUpDTO;
import com.dev.ecommerceapp.repository.UserRepository;
import com.dev.ecommerceapp.repository.UserSecretRepository;
import com.dev.ecommerceapp.security.JwtService;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserSecretRepository userSecretRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	
	
	/**
	 * @param userRepository
	 * @param userSecretRepository
	 * @param passwordEncoder
	 * @param jwtService
	 */
	public UserServiceImpl(UserRepository userRepository, UserSecretRepository userSecretRepository,
			PasswordEncoder passwordEncoder, JwtService jwtService) {
		super();
		this.userRepository = userRepository;
		this.userSecretRepository = userSecretRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	@Transactional
	public String signup(UserSignUpDTO userDetails) {
		
		User user = new User(userDetails.getUsername(), userDetails.getFirstname(), userDetails.getLastname(), userDetails.getEmail(), 
					 userDetails.getPhoneNumber(), userDetails.getRole(), null, null);
		userRepository.save(user);
		
		UserSecret userSecret = new UserSecret(userDetails.getUsername(), passwordEncoder.encode(userDetails.getPassword()), null, null);
		userSecretRepository.save(userSecret);
		
		return "Signed Up Successfully";
	}
	
	public String login(UserSignUpDTO userCredentials) {
		Optional<UserSecret> userSecret = userSecretRepository.findById(userCredentials.getUsername());
		if(userSecret.isEmpty()) {
			return "Invalid Username";
		}
		if(!passwordEncoder.matches(userCredentials.getPassword(), userSecret.get().getUserPassword())) {
			return "Invalid Password";
		}
		return jwtService.generateToken(userSecret.get().getUsername());
	}
}

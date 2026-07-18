package com.dev.ecommerceapp.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.ecommerceapp.model.User;
import com.dev.ecommerceapp.model.UserSecret;
import com.dev.ecommerceapp.model.UserDetailsDTO;
import com.dev.ecommerceapp.repository.UserRepository;
import com.dev.ecommerceapp.repository.UserSecretRepository;
import com.dev.ecommerceapp.security.JwtService;
import com.dev.ecommerceapp.support.Constant;

@Service
public class UserServiceImpl {
	
	private final UserRepository userRepository;
	
	private final UserSecretRepository userSecretRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;
	

	public UserServiceImpl(UserRepository userRepository, UserSecretRepository userSecretRepository,
			PasswordEncoder passwordEncoder, JwtService jwtService) {
		super();
		this.userRepository = userRepository;
		this.userSecretRepository = userSecretRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	@Transactional
	public String signup(UserDetailsDTO userDetails) {

		UserSecret userSecret = UserSecret.builder()
				.userPassword(passwordEncoder.encode(userDetails.getPassword()))
				.createdTs(CommonServiceImpl.getCurrentDateTime()).build();

		User user = User.builder().username(userDetails.getUsername()).firstname(userDetails.getFirstname())
				.lastname(userDetails.getLastname()).email(userDetails.getEmail())
				.phoneNumber(userDetails.getPhoneNumber()).role(Constant.USER_ROLE_END_USER)
				.createdTS(CommonServiceImpl.getCurrentDateTime()).build();

		user.setUserSecret(userSecret);
		userSecret.setUser(user);
		userRepository.save(user);

		return "Signed Up Successfully";
	}
	
	public String login(UserDetailsDTO userCredentials) {
		Optional<UserSecret> userSecret = userSecretRepository.findById(userCredentials.getUsername());
		if(userSecret.isEmpty()) {
			return "Invalid Username";
		}
		if(!passwordEncoder.matches(userCredentials.getPassword(), userSecret.get().getUserPassword())) {
			return "Invalid Password";
		}
		return jwtService.generateToken(userSecret.get().getUsername());
	}
	
	public UserDetailsDTO getUserDetails(String username) {
		Optional<User> userOptional = userRepository.findById(username);
		User user = userOptional.get();
		UserDetailsDTO userDetails = UserDetailsDTO.builder().username(username).firstname(user.getFirstname())
				.lastname(user.getLastname()).email(user.getEmail()).phoneNumber(user.getPhoneNumber()).build();
		return userDetails;
	}
}

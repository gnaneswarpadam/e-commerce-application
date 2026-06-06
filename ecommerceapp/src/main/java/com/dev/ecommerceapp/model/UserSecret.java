package com.dev.ecommerceapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_secret")
public class UserSecret {
	
	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "user_password")
	private String userPassword;
	
	@Column(name = "created_ts")
	private LocalDateTime createdTs;
	
	@Column(name = "updated_ts")
	private LocalDateTime updatedTs;

	/**
	 * 
	 */
	public UserSecret() {
		super();
	}

	/**
	 * @param username
	 * @param userPassword
	 * @param createdTs
	 * @param updatedTs
	 */
	public UserSecret(String username, String userPassword, LocalDateTime createdTs, LocalDateTime updatedTs) {
		super();
		this.username = username;
		this.userPassword = userPassword;
		this.createdTs = createdTs;
		this.updatedTs = updatedTs;
	}

	public String getUsername() {
		return username;
	}

	public String getUserPassword() {
		return userPassword;
	}

	
}

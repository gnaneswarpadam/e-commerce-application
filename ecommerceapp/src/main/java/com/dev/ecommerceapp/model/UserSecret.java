package com.dev.ecommerceapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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

}

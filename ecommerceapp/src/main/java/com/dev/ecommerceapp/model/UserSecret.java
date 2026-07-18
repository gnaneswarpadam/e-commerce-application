package com.dev.ecommerceapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "username")
	private User user;


}

package com.dev.ecommerceapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "created_ts")
	private String createdTS;
	
	@Column(name = "updated_ts")
	private String updatedTs;

	
	/**
	 * 
	 */
	public User() {
		super();
	}


	/**
	 * @param username
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param phoneNumber
	 * @param role
	 * @param createdTS
	 * @param updatedTs
	 */
	public User(String username, String firstname, String lastname, String email, String phoneNumber, String role,
			String createdTS, String updatedTs) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.createdTS = createdTS;
		this.updatedTs = updatedTs;
	}
	
	
	
	
}

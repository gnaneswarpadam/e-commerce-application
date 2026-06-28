package com.dev.ecommerceapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsDTO {
	
	@NotBlank(message = "Username is a Requried Field")
	private String username;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank(message = "Password is a Requried Field")
	private String password;
	
	@NotBlank(message = "FirstName is a Requried Field")
	private String firstname;
	
	private String lastname;
	
	@NotBlank(message = "Email is a required Field")
	private String email;
	
	private String phoneNumber;
	
	private String role;

}

package com.dev.ecommerceapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDTO {

	private int id;
	private String name;
	private double price;
	private String description;
	private int count;
	private String imageUrl;	
	
}

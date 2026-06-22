package com.dev.ecommerceapp.model;

import com.dev.ecommerceapp.marker.UpdateProductMarker;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDTO {
	
	@Positive(groups = UpdateProductMarker.class, message = "Product Id is Required and cannot be Negative")
	private int id;
	
	@NotEmpty(message = "Product Name is Requried")
	private String name;
	
	@Positive(message = "Price is required and cannot be Negative")
	private float price;
	
	private String description;
	
	private int count;
	
	private String imageUrl;
	
	@Override
	public String toString() {
		String s = String.format("id:%d name:%s price:%.2f description:%s count:%d imageUrl:%s", 
				id, name, price, description, count, imageUrl);
		return s;
	}
	
}

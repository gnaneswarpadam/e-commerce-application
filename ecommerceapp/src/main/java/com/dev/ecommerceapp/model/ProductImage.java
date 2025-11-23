package com.dev.ecommerceapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_images")
public class ProductImage {
	
	@Id
	@Column(name = "product_id")
	private int id;
	
	@Column(name = "product_image_url")
	private String imageUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public ProductImage() {
		super();
	}

	public ProductImage(int id, String imageUrl) {
		super();
		this.id = id;
		this.imageUrl = imageUrl;
	}
	
}

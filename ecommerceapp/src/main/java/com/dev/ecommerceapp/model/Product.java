package com.dev.ecommerceapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "product_name")
	private String name;
	
	@Column(name = "product_price")
	private double price;
	
	@Column(name = "product_description")
	private String description;
	
	@Column(name = "product_count")
	private int count;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	

	public Product() {
		super();
	}

	public Product(String name, double price, String description, int count) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.count = count;
	}

	public Product(int id, String name, double price, String description, int count) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.count = count;
	}
	
}

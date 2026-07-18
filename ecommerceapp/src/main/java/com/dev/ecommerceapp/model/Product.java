package com.dev.ecommerceapp.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "products")
public class Product {
	
	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "product_name")
	private String name;
	
	@Column(name = "product_price")
	private float price;
	
	@Column(name = "product_description")
	private String description;
	
	@Column(name = "product_count")
	private int count;
	
	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private ProductImage productImage;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<CartItem> cartItems;
	
	@OneToMany(mappedBy = "product")
	private List<OrderItem> orderItems;
	

	public Product(String name, float price, String description, int count) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.count = count;
	}

}

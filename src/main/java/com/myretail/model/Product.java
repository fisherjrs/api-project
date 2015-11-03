package com.myretail.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonView;

public class Product implements Serializable {

	private static final long serialVersionUID = -7298686884054779108L;

	@JsonView(View.Product.class)
	private Integer id;
	@JsonView(View.Product.class)
	private String sku;
	@JsonView(View.Product.class)
	private String name;
	@JsonView(View.Product.class)
	private String category;
	@JsonView(View.Product.class)
	private LocalDateTime creationDate;
	@JsonView(View.Product.class)
	private LocalDateTime lastUpdated;
	@JsonView(View.Product.class)
	private Double price;
	
	public Product() {
	}
	
	public static Product createProduct(Integer id,
			String sku,
			String name,
			String category,
			Double price) {
		Product product = new Product();
		product.id = id;
		product.sku = sku;
		product.name = name;
		product.category = category;
		product.price = price;
		return product;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}

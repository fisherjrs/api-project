package com.myretail.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Category model object.
 * Represents category for a product.
 * 
 * @author fishej2
 *
 */
public class Category implements Serializable {
	
	private static final long serialVersionUID = 8410457377793741130L;
	
	@JsonView(View.Category.class)
	private String name;
	
	public Category() {
	}

}

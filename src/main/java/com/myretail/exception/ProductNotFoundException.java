package com.myretail.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5084295826649331222L;

	public ProductNotFoundException(String message) {
		super(message);
	}

}

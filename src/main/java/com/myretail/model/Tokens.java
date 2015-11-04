package com.myretail.model;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Represents the token used for a modicum of security.
 * @author fishej2
 *
 */

public class Tokens {

	
	public static final String ACCESS_TOKEN = "winter";	
	public static final String ACCESS_REQUEST_TOKEN = "summer";
	
	@JsonView(View.Tokens.class)
	private String token;
	
	@JsonView(View.Tokens.class)
	private String response;
	
	public Tokens() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}

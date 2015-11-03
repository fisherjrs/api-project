package com.myretail.service;

import org.springframework.stereotype.Service;

import com.myretail.model.Tokens;

/**
 * Tiny little service that does a string compare to validate a token.
 * 
 * @author fishej2
 *
 */
@Service
public class ValidationService {

	public ValidationService() {
	}
	
	public Boolean validateTokens(String token) {
		Boolean validToken = Boolean.FALSE;
		if(Tokens.ACCESS_TOKEN.equals(token)) {
			validToken = Boolean.TRUE;
		}
		return validToken;
	}

}

package com.myretail.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Authentication issue.")
public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 286474868327652609L;

	public AuthenticationException(String message) {
		super(message);
	}

}

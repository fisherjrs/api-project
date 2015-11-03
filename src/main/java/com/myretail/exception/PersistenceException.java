package com.myretail.exception;

public class PersistenceException extends RuntimeException {

	private static final long serialVersionUID = 286474868327652609L;

	public PersistenceException(String message) {
		super(message);
	}

}

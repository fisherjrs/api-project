package com.myretail.model.error;

/**
 * Interface for the error message that's serialized back
 * to the client.
 * 
 * @author fishej2
 *
 */
public interface MyRetailError {
	void setMessage(String message);
	void setDeveloperMessage(String message);
	void setHttpStatusCode(Integer statusCode);
	void setErrorCode(String errorCode);
	String getMessage();
	String getDeveloperMessage();
	Integer getHttpStatusCode();
	String getErrorCode();
}
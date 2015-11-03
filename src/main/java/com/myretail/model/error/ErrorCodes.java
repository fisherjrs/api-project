package com.myretail.model.error;

import org.apache.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.myretail.model.View;

/**
 * Enum for declaring the error codes.
 * Jackson doesn't serialize the enum properties by default so the JsonFormat 
 * annotation is necessary to get the properties of the errors serialized. Otherwise,
 * the client wouldn't see the content of the error.
 * 
 * @author fishej2
 *
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCodes implements MyRetailError {
	E9000("We can't find the item requested in our database.", "", HttpStatus.SC_BAD_REQUEST, "E9000"),
	E9001("We cannot complete your request with the data provided.", "", HttpStatus.SC_BAD_REQUEST, "E9001"),
	E9002("We cannot complete your request.", "", HttpStatus.SC_BAD_REQUEST, "E9002"),
	E9003("There was a problem processing your request. We're looking into it.", "", HttpStatus.SC_SERVICE_UNAVAILABLE, "E9003"),
	E9004("You don't have access to the requested resources.", "", HttpStatus.SC_FORBIDDEN, "E9004"),
	E9005("There's no api at this location.", "", HttpStatus.SC_NOT_FOUND, "E9004");
	
	@JsonView(View.Error.class)
	private String message;
	@JsonView(View.Error.class)
	private String developerMessage;
	@JsonView(View.Error.class)
	private Integer httpStatusCode;
	@JsonView(View.Error.class)
	private String errorCode;
	
	private ErrorCodes(String message, String developerMessage, 
			Integer httpStatusCode, String errorCode) {
		this.message = message;
		this.developerMessage = developerMessage;
		this.httpStatusCode = httpStatusCode;
		this.errorCode = errorCode;
	}
	
	@Override
	public void setMessage(String message) {
		this.message = message;		
	}

	@Override
	public void setDeveloperMessage(String message) {
		this.developerMessage = message;		
	}

	@Override
	public void setHttpStatusCode(Integer httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	@Override
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getDeveloperMessage() {
		return developerMessage;
	}

	@Override
	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}

	@Override
	public String getErrorCode() {		
		return errorCode;
	}
}

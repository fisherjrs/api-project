package com.myretail.model.error;

import org.apache.ibatis.exceptions.PersistenceException;
import org.neo4j.cypher.InvalidArgumentException;

import com.myretail.exception.ResourceNotFoundException;
import com.myretail.exception.TokenException;
import com.myretail.exception.ProductNotFoundException;

/**
 * Factory for mapping errors to Exceptions (maybe this should be a map on the
 * error code enum?).
 * 
 * @author fishej2
 *
 */
public class ErrorFactory {
	
	private ErrorFactory() {
	}
	
	public static MyRetailError getError(Exception exception) {
		MyRetailError mpe;
		if(exception instanceof ProductNotFoundException){
			mpe = ErrorCodes.E9000;
			mpe.setDeveloperMessage(exception.getMessage());
		} else if (exception instanceof InvalidArgumentException){
			mpe = ErrorCodes.E9001;
			mpe.setDeveloperMessage(exception.getMessage());	
		} else if (exception instanceof PersistenceException){
			mpe = ErrorCodes.E9003;
			mpe.setDeveloperMessage(exception.getMessage());
		} else if (exception instanceof TokenException){
			mpe = ErrorCodes.E9004;
			mpe.setDeveloperMessage(exception.getMessage());
		} else if (exception instanceof ResourceNotFoundException){
			mpe = ErrorCodes.E9005;
			mpe.setDeveloperMessage(exception.getMessage());
		} else {
			mpe = ErrorCodes.E9002;
			mpe.setDeveloperMessage(exception.getMessage());			
		}				
		return mpe;
	}

}


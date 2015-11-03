package com.myretail.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.annotation.JsonView;
import com.myretail.exception.ProductNotFoundException;
import com.myretail.exception.TokenException;
import com.myretail.model.View;
import com.myretail.model.error.ErrorFactory;
import com.myretail.model.error.MyRetailError;
import com.myretail.service.ValidationService;

/**
 * Global exception handling controller.
 * Handles all errors, generates an error object and resturns
 * the error data to the client.
 * 
 * Contains a /error mapping for 404 errors. 404s can happen outside the normal
 * dispatcher so the controller advice doesn't necessarilly handle them.
 * Explicitly creating a /error mapping overrides the SpringBoot error handler.
 * 
 * The error message is serialized.
 * 
 * Also provides apis for testing error messages. Tests require
 * a valid token.
 * 
 * @author fishej2
 *
 */
@ControllerAdvice
@RestController
public class ExceptionHandlingController implements ErrorController {

	private static Logger LOG = LoggerFactory.getLogger(ExceptionHandlingController.class);
	
	@Autowired
	ValidationService validationService;
	
	public ExceptionHandlingController() {
	}
	
	@JsonView(View.Error.class)
	@ResponseBody
	@RequestMapping(value = "/error")
    public MyRetailError error() {
		MyRetailError error = ErrorFactory.getError(
				new com.myretail.exception.ResourceNotFoundException("Please check the api endpoint you're trying to reach. Check your version."));	
        return error;
    }
	
	@JsonView(View.Error.class)
	@ExceptionHandler({Throwable.class, NoHandlerFoundException.class})
	@ResponseBody
	public MyRetailError handleError(HttpServletRequest request, 
			HttpServletResponse response, Exception exception) throws Exception {
		LOG.info(exception.getLocalizedMessage());
			
		MyRetailError error = ErrorFactory.getError(exception);	
		response.setStatus(error.getHttpStatusCode().intValue());
		
		return error;
	}
	
	
	/*********** Exception tests ********************/
	/**    Exception tests require a valid token   **/
	/************************************************/
	@ResponseBody
	@RequestMapping("/myretail/v1/test/exception/persistence")
	void testPersistenceException(@RequestParam(required=true) String token) {
		validateToken(token);
		throw new PersistenceException("Test successful. We threw a PersistenceException.");		
	}
	
	@ResponseBody
	@RequestMapping("/myretail/v1/test/exception/token")
	void testTokenException(@RequestParam(required=true) String token) {
		validateToken(token);
		throw new TokenException("Test successful. We threw a TokenException.");		
	}
	
	@ResponseBody
	@RequestMapping("/myretail/v1/test/exception/product")
	void testProductException(@RequestParam(required=true) String token) {
		validateToken(token);
		throw new ProductNotFoundException("Test successful. We threw a ProductNotFoundException. ");		
	}

	/**
	 * The test cases require a token so we have to hit the validation
	 * service to ensure the token is valid.
	 * @param token
	 */
	private void validateToken(String token) {		
		Boolean validToken = validationService.validateTokens(token);
		if(!validToken) {
			throw new com.myretail.exception.TokenException("The token you provided is wrong or you didn't provide one. It's over Johnny.");
		}		
	}

	@Override
	public String getErrorPath() {
		return null;
	}
}

package com.myretail.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.myretail.model.Tokens;
import com.myretail.model.View;

/**
 * API used to validate a user key and return a token.
 * The token is used by the client when making api requests.
 * If the client sends in a valid token the requests are processed.
 * 
 * The token check can be turned on/off in the application.yml properties.
 * @author fishej2
 *
 */
@RestController
@RequestMapping("/myretail/v1")
public class ValidationController {

	public ValidationController() {
	}
	
	@JsonView(View.Tokens.class)
	@ResponseBody
	@RequestMapping(value="/validate", method={RequestMethod.GET})
	public Tokens validate(@RequestParam(required=true) String requestKey) {
		Tokens tokens = new Tokens();
		if(requestKey.equals(Tokens.ACCESS_REQUEST_TOKEN)){
			tokens.setResponse("Tokens are valid");
			tokens.setToken(Tokens.ACCESS_TOKEN);
		} else {
			tokens.setResponse(String.format("Tokens are not valid: %4s", requestKey));
		}
		
		return tokens;
	}

}

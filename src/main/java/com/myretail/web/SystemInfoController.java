package com.myretail.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.myretail.model.APIS;
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
public class SystemInfoController {

	public SystemInfoController() {
	}
	
	@JsonView(View.API.class)
	@ResponseBody
	@RequestMapping(value="/apis", method={RequestMethod.GET})
	public List<APIS> validate() {
		List<APIS> apis = new ArrayList<APIS>();
		apis.add(APIS.getproduct0);
		apis.add(APIS.getproduct1);
		apis.add(APIS.getallproducts);
		apis.add(APIS.getproducts0);
		apis.add(APIS.getproducts1);
		apis.add(APIS.validate);
		apis.add(APIS.errorPersistence);
		apis.add(APIS.errorProduct);
		apis.add(APIS.errorToken);
		return apis;
	}

}

package com.myretail.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Enum for holding api info.
 * 
 * @author fishej2
 *
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum APIS  {
	getproduct0("getproduct", "myretail/v1/getproduct/{productId}", "none", "http://localhost:8080/myretail/v1/getproduct/5555"),
	getproduct1("getproduct", "myretail/v1/getproduct", "?productId=xx", "http://localhost:8080/myretail/v1/getproduct?productId=5555"),
	getallproducts("getallproducts", "myretail/v1/getallproducts", "none", "http://localhost:8080/myretail/v1/getallproducts"),
	getproducts0("getproducts", "myretail/v1/getproducts/{category}", "none", "http://localhost:8080/myretail/v1/getproducts/toys"),
	getproducts1("getproducts", "myretail/v1/getproducts", "?categories=xx,yy", "http://localhost:8080/myretail/v1/getproducts/?categories=toys"),
	getcategories("getcategories", "myretail/v1/getcategories", "none", "http://localhost:8080/myretail/v1/getcategories"),
	validate("validatetokens", "myretail/v1/validate", "?requestKey=xx", "http://localhost:8080/myretail/v1/validate?requestKey=summer"),
	errorPersistence("errorPersistence", "/myretail/v1/test/exception/persistence", "?token=winter", "http:/localhost:8080/myretail/v1/test/exception/persistence?token=winter"),
	errorToken("errorToken", "/myretail/v1/test/exception/token", "?token=winter", "http://localhost:8080/myretail/v1/test/exception/token?token=winter"),
	errorProduct("errorProduct", "/myretail/v1/test/exception/product", "?token=winter", "http://localhost:8080/myretail/v1/test/exception/product?token=winter");

	
	
	@JsonView(View.API.class)
	private String name;
	@JsonView(View.API.class)
	private String endpoint;
	@JsonView(View.API.class)
	private String params;
	@JsonView(View.API.class)
	private String sample;
	
	private APIS(String name, String endpoint, String params,
			String sample) {
		this.name = name;
		this.endpoint = endpoint;
		this.sample = sample;
		this.params = params;
	}
	
}

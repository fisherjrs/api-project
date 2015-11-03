package com.myretail.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.myretail.model.Category;
import com.myretail.model.Product;
import com.myretail.model.View;
import com.myretail.service.ProductService;
import com.myretail.service.ValidationService;

/**
 * Controller for getting products.
 * Offers two apis for getting a product: 
 * 		1. by path variable  
 * 		2. query string variable.
 * 
 * There's also an api for getting a list of products.
 * A user can send in a category to filter the product list.
 * If no category is defined, the full list of products is returned.
 * 
 * (Psuedo)Security:
 * There's a psuedo security feature that can be turned on/off in the application.yml
 * If turned on, the controllers check the value of a token before allowing access.
 * 
 * @author fishej2
 *
 */

@RestController
@RequestMapping("/myretail/v1")
public class ProductsController {

	private static Logger LOG = LoggerFactory
			.getLogger(ProductsController.class);

	@Autowired
	private Environment env;
	@Autowired
	ProductService productService;
	@Autowired
	ValidationService validationService;


	/**
	 * API for retrieving a product ... expects path variable.
	 * @param productId
	 * @param token
	 * @return
	 */
	@JsonView(View.Product.class)
	@ResponseBody
	@RequestMapping(value = "/getproduct/{productId}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public Product getProductByPathId(@PathVariable String productId, String token) {
		validateToken(token);
		return getProduct(Integer.parseInt(productId));
	}
	
	
	/**
	 * API for retrieving a product ... expects request param.
	 * @param productId
	 * @param token
	 * @return
	 */
	@JsonView(View.Product.class)
	@ResponseBody
	@RequestMapping(value = "/getproduct", method = {RequestMethod.GET})
	public Product getProductById(@RequestParam String productId, String token) {
		validateToken(token);
		return getProduct(Integer.parseInt(productId));
	}
	
	/**
	 * Gets product by id.
	 * @param productId
	 * @return
	 */
	private Product getProduct(Integer productId) {
		if (productId == null) {
			throw new IllegalArgumentException(
					"The productId was not passed in.");
		}		
		return productService.getProductById(productId);
	}

	/**
	 * Gets list of all products
	 * @param token
	 * @return
	 */
	@JsonView(View.Product.class)
	@ResponseBody
	@RequestMapping(value = "/getallproducts", method = { RequestMethod.GET})
	public List<Product> getAllProducts(String token, String sort) {
		validateToken(token);
		List<Product> products = productService.getAllProducts();
		return products;
	}
	
	/**
	 * Gets list of products filtered by category.
	 * @param category
	 * @param token
	 * @return
	 */
	@JsonView(View.Product.class)
	@ResponseBody
	@RequestMapping(value = "/getproducts/{category}", method = {RequestMethod.GET})
	public List<Product> getProductsByCategory(@PathVariable String category, String token) {
		validateToken(token);
		List<Product> products = productService.getProductsByCategory(category);
		return products;
	}
	
	/**
	 * Gets list of products filtered by category.
	 * @param category
	 * @param token
	 * @return
	 */
	@JsonView(View.Product.class)
	@ResponseBody
	@RequestMapping(value = "/getproducts", method = {RequestMethod.GET})
	public List<Product> getProductsByManyCategories(@RequestParam(required=true) String categories, String token) {
		validateToken(token);
		List<Product> products = productService.getProductsByCategory(categories);
		return products;
	}
	
	/**
	 * Gets list of product categories.
	 * @param token
	 * @return
	 */
	@JsonView(View.Category.class)
	@ResponseBody
	@RequestMapping(value = "/getcategories", method = { RequestMethod.GET })
	public List<Category> getCategories( String token) {
		validateToken(token);
		List<Category> categories = productService.getProductCategories();
		return categories;
	}

	/**
	 * Checks the validity of a token.
	 * Skips check if security is not enabled in application properties.
	 * @param token
	 */
	private void validateToken(String token) {
		if ("true".equals(env
				.getProperty("application-state.use-secure-tokens"))) {
			Boolean validToken = validationService.validateTokens(token);
			if (!validToken) {
				LOG.info("Invalid token");
				throw new com.myretail.exception.TokenException(
						"The token you provided is wrong or you didn't provide one. It's over Johnny.");
			}
		}
	}	
}

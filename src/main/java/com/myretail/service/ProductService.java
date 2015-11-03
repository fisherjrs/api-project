package com.myretail.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretail.exception.ProductNotFoundException;
import com.myretail.model.Product;
import com.myretail.model.Category;
import com.myretail.persistence.ProductMapper;

/**
 * Service for interacting with the orm layers to get product data.
 * 
 * @author fishej2
 *
 */

@Service
public class ProductService {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProductService.class);
	
	private static final String ASC = "asc";
	private static final String DESC = "desc";

	@Autowired
	private ProductMapper productMapper;

	public ProductService() {

	}

	/**
	 * Get list of all products. If empty, handle message to client.
	 * 
	 * @return
	 */
	public List<Product> getAllProducts() {
		List<Product> products;
		try {
			products = productMapper.getProducts();
		} catch (Exception exception) {
			LOG.error("Persistence exception :: ", exception);
			throw new PersistenceException(exception.getMessage());
		}

		if (products == null || products.isEmpty()) {
			throw new ProductNotFoundException(
					"No products were found. This is an abnormal result. Contact myRetail support.");
		}
		return products;
	}

	
	/**
	 * Get products by category.
	 * 
	 * @param category
	 * @return
	 */
	public List<Product> getProductsByCategory(String category) {
				
		List<Product> products;
		try{ 
			List<String> categoryList = Arrays.asList((StringUtils.split(category, ",")));
			products = productMapper.getProductsByCategory(categoryList);
		} catch(Exception exception) {
			LOG.error("Persistence exception :: ", exception);
			throw new PersistenceException(exception.getMessage());
		}
		
		if(products == null || products.isEmpty()) {
			throw new ProductNotFoundException(String.format("No product was found for category %s. Please run getCategories to see a list of valid categories.", category));
		}
		
		return products;
	}

	/**
	 * Get product by id. Can throw a persistence runtime exception and a
	 * ProductNotFound runtime exception.
	 * 
	 * @param productId
	 * @return
	 */
	public Product getProductById(Integer productId) {
		Product product;
		try {
			product = productMapper.getProduct(productId);
		} catch (Exception exception) {
			LOG.error("Persistenc exception :: ", exception);
			throw new PersistenceException(exception.getMessage());
		}

		if (product == null) {
			throw new ProductNotFoundException(
					String.format(
							"No product was found for productId %s. Please check your productId",
							productId));
		}
		return product;
	}

	/**
	 * Get list of all available categories.
	 * 
	 * @return
	 */
	public List<Category> getProductCategories() {

		List<Category> categories;
		try {
			categories = productMapper.getProductCategories();
		} catch (Exception exception) {
			LOG.error("Persistenc exception :: ", exception);
			throw new PersistenceException(exception.getMessage());
		}

		if (categories == null) {
			throw new ProductNotFoundException(
					"No categories were found. This is an abnormal condition. Please contact myRetail support.");
		}

		return categories;
	}

	public void insertProduct(Product product) {
		productMapper.insertProduct(product);
	}

}

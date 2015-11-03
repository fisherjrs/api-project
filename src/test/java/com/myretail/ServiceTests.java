package com.myretail;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myretail.exception.ProductNotFoundException;
import com.myretail.model.Category;
import com.myretail.model.Product;
import com.myretail.model.Tokens;
import com.myretail.persistence.ProductMapper;
import com.myretail.service.ProductService;
import com.myretail.service.ValidationService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=com.myretail.config.MyRetailConfig.class)
public class ServiceTests {

	private static Logger LOG = LoggerFactory.getLogger(ServiceTests.class);
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ValidationService validationService;
	@Autowired
	private ProductMapper productMapper;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}


	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	
	}

	
	@Before
	public void setUp() throws Exception {

	}

	
	@After
	public void tearDown() throws Exception {

	}
	
	private Product insertProduct() {
		Product product = Product.createProduct(411, "ZZZZZZ", "Cosmic DeRegulator", "Toys", Double.valueOf(99.99));
		try{			
			productMapper.insertProduct(product);
			productMapper.insertPrice(product);
		} catch(Exception exception) {
			LOG.info("The row may already exist.");
		}		
		return product;
	}
	
	private void deleteProduct() {
		Product product = Product.createProduct(411, "ZZZZZZ", "Cosmic DeRegulator", "Toys", Double.valueOf(99.99));
		try{			
			productMapper.deleteProduct(product);
			productMapper.deletePrice(product);
		} catch(Exception exception) {
			LOG.info("The row may not already exist.");
		}		
	}

	@Test
	public void getProductByIdTest() {
		LOG.info("Test: getProductsById ");
		
		Product testProduct = insertProduct();		
		Product product = productService.getProductById(testProduct.getId());
		
		assertNotNull("failure ::: product is null", product);
		assertTrue("failure ::: incorrect product name", product.getName().equals(testProduct.getName()));
		assertTrue("failure ::: incorrect sku name", product.getSku().equals(testProduct.getSku()));
		assertTrue("failure ::: incorrect category name", product.getCategory().equals(testProduct.getCategory()));
		assertNotNull("failure ::: incorrect lastUpdated", product.getLastUpdated());
		assertNotNull("failure ::: incorrect creationDate", product.getCreationDate());
		assertTrue("failure ::: incorrect Price name", product.getPrice().equals(testProduct.getPrice()));
		
		deleteProduct();
	}

	
	@Test(expected=ProductNotFoundException.class)
	public void getProductByBadIdTest() {
		LOG.info("Test: getProductsBadById ");
		productService.getProductById(465466546);
	}
	
	@Test
	public void getProductsTest() {
		LOG.info("Test: getProducts ");
		insertProduct();
		List<Product> products = productService.getAllProducts();
		assertNotNull("failure ::: product is null", products);
		assertTrue("", products.size() >= 1);
		
		List<Product> productsByCategory = productService.getProductsByCategory("toys");
		assertNotNull("failure ::: product is null", productsByCategory);
		assertTrue("", productsByCategory.size() >= 1);
		
		deleteProduct();
	}
	
	@Test
	public void getProductCategoriesTest() {
		LOG.info("Test: getProductCategories ");
		insertProduct();
		
		List<Category> categories = productService.getProductCategories();
		assertNotNull("failure ::: Category is null", categories);
		assertTrue("", categories.size() >= 1);
		
		deleteProduct();
	}
	
	@Test
	public void validationTest() {
		LOG.info("Test: validateTokens");
		assertFalse("failure ::: tokens should be invalid", validationService.validateTokens("NewZealand"));
		assertTrue("failure ::: tokens should be valid", validationService.validateTokens(Tokens.ACCESS_TOKEN));
	}
	
}


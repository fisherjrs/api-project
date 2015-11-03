/**
 * 
 */
package com.myretail;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.myretail.model.Product;
import com.myretail.model.Category;
import com.myretail.persistence.ProductMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=com.myretail.config.MyRetailConfig.class)
public class ORMTests {

	private static Logger LOG = LoggerFactory.getLogger(ORMTests.class);
	
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
	
	private void deleteProduct(Product product) {		
		try{			
			productMapper.deleteProduct(product);
			productMapper.deletePrice(product);
		} catch(Exception exception) {
			LOG.info("The rows may not already exist.");
		}		
	}
	
	@Test
	public void crudTest1() {
		
		//Test all crud actions in one function ... makes data cleanup easier.
		
		//Negative integer is used to mark this as test data.
		Product testProduct = Product.createProduct(-811, "ZZZZZZ", "Cosmic DeRegulator", "Toys", Double.valueOf(99.99));
		
		//Make sure we start with a clean slate.
		deleteProduct(testProduct);
		
		LOG.info("Test:: insert products,prices");
		
		productMapper.insertProduct(testProduct);
		Product product = productMapper.getProduct(testProduct.getId());
		assertNotNull("failure ::: product is null", product);
		assertTrue("failure ::: incorrect product name", product.getName().equals(testProduct.getName()));
		assertTrue("failure ::: incorrect sku name", product.getSku().equals(testProduct.getSku()));
		assertTrue("failure ::: incorrect category name", product.getCategory().equals(testProduct.getCategory()));
		assertNotNull("failure ::: incorrect lastUpdated", product.getLastUpdated());
		assertNotNull("failure ::: incorrect creationDate", product.getCreationDate());
		
		productMapper.insertPrice(testProduct);
		product = productMapper.getProduct(testProduct.getId());
		assertTrue("failure ::: incorrect Price", product.getPrice().equals(testProduct.getPrice()));		
		
		LOG.info("Test: getProducts ");
		List<Product> products = productMapper.getProducts();
		assertNotNull("failure ::: product is null", products);
		assertTrue("", products.size() >= 1);
		
		LOG.info("Test:: update products,prices");
		testProduct.setCategory("Mountains");
		testProduct.setName("Gargantor");
		testProduct.setSku("BarneySKU");
		testProduct.setPrice(Double.valueOf(121.23));
		
		productMapper.updateProduct(testProduct);
		product = productMapper.getProduct(testProduct.getId());
		assertNotNull("failure ::: product is null", product);
		assertTrue("failure ::: incorrect product name", product.getName().equals(testProduct.getName()));
		assertTrue("failure ::: incorrect sku name", product.getSku().equals(testProduct.getSku()));
		assertTrue("failure ::: incorrect category name", product.getCategory().equals(testProduct.getCategory()));
		
		productMapper.updatePrice(testProduct);
		product = productMapper.getProduct(testProduct.getId());
		assertNotNull("failure ::: product is null", product);
		assertTrue("failure ::: incorrect Price name", product.getPrice().equals(testProduct.getPrice()));
		
		LOG.info("Test: getProductsByCategory");
		List<String> categories = new ArrayList<String>();
		categories.add("toys");
				
		List<Product> productsByCat = productMapper.getProductsByCategory(categories);
		assertNotNull("failure ::: product is null", productsByCat);
		
		LOG.info("Test: getCategories");
		List<Category> categoriesR = productMapper.getProductCategories();
		assertNotNull("failure ::: product is null", categoriesR);
		
		
		LOG.info("Test:: delete products,prices");
		productMapper.deletePrice(testProduct);
		product = productMapper.getProduct(testProduct.getId());
		assertNull("failure ::: price is not null", product.getPrice());
		
		productMapper.deleteProduct(testProduct);
		product = productMapper.getProduct(testProduct.getId());
		assertNull("failure ::: product is not null", product);
	}

}

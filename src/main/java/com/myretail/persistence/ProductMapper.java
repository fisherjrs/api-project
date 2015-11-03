package com.myretail.persistence;

import java.util.List;

import com.myretail.model.Product;
import com.myretail.model.Category;

/**
 * Interface for the Ibatis mappings.
 * @author fishej2
 *
 */
public interface ProductMapper {
	List<Product> getProducts();
	List<Product> getProductsByCategory(List<String> categories);
	Product getProduct(Integer productId);
	void insertProduct(Product product);
	void insertPrice(Product product);
	void updateProduct(Product product);
	void updatePrice(Product product);
	void deleteProduct(Product product);
	void deletePrice(Product product);
	List<Category> getProductCategories();
}

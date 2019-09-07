package com.inmobi.casestudy.githit.datastore;

import java.util.List;

import com.inmobi.casestudy.githit.domain.Product;

public interface ProductStore {
	List<Product> getAllProducts();
	List<Product> getProducts(int startIndex, int numberOfProductsToRetrieve);
	Product getProduct(int id);
	boolean updateProduct(Product product);
	boolean deleteProduct(int id);
	List<Product> queryForProducts(String queryString);
	
}

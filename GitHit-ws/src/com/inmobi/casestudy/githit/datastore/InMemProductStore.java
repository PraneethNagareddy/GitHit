package com.inmobi.casestudy.githit.datastore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inmobi.casestudy.githit.domain.Product;
import com.inmobi.casestudy.githit.services.AuthenticationService;

public class InMemProductStore implements ProductStore{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);
	Map<Integer, Product> productCache = new HashMap<Integer, Product>();
	public InMemProductStore(){
		productCache.put(1, new Product(1, "Product -1", "My Awesome Product 1", 10));
		productCache.put(2, new Product(2, "Product -2", "My Awesome Product 2", 10));
		productCache.put(3, new Product(3, "Product -3", "My Awesome Product 3", 10));
		productCache.put(4, new Product(4, "Product -4", "My Awesome Product 4", 10));
		productCache.put(5, new Product(5, "Product -5", "My Awesome Product 5", 10));
		productCache.put(6, new Product(6, "Product -6", "My Awesome Product 6", 10));
		productCache.put(7, new Product(7, "Product -7", "My Awesome Product 7", 10));
		productCache.put(8, new Product(8, "Product -8", "My Awesome Product 8", 10));
		productCache.put(9, new Product(9, "Product -9", "My Awesome Product 9", 10));
		productCache.put(10, new Product(10, "Product -10", "My Awesome Product 10", 10));
		productCache.put(11, new Product(11, "Product -11", "My Awesome Product 11", 10));
		productCache.put(12, new Product(12, "Product -12", "My Awesome Product 12", 10));
		productCache.put(13, new Product(13, "Product -13", "My Awesome Product 13", 10));
		productCache.put(14, new Product(14, "Product -14", "My Awesome Product 14", 10));
		productCache.put(15, new Product(15, "Product -15", "My Awesome Product 15", 10));
	}

	@Override
	public List<Product> getAllProducts() {
		if(null == productCache) {
			LOGGER.error("Unable to retrieve products from the cache");
			return new ArrayList<Product>();
		}else {
			return productCache.values().stream()
					.collect(Collectors.toList());
		}
	}

	@Override
	public List<Product> getProducts(int startIndex, int numberOfProductsToRetrieve) {
		List<Product> listOfProducts = productCache.values().stream()
					.collect(Collectors.toList());
		List<Product> subsetOfProducts = new ArrayList<Product>();
		if( listOfProducts.size() >= startIndex &&
				null != productCache &&
				!productCache.isEmpty() ) {
			
			for(int i = startIndex; i< startIndex+numberOfProductsToRetrieve; i++) {
				subsetOfProducts.add(listOfProducts.get(i));
			}
		}else {
			LOGGER.error("Unable to retrieve products in the given range:startIndex["+startIndex
					+"] , numberOfProductsToRetrieve["+numberOfProductsToRetrieve+"] , size of Cache:"+ listOfProducts.size());
		}
		return subsetOfProducts;
	}

	@Override
	public Product getProduct(int id) {
		if(productCache.containsKey(id))
			return productCache.get(id);

		LOGGER.error("Unable to retrieve product["+id+"]");
		return null;
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean hasOperationCompleted = false;
		if(productCache.containsKey(product.getId())) {
			productCache.put(product.getId(), product);
			hasOperationCompleted = true;
		}
		return hasOperationCompleted;
	}

	@Override
	public boolean deleteProduct(int id) {
		boolean hasOperationCompleted = false;
		if(productCache.containsKey(id)) {
			productCache.remove(id);
			hasOperationCompleted = true;
		}
		return hasOperationCompleted;
	}

	@Override
	public List<Product> queryForProducts(String queryString) {
		return new ArrayList<Product>();
	}

}

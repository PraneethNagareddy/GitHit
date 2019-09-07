package com.inmobi.casestudy.githit.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inmobi.casestudy.githit.datastore.ProductStore;
import com.inmobi.casestudy.githit.datastore.StoreFactory;
import com.inmobi.casestudy.githit.domain.Product;

public class ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	ProductStore productStore;
	
	public ProductService(StoreFactory storeFactory) {
		this.productStore = storeFactory.getProductStore();
	}
	
	public Product updateProduct(Product product) {
		if(null == product ||
				null == productStore) {

			LOGGER.error("Null productstore or product to be updated");
			return new Product();
		}
		productStore.updateProduct(product);
		return productStore.getProduct(product.getId());
	} 
	
	public Product getProduct(int id) {
		if(!isProductIdValid(id) ||
				null == productStore) {

			LOGGER.error("Null productstore or invalid id["+ id +"] to be retrieved");
			return new Product();
		}
		return productStore.getProduct(id);
	}
	
	public List<Product> getAllProducts(int startIndex, int numberOfProductsToRetrieve) {
		if(numberOfProductsToRetrieve == -1)
			return productStore.getAllProducts();
		return productStore.getProducts(startIndex, numberOfProductsToRetrieve);
	}
	
	private boolean isProductIdValid(int id) {
		if(id<0) return false;
		return true;
	}
}

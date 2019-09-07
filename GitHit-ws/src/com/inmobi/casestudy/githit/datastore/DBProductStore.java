package com.inmobi.casestudy.githit.datastore;

import java.util.ArrayList;
import java.util.List;

import com.inmobi.casestudy.githit.domain.Product;

public class DBProductStore implements ProductStore{

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return new ArrayList<Product>();
	}

	@Override
	public Product getProduct(int id) {
		// TODO Auto-generated method stub
		return new Product();
	}

	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProduct(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Product> queryForProducts(String queryString) {
		// TODO Auto-generated method stub
		return new ArrayList<Product>();
	}

	@Override
	public List<Product> getProducts(int startIndex, int numberOfProductsToRetrieve) {
		// TODO Auto-generated method stub
		return new ArrayList<Product>();
	}

}

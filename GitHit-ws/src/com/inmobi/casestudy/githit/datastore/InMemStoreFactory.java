package com.inmobi.casestudy.githit.datastore;

public class InMemStoreFactory implements StoreFactory{
	
	private static ProductStore productStore = new InMemProductStore();
	private static UserStore userStore = new InMemUserStore();
	
	@Override
	public ProductStore getProductStore() {
		return productStore;
	}

	@Override
	public UserStore getUserStore() {
		return userStore;
	}

}

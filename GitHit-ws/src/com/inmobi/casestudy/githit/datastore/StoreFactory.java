package com.inmobi.casestudy.githit.datastore;

public interface StoreFactory {
	ProductStore getProductStore();
	UserStore getUserStore();
}

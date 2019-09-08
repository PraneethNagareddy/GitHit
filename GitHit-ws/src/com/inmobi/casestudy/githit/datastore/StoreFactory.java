package com.inmobi.casestudy.githit.datastore;

public interface StoreFactory {
	HistoryStore getHistoryStore();
	UserStore getUserStore();
}

package com.inmobi.casestudy.githit.datastore;

public interface DataStoreFactory {
	HistoryStore getHistoryStore();
	UserStore getUserStore();
}

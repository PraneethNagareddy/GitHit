package com.inmobi.casestudy.githit.datastore;

public class DBStoreFactory implements DataStoreFactory{
	
	private static HistoryStore historyStore = new DBHistoryStore();
	private static UserStore userStore = new DBUserStore();
	
	@Override
	public UserStore getUserStore() {
		return userStore;
	}

	@Override
	public HistoryStore getHistoryStore() {
		return historyStore;
	}
}

package com.inmobi.casestudy.githit.datastore;

public class InMemStoreFactory implements StoreFactory{
	
	private static HistoryStore historyStore = new InMemHistoryStore();
	private static UserStore userStore = new InMemUserStore();

	@Override
	public UserStore getUserStore() {
		return userStore;
	}

	@Override
	public HistoryStore getHistoryStore() {
		return historyStore;
	}

}

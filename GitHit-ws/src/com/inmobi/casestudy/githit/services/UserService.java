package com.inmobi.casestudy.githit.services;

import com.inmobi.casestudy.githit.datastore.UserStore;
import com.inmobi.casestudy.githit.domain.User;

public class UserService {
	
	private UserStore userStore;
	
	public UserService( UserStore userStore) {
		this.userStore = userStore;
	}
	
	public User getUser(String emailID) {
		return userStore.getUser(emailID);
	}
}

package com.inmobi.casestudy.githit.services;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inmobi.casestudy.githit.caching.CacheManager;
import com.inmobi.casestudy.githit.datastore.DataStoreFactory;
import com.inmobi.casestudy.githit.datastore.UserStore;
import com.inmobi.casestudy.githit.domain.User;

public class AuthenticationService {
	
	private static String userCahceName = "LOGGED_IN_USERS";
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);
	private UserStore userStore;
	
	public AuthenticationService( DataStoreFactory storeFactory) {
		this.userStore = storeFactory.getUserStore();
	}
	
	public String logUserIn(String emailID, String password) {
		String userSession = "";

		User user = userStore.getUser(emailID);
		if(user.getEncryptedPassword().equals(password)) {
			
			LOGGER.debug("user["+emailID+"] successfully logged in");
			userSession = addUserToLoggedInList(user);
		}else {
			LOGGER.debug("Unable to log user["+emailID+"] in");
		}
		return userSession;
	}

	public boolean authenticateSession(String sessionId) {
		boolean userAuthenticated = false;
		if(null == sessionId) {
			LOGGER.info("Empty sessionId. Unable to authenticate");
			userAuthenticated = false;
		}else if(CacheManager.getCache(userCahceName).getEntry(sessionId) != null) {
			LOGGER.debug("Session is active");
			userAuthenticated = true;
		}
		return userAuthenticated;
	}
	
	private String addUserToLoggedInList(User user) {
		String sessionID = generateHashForUser(user);
		CacheManager.getCache(userCahceName).putEntry(sessionID, user);
		return sessionID;
	}
	
	private  synchronized String generateHashForUser(User user) {
		long timestamp = System.currentTimeMillis();
		String hash = timestamp+"";
		LOGGER.debug("Session id generated:'"+hash+"' for user["+user.getEmailId()+"]");
		return hash;
	}
	
	public User getUserFromSession(String sessionID) {
		return (User) CacheManager.getCache(userCahceName).getEntry(sessionID);
	}
}

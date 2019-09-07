package com.inmobi.casestudy.githit.services;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inmobi.casestudy.githit.datastore.StoreFactory;
import com.inmobi.casestudy.githit.datastore.UserStore;
import com.inmobi.casestudy.githit.domain.User;

public class AuthenticationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);
	private UserStore userStore;
	
	public AuthenticationService( StoreFactory storeFactory) {
		this.userStore = storeFactory.getUserStore();
	}
	private static HashMap<String, User> loggedInUsers = new HashMap<String, User>();
	
	public String logUser(String emailID, String password) {
		String userSession = "";
		for(String session : loggedInUsers.keySet()) {
			if(null != loggedInUsers.get(session) &&
					null != loggedInUsers.get(session).getEmailId() &&
					loggedInUsers.get(session).getEmailId().equals(emailID)) {
				
				LOGGER.debug("user["+emailID+"] already logged in");
				userSession = session;
			}
		}
		User user = userStore.getUser(emailID);
		if(user.getEncryptedPassword().equals(password)) {
			
			LOGGER.debug("user["+emailID+"] successfully logged in");
			userSession = addUserToLoggedInList(emailID);
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
		}else if(loggedInUsers.containsKey(sessionId)) {
			
			LOGGER.debug("Session is active");
			userAuthenticated = true;
		}
		return userAuthenticated;
	}
	
	public boolean isUserAdmin(String sessionId) {
		boolean isUserAdmin = false;
		User user = getUserFromSession(sessionId);
		if(null == user) {
			isUserAdmin = false;
		}
		else isUserAdmin = user.isAdmin() ? true : false;
		return isUserAdmin;
	}
	
	private String addUserToLoggedInList(String emailID) {
		String sessionID = generateHashForUser(emailID);
		loggedInUsers.put(sessionID, userStore.getUser(emailID));
		return sessionID;
	}
	
	private  synchronized String generateHashForUser(String emailID) {
		long timestamp = System.currentTimeMillis();
		int currentActiveUsers = loggedInUsers.keySet().size();
		String hash = timestamp+""+currentActiveUsers;
		LOGGER.debug("Session id generated:'"+hash+"' for user["+emailID+"]");
		return hash;
	}
	
	public User getUserFromSession(String sessionID) {
		if(loggedInUsers.containsKey(sessionID)) {
			return loggedInUsers.get(sessionID);
		}
		return null;
	}
}

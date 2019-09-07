package com.inmobi.casestudy.githit.datastore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.inmobi.casestudy.githit.domain.User;

public class InMemUserStore implements UserStore{

	Map<String, User> userCache = new HashMap<String, User>();
	public InMemUserStore(){
		userCache.put("user1@retail.com", new User("User1", "user1@retail.com", null, "password", true));
		userCache.put("user2@retail.com", new User("User2", "user2@retail.com", null, "password", true));
		userCache.put("user3@retail.com", new User("User3", "user3@retail.com", null, "password", true));
		userCache.put("user4@retail.com", new User("User4", "user4@retail.com", null, "password", true));
		userCache.put("user5@retail.com", new User("User5", "user5@retail.com", null, "password", true));
		userCache.put("user6@retail.com", new User("User6", "user6@retail.com", null, "password", true));
		userCache.put("user7@retail.com", new User("User7", "user7@retail.com", null, "password", true));
		userCache.put("user8@retail.com", new User("User8", "user8@retail.com", null, "password", true));
		userCache.put("user9@retail.com", new User("User9", "user9@retail.com", null, "password", true));
		userCache.put("user10@retail.com", new User("User10", "user10@retail.com", null, "password", true));
		userCache.put("user11@retail.com", new User("User11", "user11@retail.com", null, "password", true));
		userCache.put("user12@retail.com", new User("User12", "user12@retail.com", null, "password", true));
		userCache.put("user13@retail.com", new User("User13", "user13@retail.com", null, "password", true));
		userCache.put("user14@retail.com", new User("User14", "user14@retail.com", null, "password", true));
		userCache.put("user15@retail.com", new User("User15", "user15@retail.com", null, "password", true));
	}

	@Override
	public User getUser(String emailId) {
		User user = null;
		if(userCache.containsKey(emailId))
			user = userCache.get(emailId);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		return userCache.values().stream()
				.collect(Collectors.toList());
	}

	@Override
	public boolean updateUser(User user) {
		boolean hasOperationCompleted = false;
		if(userCache.containsKey(user.getEmailId())) {
			userCache.put(user.getEmailId(), user);
			hasOperationCompleted = true;
		}
		return hasOperationCompleted;
	}

	@Override
	public boolean deleteUser(String emailId) {
		boolean hasOperationCompleted = false;
		if(userCache.containsKey(emailId)) {
			userCache.remove(emailId);
			hasOperationCompleted = true;
		}
		return hasOperationCompleted;
	}

	@Override
	public List<User> queryForUsers(String query) {
		return null;
	}

}

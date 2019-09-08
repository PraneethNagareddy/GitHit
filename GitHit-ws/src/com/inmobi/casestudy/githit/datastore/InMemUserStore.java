package com.inmobi.casestudy.githit.datastore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.inmobi.casestudy.githit.domain.User;

public class InMemUserStore implements UserStore{

	Map<String, User> userCache = new HashMap<String, User>();
	public InMemUserStore(){
		userCache.put("user1@git.com", new User("User1", "user1@git.com", null, "password"));
		userCache.put("user2@git.com", new User("User2", "user2@git.com", null, "password"));
		userCache.put("user3@git.com", new User("User3", "user3@git.com", null, "password"));
		userCache.put("user4@git.com", new User("User4", "user4@git.com", null, "password"));
		userCache.put("user5@git.com", new User("User5", "user5@git.com", null, "password"));
		userCache.put("user6@git.com", new User("User6", "user6@git.com", null, "password"));
		userCache.put("user7@git.com", new User("User7", "user7@git.com", null, "password"));
		userCache.put("user8@git.com", new User("User8", "user8@git.com", null, "password"));
		userCache.put("user9@git.com", new User("User9", "user9@git.com", null, "password"));
		userCache.put("user10@git.com", new User("User10", "user10@git.com", null, "password"));
		userCache.put("user11@git.com", new User("User11", "user11@git.com", null, "password"));
		userCache.put("user12@git.com", new User("User12", "user12@git.com", null, "password"));
		userCache.put("user13@git.com", new User("User13", "user13@git.com", null, "password"));
		userCache.put("user14@git.com", new User("User14", "user14@git.com", null, "password"));
		userCache.put("user15@git.com", new User("User15", "user15@git.com", null, "password"));
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

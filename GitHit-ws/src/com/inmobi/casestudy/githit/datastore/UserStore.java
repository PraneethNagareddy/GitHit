package com.inmobi.casestudy.githit.datastore;

import java.util.List;

import com.inmobi.casestudy.githit.domain.User;

public interface UserStore {
	User getUser(String emailId);
	List<User> getAllUsers();
	boolean updateUser(User user);
	boolean deleteUser(String emailId);
	List<User> queryForUsers(String query);
}

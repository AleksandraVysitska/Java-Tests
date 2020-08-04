package com.tests.mockito.dao;

import java.util.List;

import com.tests.mockito.model.User;

public interface UserDao {
	
	User getUserByUsername(String username) throws Exception;
	List <User> findAllUsers();

}

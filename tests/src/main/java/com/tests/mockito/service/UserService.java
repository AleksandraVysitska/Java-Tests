package com.tests.mockito.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.tests.mockito.dao.UserDao;
import com.tests.mockito.model.User;

public class UserService {

	@Autowired
	private UserDao dao;
	
	public UserService (UserDao dao) {
		this.dao = dao;
	}
	
	public boolean checkUserPresense (User user) throws Exception{
		User userByUsernameUser = (User)dao.getUserByUsername(user.getUsername());
		
		return userByUsernameUser!=null;
	}
	
}

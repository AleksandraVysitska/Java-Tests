package com.tests.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.tests.mockito.dao.UserDao;
import com.tests.mockito.dao.UserDaoImpl;
import com.tests.mockito.model.User;

class UserDaoTest {
	@Autowired
	private UserDao dao;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.dao = new UserDaoImpl();
	}

	@Test
	public void testGetUserByUsername_ShouldReturnTrue() throws Exception{
		User olga = dao.getUserByUsername("olga");
		
		assertThat(olga).isNotNull();
		assertThat(olga.getUsername()).isEqualTo("olga");
		System.out.println(olga.getRole());
	}
	
	@Test
	public void testGetUserByUsername_NullUser () throws Exception {
		User alex = dao.getUserByUsername("alex");
		assertThat(alex).isNull();
	}
	
	@Test
	public void testFindAllUsers_Contain() throws Exception{
		
		List<User> allUsers = dao.findAllUsers();
		
		assertThat(allUsers.get(2)).isEqualToComparingFieldByField(new User ("maia", "ADMIN"));
		
		assertThat(allUsers).contains(new User ("maia", "ADMIN")); //methods .equals() && .hashCode() of User
		
	}

}

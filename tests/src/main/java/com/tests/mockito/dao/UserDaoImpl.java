/**
 * 
 */
package com.tests.mockito.dao;

import java.util.Arrays;
import java.util.List;

import com.tests.mockito.model.User;

/**
 * @author aleksandra
 *
 */
public class UserDaoImpl implements UserDao {
	
	private List<User> users;
	
	public UserDaoImpl() {
		this.users = Arrays.asList(
				new User ("olga", "GUEST"),
				new User ("kirill", "USER"),
				new User ("maia", "ADMIN")
				);
	}

	@Override
	public User getUserByUsername(String username) throws Exception {
		return users.stream().filter( u -> u.getUsername().equals(username))
				.findAny() 			//findAny() ritorna optional, cioè User può essere trovato o meno, 
				.orElse(null); 		//perciò bisogna specificare cosa fare se User non verrà trovato	
	}
	
	@Override
	public List <User> findAllUsers(){
		return users;
	}
	

}

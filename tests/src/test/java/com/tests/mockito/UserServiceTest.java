/**
 * 
 */
package com.tests.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tests.mockito.dao.UserDao;
import com.tests.mockito.model.User;
import com.tests.mockito.service.UserService;

/**
 * @author aleksandra
 *
 */
class UserServiceTest {

	@Mock
	private UserDao dao;
	private UserService service;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.service = new UserService(dao);
	}

	@Test
	public void checkUserPresense_ShouldReturnTrue() throws Exception {
		given(dao.getUserByUsername("olga")).willReturn(
				new User ("olga"));
		boolean b = service.checkUserPresense(new User ("olga"));
		
		assertThat(b).isTrue();
		
		//verify
		verify(dao).getUserByUsername("olga");
	}
	
	@Test
	public void checkUserPresense_ShouldReturnFalse() throws Exception {
		given(dao.getUserByUsername("olga")).willReturn(null);
		boolean b = service.checkUserPresense(new User ("olga"));
		
		assertThat(b).isFalse();
	}

	@Test
	public void checkUserPresense_ShouldThrowException() throws Exception {
		 Exception exception = assertThrows(Exception.class, () -> {
		given(dao.getUserByUsername(anyString())).willThrow(Exception.class);
		service.checkUserPresense(new User ("olga"));
		 });
	}
	
	@Test
	public void testCaptor() throws Exception {
		given(dao.getUserByUsername(anyString())).willReturn(new User("olga"));
		boolean b = service.checkUserPresense(new User("olga"));
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(dao).getUserByUsername(captor.capture());
		String argument = captor.getValue();
		assertThat(argument).isEqualTo("olga");

	}
}

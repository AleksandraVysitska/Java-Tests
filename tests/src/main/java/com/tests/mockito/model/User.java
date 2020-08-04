package com.tests.mockito.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	private long id;
	

	private  String username;
	private  String role;
	
	public User(String username) {
		this.username = username;
	}
	
	public User(String username, String role) {
		this.username = username;
		this.role = role;
	}
	
}

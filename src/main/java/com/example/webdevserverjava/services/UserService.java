package com.example.webdevserverjava.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.model.User;

@RestController
public class UserService {
	User alice = new User(123, "alice", "pw", "Alice", "Wonderland");
	User bob   = new User(234, "bob", "pw", "Bob", "Marley");
	User steve = new User(456, "steve", "pw", "Steve", "Smith");
	User[] users = {alice, bob, steve};

	@GetMapping("/api/user")
	public User[] findAllUser() {
		return users;
	}
	@GetMapping("/api/user/{userId}")
	public User findUserById(
			@PathVariable("userId") Integer id) {
		for(User user: users) {
			if(id == user.getId().intValue())
				return user;
		}
		return null;
	}
	public User createUser(User user) {
		int id = user.getId();
		String userName = user.getUsername();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		User newUser = new User (id, userName, password, firstName, lastName);
		return newUser;
		
	}
//	public void deleteUser(Integer id) {
//		
//	}
//	public User updateUser(Integer id, User user) {
//		
//	}
}
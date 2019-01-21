package com.example.webdevserverjava.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

import com.example.webdevserverjava.model.User;

@RestController
public class UserService {

	List<User> users = new ArrayList<User>();
	User alice = new User(123, "alice", "pw", "Alice", "Wonderland");
	User bob   = new User(234, "bob", "pw", "Bob", "Marley");
	User steve = new User(456, "steve", "pw", "Steve", "Smith");
	Boolean initialized = false;
	List<Integer> idList = new ArrayList<Integer>();
	
	public void initialize () {
		users.add(alice);
		users.add(bob);
		users.add(steve);
		idList.add(123);
		idList.add(234);
		idList.add(456);
	}
	
;	
	@GetMapping("/api/user")
	public List<User> findAllUser() {
		if (! this.initialized) {
			initialize();
			this.initialized = true;
		}
		
		return users;
	}
	@GetMapping("/api/user/{userId}")
	public User findUserById(
			@PathVariable("userId") Integer id) {
		for(User user: users) {
			if(id == user.getId().intValue())
				System.out.println("got it from the findUserById method " + user.getFirstName());
				return user;
		}
		return null;
	}
	
	@RequestMapping(value="/createUser/{username}/{password}/{firstName}/{lastName}", method=RequestMethod.POST)
	public User createUser(@PathVariable(value="username") String userName,
			@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName,
			@PathVariable("password") String password) {
		int id = generateID();
		User newUser = new User (id, userName, password, firstName, lastName);
		System.out.println(newUser.getFirstName());
		users.add(newUser);
		System.out.println("There are now" + users.size() +" users");
		return newUser;
		
	} 
//	public void deleteUser(Integer id) {
//		
//	}
//	public User updateUser(Integer id, User user) {
//		
//	}
	public int generateID () {
		Boolean numberGenerated = false;
		int attempt = 0;
		while (! numberGenerated) {
			Random rand = new Random();
			attempt = rand.nextInt(999) + 1;
			if (! idList.contains(attempt)) {
				numberGenerated = true;
				idList.add(attempt);
			}
		}
		return attempt;
	}
	
	public static void main (String [] args) {
		UserService test = new UserService();
		test.findUserById(123);
	}
}


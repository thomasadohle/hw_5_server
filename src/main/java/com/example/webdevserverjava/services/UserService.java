package com.example.webdevserverjava.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

import javax.servlet.http.HttpSession;

import com.example.webdevserverjava.model.User;

@RestController
public class UserService {

	List<User> users = new ArrayList<User>();
	
	//Keeps track of all existing user IDs to ensure that new users get unique Ids
	List<Integer> idList = new ArrayList<Integer>();
	
	/**
	 * Register a new user
	 * @param user
	 * @param session
	 * @return
	 */
	@PostMapping("/api/register")
	@CrossOrigin
	public User register (@RequestBody User user,
			HttpSession session) {
		session.setAttribute("currentUser", user);
		int id = generateID();
		user.setId(id);
		users.add(user);
		printUserData();
		return user;
	}
	
	@PostMapping("/api/login")
	@CrossOrigin
	public User login(	@RequestBody User credentials,
	HttpSession session) {
	 for (User user : users) {
	  if( user.getUsername().equals(credentials.getUsername())
	   && user.getPassword().equals(credentials.getPassword())) {
	    session.setAttribute("currentUser", user);
	    System.out.println("Successfully logged in user " + credentials.getUsername());
	    return user;
	  }
	 }
	 return null;
	}

	
	
	/**
	 * Return the currently logged in user
	 * @param session
	 * @return
	 */
	@GetMapping("/api/profile")
	@CrossOrigin
	public User profile(HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		System.out.println("Current user is: " + currentUser.getUsername());
		return currentUser;
	}
	
	@PostMapping("/api/logout")
	@CrossOrigin
	public void logout (HttpSession session) {
		System.out.println("User logged out");
		session.invalidate();
	}

	/**
	 * Handless GET request to return all User
	 * @return JSON containing all users stored on the server
	 */
	@GetMapping("/api/user")
	@CrossOrigin
	public List<User> findAllUser() {
		printUserData(); //used for testing
		return users;
	}
	
	@GetMapping("/test")
	@CrossOrigin
	public User returnTestUser() {
		User newUser = new User ("testUn","testFN","testLN","testPW","FACULTY");
		return newUser;
	}
	
	/**
	 * Handles GET request to return a specific user
	 * @param id is the unique userId
	 * @return the user with the given ID
	 */
	@GetMapping("/api/user/{userId}")
	public User findUserById(
			@PathVariable("userId") Integer id) {
		for(User user: users) {
			if(id == user.getId().intValue()) {
				System.out.println("got it from the findUserById method " + user.getFirstName());
				return user;
		}
		}
		return null;
	}
	

	
	/**
	 * Returns the User most recently added to the server
	 * @return most recently added user
	 */
	@GetMapping("/api/recent")
	public User returnMostRecentUser () {
		User recent = users.get(users.size()-1);
		System.out.println("From the recent user API call");
		printUserData();
		return recent;
	}
	
	/**
	 * Handles POST request to delete a User from the server
	 * @param id
	 */
	@RequestMapping(value="/deleteUser/{id}", method=RequestMethod.POST)
	public void deleteUser(@PathVariable(value="id") int id) {
		int i = 0;
		for (User u : users) {
			if (u.getId() == id) {
				users.remove(i);
				System.out.println(users);
				return;
			}
			i++;
		}
		System.out.println("User was not found");
	}
	
	/**
	 * Handles POST request to update User information. The method name does not match it's function, but I'm scared to change it right now.
	 * @param userName
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param userId
	 * @param role
	 */
	@RequestMapping(value="/updateUser/{username}/{password}/{firstName}/{lastName}/{userId}/{role}", method=RequestMethod.POST)
	public void createReplacementUser(@PathVariable(value="username") String userName,
			@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName,
			@PathVariable("password") String password,
			@PathVariable("userId") Integer userId,
			@PathVariable("role") String role) {
		User updatedUser = new User(userId,userName,password,firstName,lastName, role);
		System.out.println("The usernmae of the updated user is: " + updatedUser.getUsername());
		updateUser(userId, updatedUser);
	}
	
	/**
	 * Filters User based on search parameters. If the parameter is "banana_Pancakes, then it is ignored
	 * @param userName
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param role
	 * @return a List of User matching search paramters
	 */
	@GetMapping("/api/search/{username}/{password}/{firstName}/{lastName}/{role}")
	public List<User> searchForUsers(@PathVariable(value="username") String userName,
			@PathVariable(value="password")  String password,	
			@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName,
			@PathVariable("role") String role) {
		System.out.println("Password is: " + password);
		List<User> searchResults = new ArrayList<User>();
		String defaultValueForBlank = "banana_Pancakes";
		for (User u : users) {
			if((u.getUsername().toLowerCase().contains(userName.toLowerCase()) || userName.equals(defaultValueForBlank)) 
					&& (u.getPassword().toLowerCase().contains(password.toLowerCase()) || password.equals(defaultValueForBlank))
					&& (u.getFirstName().toLowerCase().contains(firstName.toLowerCase()) || firstName.equals(defaultValueForBlank)) 
					&& (u.getLastName().toLowerCase().contains(lastName.toLowerCase()) || lastName.equals(defaultValueForBlank))
					&& (u.getRole().toLowerCase().contains(role.toLowerCase()) || role.equals(defaultValueForBlank))) {
				searchResults.add(u);
			} 
		}
		System.out.println("Search results for:");
		System.out.println("username: " + userName);
		System.out.println("password: " + password);
		System.out.println("first name: " + firstName);
		System.out.println("last name: " + lastName);
		System.out.println("role: " + role);
		for (User user : searchResults) {
			System.out.println(user.getUsername());
		} 
		System.out.println("There are " + searchResults.size() + " results");
		return searchResults;
		
	}
	
	/**
	 * Actually handles the updating of the user
	 * @param id is the userId of the original user
	 * @param updatedUser is a temporary User to compare to the old one
	 */
	public void updateUser(Integer id, User updatedUser) {
		System.out.println("The id of the user to be updated is: " + id);
		for (User u : users) {
			if (u.getId().intValue() == id.intValue()) {
				u.setUsername(updatedUser.getUsername());
				u.setPassword(updatedUser.getPassword());
				u.setFirstName(updatedUser.getFirstName());
				u.setLastName(updatedUser.getLastName());
				u.setRole(updatedUser.getRole());
				System.out.println("User updated successfully");
				for (User us : users) {
					System.out.println(us.getUsername());
				}
				return;
			}
		}
		System.out.println("Unable to find the user to update");
		
		}
	
	/**
	 * Ensures that all User have unique Id
	 * @return
	 */
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
	

	/**
	 * Used for testing
	 */
	public void printUserData() {
		int i=0;
		for (User u : users) {
			System.out.println("User "+i);
			System.out.println("ID: " + u.getId());
			System.out.println("Username0: " + u.getUsername());
			System.out.println("First Name: " + u.getFirstName());
			System.out.println("Last Name: " + u.getLastName());
			System.out.println("Password: " + u.getPassword());
			System.out.println("Role: " + u.getRole());
			System.out.println("");
			i++;
		}
	}
}


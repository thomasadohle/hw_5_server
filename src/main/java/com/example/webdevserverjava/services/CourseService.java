package com.example.webdevserverjava.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;
import javax.servlet.http.HttpSession;
import com.example.webdevserverjava.model.Course;
import com.example.webdevserverjava.model.Lesson;
import com.example.webdevserverjava.model.Module;
import com.example.webdevserverjava.model.Topic;
import com.example.webdevserverjava.model.User;

@RestController
public class CourseService {
	final String origins = "https://pacific-lake-81602.herokuapp.com";
	
	List<Course> courses = new ArrayList<Course>();
	List<Integer> idList = new ArrayList<Integer>();
	
	@GetMapping("/test/courses")
	@CrossOrigin(allowCredentials="true")
	public String testCourses () {
		String out = "";
		for (Course c : this.courses) {
			out+= c.toString();
			out+="\n";
		}
		return out;
	}
	
	@PostMapping("/api/courses")
	@CrossOrigin(allowCredentials="true")
	public Course createCourse(@RequestBody Course course,
			HttpSession session) {
		User author = (User) session.getAttribute("currentUser");
//		int id = generateID();
//		course.setId(id);
		course.setAuthor(author);
		courses.add(course);
		session.setAttribute("currentCourse", course);
		return course;
	}
	

	
	@GetMapping("/api/courses")
	@CrossOrigin(allowCredentials="true")
	public List<Course> findAllCourses(HttpSession session) {
		List<Course> userCourses = new ArrayList<>();
		User currentUser = (User)session.getAttribute("currentUser");
		for (Course c : this.courses) {
			if (c.getAuthor().getId().equals(currentUser.getId())) {
				userCourses.add(c);
			}
		}
		Date now = new Date();
		System.out.println(now.getTime());
		printCourses(userCourses);
		return userCourses;
	}
	
	@GetMapping("/api/courses/{cid}")
	@CrossOrigin(allowCredentials="true")
	public Course findCourseById(@PathVariable(value="cid")  int id, HttpSession session) {
		for (Course c : this.courses) {
			if (c.getID() == id) {
				System.out.println("found course: " + c.toString());
				session.setAttribute("currentCourse", c);
				return c;
			}
		}
		System.out.println("could not find course");
		return null;
	}
	
	@DeleteMapping("/api/courses/{cid}")
	@CrossOrigin(allowCredentials="true")
	public Course deleteCourse(@PathVariable(value="cid") int id) {
		for (Course c : this.courses) {
			if (c.getID() == id) {
				courses.remove(c);
				System.out.println("deleted a course");
				printCourses(this.courses);
				return c;
			}
		}
		return null;
	}
	
	@PutMapping("/api/courses/{cid}")
	@CrossOrigin(allowCredentials="true")
	public Course updateCourse(@PathVariable(value="cid") int id, @RequestBody Course course) {
		for (Course c : this.courses) {
			if (c.getID() == id) {
				c.setCourseTitle(course.getCourseTitle());
				return c;
			}
		}
		System.out.println("course was not found and therefore not updated");
		return course;
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
	
	
	public void printCourses(List<Course> courseList) {
		System.out.println("Current Courses: ");
		for (Course c : courseList) {
			System.out.println(c.toString());
		}
	}
	

}

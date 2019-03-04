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
import com.example.webdevserverjava.model.Course;


@RestController
public class CourseService {
	List<Course> courses = new ArrayList<Course>();
	List<Integer> idList = new ArrayList<Integer>();
	
	@PostMapping("/api/course/{cid}/module")
	@CrossOrigin
	public Course createCourse(@RequestBody Course course, HttpSession session) {
		//User currentUser = session.getAttribute("currentUser");
		return course;
	}
}

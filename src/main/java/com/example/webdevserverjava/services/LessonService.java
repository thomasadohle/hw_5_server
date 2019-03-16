package com.example.webdevserverjava.services;


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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpSession;
import com.example.webdevserverjava.model.Course;
import com.example.webdevserverjava.model.Lesson;
import com.example.webdevserverjava.model.Module;
import com.example.webdevserverjava.model.User;

@RestController
public class LessonService {
	final String origins = "http://www.pacific-lake-81602.herokuapp.com";
	
	List<Lesson> lessons = new ArrayList<Lesson>();
	List<Integer> idList = new ArrayList<Integer>();
	
	@PostMapping("/api/modules/{mid}/lesson")
	@CrossOrigin(origins=origins ,allowCredentials="true")
	public Lesson createModule(@RequestBody Lesson lesson,
			HttpSession session, @PathVariable (value="mid") int moduleId) {
//		int id = generateID();
//		lesson.setId(id);
		lesson.setModuleId(moduleId);
		lessons.add(lesson);
		session.setAttribute("currentLesson", lesson);
		System.out.println(lesson.toString());
		return lesson;
	}
	
	@PutMapping("/api/lessons/{lid}")
	@CrossOrigin(origins=origins ,allowCredentials="true")
	public Lesson updateLesson(@RequestBody Lesson lesson, @PathVariable(value="lid") int lessonId) {
		for (Lesson l : this.lessons) {
			if (l.getId() == lessonId) {
				l.setTitle(lesson.getTitle());
				System.out.println("LessonTitle successfully updated to " + l.getTitle());
				return l;
			}
		}
		System.out.println("Failed to update Lesson");
		return lesson;
	}
	
	@GetMapping("/api/modules/{mid}/lessons")
	@CrossOrigin(origins=origins ,allowCredentials="true")
	public List<Lesson> findAllLessons(@PathVariable(value="mid") int moduleId){
		List<Lesson> lessons = new ArrayList<>();
		for (Lesson l : this.lessons) {
			if (l.getModuleId() == moduleId) {
				lessons.add(l);
			}
		}
		return lessons;
	}
	
	@GetMapping("/api/lessons/{lid}")
	@CrossOrigin(origins=origins ,allowCredentials="true")
	public Lesson findLessonById(@PathVariable(value="lid")  int id, HttpSession session) {
		for (Lesson l : this.lessons) {
			if (l.getId() == id) {
				System.out.println("found lesson: "+ l.toString());
				session.setAttribute("currentLesson",l);
				return l;
			}
		}
		System.out.println("could not find lesson");
		return null;
	}
	
	
	
	@DeleteMapping("/api/lessons/{lid}")
	@CrossOrigin(origins=origins ,allowCredentials="true")
	public Lesson deleteLesson(@PathVariable(value="lid") int id) {
		for (Lesson l : this.lessons) {
			if (l.getId() == id) {
				lessons.remove(l);
				System.out.println("deleted a lesson");
				return l;
			}
		}
		System.out.println("Could not find lesson to delete");
		return null;
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
	

}

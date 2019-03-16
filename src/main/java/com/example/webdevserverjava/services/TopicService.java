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

import com.example.webdevserverjava.model.Lesson;
import com.example.webdevserverjava.model.Topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpSession;

@RestController
public class TopicService {
	final String origins = "http://www.pacific-lake-81602.herokuapp.com";
	List<Topic> topics = new ArrayList<Topic>();
	
	@PostMapping("/api/lessons/{lid}/topic")
	@CrossOrigin(origins=origins ,allowCredentials="true")
	public Topic createTopic(@RequestBody Topic topic,
			HttpSession session, @PathVariable (value="lid") int lessonId) {
		topic.setLessonId(lessonId);
		topics.add(topic);
		session.setAttribute("currentTopic", topic);
		System.out.println("Topic added: " + topic.toString());
		return topic;
	}
	
	@PutMapping("/api/topics/{tid}")
	@CrossOrigin(origins=origins ,allowCredentials="true")
	public Topic updateTopic(@RequestBody Topic topic, @PathVariable(value="tid") int topicId) {
		for (Topic t : this.topics) {
			if (t.getId() == topicId) {
				t.setTitle(topic.getTitle());
				System.out.println("TopicTitle successfully updated to " +t.getTitle());
				return t;
			}
		}
		System.out.println("Failed to update Topic");
		return topic;
	}
	
	//           /api/lessons/2078786103/topics
	@GetMapping("/api/lessons/{lid}/topics")
	@CrossOrigin(origins=origins ,allowCredentials="true")
	public List<Topic> findAllTopics(@PathVariable(value="lid") int lessonId){
		System.out.println("findAllTopics called");
		List<Topic> topicsForLesson = new ArrayList<>();
		for (Topic t : this.topics) {
			if (t.getLessonId() == lessonId) {
				topicsForLesson.add(t);
			}
		}
		return topicsForLesson;
	}
	
	@GetMapping("/api/topics/{tid}")
	@CrossOrigin(origins=origins ,allowCredentials="true")
	public Topic findTopicById(@PathVariable(value="tid")  int topicId, HttpSession session) {
		for (Topic t : this.topics) {
			if (t.getId() == topicId) {
				System.out.println("found topic: "+ t.toString());
				session.setAttribute("currentTopic",t);
				return t;
			}
		}
		System.out.println("could not find topic");
		return null;
	}
	
	@DeleteMapping("/api/topics/{tid}")
	@CrossOrigin(origins=origins ,allowCredentials="true")
	public Topic deleteTopic(@PathVariable(value="tid") int topicId) {
		for (Topic t : this.topics) {
			if (t.getId() == topicId) {
				topics.remove(t);
				System.out.println("deleted a topic");
				return t;
			}
		}
		System.out.println("Could not find topic to delete");
		return null;
	}
}

package com.example.webdevserverjava.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lesson {
	private List<Topic> topics = new ArrayList<>();
	private Integer lessonId;
	private String title;
	private Integer moduleId;
	
	public Lesson() {
		this.title = "New Lesson";
		//this.topics.add(new Topic());
		Date now = new Date();
		int id = (int)now.getTime();
		this.lessonId=id;
	}
	
	public Lesson (String title) {
		this.title=title;
		//this.topics.add(new Topic());
		Date now = new Date();
		int id = (int)now.getTime();
		this.lessonId=id;
	}
	
	public List<Topic> getTopics(){return this.topics;}
	public Integer getId() {return this.lessonId;}
	public String getTitle() {return this.title;}
	public Integer getModuleId() {return this.moduleId;}
	
	public void addTopic(Topic t) {this.topics.add(t);}
	public void setId(Integer id) {this.lessonId = id;}
	public void setModuleId(Integer id) {this.moduleId = id;}
	public void setTitle(String title) {this.title=title;}
	
	@Override
	public String toString() {
		String str = "";
		str += "Lesson " + this.getTitle();
		return str;
	}
	
}

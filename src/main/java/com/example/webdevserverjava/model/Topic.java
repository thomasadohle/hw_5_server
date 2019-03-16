package com.example.webdevserverjava.model;

import java.util.Date;

public class Topic {
	private Integer id;
	private Integer lessonId;
	private String title;
	
	public Topic() {
		this.title="New Topic";
		Date now = new Date();
		int id = (int)now.getTime();
		this.id=id;
	}
	
	public Integer getId() {return this.id;}
	public Integer getLessonId() {return this.lessonId;}
	public String getTitle() {return this.title;}
	
	public void setId(Integer id) {this.id = id;}
	public void setLessonId(Integer id) { this.lessonId=id;}
	public void setTitle(String title) {this.title=title;}
	
	@Override
	public String toString() {
		return "topic: " + this.id + " " + this.title + "belongs to: " + this.lessonId;
	}
}

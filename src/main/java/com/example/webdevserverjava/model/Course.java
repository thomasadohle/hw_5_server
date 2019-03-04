package com.example.webdevserverjava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Course {
	private List<Module> modules = new ArrayList<>();
	private List<Integer> idList = new ArrayList<Integer>();
	private User author;
	private Integer courseId;
	private String courseTitle;
	
	public Course() {
	}
	
	public Course (User author, String courseTitle) {
		this.author=author;
		this.courseTitle = courseTitle;
	}
	
	public List<Module> getModules() {return this.modules;}
	public User getAuthor() {return this.author;}
	public Integer getID() {return this.courseId;}
	public String getCourseTitle() {return this.courseTitle;}
	
	public void setCourseTitle(String title) {
		this.courseTitle=title;
	}
	public void setId (Integer id) {
		this.courseId = id;
	}
	
	public Module addModule(Module m) {
		this.modules.add(m);
		return m;
	}
	
	public void removeModule(Module m) {
		for (Module mod : this.modules) {
			if (m.getID().equals(mod.getID())) {
				this.modules.remove(mod);
			}
		}
	}
	

	
}

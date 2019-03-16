package com.example.webdevserverjava.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Course {
	private List<Module> modules = new ArrayList<>();
	private User author;
	private Integer courseId;
	private String courseTitle;
	
	public Course() {
		Date now = new Date();
		int id = (int)now.getTime();
		this.courseId=id;
//		Module newModule = new Module();
//		newModule.setcourseId(this.courseId);
//		this.modules.add(newModule);
	}
	
	public Course (User author, String courseTitle) {
		Date now = new Date();
		int id = (int)now.getTime();
		this.courseId=id;
		this.author=author;
		this.courseTitle = courseTitle;
//		Module newModule = new Module();
//		newModule.setcourseId(this.courseId);
//		this.modules.add(newModule);
	}
	
	public Course (String courseTitle) {
		Date now = new Date();
		int id = (int)now.getTime();
		this.courseId=id;
		this.courseTitle = courseTitle;
//		Module newModule = new Module();
//		newModule.setcourseId(this.courseId);
//		this.modules.add(newModule);
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
	public void setAuthor(User author) {
		this.author = author;
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
	
	@Override
	public String toString() {
		String course = this.getCourseTitle();
		course += " \n Modules: ";
		for (Module m : this.modules) {
			course+="\n " + m.toString();
			course+= "\n         Lessons: ";
			for (Lesson l : m.getLessons()) {
				course+= "\n                 " + l.toString();
				course+= "\n                             Topics: ";
				for (Topic t : l.getTopics()) {
					course+= "\n                               " + t.getTitle();
				}
			}
		}
		return course;
	}
	

	
}

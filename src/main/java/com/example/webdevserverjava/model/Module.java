package com.example.webdevserverjava.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Module {
	private List<Lesson> lessons = new ArrayList<>();
	private Integer moduleId;
	private String moduleTitle;
	private Integer courseId;
	
	public Module() {
		this.moduleTitle = "New Module";
		//this.lessons.add(new Lesson());
		Date now = new Date();
		int id = (int)now.getTime();
		this.moduleId=id;
	}
	
	public Module(String moduleTitle) {
		this.moduleTitle = moduleTitle;
		//this.lessons.add(new Lesson());
		Date now = new Date();
		int id = (int)now.getTime();
		this.moduleId=id;
	}
	
	public Module(Integer courseId, String moduleTitle) {
		this.courseId=courseId;
		this.moduleTitle = moduleTitle;
		//this.lessons.add(new Lesson());
		Date now = new Date();
		int id = (int)now.getTime();
		this.moduleId=id;
	}
	
	public List<Lesson> getLessons() {return this.lessons;}

	public Integer getID() {return this.moduleId;}
	public String getModuleTitle() {return this.moduleTitle;}
	public Integer getCourseId() {return this.courseId;}
	
	public void setId(Integer id) {this.moduleId = id;}
	public void setcourseId(Integer id) {this.courseId = id;}
	public void setModuleTitle(String title) {this.moduleTitle=title;}
	
	@Override
	public String toString() {
		String out = "Module " + this.moduleId;
		out+="\n title: " + this.moduleTitle;
		out += "\n courseId: " + this.courseId;
		return out;
	}
	

}



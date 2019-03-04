package com.example.webdevserverjava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Module {
	private List<Lesson> lessons = new ArrayList<>();
	private List<Integer> idList = new ArrayList<Integer>();
	private Course parent;
	private Integer moduleId;
	private String moduleTitle;
	
	public Module() {
		this.moduleId = generateID();
	}
	
	public Module(Course parent, String moduleTitle) {
		this.parent = parent;
		this.moduleTitle = moduleTitle;
		this.moduleId = generateID();
	}
	
	public List<Lesson> getLessons() {return this.lessons;}
	public Course getParent() {return this.parent;}
	public Integer getID() {return this.moduleId;}
	public String getModuleTitle() {return this.moduleTitle;}
	
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



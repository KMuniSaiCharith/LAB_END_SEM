package com.klef.jfsd.exam.EndLab;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Project {
	public Project(String projectName, int duration, double budget, String teamLead) {
		super();
		this.projectName = projectName;
		this.duration = duration;
		this.budget = budget;
		this.teamLead = teamLead;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String projectName;
	private int duration;
	public double budget;
	public String teamLead;

}

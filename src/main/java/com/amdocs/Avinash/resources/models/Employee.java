package com.amdocs.Avinash.resources.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "EmployeeCollection")
@Data
public class Employee {

	@Id
	private String id;

	@Field("name")
	private String name;
	@Field("email")
	private String email;
	@Field("username")
	private String username;
	@Field("parentId")
	private String parentId;

	@Field("project")
	private String project;

	@Field("manager")
	private String manager;
	@Field("experience")
	private String experience;
	@Field("contact")
	private String contact;

	public Employee(String name, String email, String username) {
		this.username = username;
		this.name = name;
		this.email = email;
	}
}

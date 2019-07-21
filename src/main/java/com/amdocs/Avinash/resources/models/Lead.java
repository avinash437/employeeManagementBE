package com.amdocs.Avinash.resources.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "LeadsCollection")
@Data
public class Lead {

	@Id
	private String id;

	@Field("name")
	private String name;
	
	@Field("parentId")
	private String parentId;

	@Field("project")
	private String project;

	@Field("manager")
	private String manager;
	@Field("username")
	private String username;
	
}

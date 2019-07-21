package com.amdocs.Avinash.resources.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "EmployeeNotesCollection")
@Data
public class EmployeeNotes {

	@Id
	private String id;

	@Field("name")
	private String name;
	
	@Field("username")
	private String username;
	
	
	@Field("parentId")
	private String parentId;
	
	@Field("notes")
	private String note;
	
	@Field("type")
	private String type;
	
	@Field("date")
	private Date time;
}

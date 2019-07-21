package com.amdocs.Avinash.resources.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "VacationCollection")
@Data
public class Vacations {
	@Id
	private String id;

	@Field("username")
	private String username;

	@Field("reason")
	private String reason;

	@Field("parentId")
	private String parentId;
	
	@Field("name")
	private String name;

	@Field("to")
	private Date to;

	@Field("from")
	private Date from;

}

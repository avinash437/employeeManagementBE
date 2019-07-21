package com.amdocs.Avinash.resources.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "UserPreferencesCollection")
@Data
public class UserPreferences {
	@Id
	private String id;

	@Field("sprintStartDate")
	private Date sprintStartDate;

	@Field("username")
	private String username;
	@Field("sprintNumber")
	private int sprintNumber;

	@Field("parentId")
	private String parentId;
	
	@Field("scrumTeamName")
	private String scrumTeamName;
	
	@Field("displayList")
	private int displayList;

}

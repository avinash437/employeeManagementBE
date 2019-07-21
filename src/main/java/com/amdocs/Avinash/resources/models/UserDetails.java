package com.amdocs.Avinash.resources.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "UserDetailsCollection")
@Data
public class UserDetails {

	@Id
	private String id;

	@Field("name")
	private String name;
	
	@Field("password")
	private String password;

	@Field("username")
	private String username;

	@Field("role")
	private String role;

	@Field("is_developer")
	private boolean developer;
	
	@Field("is_lead")
	private boolean lead;
	
	@Field("is_manager")
	private boolean manager;
	
	@Field("is_tester")
	private boolean tester;

}

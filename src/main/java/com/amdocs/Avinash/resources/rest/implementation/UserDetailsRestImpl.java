package com.amdocs.Avinash.resources.rest.implementation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.Avinash.exceptions.model.ApplicationException;
import com.amdocs.Avinash.mongo.repository.UserDetailsRepository;
import com.amdocs.Avinash.resources.models.UserDetails;
import com.amdocs.Avinash.resources.rest.interfaces.UserLoginRest;


@RestController
public class UserDetailsRestImpl  implements UserLoginRest {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public ResponseEntity<UserDetails> checkLogin(String contentType, @Valid UserDetails userDetails) throws Exception {
		
		UserDetails users = checkLogin(userDetails);
		if(null== users) {
			throw new ApplicationException("Not Found", "NOT_FOUND", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDetails>(users, HttpStatus.OK);
	}

	public UserDetails checkLogin(UserDetails userDetails) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(userDetails.getUsername()));
		query.addCriteria(Criteria.where("password").is(userDetails.getPassword()));
		List<UserDetails> users = mongoTemplate.find(query, UserDetails.class);
		if(users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

	@Override
	public ResponseEntity<Void> addUser(String contentType, @Valid UserDetails userDetails) {
		UserDetails users = checkLogin(userDetails);
		if(null== users) {
			userDetailsRepository.insert(userDetails);
		}else {
			throw new ApplicationException("Already Exists", "ALREADY_EXISTS", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Void> updateUser(String contentType, @Valid UserDetails userDetails) {
		
		UserDetails users = checkLogin(userDetails);
		if(null== users) {
			throw new ApplicationException("Not Found", "NOT_FOUND", HttpStatus.NOT_FOUND);
		}else {
			users.setRole(userDetails.getRole());
			users.setName(userDetails.getName());
			mongoTemplate.save(users);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
		
	} 
	
}

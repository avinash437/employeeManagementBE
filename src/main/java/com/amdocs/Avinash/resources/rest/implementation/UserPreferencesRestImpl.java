package com.amdocs.Avinash.resources.rest.implementation;

import java.util.List;

import javax.validation.Valid;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.Avinash.mongo.repository.UserPreferenceRespository;
import com.amdocs.Avinash.resources.models.UserPreferences;
import com.amdocs.Avinash.resources.rest.interfaces.UserPreferencesRest;

@RestController
public class UserPreferencesRestImpl implements UserPreferencesRest {

	@Autowired
	UserPreferenceRespository userPreferenceRespository;

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public ResponseEntity<List<UserPreferences>> getPreference() {
		List<UserPreferences> preferenceList = getPreferences();
		return new ResponseEntity<List<UserPreferences>>(preferenceList, HttpStatus.OK);
	}

	public List<UserPreferences> getPreferences() {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(MDC.get("parentId").toString().toLowerCase()));
		List<UserPreferences> preferenceList = mongoTemplate.find(query, UserPreferences.class);
		return preferenceList;
	}

	@Override
	public ResponseEntity<Void> postPreference(String contentType, @Valid UserPreferences userPreferences) {
		List<UserPreferences> preferenceList = getPreferences();
		if (preferenceList.isEmpty()) {
			userPreferenceRespository.insert(userPreferences);
		} else {
			UserPreferences tempPreference = preferenceList.get(0);
			tempPreference.setSprintStartDate(userPreferences.getSprintStartDate());
			userPreferenceRespository.save(tempPreference);

		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

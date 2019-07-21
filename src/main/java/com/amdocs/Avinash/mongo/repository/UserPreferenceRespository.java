package com.amdocs.Avinash.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.amdocs.Avinash.resources.models.UserPreferences;

public interface UserPreferenceRespository extends MongoRepository<UserPreferences, String> {

}

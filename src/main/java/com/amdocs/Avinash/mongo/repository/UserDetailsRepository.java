package com.amdocs.Avinash.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.amdocs.Avinash.resources.models.UserDetails;

public interface UserDetailsRepository extends MongoRepository<UserDetails, String> {

//	public UserDetails findByUsernamePassword(@Param("username") String userName,@Param("password") String password);
}

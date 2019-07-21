package com.amdocs.Avinash.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.amdocs.Avinash.resources.models.Lead;

public interface LeadRepository  extends MongoRepository<Lead, String> {

}

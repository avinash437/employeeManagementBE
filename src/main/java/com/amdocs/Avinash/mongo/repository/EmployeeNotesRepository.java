package com.amdocs.Avinash.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.amdocs.Avinash.resources.models.EmployeeNotes;

public interface EmployeeNotesRepository  extends MongoRepository<EmployeeNotes, String> {

}

package com.amdocs.Avinash.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.amdocs.Avinash.resources.models.Vacations;

public interface VacationRepository extends MongoRepository<Vacations, String> {

}

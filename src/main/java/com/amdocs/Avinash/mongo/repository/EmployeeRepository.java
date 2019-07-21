package com.amdocs.Avinash.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.amdocs.Avinash.resources.models.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

}

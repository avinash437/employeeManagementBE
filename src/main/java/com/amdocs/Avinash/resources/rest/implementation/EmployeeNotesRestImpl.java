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

import com.amdocs.Avinash.mongo.repository.EmployeeNotesRepository;
import com.amdocs.Avinash.mongo.repository.EmployeeRepository;
import com.amdocs.Avinash.resources.models.Employee;
import com.amdocs.Avinash.resources.models.EmployeeNotes;
import com.amdocs.Avinash.resources.rest.interfaces.EmployeeNotesRest;

@RestController
public class EmployeeNotesRestImpl implements EmployeeNotesRest {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeNotesRepository employeeNotesRepository;
	@Autowired
	MongoTemplate mongoTemplate;
	@Override
	public ResponseEntity<List<EmployeeNotes>> getAllEmployeeNotes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<EmployeeNotes>> getEmployeeNotes(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> addNote(String contentType, @Valid EmployeeNotes employeeNotes) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(employeeNotes.getUsername()));
		List<Employee> employeeList = mongoTemplate.find(query, Employee.class);
		employeeNotes.setName(employeeList.get(0).getName());
		employeeNotes.setParentId(MDC.get("parentId").toString());
		employeeNotesRepository.insert(employeeNotes);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

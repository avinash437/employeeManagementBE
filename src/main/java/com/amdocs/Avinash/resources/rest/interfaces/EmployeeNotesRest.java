package com.amdocs.Avinash.resources.rest.interfaces;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amdocs.Avinash.resources.models.Employee;
import com.amdocs.Avinash.resources.models.EmployeeNotes;

public interface EmployeeNotesRest {

	@RequestMapping(value = "/notes", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeNotes>> getAllEmployeeNotes();

	@RequestMapping(value = "/notes/{username}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeNotes>> getEmployeeNotes(@PathVariable("username") String username);

	@RequestMapping(value = "/notes/add", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Void> addNote(
			@RequestHeader(value = "Content-Type", required = true) String contentType,
			@Valid @RequestBody EmployeeNotes employeeNotes);
}

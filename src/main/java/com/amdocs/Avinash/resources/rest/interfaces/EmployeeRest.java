package com.amdocs.Avinash.resources.rest.interfaces;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amdocs.Avinash.resources.models.Employee;
import com.amdocs.Avinash.resources.models.Lead;
import com.amdocs.Avinash.resources.models.VacationResponse;
import com.amdocs.Avinash.resources.models.Vacations;

public interface EmployeeRest {

	@RequestMapping(value = "/employees", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getTeamInformation();

	@RequestMapping(value = "/employee/add", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Void> addTeamMember(
			@RequestHeader(value = "Content-Type", required = true) String contentType,
			@Valid @RequestBody Employee employee);

	@RequestMapping(value = "/add/leads", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Void> addLead(@RequestHeader(value = "Content-Type", required = true) String contentType,
			@Valid @RequestBody Lead lead);

	@RequestMapping(value = "/leads", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Lead>> getLeads(
			@RequestHeader(value = "Content-Type", required = true) String contentType);

	@RequestMapping(value = "/employee/update", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Void> updateEmployeeProfile(
			@RequestHeader(value = "Content-Type", required = true) String contentType,
			@Valid @RequestBody Employee employee);

	@RequestMapping(value = "/employee/add/vacations", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Void> addVacation(@RequestHeader(value = "Content-Type", required = true) String contentType,
			@Valid @RequestBody Vacations vacations);

	@RequestMapping(value = "/employee/vacations", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<VacationResponse>> listAllEmployeesVacation();

}

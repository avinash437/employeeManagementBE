package com.amdocs.Avinash.resources.rest.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.validation.Valid;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.Avinash.common.util.CommonUtils;
import com.amdocs.Avinash.common.util.Constants;
import com.amdocs.Avinash.common.util.SendMailUtility;
import com.amdocs.Avinash.exceptions.model.ApplicationException;
import com.amdocs.Avinash.mongo.repository.EmployeeRepository;
import com.amdocs.Avinash.mongo.repository.LeadRepository;
import com.amdocs.Avinash.mongo.repository.VacationRepository;
import com.amdocs.Avinash.resources.models.Employee;
import com.amdocs.Avinash.resources.models.Lead;
import com.amdocs.Avinash.resources.models.VacationInner;
import com.amdocs.Avinash.resources.models.VacationResponse;
import com.amdocs.Avinash.resources.models.Vacations;
import com.amdocs.Avinash.resources.rest.interfaces.EmployeeRest;

@RestController
public class EmployeeRestImpl implements EmployeeRest {

	@Autowired
	private SendMailUtility sendMailUtility;
	@Autowired
	private VacationRepository vacationRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	private LeadRepository leadRepository;

	@Override
	public ResponseEntity<List<Employee>> getTeamInformation() {
		Query query = new Query();
		query.addCriteria(Criteria.where("parentId").is(MDC.get("parentId").toString()));
		List<Employee> employeeList = mongoTemplate.find(query, Employee.class);
		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> addTeamMember(String contentType, @Valid Employee employee) {
		employee.setParentId(MDC.get("parentId").toString());
		employeeRepository.insert(employee);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Void> addLead(String contentType, @Valid Lead lead) {
		lead.setParentId(MDC.get("parentId").toString());
		leadRepository.insert(lead);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<List<Lead>> getLeads(String contentType) {

		List<Lead> list = leadRepository.findAll();
		return new ResponseEntity<List<Lead>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> updateEmployeeProfile(String contentType, @Valid Employee employee) {
		Employee fetchedEmployee = fetchEmployee(employee);

		if (null == fetchedEmployee) {
			throw new ApplicationException("Not Found", "NOT_FOUND", HttpStatus.NOT_FOUND);
		} else {
			fetchedEmployee.setName(employee.getName());
			fetchedEmployee.setProject(employee.getProject());
			fetchedEmployee.setManager(employee.getManager());
			fetchedEmployee.setExperience(employee.getExperience());
			fetchedEmployee.setContact(employee.getContact());
			mongoTemplate.save(fetchedEmployee);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
	}

	public Employee fetchEmployee(Employee employee) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(employee.getUsername()));
		List<Employee> employeeList = mongoTemplate.find(query, Employee.class);
		if (employeeList.isEmpty()) {
			return null;
		}
		return employeeList.get(0);
	}
	
	
	@Override
	public ResponseEntity<List<VacationResponse>> listAllEmployeesVacation() {
		// TODO Auto-generated method stub

		List<VacationResponse> vacationResponseList = new ArrayList<VacationResponse>();
		List<Vacations> vacationsList = vacationRepository.findAll();
		HashMap<String, List<Vacations>> hm = new HashMap<String, List<Vacations>>();
		for (int counter = 0; counter < vacationsList.size(); counter++) {
			Vacations temp = vacationsList.get(counter);
			String month = Constants.monthName[temp.getFrom().getMonth()];
			if (null == hm.get(month)) {
				ArrayList list = new ArrayList<Vacations>();
				list.add(temp);
				hm.put(Constants.monthName[temp.getFrom().getMonth()], list);
			} else {
				List<Vacations> list = hm.get(month);
				list.add(temp);
				hm.put(Constants.monthName[temp.getFrom().getMonth()], list);
			}
		}

		for (Entry<String, List<Vacations>> entry : hm.entrySet()) {

			VacationResponse vacationResponse = new VacationResponse();
			vacationResponse.setMonth(entry.getKey());
			List<VacationInner> vacationInnerList = new ArrayList<VacationInner>();
			for (Vacations vac : entry.getValue()) {
				VacationInner vaccInner = new VacationInner();
				vaccInner.setFrom(CommonUtils.getDateFormat(vac.getFrom()));
				vaccInner.setTo(CommonUtils.getDateFormat(vac.getTo()));
				vaccInner.setName(vac.getName());
				vacationInnerList.add(vaccInner);
			}
			vacationResponse.setVacationList(vacationInnerList.toArray(new VacationInner[0]));
			vacationResponseList.add(vacationResponse);
		}

		return new ResponseEntity<List<VacationResponse>>(vacationResponseList, HttpStatus.OK);
	}

	
	@Override
	public ResponseEntity<Void> addVacation(String contentType, @Valid Vacations vacation) {
		
		vacation.setParentId(MDC.get("parentId").toString());
		vacationRepository.insert(vacation);
		sendMailUtility.sendMail("Vacation added for "+ vacation.getName() , ""
				+ "This is to inform you that " +vacation.getName() + " has added vacation - "
				+ vacation.getFrom() + "- " + vacation.getTo());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

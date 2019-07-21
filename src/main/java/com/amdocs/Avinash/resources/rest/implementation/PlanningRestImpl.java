package com.amdocs.Avinash.resources.rest.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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

import com.amdocs.Avinash.common.util.Constants;
import com.amdocs.Avinash.mongo.repository.UserDetailsRepository;
import com.amdocs.Avinash.mongo.repository.VacationRepository;
import com.amdocs.Avinash.resources.models.Capacity;
import com.amdocs.Avinash.resources.models.Employee;
import com.amdocs.Avinash.resources.models.UserPreferences;
import com.amdocs.Avinash.resources.models.Vacations;
import com.amdocs.Avinash.resources.rest.interfaces.PlanningRest;

@RestController
public class PlanningRestImpl implements PlanningRest {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private VacationRepository vacationRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public ResponseEntity<List<Capacity>> getCapacity(String contentType) {

		List<UserPreferences> preferenceList = getPreferences();
		UserPreferences userPreferences = preferenceList.get(0);
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
		List<Capacity> capacityList = new ArrayList<Capacity>();
		Date sprintStartDate = userPreferences.getSprintStartDate();
		int sprintNumber = userPreferences.getSprintNumber();
		for (int counter = 0; counter < userPreferences.getDisplayList(); counter++) {
			Date sprintEndDate = addDays(sprintStartDate, 13);
			List<Vacations> capacityVacationlist = new ArrayList<Vacations>();
			Capacity capacity = new Capacity();
			int totalCapacity = 0;
			try {
//			Date sprintStartDate = new SimpleDateFormat(Constants.FORMATTER).parse(Constants.SPRINT_START);

				Query query = new Query();
				query.addCriteria(Criteria.where("parentId").is(MDC.get("parentId").toString().toLowerCase()));
				List<Employee> employeeList = mongoTemplate.find(query, Employee.class);

				for (Iterator iterator = employeeList.iterator(); iterator.hasNext();) {
					Employee employee = (Employee) iterator.next();
					int tempCapacity = 0;

					capacity.getEmployeeList().add(employee.getName());
					System.out.println(employee.getName());
					query = new Query();
					query.addCriteria(Criteria.where("username").is(employee.getUsername()));
					List<Vacations> vacationlist = mongoTemplate.find(query, Vacations.class);
					for (Iterator iterator2 = vacationlist.iterator(); iterator2.hasNext();) {
						Vacations vacations = (Vacations) iterator2.next();
						if ((dateLies(sprintStartDate, sprintEndDate, vacations.getFrom()))
								|| (dateLies(sprintStartDate, sprintEndDate, vacations.getTo()))) { 

							tempCapacity += calculateCapacity(formatter.format(vacations.getFrom()),
									formatter.format(vacations.getTo()), sprintStartDate);
							capacityVacationlist.add(vacations);
						}
					}

					if (tempCapacity == 0) {
						totalCapacity += 10;
					} else {
						totalCapacity += tempCapacity;
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			capacity.setCapacity(String.valueOf(totalCapacity));
			capacity.setSprintNumber(sprintNumber);
			capacity.setSprintStartDate(formatter1.format(sprintStartDate));
			capacity.setSprintEndDate(formatter1.format(sprintEndDate));
			capacity.setScrumTeamName(userPreferences.getScrumTeamName());
			capacity.setVacationlist(capacityVacationlist);
			capacityList.add(capacity);
			sprintNumber++;
			sprintStartDate = addDays(sprintEndDate, 1);
		}
		return new ResponseEntity<List<Capacity>>(capacityList, HttpStatus.OK);
	}

	public Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}

	public int calculateCapacity(String vacationStart, String vacationEnd, Date sprintStartDate) throws ParseException {

		System.out.println(" Sprint Start Date  ==>" + sprintStartDate);
		boolean overlapped = false;
		int overlappingDays = 0;
		for (int counter = 0; counter < 14; counter++) {

			boolean value = dateLies(new SimpleDateFormat(Constants.FORMATTER).parse(vacationStart),
					new SimpleDateFormat(Constants.FORMATTER).parse(vacationEnd), sprintStartDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sprintStartDate);
			if (value) {
				overlapped = true;
				if (!((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
						|| (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY))) {
					System.out.println(sprintStartDate);
					++overlappingDays;
				}
			}
			sprintStartDate = addDays(sprintStartDate, 1);

		}
		System.out.println(" overlappingDays ==>" + overlappingDays);
		int capacity = 10 - overlappingDays;
		if (!overlapped) {
			capacity = 10;
		}
		System.out.println(" Sprint Endate Date  ==>" + sprintStartDate);
		System.out.println(" overlappingDays ==>" + overlappingDays);
		System.out.println(" Capacity  ==>" + capacity);
		return capacity;
	}

	public boolean dateLies(Date vacationStart, Date vacationend, Date date) {
		return vacationStart.compareTo(date) * date.compareTo(vacationend) >= 0;
	}

	public List<UserPreferences> getPreferences() {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(MDC.get("parentId").toString().toLowerCase()));
		List<UserPreferences> preferenceList = mongoTemplate.find(query, UserPreferences.class);
		return preferenceList;
	}
}

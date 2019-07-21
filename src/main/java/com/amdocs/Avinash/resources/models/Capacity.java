package com.amdocs.Avinash.resources.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Capacity {

	private String capacity;
	private String scrumTeamName;
	private int displayList;
	private int sprintNumber;
	private List<Vacations> vacationlist;
	private String sprintStartDate;
	private String sprintEndDate;
	private List<String> employeeList = new ArrayList<String>();
}

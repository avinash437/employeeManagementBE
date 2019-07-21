package com.amdocs.Avinash.resources.models;

import lombok.Data;

@Data
public class VacationResponse {

	
	private String month;
	private VacationInner[] vacationList;
	
}

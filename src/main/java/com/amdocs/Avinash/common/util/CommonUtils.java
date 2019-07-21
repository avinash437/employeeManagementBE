package com.amdocs.Avinash.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

	
	public static String getDateFormat(Date date) {
		
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");  
	    String strDate= formatter.format(date);  
	    return strDate;  
	}
	
}

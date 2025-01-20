package com.mysecondproject.demo_spring_framework_002.businesscalculation;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service
public class MyDatabaseRunner {
	
	private DataService dataService;
	public MyDatabaseRunner(DataService dataService) {
		this.dataService = dataService;
		
	}
	
	public int findMax() {
		return Arrays.stream(dataService.retriveData()).max().orElse(0);
	}
	
}

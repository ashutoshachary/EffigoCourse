package com.mysecondproject.demo_spring_framework_002.businesscalculation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component
@Repository
public class MySQLDataService implements DataService{

	@Override
	public int[] retriveData() {
		// TODO Auto-generated method stub
		return new int[]{1, 2, 3, 4, 5};
	}
	

}

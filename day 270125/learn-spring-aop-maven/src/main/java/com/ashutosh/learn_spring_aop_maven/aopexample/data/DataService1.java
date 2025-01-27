package com.ashutosh.learn_spring_aop_maven.aopexample.data;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class DataService1 {
	public int[] retriveData() {
		return new int[] {1,2,3,4};
	
	}

}

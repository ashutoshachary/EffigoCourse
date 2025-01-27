package com.ashutosh.learn_spring_aop_maven.aopexample.business;

import java.util.Arrays;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ashutosh.learn_spring_aop_maven.aopexample.data.DataService1;
import com.ashutosh.learn_spring_aop_maven.aopexample.data.DataService2;

@Service
public class BusinessService2 {
	
	private DataService2 dataService2;
	
	public BusinessService2(DataService2 dataService2) {
		this.dataService2 = dataService2;
	}
	
	public  int calculateMin() {
		int[] data = dataService2.retriveData();
		
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//throw new RuntimeException("Something Went Wrong!");
		return Arrays.stream(data).min().orElse(0);
		
	}

}

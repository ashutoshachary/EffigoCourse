package com.mysecondproject.demo_spring_framework_002;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mysecondproject.demo_spring_framework_002.businesscalculation.MyDatabaseRunner;



@Configuration
@ComponentScan("com.mysecondproject.demo_spring_framework_002.businesscalculation")
public class BusinessCalculationService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try(var context = new AnnotationConfigApplicationContext(BusinessCalculationService.class)){
			
			
			System.out.println(context.getBean(MyDatabaseRunner.class).findMax());
			
			
			
		}
		
		

	}

}

package com.ashutosh.learn_spring_aop_maven;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ashutosh.learn_spring_aop_maven.aopexample.business.BusinessService1;
import com.ashutosh.learn_spring_aop_maven.aopexample.business.BusinessService2;



@SpringBootApplication
public class LearnSpringAopMavenApplication  implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private BusinessService1 businessService1;
	private BusinessService2 businessService2;
	
	public LearnSpringAopMavenApplication(BusinessService1 businessService1, BusinessService2 businessService2) {
		this.businessService1 = businessService1;
		this.businessService2 =businessService2;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LearnSpringAopMavenApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Value returned is For Business 1" + businessService1.calculateMax());
		logger.info("Value returned is For Business 2" + businessService2.calculateMin());
		
		
		
	}

}

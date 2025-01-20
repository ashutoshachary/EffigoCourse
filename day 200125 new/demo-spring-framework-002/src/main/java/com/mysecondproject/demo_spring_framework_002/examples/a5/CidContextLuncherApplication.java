package com.mysecondproject.demo_spring_framework_002.examples.a5;

import java.util.Arrays;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.mysecondproject.demo_spring_framework_002.game.GameRunner;
import com.mysecondproject.demo_spring_framework_002.game.GamingConsole;
import com.mysecondproject.demo_spring_framework_002.game.PacmanGame;

import jakarta.inject.Inject;
import jakarta.inject.Named;



//@Component
@Named
class BusinessService{
	private DataService dataService;
	
	//@Autowired
	@Inject
	public void setDataService(DataService dataService) {
		System.out.println("Setter Injection");
		this.dataService = dataService;
	}
	
	
	public DataService getDataService() {
		
		return dataService;
	}
	
	
}

@Component
class DataService{
	
}
@Configuration
@ComponentScan
public class CidContextLuncherApplication {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(var context = new AnnotationConfigApplicationContext(CidContextLuncherApplication.class)){
			
			Arrays.stream(context.getBeanDefinitionNames())
			.forEach(System.out::println);
			
			System.out.println(context.getBean(BusinessService.class).getDataService());
			
		}

	}

}

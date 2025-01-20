package com.mysecondproject.demo_spring_framework_002.examples.a1;

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

@Component
class YourBusinessClss{
	
	
	Dependency1 dependency1;
	
	Dependency2 dependency2;
	
    @Autowired
	public YourBusinessClss(Dependency1 dependency1, Dependency2 dependency2) {
		super();
		System.out.println("Cunstructer injection");
		this.dependency1 = dependency1;
		this.dependency2 = dependency2;
	}


//	@Autowired
//	public Dependency1 getDependency1() {
//		return dependency1;
//	}

//
//	@Autowired
//	public void setDependency1(Dependency1 dependency1) {
//		System.out.println("Set Dependency - 1");
//		this.dependency1 = dependency1;
//	}


//	@Autowired
//	public Dependency2 getDependency2() {
//		return dependency2;
//	}


//	@Autowired
//	public void setDependency2(Dependency2 dependency2) {
//		System.out.println("Set Dependency - 2");
//		this.dependency2 = dependency2;
//	}



	public String toString()
	{
		return "Using " + dependency1 + " and " + dependency2;
	}
	
}

@Component
class Dependency1{
	
}

@Component
class Dependency2{
	
}




@Configuration
@ComponentScan
public class DepInjectionLuncherApplication {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(var context = new AnnotationConfigApplicationContext(DepInjectionLuncherApplication.class)){
			
			Arrays.stream(context.getBeanDefinitionNames())
			.forEach(System.out::println);
			
			
			System.out.println(context.getBean(YourBusinessClss.class));
			
		}

	}

}

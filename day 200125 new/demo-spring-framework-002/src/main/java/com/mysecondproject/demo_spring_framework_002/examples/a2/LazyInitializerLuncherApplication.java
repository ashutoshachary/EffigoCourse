package com.mysecondproject.demo_spring_framework_002.examples.a2;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.mysecondproject.demo_spring_framework_002.game.GameRunner;
import com.mysecondproject.demo_spring_framework_002.game.GamingConsole;
import com.mysecondproject.demo_spring_framework_002.game.PacmanGame;



@Component
class ClassA{
	
}

@Component
@Lazy
class ClassB{
	private ClassA classA;
	public ClassB(ClassA classA) {
		System.out.println("Some Initalization logic");
		this.classA = classA;
	}
	
	public void doSomething() {
		System.out.println("Do something");
	}
	
}


@Configuration
@ComponentScan
public class LazyInitializerLuncherApplication {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(var context = new AnnotationConfigApplicationContext(LazyInitializerLuncherApplication.class)){
			
			System.out.println("Initailization of the context completed");
			
			context.getBean(ClassB.class);
			
			
			
		}

	}

}

package com.myfirstproject.demo_spring_framework.helloworld;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.myfirstproject.demo_spring_framework.game.GameRunner;
import com.myfirstproject.demo_spring_framework.game.MarioGame;
import com.myfirstproject.demo_spring_framework.game.PacmanGame;
import com.myfirstproject.demo_spring_framework.game.SuperContraGame;

public class App02HelloWorldSpring {

	public static void main(String[] args) {
		
		// Lunch a spring context
		// configure the things that we want Spring to manage
		
		try(var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)){
			
			System.out.println(context.getBean("name"));
			System.out.println(context.getBean("age"));
			System.out.println(context.getBean("person"));
			System.out.println(context.getBean("person2MethodCall"));
			System.out.println(context.getBean("person3Parameter"));
			System.out.println(context.getBean("address2"));
			
			System.out.println(context.getBean(Address.class));
			System.out.println(context.getBean(Person.class));
			
		}
		
		
		
		
		

	}

}

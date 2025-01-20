package com.mysecondproject.demo_spring_framework_002.examples.a0;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mysecondproject.demo_spring_framework_002.game.GameRunner;
import com.mysecondproject.demo_spring_framework_002.game.GamingConsole;
import com.mysecondproject.demo_spring_framework_002.game.PacmanGame;


@Configuration
@ComponentScan
public class DepInjectionLuncherApplication {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(var context = new AnnotationConfigApplicationContext(DepInjectionLuncherApplication.class)){
			
			Arrays.stream(context.getBeanDefinitionNames())
			.forEach(System.out::println);
			
		}

	}

}

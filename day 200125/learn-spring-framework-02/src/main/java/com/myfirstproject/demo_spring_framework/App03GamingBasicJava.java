package com.myfirstproject.demo_spring_framework;

import com.myfirstproject.demo_spring_framework.game.GameRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.myfirstproject.demo_spring_framework.game.MarioGame;
import com.myfirstproject.demo_spring_framework.game.PacmanGame;
import com.myfirstproject.demo_spring_framework.game.SuperContraGame;
import com.myfirstproject.demo_spring_framework.game.GamingConsole;

public class App03GamingBasicJava {

	public static void main(String[] args) {
		
		try(var context = new AnnotationConfigApplicationContext(GamingConfiguration.class)){
			context.getBean(GamingConsole.class).up();
			context.getBean(GamingConsole.class).run();
		}

	}

}

package com.myfirstproject.demo_spring_framework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myfirstproject.demo_spring_framework.game.GameRunner;
import com.myfirstproject.demo_spring_framework.game.GamingConsole;
import com.myfirstproject.demo_spring_framework.game.PacmanGame;


@Configuration
public class GamingConfiguration {
	
	
	@Bean
	public GamingConsole game() {
		var game = new PacmanGame();
		return game;
	}
	
	@Bean
	public GameRunner gameRunner(GamingConsole game) {
		 
		return new GameRunner(game);
	}
	
}

package com.mysecondproject.demo_spring_framework_002.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
	
	private GamingConsole game;
	public GameRunner(@Qualifier("SuperContraGameQualifier") GamingConsole game) {
		this.game = game;
		
	}
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Running game" + game);
		game.up();
		game.down();
		game.left();
		game.right();
	}
		
	

}

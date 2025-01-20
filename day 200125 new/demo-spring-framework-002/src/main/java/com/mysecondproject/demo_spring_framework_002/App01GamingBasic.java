package com.mysecondproject.demo_spring_framework_002;

import com.mysecondproject.demo_spring_framework_002.game.GameRunner;
import com.mysecondproject.demo_spring_framework_002.game.PacmanGame;

public class App01GamingBasic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 var game = new PacmanGame(); // 1: Object Creation
			var gameRunner = new GameRunner(game); // 2. Wiring of dependencies
			// Game is a dependency of GameRunner
			
			
			gameRunner.run();

	}

}

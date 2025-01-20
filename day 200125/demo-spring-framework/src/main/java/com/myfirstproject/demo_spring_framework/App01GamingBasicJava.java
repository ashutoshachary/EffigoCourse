package com.myfirstproject.demo_spring_framework;

import com.myfirstproject.demo_spring_framework.game.GameRunner;
import com.myfirstproject.demo_spring_framework.game.MarioGame;
import com.myfirstproject.demo_spring_framework.game.PacmanGame;
import com.myfirstproject.demo_spring_framework.game.SuperContraGame;

public class App01GamingBasicJava {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 //var game = new MarioGame();
		//var game = new SuperContraGame();
		 var game = new PacmanGame(); // 1: Object Creation
		var gameRunner = new GameRunner(game); // 2. Wiring of dependencies
		// Game is a dependency of GameRunner
		
		
		gameRunner.run();
		

	}

}

package com.Reversi;

import java.util.Scanner;

public class Reversi {
	GameBoardGraph game;
	
	public void playReversiConsole()
	{
		game = new GameBoardGraph();
		game.printBoard();
		prompt();

		
		
	}
	
	public void prompt()
	{
		Scanner type = new Scanner(System.in);
		
		System.out.println("\nWhere would you like to place a piece?");
		String input = type.next();
		boolean result = game.placePiece(input, 1);
		if(!result)
		{
			prompt();
		}
		turnAI();
	}
	
	public void turnAI()
	{
		int Random = (int) Math.ceil((Math.random()*2));
		switch(Random)
		{
		case 1:
			turnAI_1();
			break;
		case 2:
			turnAI_2();
			break;
		default:
			turnAI_1();
			break;
		}
		prompt();
	}
	
	public void turnAI_1()
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				
			}
		}
	}
	
	public void turnAI_2()
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				
			}
		}
	}
	

}

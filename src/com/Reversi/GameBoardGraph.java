package com.Reversi;

import java.util.ArrayList;
import java.util.HashMap;

public class GameBoardGraph {
	HashMap<String, PieceVertex> GameBoard = new HashMap<String, PieceVertex>();
	int moves = 0;
	//ArrayList<PieceVertex> GameBoard = new ArrayList<PieceVertex>();
	
	public GameBoardGraph()
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				GameBoard.put(getAlgebra(i,j), new PieceVertex());
			}
		}
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				PieceVertex addEdges = GameBoard.get(getAlgebra(i,j));
				PieceVertex up_Edge = GameBoard.get(getAlgebra(i+1,j));
				PieceVertex up_rightEdge = GameBoard.get(getAlgebra(i+1,j+1));
				PieceVertex rightEdge = GameBoard.get(getAlgebra(i,j+1));
				PieceVertex down_rightEdge = GameBoard.get(getAlgebra(i-1,j+1));
				PieceVertex downEdge = GameBoard.get(getAlgebra(i-1,j));
				PieceVertex down_leftEdge = GameBoard.get(getAlgebra(i-1,j-1));
				PieceVertex leftEdge = GameBoard.get(getAlgebra(i,j-1));
				PieceVertex up_leftEdge = GameBoard.get(getAlgebra(i+1,j-1));
				addEdges.up=up_Edge;
				addEdges.up_right=up_rightEdge;
				addEdges.right=rightEdge;
				addEdges.down_right=down_rightEdge;
				addEdges.down=downEdge;
				addEdges.down_left=down_leftEdge;
				addEdges.left=leftEdge;
				addEdges.up_left=up_leftEdge;

			}
		}
	}

	public boolean placePiece(String algebraNotation, int a)
	{
		if(GameBoard.containsKey(algebraNotation.toLowerCase()))
		{
			if(!validMove(algebraNotation, a))
			{
				return false;
			}else if((GameBoard.get(algebraNotation).type=="-"))
			{
				
				GameBoard.get(algebraNotation).makeBlack();
				printBoard();
				return true;
			}else
			{
				System.out.println("A piece is already there. Try again.");
				return false;
			}
		}else{
			System.out.println("Invalid coordinate. Try again.");
			return false;
		}
		//Check if it changes any pieces
	}

	public void printBoard()
	{
		System.out.println(" abcdefgh"); 
		for(int i = 7; i >= 0; i--)
		{
			System.out.print(i+1);
			
			for(int j = 0; j < 8; j++)
			{
				System.out.print(GameBoard.get(getAlgebra(i,j)).type);
			}
			System.out.print(i+1);
			System.out.println(); 
		}
		System.out.println(" abcdefgh");
		System.out.println();
	}

	public void Initialize()
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				GameBoard.put(getAlgebra(i,j), new PieceVertex());
			}
		}
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				PieceVertex addEdges = GameBoard.get(getAlgebra(i,j));
				PieceVertex up_Edge = GameBoard.get(getAlgebra(i+1,j));
				PieceVertex up_rightEdge = GameBoard.get(getAlgebra(i+1,j+1));
				PieceVertex rightEdge = GameBoard.get(getAlgebra(i,j+1));
				PieceVertex down_rightEdge = GameBoard.get(getAlgebra(i-1,j+1));
				PieceVertex downEdge = GameBoard.get(getAlgebra(i-1,j));
				PieceVertex down_leftEdge = GameBoard.get(getAlgebra(i-1,j-1));
				PieceVertex leftEdge = GameBoard.get(getAlgebra(i,j-1));
				PieceVertex up_leftEdge = GameBoard.get(getAlgebra(i+1,j-1));
				addEdges.up=up_Edge;
				addEdges.up_right=up_rightEdge;
				addEdges.right=rightEdge;
				addEdges.down_right=down_rightEdge;
				addEdges.down=downEdge;
				addEdges.down_left=down_leftEdge;
				addEdges.left=leftEdge;
				addEdges.up_left=up_leftEdge;

			}
		}
	}
	
	public String getAlgebra(int a, int b)
	{
		String output;
		String algebra = intToAlgebra(a);
		int nonZero = b+1;
		if((algebra=="")||(b>7)||(b<0)) //intToAlgebra will determine for us if either integer is out of bounds.
		{
			return null;
		}
		return algebra+nonZero;
	}
	
 	public int algebraToInt(String algebra)
	{
		switch(algebra)
		{
		case "a":
			return 0;
		case "b":
			return 1;
		case "c":
			return 2;
		case "d":
			return 3;
		case "e":
			return 4;
		case "f":
			return 5;
		case "g":
			return 6;
		case "h":
			return 7;
		default:
			return -1;
		}
	}

	public String intToAlgebra(int algebra)
	{
		switch(algebra)
		{
		case 0:
			return "a";
		case 1:
			return "b";
		case 2:
			return "c";
		case 3:
			return "d";
		case 4:
			return "e";
		case 5:
			return "f";
		case 6:
			return "g";
		case 7:
			return "h";
		default:
			return "";
		}
	}

	public boolean validMove(String algebraNotation, int type)
	{
		PieceVertex spaceGiven = GameBoard.get(algebraNotation);

		if(!GameBoard.containsKey(algebraNotation))
		{
			System.out.println("Space given is not within the board.");
			return false;
		}
		if((moves<4)&&((algebraNotation!="d5")||(algebraNotation!="d4")||(algebraNotation!="e5")||(algebraNotation!="e4")))
		{
			System.out.println("Initial moves must be in the center four spaces");
			return false;
		}
		if(!moveChecker(algebraNotation, type))
		{
			return false;
		}
		return true;
	}
	
	public boolean moveChecker(String algebraNotation, int type)
	{
		
		String checker = type(type);
		PieceVertex starting = GameBoard.get(algebraNotation);
		PieceVertex cursor = GameBoard.get(algebraNotation);
		//Left
		for(int i = 0; i < 8;)
		{
			cursor=cursor.left;
			if(cursor.type==starting.type) //
			{
				if(i>0)
				{
					return true; //All moves up to this point have been legal, and this is only reachable by the endpoint of a legal move.
				}
				break; //Not a legal move in this direction. Break, go to next check statement.
			}else if((cursor.type=="-"))
			{
				break; //left isn't legal, but not necessarily illegal.
			}else if((cursor.type!=starting.type)) //If it isn't different, then it isn't necessarily legal.
			{
				i++; //If and only if the type is opposite
			}
		}
		//Right
		for(int i = 0; i < 8;)
		{
			cursor=cursor.right;
			if(cursor.type==starting.type) //
			{
				if(i>0)
				{
					return true; //All moves up to this point have been legal, and this is only reachable by the endpoint of a legal move.
				}
				break; //Not a legal move in this direction. Break, go to next check statement.
			}else if((cursor.type=="-"))
			{
				break; //left isn't legal, but not necessarily illegal.
			}else if((cursor.type!=starting.type)) //If it isn't different, then it isn't necessarily legal.
			{
				i++; //If and only if the type is opposite
			}
		}
		//Up
		for(int i = 0; i < 8;)
		{
			cursor=cursor.up;
			if(cursor.type==starting.type) //
			{
				if(i>0)
				{
					return true; //All moves up to this point have been legal, and this is only reachable by the endpoint of a legal move.
				}
				break; //Not a legal move in this direction. Break, go to next check statement.
			}else if((cursor.type=="-"))
			{
				break; //left isn't legal, but not necessarily illegal.
			}else if((cursor.type!=starting.type)) //If it isn't different, then it isn't necessarily legal.
			{
				i++; //If and only if the type is opposite
			}
		}
		//Down
		for(int i = 0; i < 8;)
		{
			cursor=cursor.down;
			if(cursor.type==starting.type) //
			{
				if(i>0)
				{
					return true; //All moves up to this point have been legal, and this is only reachable by the endpoint of a legal move.
				}
				break; //Not a legal move in this direction. Break, go to next check statement.
			}else if((cursor.type=="-"))
			{
				break; //left isn't legal, but not necessarily illegal.
			}else if((cursor.type!=starting.type)) //If it isn't different, then it isn't necessarily legal.
			{
				i++; //If and only if the type is opposite
			}
		}
		//Up-Right
		for(int i = 0; i < 8;)
		{
			cursor=cursor.up_right;
			if(cursor.type==starting.type) //
			{
				if(i>0)
				{
					return true; //All moves up to this point have been legal, and this is only reachable by the endpoint of a legal move.
				}
				break; //Not a legal move in this direction. Break, go to next check statement.
			}else if((cursor.type=="-"))
			{
				break; //left isn't legal, but not necessarily illegal.
			}else if((cursor.type!=starting.type)) //If it isn't different, then it isn't necessarily legal.
			{
				i++; //If and only if the type is opposite
			}
		}
		//Down-Right
		for(int i = 0; i < 8;)
		{
			cursor=cursor.down_right;
			if(cursor.type==starting.type) //
			{
				if(i>0)
				{
					return true; //All moves up to this point have been legal, and this is only reachable by the endpoint of a legal move.
				}
				break; //Not a legal move in this direction. Break, go to next check statement.
			}else if((cursor.type=="-"))
			{
				break; //left isn't legal, but not necessarily illegal.
			}else if((cursor.type!=starting.type)) //If it isn't different, then it isn't necessarily legal.
			{
				i++; //If and only if the type is opposite
			}
		}
		//Down-Left
		for(int i = 0; i < 8;)
		{
			cursor=cursor.down_left;
			if(cursor.type==starting.type) //
			{
				if(i>0)
				{
					return true; //All moves up to this point have been legal, and this is only reachable by the endpoint of a legal move.
				}
				break; //Not a legal move in this direction. Break, go to next check statement.
			}else if((cursor.type=="-"))
			{
				break; //left isn't legal, but not necessarily illegal.
			}else if((cursor.type!=starting.type)) //If it isn't different, then it isn't necessarily legal.
			{
				i++; //If and only if the type is opposite
			}
		}
		//Up-Left
		for(int i = 0; i < 8;)
		{
			cursor=cursor.up_left;
			if(cursor.type==starting.type) //
			{
				if(i>0)
				{
					return true; //All moves up to this point have been legal, and this is only reachable by the endpoint of a legal move.
				}
				break; //Not a legal move in this direction. Break, go to next check statement.
			}else if((cursor.type=="-"))
			{
				break; //left isn't legal, but not necessarily illegal.
			}else if((cursor.type!=starting.type)) //If it isn't different, then it isn't necessarily legal.
			{
				i++; //If and only if the type is opposite
			}
		}
		return false;
	}
	
	String type(int a)
	{
		switch(a)
		{
		case 0:
			return "-";
		case 1:
			return "B";
		case 2:
			return "W";
		default:
			return "";
		}
	}
}

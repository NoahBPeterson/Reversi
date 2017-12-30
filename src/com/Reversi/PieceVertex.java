package com.Reversi;

public class PieceVertex {
	//Graph Edges
	PieceVertex up;
	PieceVertex up_right;
	PieceVertex right;
	PieceVertex down_right;
	PieceVertex down;
	PieceVertex down_left;
	PieceVertex left;
	PieceVertex up_left;
	
	//Is this Empty/Black/White?
	String type; //0/1/2
	
	PieceVertex()
	{
		this.type="-";
	}
	
	public void makeBlack()
	{
		this.type="B";
	}
	
	public void makeWhite()
	{
		this.type="W";
	}

}

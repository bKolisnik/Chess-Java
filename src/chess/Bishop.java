package chess;

import java.awt.Graphics;

public class Bishop extends Piece {
	
	

	public Bishop(String colour) {
		super(colour);
		
	}

	
	
	
	
	
}

//Recursively check every box in each possible direction until piece of own type or board ends or enemy tile. 
//Or check if proposed location is possble distance from start point given x and y vectors
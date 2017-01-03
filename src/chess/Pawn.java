package chess;

import java.awt.Graphics;

public class Pawn extends Piece {
	
	private boolean m_hadFirstMove = false;

	public Pawn(String colour) {
		super(colour);
		
	}

	public boolean hadFirstMove(){
		return m_hadFirstMove;
	}
	
	//make getters and setters for the boolean
	public void completedFirstMove(){
		m_hadFirstMove = true;
	}
	
}

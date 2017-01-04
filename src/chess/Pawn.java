package chess;

import java.awt.Graphics;

public class Pawn extends Piece {
	
	private boolean m_hadFirstMove = false;
	private boolean m_justMovedTwo = false;

	public Pawn(String colour) {
		super(colour);
		
	}

	public boolean hadFirstMove(){
		return m_hadFirstMove;
	}
	
	public boolean justMovedTwo(){
		return m_justMovedTwo;
	}
	
	
	
	//make getters and setters for the boolean
	public void completedFirstMove(){
		m_hadFirstMove = true;
	}
	
	public void setMovedTwo(boolean movedTwo){
		m_justMovedTwo = movedTwo;
	}
	
}

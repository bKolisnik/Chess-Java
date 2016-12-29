package chess;

import java.awt.Graphics;

public abstract class Piece {

	String m_colour;
	
	public Piece(String colour){
		
		m_colour = colour;
		
	}
	
	//Override rendering methods in subclasses with different images
	

	public abstract void paint(Graphics g, int x, int y);

	
}

package chess;

import java.awt.Color;
import java.awt.Graphics;

public class Box {
	
	Piece m_piece;
	int m_colour;
	private int m_x;
	private int m_y;
	
	
	
	public Box(Piece piece, int colour, int x, int y){
		m_piece = piece;
		m_colour = colour;
		m_x = x;
		m_y = y;
	}
	
	
	public void paint(Graphics g){
		if(m_colour==0){
			g.setColor(Color.black);
			g.fillRect(m_x, m_y, 32, 32);
			m_piece.paint(g, m_x, m_y);
			
		}
		else{
			g.setColor(Color.white);
			g.fillRect(m_x, m_y, 32, 32);
			m_piece.paint(g, m_x, m_y);
		}
			
	}
	
	
}

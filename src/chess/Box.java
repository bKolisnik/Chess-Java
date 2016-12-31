package chess;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Box {
	
	Piece m_piece;
	int m_colour;
	private int m_row;
	private int m_column;
	private JPanel m_window;
	
	
	public Box(Piece piece, int colour, int row, int column,JPanel window){
		m_piece = piece;
		m_colour = colour;
		m_row = row;
		m_column = column;
		m_window = window;
	}
	
	
	public void paint(Graphics g){
		
		if(m_colour==0){
			g.setColor(Color.black);
			g.fillRect(m_column, m_row, 50, 50);
			
			if(m_piece!= null&&m_piece.getImage()!=null){
				g.drawImage(m_piece.getImage(),m_column, m_row, m_window);
				System.out.println(m_row + " " + m_column);
			}
			
		}
		else{
			g.setColor(Color.white);
			g.fillRect(m_column, m_row, 50, 50);
			if(m_piece!= null&&m_piece.getImage()!=null){
				g.drawImage(m_piece.getImage(), m_column, m_row, m_window);
				System.out.println(m_row + " " + m_column);
			}
		}
			
	}
	
	
	
}

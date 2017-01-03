package chess;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

public abstract class Piece {
	
	private BufferedImage m_pieceImg;

	private String m_colour;
	
	public Piece(String colour){
		
		m_colour = colour;
		
	}
	
	//Override rendering methods in subclasses with different images
	public void setImage(BufferedImage img){
		this.m_pieceImg = img;
	}
	
	public BufferedImage getImage(){
		return this.m_pieceImg;
	}

	//Render all pieces in jpanel section by using g.drawImage(the piece image, x,y,with,height,jpanel)

	public String getColour(){
		return m_colour;
		
	}
}

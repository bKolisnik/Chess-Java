package chess;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Window extends JPanel {

	private ChessBoard m_chessBoard;
	public Window(){
		
	}
	
	public void setChessBoard(ChessBoard cb){
		m_chessBoard = cb;
	}
	
	//paint or paintcomponent
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		
		if(m_chessBoard!=null){
			for (int i = 0; i < 8; i++){
				for (int j = 0; j<8;j++){
					
					m_chessBoard.m_boxes[i][j].paint(g);
					
				}
			}
			
					
			
		}
	}
}

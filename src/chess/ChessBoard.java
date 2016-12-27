package chess;

import java.util.LinkedList;


public class ChessBoard {
	
	Box[][] m_boxes;
	private Boolean m_rowEven;
	private LinkedList<Piece> m_removedPieces = new LinkedList<Piece>();
	
	//0 for black, 1 for white
	
	public ChessBoard(){
		
		initializeChessBoard();
		
	}
	
	private void initializeChessBoard(){
		initializeBoard();
		//initializePieces();
	}
	
	
	
	private void initializeBoard(){
		
		m_boxes = new Box[8][8];
		
		for(int i = 0; i < m_boxes.length; i++){
			
			//Rows alternate colour pattern.
			if(i%2==0){
				m_rowEven = true;
			}
			else{
				m_rowEven=false;
			}
			
			for(int j = 0; j < m_boxes.length; j++){
				
				if(j%2==0){
					if(m_rowEven){
						Box box = new Box(null,1);
						m_boxes[i][j]=box;
						if(j<7){
							System.out.print("1");
						}
						else{
							System.out.print("1 \n");
						}
					}
					else{
						Box box = new Box(null,0);
						m_boxes[i][j]=box;
						
						if(j<7){
							System.out.print("0");
						}
						else{
							System.out.print("0\n");
						}
					}
				}
				else{
					if(m_rowEven){
						Box box = new Box(null,0);
						m_boxes[i][j] = box;
						
						if(j<7){
							System.out.print("0");
						}
						else{
							System.out.print("0 \n");
						}
						
					}
					else{
						Box box = new Box(null,1);
						m_boxes[i][j]=box;
						
						if(j<7){
							System.out.print("1");
						}
						else{
							System.out.print("1 \n");
						}
					}
					
				}
				
			}
			
		}
	}
	
	
	private void removePiece(int row, int column){
		
		m_removedPieces.add(m_boxes[row][column].m_Piece);
		m_boxes[row][column].m_Piece = null;
		
	}
	
	private boolean checkBoxForPiece(int row, int column){
		if(m_boxes[row][column].m_Piece!= null){
		
			return true;
		}
		else{
			return false;
		}
		
	}
	
	private boolean canMoveToPosition(String PieceColour, int startRow,int startColumn,int destinationRow,int destinationColumn){
		
		//if tile not occupied by own piece and piece can move like that
		
		if(m_boxes[destinationRow][destinationColumn].m_Piece.m_colour == PieceColour){
			return false;
		}
		else{
			
			return true;
		}
		
		
		//Implement pathing system for piece
		
		
	}
	
	private void movePiece(int startRow,int startColumn,int destinationRow,int destinationColumn){
		if(checkBoxForPiece(destinationRow,destinationColumn)){
			removePiece(destinationRow,destinationColumn);
			
		}
		
		m_boxes[destinationRow][destinationColumn].m_Piece = m_boxes[startRow][startColumn].m_Piece;
		m_boxes[startRow][startColumn].m_Piece = null;
	}
	
	
	
	
	
	
}

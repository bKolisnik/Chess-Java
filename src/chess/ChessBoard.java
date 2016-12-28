package chess;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ChessBoard {
	
	Box[][] m_boxes;
	private Boolean m_rowEven;
	private List<Piece> m_removedPieces = new ArrayList<Piece>();
	
	//Diagonal
	private List<int[]>m_PossibleMovesDiagonalForwardRight = new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesDiagonalBackwardRight = new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesDiagonalForwardLeft = new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesDiagonalBackwardLeft = new ArrayList<int[]>();
	
	//Horizontal
	private List<int[]>m_PossibleMovesHorizontalRight = new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesHorizontalLeft = new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesVerticalForward= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesVerticalBackward= new ArrayList<int[]>();
	
	
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
						Box box = new Box(null,1,(32*8 - (i+1)*32),j*32);
						m_boxes[i][j]=box;
						if(j<7){
							System.out.print("1");
						}
						else{
							System.out.print("1 \n");
						}
					}
					else{
						Box box = new Box(null,0, (32*8 - (i+1)*32),j*32);
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
						Box box = new Box(null,0,(32*8 - (i+1)*32),j*32);
						m_boxes[i][j] = box;
						
						if(j<7){
							System.out.print("0");
						}
						else{
							System.out.print("0 \n");
						}
						
					}
					else{
						Box box = new Box(null,1,(32*8 - (i+1)*32),j*32);
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
	
	
	private void removePieceDueToAttack(int row, int column){
		
		m_removedPieces.add(m_boxes[row][column].m_piece);
		m_boxes[row][column].m_piece = null;
		
	}
	
	private boolean checkBoxForPiece(int row, int column){
		if(m_boxes[row][column].m_piece!= null){
		
			return true;
		}
		else{
			return false;
		}
		
	}
	
	private boolean pieceOfOwnColourOnBox(String startPieceColour, int destinationRow,int destinationColumn){
		
		//if tile not occupied by own piece and piece can move like that
		
		if(checkBoxForPiece(destinationRow,destinationColumn)&& m_boxes[destinationRow][destinationColumn].m_piece.m_colour == startPieceColour){
			return true;
		}
		
			
			return false;
		
		
		
		
	}
	
	private boolean pieceofOpposingColourOnBox(String startPieceColour,int destinationRow,int destinationColumn){
		if(!checkBoxForPiece(destinationRow,destinationColumn) && m_boxes[destinationRow][destinationColumn].m_piece.m_colour != startPieceColour){
			return true;
		}
		
			
			return false;
	}
	
	private void movePiece(String pieceColour, int startRow,int startColumn,int destinationRow,int destinationColumn){
		if(pieceofOpposingColourOnBox(pieceColour,destinationRow,destinationColumn)){
			 
			removePieceDueToAttack(destinationRow,destinationColumn);
			
		}
		
		m_boxes[destinationRow][destinationColumn].m_piece = m_boxes[startRow][startColumn].m_piece;
		m_boxes[startRow][startColumn].m_piece = null;
	}
	
	
	
	private List<int[]> checkForwardDiagonalRight(String startPieceColour,int startRow, int startColumn){
		if(startRow == 7 || startColumn == 7){
			//return m_PossibleMovesDiagonalForwardRight = null;
			return m_PossibleMovesDiagonalForwardRight;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn+1)){
			return m_PossibleMovesDiagonalForwardRight;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn+1)){
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn+1;
			m_PossibleMovesDiagonalForwardRight.add(point);
			//return null;
			return m_PossibleMovesDiagonalForwardRight;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn+1;
			m_PossibleMovesDiagonalForwardRight.add(point);
			
			return checkForwardDiagonalRight(startPieceColour,startRow+1,startColumn+1);
			
		}
		
		
	}
	
	private List<int[]> checkBackwardDiagonalRight(String startPieceColour,int startRow, int startColumn){
		if(startRow == 0 || startColumn == 7){
			return m_PossibleMovesDiagonalBackwardRight;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn+1)){
			return m_PossibleMovesDiagonalBackwardRight;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn+1)){
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn+1;
			m_PossibleMovesDiagonalBackwardRight.add(point);
			//return null;
			return m_PossibleMovesDiagonalBackwardRight;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn+1;
			m_PossibleMovesDiagonalBackwardRight.add(point);
			
			return checkBackwardDiagonalRight(startPieceColour,startRow-1,startColumn+1);
			
		}
		
		
	}
	
	private List<int[]> checkForwardDiagonalLeft(String startPieceColour,int startRow, int startColumn){
		if(startRow == 7 || startColumn == 0){
			return m_PossibleMovesDiagonalForwardLeft;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn-1)){
			return m_PossibleMovesDiagonalForwardLeft;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn-1)){
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn-1;
			m_PossibleMovesDiagonalForwardLeft.add(point);
			//return null;
			return m_PossibleMovesDiagonalForwardLeft;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn-1;
			m_PossibleMovesDiagonalForwardLeft.add(point);
			
			return checkForwardDiagonalLeft(startPieceColour,startRow+1,startColumn-1);
			
		}
		
		
	}
	
	private List<int[]> checkBackwardDiagonalLeft(String startPieceColour,int startRow, int startColumn){
		if(startRow == 0 || startColumn == 0){
			return m_PossibleMovesDiagonalBackwardLeft;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn-1)){
			return m_PossibleMovesDiagonalBackwardLeft;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn-1)){
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn-1;
			m_PossibleMovesDiagonalBackwardLeft.add(point);
			//return null;
			return m_PossibleMovesDiagonalBackwardLeft;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn-1;
			m_PossibleMovesDiagonalBackwardLeft.add(point);
			
			return checkBackwardDiagonalLeft(startPieceColour,startRow-1,startColumn-1);
			
		}
		
		
	}
	
	private List<int[]> checkHorizontalRight(String startPieceColour,int startRow, int startColumn){
		if(startColumn == 7){
			return m_PossibleMovesHorizontalRight;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow,startColumn+1)){
			return m_PossibleMovesHorizontalRight;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow,startColumn+1)){
			int[] point = new int[2];
			point[0]=startRow;
			point[1]=startColumn+1;
			m_PossibleMovesHorizontalRight.add(point);
			//return null;
			return m_PossibleMovesHorizontalRight;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow;
			point[1]=startColumn+1;
			m_PossibleMovesHorizontalRight.add(point);
			
			return checkHorizontalRight(startPieceColour,startRow,startColumn+1);
			
		}
		
		
	}
	
	private List<int[]> checkHorizontalLeft(String startPieceColour,int startRow, int startColumn){
		if(startColumn == 0){
			return m_PossibleMovesHorizontalLeft;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow,startColumn-1)){
			return m_PossibleMovesHorizontalLeft;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow,startColumn-1)){
			int[] point = new int[2];
			point[0]=startRow;
			point[1]=startColumn-1;
			m_PossibleMovesHorizontalLeft.add(point);
			//return null;
			return m_PossibleMovesHorizontalLeft;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow;
			point[1]=startColumn-1;
			m_PossibleMovesHorizontalLeft.add(point);
			
			return checkHorizontalLeft(startPieceColour,startRow,startColumn-1);
			
		}
		
		
	}
	
	private List<int[]> checkVerticalForward(String startPieceColour,int startRow, int startColumn){
		if(startRow == 7){
			return m_PossibleMovesVerticalForward;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn)){
			return m_PossibleMovesVerticalForward;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn)){
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn;
			m_PossibleMovesVerticalForward.add(point);
			//return null;
			return m_PossibleMovesVerticalForward;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn;
			m_PossibleMovesVerticalForward.add(point);
			
			return checkVerticalForward(startPieceColour,startRow+1,startColumn);
			
		}
		
		
	}
	
	private List<int[]> checkVerticalBackward(String startPieceColour,int startRow, int startColumn){
		if(startRow == 0){
			return m_PossibleMovesVerticalBackward;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn)){
			return m_PossibleMovesVerticalBackward;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn)){
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn;
			m_PossibleMovesVerticalBackward.add(point);
			//return null;
			return m_PossibleMovesVerticalBackward;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn;
			m_PossibleMovesVerticalBackward.add(point);
			
			return checkVerticalBackward(startPieceColour,startRow-1,startColumn);
			
		}
		
		
	}
	
	
}

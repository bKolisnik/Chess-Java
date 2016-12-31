package chess;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;


public class ChessBoard  {
	
	Box[][] m_boxes;
	private Boolean m_rowEven;
	private List<Piece> m_removedPieces = new ArrayList<Piece>();
	
	//keep fields private use getters and setters to retrieve and alter from other objects.
	
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
	
	//Singlebox directions (for king)
	private List<int[]>m_PossibleMovesSingleBoxVerticalForward= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxVerticalBackward= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxHorizontalRight= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxHorizontalLeft= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxDiagonalForwardRight= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxDiagonalForwardLeft= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxDiagonalBackwardRight= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxDiagonalBackwardLeft= new ArrayList<int[]>();
	
	//Singlebox directions (for pawn). Special as cannot attack forward can only move diagonally if attacking..
	private List<int[]>m_PossibleMovesPawnVerticalForward= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesPawnVerticalForwardFirstMoveWithPiece= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesPawnDiagonalForwardRight= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesPawnDiagonalForwardLeft= new ArrayList<int[]>();
	
	//Knight leaps. Jump 1 is vertical 2, horizontal 1. Jump 2 is vertical 1 , horizontal 2.
	private List<int[]>m_PossibleMovesKnightJump1ForwardRight= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump1ForwardLeft= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump1BackwardRight= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump1BackwardLeft= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump2ForwardRight= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump2ForwardLeft= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump2BackwardRight= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump2BackwardLeft= new ArrayList<int[]>();
	
	
	//May need to edit king possible move methods.Specifically taking another piece ONLY if that spot will not then in turn be taken.
	//Possible create arraylist to keep track of alive pieces and loop through each one to see if it can take king if king moves there.
	
	//or first create list of spots possible to move to as per usual. Then check of those spots if it can be taken by another colour piece remove it from list.
	// for each possible spot check if any opponent colour piece can move there.
	
	//0 for black, 1 for white
	
	public ChessBoard(JPanel window){
		
		initializeChessBoard(window);
		
	}
	
	private void initializeChessBoard(JPanel window){
		initializeBoard(window);
		//initializePieces();
		
		
		
	}
	
	public void printChessBoard(){
		for (int i = 0; i < 8; i++){
			for(int j = 0; j< 8; j++){
				if(m_boxes[i][j].m_piece!= null){
					if(j<7){
						System.out.print("P");
					}else{
						System.out.print("P\n");
					}
					
				}else {
					if(j<7){
						System.out.print("O");
					}else{
						System.out.print("O\n");
					}
					
				}
				
				
			}
		}
	}
	
	private void initializeBoard(JPanel window){
		
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
						Box box = new Box(null,1,(50*8 - (i+1)*50),j*50,window);
						m_boxes[i][j]=box;
						if(j<7){
							System.out.print("1");
						}
						else{
							System.out.print("1 \n");
						}
					}
					else{
						Box box = new Box(null,0, (50*8 - (i+1)*50),j*50,window);
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
						Box box = new Box(null,0,(50*8 - (i+1)*50),j*50,window);
						m_boxes[i][j] = box;
						
						if(j<7){
							System.out.print("0");
						}
						else{
							System.out.print("0 \n");
						}
						
					}
					else{
						Box box = new Box(null,1,(50*8 - (i+1)*50),j*50,window);
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
	
	private List<int[]> checkVerticalForwardOneBox(String startPieceColour,int startRow, int startColumn){
		if(startRow == 7){
			return m_PossibleMovesSingleBoxVerticalForward;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn)){
			return m_PossibleMovesSingleBoxVerticalForward;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn)){
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn;
			m_PossibleMovesSingleBoxVerticalForward.add(point);
			//return null;
			return m_PossibleMovesSingleBoxVerticalForward;
		}
		
		else{
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn;
			m_PossibleMovesSingleBoxVerticalForward.add(point);
			
			return m_PossibleMovesSingleBoxVerticalForward;
		}
			
	}
		
		private List<int[]> checkVerticalBackwardOneBox(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0){
				return m_PossibleMovesSingleBoxVerticalBackward;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn)){
				return m_PossibleMovesSingleBoxVerticalBackward;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn)){
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn;
				m_PossibleMovesSingleBoxVerticalBackward.add(point);
				//return null;
				return m_PossibleMovesSingleBoxVerticalBackward;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn;
				m_PossibleMovesSingleBoxVerticalBackward.add(point);
				
				return m_PossibleMovesSingleBoxVerticalBackward;
				
			}
			
	}
		
		private List<int[]> checkHorizontalRightOneBox(String startPieceColour,int startRow, int startColumn){
			if(startColumn == 7){
				return m_PossibleMovesSingleBoxHorizontalRight;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow,startColumn+1)){
				return m_PossibleMovesSingleBoxHorizontalRight;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxHorizontalRight.add(point);
				//return null;
				return m_PossibleMovesSingleBoxHorizontalRight;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxHorizontalRight.add(point);
				
				return m_PossibleMovesSingleBoxHorizontalRight;
				
			}
			
	}
		
		private List<int[]> checkHorizontalLeftOneBox(String startPieceColour,int startRow, int startColumn){
			if(startColumn == 0){
				return m_PossibleMovesSingleBoxHorizontalLeft;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow,startColumn-1)){
				return m_PossibleMovesSingleBoxHorizontalLeft;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxHorizontalLeft.add(point);
				//return null;
				return m_PossibleMovesSingleBoxHorizontalLeft;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxHorizontalLeft.add(point);
				
				return m_PossibleMovesSingleBoxHorizontalLeft;
				
			}
			
	}
		
		private List<int[]> checkDiagonalForwardRightOneBox(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7 || startColumn == 7){
				return m_PossibleMovesSingleBoxDiagonalForwardRight;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn+1)){
				return m_PossibleMovesSingleBoxDiagonalForwardRight;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxDiagonalForwardRight.add(point);
				//return null;
				return m_PossibleMovesSingleBoxDiagonalForwardRight;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxDiagonalForwardRight.add(point);
				
				return m_PossibleMovesSingleBoxDiagonalForwardRight;
				
			}
			
	}
	
		private List<int[]> checkDiagonalBackwardRightOneBox(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0 || startColumn == 7){
				return m_PossibleMovesSingleBoxDiagonalBackwardRight;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn+1)){
				return m_PossibleMovesSingleBoxDiagonalBackwardRight;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxDiagonalBackwardRight.add(point);
				//return null;
				return m_PossibleMovesSingleBoxDiagonalBackwardRight;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxDiagonalBackwardRight.add(point);
				
				return m_PossibleMovesSingleBoxDiagonalBackwardRight;
				
			}
			
	}
		private List<int[]> checkDiagonalForwardLeftOneBox(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7 || startColumn == 0){
				return m_PossibleMovesSingleBoxDiagonalForwardLeft;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn-1)){
				return m_PossibleMovesSingleBoxDiagonalForwardLeft;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxDiagonalForwardLeft.add(point);
				//return null;
				return m_PossibleMovesSingleBoxDiagonalForwardLeft;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxDiagonalForwardLeft.add(point);
				
				return m_PossibleMovesSingleBoxDiagonalForwardLeft;
				
			}
			
	}
		
		private List<int[]> checkDiagonalBackwardLeftOneBox(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0 || startColumn == 0){
				return m_PossibleMovesSingleBoxDiagonalBackwardLeft;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn-1)){
				return m_PossibleMovesSingleBoxDiagonalBackwardLeft;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxDiagonalBackwardLeft.add(point);
				//return null;
				return m_PossibleMovesSingleBoxDiagonalBackwardLeft;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxDiagonalBackwardLeft.add(point);
				
				return m_PossibleMovesSingleBoxDiagonalBackwardLeft;
				
			}
			
	}
	
		private List<int[]> checkPawnForwardOneBox(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7){
				return m_PossibleMovesPawnVerticalForward;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn)){
				return m_PossibleMovesPawnVerticalForward;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn)){
				
				
				return m_PossibleMovesPawnVerticalForward;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn;
				m_PossibleMovesPawnVerticalForward.add(point);
				
				return m_PossibleMovesPawnVerticalForward;
			}
				
		}
		
		private List<int[]> checkPawnForwardFirstMoveWithPiece(String startPieceColour,int startRow, int startColumn){
			
				//First move of each pawn  and thus impossible for pawns of 
			
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn;
				m_PossibleMovesPawnVerticalForwardFirstMoveWithPiece.add(point);
				
				point = new int[2];
				point[0]=startRow+2;
				point[1]=startColumn;
				m_PossibleMovesPawnVerticalForwardFirstMoveWithPiece.add(point);
				
				
				return m_PossibleMovesPawnVerticalForwardFirstMoveWithPiece;
			
				
		}
		
		private List<int[]> checkPawnForwardDiagonalRight(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7||startColumn==7){
				return m_PossibleMovesPawnDiagonalForwardRight;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn+1)){
				return m_PossibleMovesPawnDiagonalForwardRight;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn+1;
				
				return m_PossibleMovesPawnDiagonalForwardRight;
			}
			
			else{
				//Nothing on box can't attack there.
				
				return m_PossibleMovesPawnDiagonalForwardRight;
			}
				
		}
		
		private List<int[]> checkKnightJump1ForwardRight(String startPieceColour,int startRow, int startColumn){
			if(startRow >= 6||startColumn==7){
				return m_PossibleMovesKnightJump1ForwardRight;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+2,startColumn+1)){
				return m_PossibleMovesKnightJump1ForwardRight;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+2,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow+2;
				point[1]=startColumn+1;
				
				return m_PossibleMovesKnightJump1ForwardRight;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+2;
				point[1]=startColumn+1;
				
				return m_PossibleMovesKnightJump1ForwardRight;
			}
				
		}
		
		private List<int[]> checkKnightJump1ForwardLeft(String startPieceColour,int startRow, int startColumn){
			if(startRow >= 6||startColumn==0){
				return m_PossibleMovesKnightJump1ForwardLeft;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+2,startColumn-1)){
				return m_PossibleMovesKnightJump1ForwardLeft;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+2,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow+2;
				point[1]=startColumn-1;
				
				return m_PossibleMovesKnightJump1ForwardLeft;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+2;
				point[1]=startColumn-1;
				
				return m_PossibleMovesKnightJump1ForwardLeft;
			}
				
		}
		
		private List<int[]> checkKnightJump1BackwardRight(String startPieceColour,int startRow, int startColumn){
			if(startRow <= 1||startColumn==7){
				return m_PossibleMovesKnightJump1BackwardRight;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-2,startColumn+1)){
				return m_PossibleMovesKnightJump1BackwardRight;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-2,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow-2;
				point[1]=startColumn+1;
				
				return m_PossibleMovesKnightJump1BackwardRight;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-2;
				point[1]=startColumn+1;
				
				return m_PossibleMovesKnightJump1BackwardRight;
			}
				
		}
		
		private List<int[]> checkKnightJump1BackwardLeft(String startPieceColour,int startRow, int startColumn){
			if(startRow <= 1||startColumn==0){
				return m_PossibleMovesKnightJump1BackwardLeft;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-2,startColumn-1)){
				return m_PossibleMovesKnightJump1BackwardLeft;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-2,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow-2;
				point[1]=startColumn-1;
				
				return m_PossibleMovesKnightJump1BackwardLeft;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-2;
				point[1]=startColumn-1;
				
				return m_PossibleMovesKnightJump1BackwardLeft;
			}
				
		}
		
		private List<int[]> checkKnightJump2ForwardRight(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7||startColumn>=6){
				return m_PossibleMovesKnightJump2ForwardRight;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn+2)){
				return m_PossibleMovesKnightJump2ForwardRight;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn+2)){
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn+2;
				
				return m_PossibleMovesKnightJump2ForwardRight;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn+2;
				
				return m_PossibleMovesKnightJump2ForwardRight;
			}
				
		}
		
		private List<int[]> checkKnightJump2ForwardLeft(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7||startColumn<=1){
				return m_PossibleMovesKnightJump2ForwardLeft;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn-2)){
				return m_PossibleMovesKnightJump2ForwardLeft;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn-2)){
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn-2;
				
				return m_PossibleMovesKnightJump2ForwardLeft;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn-2;
				
				return m_PossibleMovesKnightJump2ForwardLeft;
			}
				
		}
		
		private List<int[]> checkKnightJump2BackwardRight(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0||startColumn>=6){
				return m_PossibleMovesKnightJump2BackwardRight;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn+2)){
				return m_PossibleMovesKnightJump2BackwardRight;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn+2)){
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn+2;
				
				return m_PossibleMovesKnightJump2BackwardRight;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn+2;
				
				return m_PossibleMovesKnightJump2BackwardRight;
			}
				
		}
		
		private List<int[]> checkKnightJump2BackwardLeft(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0||startColumn<=1){
				return m_PossibleMovesKnightJump2BackwardRight;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn-2)){
				return m_PossibleMovesKnightJump2BackwardRight;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn-2)){
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn-2;
				
				return m_PossibleMovesKnightJump2BackwardRight;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn-2;
				
				return m_PossibleMovesKnightJump2BackwardRight;
			}
				
		}
		
}

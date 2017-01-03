package chess;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;


public class ChessBoard  {
	
	Box[][] m_boxes;
	private Boolean m_rowEven;
	private List<Piece> m_removedPieces = new ArrayList<Piece>();
	
	//keep fields public use getters and setters to retrieve and alter from other objects.
	
	//Diagonal
	private List<int[]>m_PossibleMovesDiagonalForwardRightWhite = new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesDiagonalBackwardRightWhite = new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesDiagonalForwardLeftWhite = new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesDiagonalBackwardLeftWhite = new ArrayList<int[]>();
	
	//Horizontal
	private List<int[]>m_PossibleMovesHorizontalRightWhite = new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesHorizontalLeftWhite = new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesVerticalForwardWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesVerticalBackwardWhite= new ArrayList<int[]>();
	
	//Singlebox directions (for king)
	private List<int[]>m_PossibleMovesSingleBoxVerticalForwardWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxVerticalBackwardWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxHorizontalRightWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxHorizontalLeftWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxDiagonalForwardRightWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxDiagonalForwardLeftWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxDiagonalBackwardRightWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxDiagonalBackwardLeftWhite= new ArrayList<int[]>();
	
	//Singlebox directions (for pawn). Special as cannot attack forward can only move diagonally if attacking..
	private List<int[]>m_PossibleMovesPawnVerticalForwardWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesPawnVerticalForwardFirstMoveWithPieceWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesPawnDiagonalForwardRightWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesPawnDiagonalForwardLeftWhite= new ArrayList<int[]>();
	
	//Knight leaps. Jump 1 is vertical 2, horizontal 1. Jump 2 is vertical 1 , horizontal 2.
	private List<int[]>m_PossibleMovesKnightJump1ForwardRightWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump1ForwardLeftWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump1BackwardRightWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump1BackwardLeftWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump2ForwardRightWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump2ForwardLeftWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump2BackwardRightWhite= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump2BackwardLeftWhite= new ArrayList<int[]>();
	
	//Diagonal
	private List<int[]>m_PossibleMovesDiagonalForwardRightBlack = new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesDiagonalBackwardRightBlack = new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesDiagonalForwardLeftBlack = new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesDiagonalBackwardLeftBlack = new ArrayList<int[]>();
		
	//Horizontal
	private List<int[]>m_PossibleMovesHorizontalRightBlack = new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesHorizontalLeftBlack = new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesVerticalForwardBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesVerticalBackwardBlack= new ArrayList<int[]>();
		
	//Singlebox directions (for king)
	private List<int[]>m_PossibleMovesSingleBoxVerticalForwardBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxVerticalBackwardBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxHorizontalRightBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxHorizontalLeftBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxDiagonalForwardRightBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxDiagonalForwardLeftBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxDiagonalBackwardRightBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesSingleBoxDiagonalBackwardLeftBlack= new ArrayList<int[]>();
		
	//Singlebox directions (for pawn). Special as cannot attack forward can only move diagonally if attacking..
	private List<int[]>m_PossibleMovesPawnVerticalForwardBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesPawnVerticalForwardFirstMoveWithPieceBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesPawnDiagonalForwardRightBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesPawnDiagonalForwardLeftBlack= new ArrayList<int[]>();
		
	//Knight leaps. Jump 1 is vertical 2, horizontal 1. Jump 2 is vertical 1 , horizontal 2.
	private List<int[]>m_PossibleMovesKnightJump1ForwardRightBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump1ForwardLeftBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump1BackwardRightBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump1BackwardLeftBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump2ForwardRightBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump2ForwardLeftBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump2BackwardRightBlack= new ArrayList<int[]>();
	private List<int[]>m_PossibleMovesKnightJump2BackwardLeftBlack= new ArrayList<int[]>();
	
	
	//May need to edit king possible move methods.Specifically taking another piece ONLY if that spot will not then in turn be taken.
	//Possible create arraylist to keep track of alive pieces and loop through each one to see if it can take king if king moves there.
	
	//or first create list of spots possible to move to as per usual. Then check of those spots if it can be taken by another colour piece remove it from list.
	// for each possible spot check if any opponent colour piece can move there.
	
	//0 for black, 1 for white
	
	public ChessBoard(JPanel window){
		
		initializeChessBoard(window);
		
	}
	
	public void initializeChessBoard(JPanel window){
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
	
	public void initializeBoard(JPanel window){
		
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
						
						
					}
					else{
						Box box = new Box(null,0, (50*8 - (i+1)*50),j*50,window);
						m_boxes[i][j]=box;
						
						
					}
				}
				else{
					if(m_rowEven){
						Box box = new Box(null,0,(50*8 - (i+1)*50),j*50,window);
						m_boxes[i][j] = box;
						
						
						
					}
					else{
						Box box = new Box(null,1,(50*8 - (i+1)*50),j*50,window);
						m_boxes[i][j]=box;
						
						
					}
					
				}
				
			}
			
		}
	}
	
	
	public void removePieceDueToAttack(int row, int column){
		
		m_removedPieces.add(m_boxes[row][column].m_piece);
		m_boxes[row][column].m_piece = null;
		
	}
	
	public boolean checkBoxForPiece(int row, int column){
		if(m_boxes[row][column].m_piece!= null){
		
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public boolean pieceOfOwnColourOnBox(String startPieceColour, int destinationRow,int destinationColumn){
		
		//if tile not occupied by own piece and piece can move like that
		
		if(checkBoxForPiece(destinationRow,destinationColumn)&& m_boxes[destinationRow][destinationColumn].m_piece.getColour() == startPieceColour){
			return true;
		}
		
			
			return false;
		
		
		
		
	}
	
	public boolean pieceofOpposingColourOnBox(String startPieceColour,int destinationRow,int destinationColumn){
		if(checkBoxForPiece(destinationRow,destinationColumn) && m_boxes[destinationRow][destinationColumn].m_piece.getColour() != startPieceColour){
			return true;
		}
		
			
			return false;
	}
	
	public void movePiece(String pieceColour, int startRow,int startColumn,int destinationRow,int destinationColumn){
		if(pieceofOpposingColourOnBox(pieceColour,destinationRow,destinationColumn)){
			 
			removePieceDueToAttack(destinationRow,destinationColumn);
			
		}
		
		m_boxes[destinationRow][destinationColumn].m_piece = m_boxes[startRow][startColumn].m_piece;
		m_boxes[startRow][startColumn].m_piece = null;
	}
	
	
	
	public List<int[]> checkForwardDiagonalRightWhite(String startPieceColour,int startRow, int startColumn){
		if(startRow == 7 || startColumn == 7){
			//return m_PossibleMovesDiagonalForwardRight = null;
			return m_PossibleMovesDiagonalForwardRightWhite;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn+1)){
			return m_PossibleMovesDiagonalForwardRightWhite;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn+1)){
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn+1;
			m_PossibleMovesDiagonalForwardRightWhite.add(point);
			//return null;
			return m_PossibleMovesDiagonalForwardRightWhite;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn+1;
			m_PossibleMovesDiagonalForwardRightWhite.add(point);
			
			return checkForwardDiagonalRightWhite(startPieceColour,startRow+1,startColumn+1);
			
		}
		
		
	}
	
	public List<int[]> checkForwardDiagonalRightBlack(String startPieceColour,int startRow, int startColumn){
		if(startRow == 0 || startColumn == 0){
			//return m_PossibleMovesDiagonalForwardRight = null;
			return m_PossibleMovesDiagonalForwardRightBlack;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn-1)){
			return m_PossibleMovesDiagonalForwardRightBlack;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn-1)){
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn-1;
			m_PossibleMovesDiagonalForwardRightBlack.add(point);
			//return null;
			return m_PossibleMovesDiagonalForwardRightBlack;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn-1;
			m_PossibleMovesDiagonalForwardRightBlack.add(point);
			
			return checkForwardDiagonalRightBlack(startPieceColour,startRow-1,startColumn-1);
			
		}
		
		
	}
	
	public List<int[]> checkBackwardDiagonalRightWhite(String startPieceColour,int startRow, int startColumn){
		if(startRow == 0 || startColumn == 7){
			return m_PossibleMovesDiagonalBackwardRightWhite;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn+1)){
			return m_PossibleMovesDiagonalBackwardRightWhite;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn+1)){
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn+1;
			m_PossibleMovesDiagonalBackwardRightWhite.add(point);
			//return null;
			return m_PossibleMovesDiagonalBackwardRightWhite;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn+1;
			m_PossibleMovesDiagonalBackwardRightWhite.add(point);
			
			return checkBackwardDiagonalRightWhite(startPieceColour,startRow-1,startColumn+1);
			
		}
		
		
	}
	
	public List<int[]> checkBackwardDiagonalRightBlack(String startPieceColour,int startRow, int startColumn){
		if(startRow == 7 || startColumn == 0){
			return m_PossibleMovesDiagonalBackwardRightBlack;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn-1)){
			return m_PossibleMovesDiagonalBackwardRightBlack;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn-1)){
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn-1;
			m_PossibleMovesDiagonalBackwardRightBlack.add(point);
			//return null;
			return m_PossibleMovesDiagonalBackwardRightBlack;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn-1;
			m_PossibleMovesDiagonalBackwardRightBlack.add(point);
			
			return checkBackwardDiagonalRightWhite(startPieceColour,startRow+1,startColumn-1);
			
		}
		
		
	}
	
	public List<int[]> checkForwardDiagonalLeftWhite(String startPieceColour,int startRow, int startColumn){
		if(startRow == 7 || startColumn == 0){
			return m_PossibleMovesDiagonalForwardLeftWhite;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn-1)){
			return m_PossibleMovesDiagonalForwardLeftWhite;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn-1)){
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn-1;
			m_PossibleMovesDiagonalForwardLeftWhite.add(point);
			//return null;
			return m_PossibleMovesDiagonalForwardLeftWhite;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn-1;
			m_PossibleMovesDiagonalForwardLeftWhite.add(point);
			
			return checkForwardDiagonalLeftWhite(startPieceColour,startRow+1,startColumn-1);
			
		}
		
		
	}
	
	public List<int[]> checkForwardDiagonalLeftBlack(String startPieceColour,int startRow, int startColumn){
		if(startRow == 0 || startColumn == 7){
			return m_PossibleMovesDiagonalForwardLeftBlack;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn+1)){
			return m_PossibleMovesDiagonalForwardLeftBlack;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn+1)){
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn+1;
			m_PossibleMovesDiagonalForwardLeftBlack.add(point);
			//return null;
			return m_PossibleMovesDiagonalForwardLeftBlack;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn+1;
			m_PossibleMovesDiagonalForwardLeftBlack.add(point);
			
			return checkForwardDiagonalLeftBlack(startPieceColour,startRow-1,startColumn+1);
			
		}
		
		
	}
	
	
	public List<int[]> checkBackwardDiagonalLeftWhite(String startPieceColour,int startRow, int startColumn){
		if(startRow == 0 || startColumn == 0){
			return m_PossibleMovesDiagonalBackwardLeftWhite;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn-1)){
			return m_PossibleMovesDiagonalBackwardLeftWhite;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn-1)){
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn-1;
			m_PossibleMovesDiagonalBackwardLeftWhite.add(point);
			//return null;
			return m_PossibleMovesDiagonalBackwardLeftWhite;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn-1;
			m_PossibleMovesDiagonalBackwardLeftWhite.add(point);
			
			return checkBackwardDiagonalLeftWhite(startPieceColour,startRow-1,startColumn-1);
			
		}
		
		
	}
	
	public List<int[]> checkBackwardDiagonalLeftBlack(String startPieceColour,int startRow, int startColumn){
		if(startRow == 7 || startColumn == 7){
			return m_PossibleMovesDiagonalBackwardLeftBlack;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn+1)){
			return m_PossibleMovesDiagonalBackwardLeftBlack;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn+1)){
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn+1;
			m_PossibleMovesDiagonalBackwardLeftBlack.add(point);
			//return null;
			return m_PossibleMovesDiagonalBackwardLeftBlack;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn+1;
			m_PossibleMovesDiagonalBackwardLeftBlack.add(point);
			
			return checkBackwardDiagonalLeftBlack(startPieceColour,startRow+1,startColumn+1);
			
		}
		
		
	}
	
	public List<int[]> checkHorizontalRightWhite(String startPieceColour,int startRow, int startColumn){
		if(startColumn == 7){
			return m_PossibleMovesHorizontalRightWhite;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow,startColumn+1)){
			return m_PossibleMovesHorizontalRightWhite;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow,startColumn+1)){
			int[] point = new int[2];
			point[0]=startRow;
			point[1]=startColumn+1;
			m_PossibleMovesHorizontalRightWhite.add(point);
			//return null;
			return m_PossibleMovesHorizontalRightWhite;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow;
			point[1]=startColumn+1;
			m_PossibleMovesHorizontalRightWhite.add(point);
			
			return checkHorizontalRightWhite(startPieceColour,startRow,startColumn+1);
			
		}
		
		
	}
	
	public List<int[]> checkHorizontalRightBlack(String startPieceColour,int startRow, int startColumn){
		if(startColumn == 0){
			return m_PossibleMovesHorizontalRightBlack;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow,startColumn-1)){
			return m_PossibleMovesHorizontalRightBlack;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow,startColumn-1)){
			int[] point = new int[2];
			point[0]=startRow;
			point[1]=startColumn-1;
			m_PossibleMovesHorizontalRightBlack.add(point);
			//return null;
			return m_PossibleMovesHorizontalRightBlack;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow;
			point[1]=startColumn-1;
			m_PossibleMovesHorizontalRightBlack.add(point);
			
			return checkHorizontalRightBlack(startPieceColour,startRow,startColumn-1);
			
		}
		
		
	}
	
	public List<int[]> checkHorizontalLeftWhite(String startPieceColour,int startRow, int startColumn){
		if(startColumn == 0){
			return m_PossibleMovesHorizontalLeftWhite;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow,startColumn-1)){
			return m_PossibleMovesHorizontalLeftWhite;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow,startColumn-1)){
			int[] point = new int[2];
			point[0]=startRow;
			point[1]=startColumn-1;
			m_PossibleMovesHorizontalLeftWhite.add(point);
			//return null;
			return m_PossibleMovesHorizontalLeftWhite;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow;
			point[1]=startColumn-1;
			m_PossibleMovesHorizontalLeftWhite.add(point);
			
			return checkHorizontalLeftWhite(startPieceColour,startRow,startColumn-1);
			
		}
		
		
	}
	
	public List<int[]> checkHorizontalLeftBlack(String startPieceColour,int startRow, int startColumn){
		if(startColumn == 7){
			return m_PossibleMovesHorizontalLeftBlack;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow,startColumn+1)){
			return m_PossibleMovesHorizontalLeftBlack;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow,startColumn+1)){
			int[] point = new int[2];
			point[0]=startRow;
			point[1]=startColumn+1;
			m_PossibleMovesHorizontalLeftBlack.add(point);
			//return null;
			return m_PossibleMovesHorizontalLeftBlack;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow;
			point[1]=startColumn+1;
			m_PossibleMovesHorizontalLeftBlack.add(point);
			
			return checkHorizontalLeftBlack(startPieceColour,startRow,startColumn+1);
			
		}
		
		
	}
	
	public List<int[]> checkVerticalForwardWhite(String startPieceColour,int startRow, int startColumn){
		if(startRow == 7){
			return m_PossibleMovesVerticalForwardWhite;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn)){
			return m_PossibleMovesVerticalForwardWhite;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn)){
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn;
			m_PossibleMovesVerticalForwardWhite.add(point);
			//return null;
			return m_PossibleMovesVerticalForwardWhite;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn;
			m_PossibleMovesVerticalForwardWhite.add(point);
			
			return checkVerticalForwardWhite(startPieceColour,startRow+1,startColumn);
			
		}
		
		
	}
	
	public List<int[]> checkVerticalForwardBlack(String startPieceColour,int startRow, int startColumn){
		if(startRow == 0){
			return m_PossibleMovesVerticalForwardBlack;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn)){
			return m_PossibleMovesVerticalForwardBlack;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn)){
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn;
			m_PossibleMovesVerticalForwardBlack.add(point);
			//return null;
			return m_PossibleMovesVerticalForwardBlack;
		}
		else{
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn;
			m_PossibleMovesVerticalForwardBlack.add(point);
			
			return checkVerticalForwardBlack(startPieceColour,startRow-1,startColumn);
			
		}
		
		
	}
	
	public List<int[]> checkVerticalBackwardWhite(String startPieceColour,int startRow, int startColumn){
		if(startRow == 0){
			return m_PossibleMovesVerticalBackwardWhite;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn)){
			return m_PossibleMovesVerticalBackwardWhite;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn)){
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn;
			m_PossibleMovesVerticalBackwardWhite.add(point);
			//return null;
			return m_PossibleMovesVerticalBackwardWhite;
		}
		
		else{
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn;
			m_PossibleMovesVerticalBackwardWhite.add(point);
			
			return checkVerticalBackwardWhite(startPieceColour,startRow-1,startColumn);
			
		}
		
		
	}
	
	public List<int[]> checkVerticalBackwardBlack(String startPieceColour,int startRow, int startColumn){
		if(startRow == 7){
			return m_PossibleMovesVerticalBackwardBlack;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn)){
			return m_PossibleMovesVerticalBackwardBlack;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn)){
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn;
			m_PossibleMovesVerticalBackwardBlack.add(point);
			//return null;
			return m_PossibleMovesVerticalBackwardBlack;
		}
		
		else{
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn;
			m_PossibleMovesVerticalBackwardBlack.add(point);
			
			return checkVerticalBackwardBlack(startPieceColour,startRow+1,startColumn);
			
		}
		
		
	}
	
	public List<int[]> checkVerticalForwardOneBoxWhite(String startPieceColour,int startRow, int startColumn){
		if(startRow == 7){
			return m_PossibleMovesSingleBoxVerticalForwardWhite;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn)){
			return m_PossibleMovesSingleBoxVerticalForwardWhite;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn)){
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn;
			m_PossibleMovesSingleBoxVerticalForwardWhite.add(point);
			//return null;
			return m_PossibleMovesSingleBoxVerticalForwardWhite;
		}
		
		else{
			int[] point = new int[2];
			point[0]=startRow+1;
			point[1]=startColumn;
			m_PossibleMovesSingleBoxVerticalForwardWhite.add(point);
			
			return m_PossibleMovesSingleBoxVerticalForwardWhite;
		}
			
	}
	
	public List<int[]> checkVerticalForwardOneBoxBlack(String startPieceColour,int startRow, int startColumn){
		if(startRow == 0){
			return m_PossibleMovesSingleBoxVerticalForwardBlack;
		}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn)){
			return m_PossibleMovesSingleBoxVerticalForwardBlack;
			
			
			
		}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn)){
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn;
			m_PossibleMovesSingleBoxVerticalForwardBlack.add(point);
			//return null;
			return m_PossibleMovesSingleBoxVerticalForwardBlack;
		}
		
		else{
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn;
			m_PossibleMovesSingleBoxVerticalForwardBlack.add(point);
			
			return m_PossibleMovesSingleBoxVerticalForwardBlack;
		}
			
	}
		
		public List<int[]> checkVerticalBackwardOneBoxWhite(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0){
				return m_PossibleMovesSingleBoxVerticalBackwardWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn)){
				return m_PossibleMovesSingleBoxVerticalBackwardWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn)){
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn;
				m_PossibleMovesSingleBoxVerticalBackwardWhite.add(point);
				//return null;
				return m_PossibleMovesSingleBoxVerticalBackwardWhite;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn;
				m_PossibleMovesSingleBoxVerticalBackwardWhite.add(point);
				
				return m_PossibleMovesSingleBoxVerticalBackwardWhite;
				
			}
			
	}
		
		public List<int[]> checkVerticalBackwardOneBoxBlack(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7){
				return m_PossibleMovesSingleBoxVerticalBackwardBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn)){
				return m_PossibleMovesSingleBoxVerticalBackwardBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn)){
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn;
				m_PossibleMovesSingleBoxVerticalBackwardBlack.add(point);
				//return null;
				return m_PossibleMovesSingleBoxVerticalBackwardBlack;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn;
				m_PossibleMovesSingleBoxVerticalBackwardBlack.add(point);
				
				return m_PossibleMovesSingleBoxVerticalBackwardBlack;
				
			}
			
	}
		
		public List<int[]> checkHorizontalRightOneBoxWhite(String startPieceColour,int startRow, int startColumn){
			if(startColumn == 7){
				return m_PossibleMovesSingleBoxHorizontalRightWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow,startColumn+1)){
				return m_PossibleMovesSingleBoxHorizontalRightWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxHorizontalRightWhite.add(point);
				//return null;
				return m_PossibleMovesSingleBoxHorizontalRightWhite;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxHorizontalRightWhite.add(point);
				
				return m_PossibleMovesSingleBoxHorizontalRightWhite;
				
			}
			
	}
		
		public List<int[]> checkHorizontalRightOneBoxBlack(String startPieceColour,int startRow, int startColumn){
			if(startColumn == 0){
				return m_PossibleMovesSingleBoxHorizontalRightBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow,startColumn-1)){
				return m_PossibleMovesSingleBoxHorizontalRightBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxHorizontalRightBlack.add(point);
				
				return m_PossibleMovesSingleBoxHorizontalRightBlack;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxHorizontalRightBlack.add(point);
				
				return m_PossibleMovesSingleBoxHorizontalRightBlack;
				
			}
			
	}
		
		public List<int[]> checkHorizontalLeftOneBoxWhite(String startPieceColour,int startRow, int startColumn){
			if(startColumn == 0){
				return m_PossibleMovesSingleBoxHorizontalLeftWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow,startColumn-1)){
				return m_PossibleMovesSingleBoxHorizontalLeftWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxHorizontalLeftWhite.add(point);
				//return null;
				return m_PossibleMovesSingleBoxHorizontalLeftWhite;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxHorizontalLeftWhite.add(point);
				
				return m_PossibleMovesSingleBoxHorizontalLeftWhite;
				
			}
			
	}
		
		public List<int[]> checkHorizontalLeftOneBoxBlack(String startPieceColour,int startRow, int startColumn){
			if(startColumn == 7){
				return m_PossibleMovesSingleBoxHorizontalLeftBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow,startColumn+1)){
				return m_PossibleMovesSingleBoxHorizontalLeftBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxHorizontalLeftBlack.add(point);
				//return null;
				return m_PossibleMovesSingleBoxHorizontalLeftBlack;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxHorizontalLeftBlack.add(point);
				
				return m_PossibleMovesSingleBoxHorizontalLeftBlack;
				
			}
			
	}
		
		public List<int[]> checkDiagonalForwardRightOneBoxWhite(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7 || startColumn == 7){
				return m_PossibleMovesSingleBoxDiagonalForwardRightWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn+1)){
				return m_PossibleMovesSingleBoxDiagonalForwardRightWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxDiagonalForwardRightWhite.add(point);
				//return null;
				return m_PossibleMovesSingleBoxDiagonalForwardRightWhite;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxDiagonalForwardRightWhite.add(point);
				
				return m_PossibleMovesSingleBoxDiagonalForwardRightWhite;
				
			}
			
	}
		
		public List<int[]> checkDiagonalForwardRightOneBoxBlack(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0 || startColumn == 0){
				return m_PossibleMovesSingleBoxDiagonalForwardRightBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn-1)){
				return m_PossibleMovesSingleBoxDiagonalForwardRightBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxDiagonalForwardRightBlack.add(point);
				//return null;
				return m_PossibleMovesSingleBoxDiagonalForwardRightBlack;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxDiagonalForwardRightBlack.add(point);
				
				return m_PossibleMovesSingleBoxDiagonalForwardRightBlack;
				
			}
			
	}
	
		public List<int[]> checkDiagonalBackwardRightOneBoxWhite(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0 || startColumn == 7){
				return m_PossibleMovesSingleBoxDiagonalBackwardRightWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn+1)){
				return m_PossibleMovesSingleBoxDiagonalBackwardRightWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxDiagonalBackwardRightWhite.add(point);
				//return null;
				return m_PossibleMovesSingleBoxDiagonalBackwardRightWhite;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxDiagonalBackwardRightWhite.add(point);
				
				return m_PossibleMovesSingleBoxDiagonalBackwardRightWhite;
				
			}
			
	}
		
		public List<int[]> checkDiagonalBackwardRightOneBoxBlack(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7 || startColumn == 0){
				return m_PossibleMovesSingleBoxDiagonalBackwardRightBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn-1)){
				return m_PossibleMovesSingleBoxDiagonalBackwardRightBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxDiagonalBackwardRightBlack.add(point);
				//return null;
				return m_PossibleMovesSingleBoxDiagonalBackwardRightBlack;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxDiagonalBackwardRightBlack.add(point);
				
				return m_PossibleMovesSingleBoxDiagonalBackwardRightBlack;
				
			}
			
	}
		
		public List<int[]> checkDiagonalForwardLeftOneBoxWhite(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7 || startColumn == 0){
				return m_PossibleMovesSingleBoxDiagonalForwardLeftWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn-1)){
				return m_PossibleMovesSingleBoxDiagonalForwardLeftWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxDiagonalForwardLeftWhite.add(point);
				//return null;
				return m_PossibleMovesSingleBoxDiagonalForwardLeftWhite;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxDiagonalForwardLeftWhite.add(point);
				
				return m_PossibleMovesSingleBoxDiagonalForwardLeftWhite;
				
			}
			
	}
		
		public List<int[]> checkDiagonalForwardLeftOneBoxBlack(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0 || startColumn == 7){
				return m_PossibleMovesSingleBoxDiagonalForwardLeftBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn+1)){
				return m_PossibleMovesSingleBoxDiagonalForwardLeftBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxDiagonalForwardLeftBlack.add(point);
				//return null;
				return m_PossibleMovesSingleBoxDiagonalForwardLeftBlack;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxDiagonalForwardLeftBlack.add(point);
				
				return m_PossibleMovesSingleBoxDiagonalForwardLeftBlack;
				
			}
			
	}
		
		public List<int[]> checkDiagonalBackwardLeftOneBoxWhite(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0 || startColumn == 0){
				return m_PossibleMovesSingleBoxDiagonalBackwardLeftWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn-1)){
				return m_PossibleMovesSingleBoxDiagonalBackwardLeftWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxDiagonalBackwardLeftWhite.add(point);
				//return null;
				return m_PossibleMovesSingleBoxDiagonalBackwardLeftWhite;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn-1;
				m_PossibleMovesSingleBoxDiagonalBackwardLeftWhite.add(point);
				
				return m_PossibleMovesSingleBoxDiagonalBackwardLeftWhite;
				
			}
			
	}
		
		public List<int[]> checkDiagonalBackwardLeftOneBoxBlack(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7 || startColumn == 7){
				return m_PossibleMovesSingleBoxDiagonalBackwardLeftBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn+1)){
				return m_PossibleMovesSingleBoxDiagonalBackwardLeftBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxDiagonalBackwardLeftBlack.add(point);
				//return null;
				return m_PossibleMovesSingleBoxDiagonalBackwardLeftBlack;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn+1;
				m_PossibleMovesSingleBoxDiagonalBackwardLeftBlack.add(point);
				
				return m_PossibleMovesSingleBoxDiagonalBackwardLeftBlack;
				
			}
			
	}
	
		public List<int[]> checkPawnForwardOneBoxWhite(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7){
				return m_PossibleMovesPawnVerticalForwardWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn)){
				return m_PossibleMovesPawnVerticalForwardWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn)){
				
				
				return m_PossibleMovesPawnVerticalForwardWhite;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn;
				m_PossibleMovesPawnVerticalForwardWhite.add(point);
				
				return m_PossibleMovesPawnVerticalForwardWhite;
			}
				
		}
		
		public List<int[]> checkPawnForwardOneBoxBlack(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0){
				return m_PossibleMovesPawnVerticalForwardBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn)){
				return m_PossibleMovesPawnVerticalForwardBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn)){
				
				
				return m_PossibleMovesPawnVerticalForwardBlack;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn;
				m_PossibleMovesPawnVerticalForwardBlack.add(point);
				
				return m_PossibleMovesPawnVerticalForwardBlack;
			}
				
		}
		
		public List<int[]> checkPawnForwardFirstMoveWithPieceWhite(String startPieceColour,int startRow, int startColumn){
			
				//First move of each pawn  and thus impossible for pawns of 
			
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn;
				m_PossibleMovesPawnVerticalForwardFirstMoveWithPieceWhite.add(point);
				
				point = new int[2];
				point[0]=startRow+2;
				point[1]=startColumn;
				m_PossibleMovesPawnVerticalForwardFirstMoveWithPieceWhite.add(point);
				
				
				return m_PossibleMovesPawnVerticalForwardFirstMoveWithPieceWhite;
			
				
		}
		
		public List<int[]> checkPawnForwardFirstMoveWithPieceBlack(String startPieceColour,int startRow, int startColumn){
			
			//First move of each pawn  and thus impossible for pawns of 
		
			int[] point = new int[2];
			point[0]=startRow-1;
			point[1]=startColumn;
			m_PossibleMovesPawnVerticalForwardFirstMoveWithPieceBlack.add(point);
			
			point = new int[2];
			point[0]=startRow-2;
			point[1]=startColumn;
			m_PossibleMovesPawnVerticalForwardFirstMoveWithPieceBlack.add(point);
			
			
			return m_PossibleMovesPawnVerticalForwardFirstMoveWithPieceBlack;
		
			
	}
		
		public List<int[]> checkPawnForwardDiagonalRightWhite(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7||startColumn==7){
				return m_PossibleMovesPawnDiagonalForwardRightWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn+1)){
				return m_PossibleMovesPawnDiagonalForwardRightWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn+1;
				
				m_PossibleMovesPawnDiagonalForwardRightWhite.add(point);
				return m_PossibleMovesPawnDiagonalForwardRightWhite;
			}
			
			else{
				//Nothing on box can't attack there.
				
				return m_PossibleMovesPawnDiagonalForwardRightWhite;
			}
				
		}
		
		public List<int[]> checkPawnForwardDiagonalRightBlack(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0||startColumn==0){
				return m_PossibleMovesPawnDiagonalForwardRightBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn-1)){
				return m_PossibleMovesPawnDiagonalForwardRightBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn-1;
				
				m_PossibleMovesPawnDiagonalForwardRightBlack.add(point);
				return m_PossibleMovesPawnDiagonalForwardRightBlack;
			}
			
			else{
				//Nothing on box can't attack there.
				
				return m_PossibleMovesPawnDiagonalForwardRightBlack;
			}
				
		}
		
		public List<int[]> checkPawnForwardDiagonalLeftWhite(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7||startColumn==0){
				return m_PossibleMovesPawnDiagonalForwardLeftWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn-1)){
				return m_PossibleMovesPawnDiagonalForwardLeftWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn-1;
				m_PossibleMovesPawnDiagonalForwardLeftWhite.add(point);
				
				return m_PossibleMovesPawnDiagonalForwardLeftWhite;
			}
			
			else{
				//Nothing on box can't attack there.
				
				return m_PossibleMovesPawnDiagonalForwardLeftWhite;
			}
				
		}
		
		public List<int[]> checkPawnForwardDiagonalLeftBlack(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0||startColumn==7){
				return m_PossibleMovesPawnDiagonalForwardLeftBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn+1)){
				return m_PossibleMovesPawnDiagonalForwardLeftBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn+1;
				m_PossibleMovesPawnDiagonalForwardLeftBlack.add(point);
				
				return m_PossibleMovesPawnDiagonalForwardLeftBlack;
			}
			
			else{
				//Nothing on box can't attack there.
				
				return m_PossibleMovesPawnDiagonalForwardLeftBlack;
			}
				
		}
		
		public List<int[]> checkKnightJump1ForwardRightWhite(String startPieceColour,int startRow, int startColumn){
			if(startRow >= 6||startColumn==7){
				return m_PossibleMovesKnightJump1ForwardRightWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+2,startColumn+1)){
				return m_PossibleMovesKnightJump1ForwardRightWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+2,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow+2;
				point[1]=startColumn+1;
				m_PossibleMovesKnightJump1ForwardRightWhite.add(point);
				return m_PossibleMovesKnightJump1ForwardRightWhite;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+2;
				point[1]=startColumn+1;
				m_PossibleMovesKnightJump1ForwardRightWhite.add(point);
				return m_PossibleMovesKnightJump1ForwardRightWhite;
			}
				
		}
		
		public List<int[]> checkKnightJump1ForwardRightBlack(String startPieceColour,int startRow, int startColumn){
			if(startRow <= 1||startColumn==0){
				return m_PossibleMovesKnightJump1ForwardRightBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-2,startColumn-1)){
				return m_PossibleMovesKnightJump1ForwardRightBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-2,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow-2;
				point[1]=startColumn-1;
				m_PossibleMovesKnightJump1ForwardRightBlack.add(point);
				return m_PossibleMovesKnightJump1ForwardRightBlack;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-2;
				point[1]=startColumn-1;
				m_PossibleMovesKnightJump1ForwardRightBlack.add(point);
				return m_PossibleMovesKnightJump1ForwardRightBlack;
			}
				
		}
		
		public List<int[]> checkKnightJump1ForwardLeftWhite(String startPieceColour,int startRow, int startColumn){
			if(startRow >= 6||startColumn==0){
				return m_PossibleMovesKnightJump1ForwardLeftWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+2,startColumn-1)){
				return m_PossibleMovesKnightJump1ForwardLeftWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+2,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow+2;
				point[1]=startColumn-1;
				m_PossibleMovesKnightJump1ForwardLeftWhite.add(point);
				return m_PossibleMovesKnightJump1ForwardLeftWhite;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+2;
				point[1]=startColumn-1;
				m_PossibleMovesKnightJump1ForwardLeftWhite.add(point);
				return m_PossibleMovesKnightJump1ForwardLeftWhite;
			}
				
		}
		
		public List<int[]> checkKnightJump1ForwardLeftBlack(String startPieceColour,int startRow, int startColumn){
			if(startRow <= 1||startColumn==7){
				return m_PossibleMovesKnightJump1ForwardLeftBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-2,startColumn+1)){
				return m_PossibleMovesKnightJump1ForwardLeftBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-2,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow-2;
				point[1]=startColumn+1;
				m_PossibleMovesKnightJump1ForwardLeftBlack.add(point);
				return m_PossibleMovesKnightJump1ForwardLeftBlack;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-2;
				point[1]=startColumn+1;
				m_PossibleMovesKnightJump1ForwardLeftBlack.add(point);
				return m_PossibleMovesKnightJump1ForwardLeftBlack;
			}
				
		}
		
		public List<int[]> checkKnightJump1BackwardRightWhite(String startPieceColour,int startRow, int startColumn){
			if(startRow <= 1||startColumn==7){
				return m_PossibleMovesKnightJump1BackwardRightWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-2,startColumn+1)){
				return m_PossibleMovesKnightJump1BackwardRightWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-2,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow-2;
				point[1]=startColumn+1;
				m_PossibleMovesKnightJump1BackwardRightWhite.add(point);
				return m_PossibleMovesKnightJump1BackwardRightWhite;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-2;
				point[1]=startColumn+1;
				m_PossibleMovesKnightJump1BackwardRightWhite.add(point);
				return m_PossibleMovesKnightJump1BackwardRightWhite;
			}
				
		}
		
		public List<int[]> checkKnightJump1BackwardRightBlack(String startPieceColour,int startRow, int startColumn){
			if(startRow >= 6||startColumn==0){
				return m_PossibleMovesKnightJump1BackwardRightBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+2,startColumn-1)){
				return m_PossibleMovesKnightJump1BackwardRightBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+2,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow+2;
				point[1]=startColumn-1;
				m_PossibleMovesKnightJump1BackwardRightBlack.add(point);
				return m_PossibleMovesKnightJump1BackwardRightBlack;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+2;
				point[1]=startColumn-1;
				m_PossibleMovesKnightJump1BackwardRightBlack.add(point);
				return m_PossibleMovesKnightJump1BackwardRightBlack;
			}
				
		}
		
		public List<int[]> checkKnightJump1BackwardLeftWhite(String startPieceColour,int startRow, int startColumn){
			if(startRow <= 1||startColumn==0){
				return m_PossibleMovesKnightJump1BackwardLeftWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-2,startColumn-1)){
				return m_PossibleMovesKnightJump1BackwardLeftWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-2,startColumn-1)){
				int[] point = new int[2];
				point[0]=startRow-2;
				point[1]=startColumn-1;
				m_PossibleMovesKnightJump1BackwardLeftWhite.add(point);
				return m_PossibleMovesKnightJump1BackwardLeftWhite;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-2;
				point[1]=startColumn-1;
				m_PossibleMovesKnightJump1BackwardLeftWhite.add(point);
				return m_PossibleMovesKnightJump1BackwardLeftWhite;
			}
				
		}
		
		public List<int[]> checkKnightJump1BackwardLeftBlack(String startPieceColour,int startRow, int startColumn){
			if(startRow >= 6||startColumn==7){
				return m_PossibleMovesKnightJump1BackwardLeftBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+2,startColumn+1)){
				return m_PossibleMovesKnightJump1BackwardLeftBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+2,startColumn+1)){
				int[] point = new int[2];
				point[0]=startRow+2;
				point[1]=startColumn+1;
				m_PossibleMovesKnightJump1BackwardLeftBlack.add(point);
				return m_PossibleMovesKnightJump1BackwardLeftBlack;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+2;
				point[1]=startColumn+1;
				m_PossibleMovesKnightJump1BackwardLeftBlack.add(point);
				return m_PossibleMovesKnightJump1BackwardLeftBlack;
			}
				
		}
		
		public List<int[]> checkKnightJump2ForwardRightWhite(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7||startColumn>=6){
				return m_PossibleMovesKnightJump2ForwardRightWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn+2)){
				return m_PossibleMovesKnightJump2ForwardRightWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn+2)){
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn+2;
				m_PossibleMovesKnightJump2ForwardRightWhite.add(point);
				return m_PossibleMovesKnightJump2ForwardRightWhite;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn+2;
				m_PossibleMovesKnightJump2ForwardRightWhite.add(point);
				return m_PossibleMovesKnightJump2ForwardRightWhite;
			}
				
		}
		
		public List<int[]> checkKnightJump2ForwardRightBlack(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0||startColumn<=1){
				return m_PossibleMovesKnightJump2ForwardRightBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn-2)){
				return m_PossibleMovesKnightJump2ForwardRightBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn-2)){
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn-2;
				m_PossibleMovesKnightJump2ForwardRightBlack.add(point);
				return m_PossibleMovesKnightJump2ForwardRightBlack;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn-2;
				m_PossibleMovesKnightJump2ForwardRightBlack.add(point);
				return m_PossibleMovesKnightJump2ForwardRightBlack;
			}
				
		}
		
		public List<int[]> checkKnightJump2ForwardLeftWhite(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7||startColumn<=1){
				return m_PossibleMovesKnightJump2ForwardLeftWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn-2)){
				return m_PossibleMovesKnightJump2ForwardLeftWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn-2)){
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn-2;
				m_PossibleMovesKnightJump2ForwardLeftWhite.add(point);
				return m_PossibleMovesKnightJump2ForwardLeftWhite;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn-2;
				m_PossibleMovesKnightJump2ForwardLeftWhite.add(point);
				return m_PossibleMovesKnightJump2ForwardLeftWhite;
			}
				
		}
		
		public List<int[]> checkKnightJump2ForwardLeftBlack(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0||startColumn>=6){
				return m_PossibleMovesKnightJump2ForwardLeftBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn+2)){
				return m_PossibleMovesKnightJump2ForwardLeftBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn+2)){
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn+2;
				m_PossibleMovesKnightJump2ForwardLeftBlack.add(point);
				return m_PossibleMovesKnightJump2ForwardLeftBlack;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn+2;
				m_PossibleMovesKnightJump2ForwardLeftBlack.add(point);
				return m_PossibleMovesKnightJump2ForwardLeftBlack;
			}
				
		}
		
		public List<int[]> checkKnightJump2BackwardRightWhite(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0||startColumn>=6){
				return m_PossibleMovesKnightJump2BackwardRightWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn+2)){
				return m_PossibleMovesKnightJump2BackwardRightWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn+2)){
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn+2;
				m_PossibleMovesKnightJump2BackwardRightWhite.add(point);
				return m_PossibleMovesKnightJump2BackwardRightWhite;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn+2;
				m_PossibleMovesKnightJump2BackwardRightWhite.add(point);
				return m_PossibleMovesKnightJump2BackwardRightWhite;
			}
				
		}
		
		public List<int[]> checkKnightJump2BackwardRightBlack(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7||startColumn<=1){
				return m_PossibleMovesKnightJump2BackwardRightBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn-2)){
				return m_PossibleMovesKnightJump2BackwardRightBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn-2)){
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn-2;
				m_PossibleMovesKnightJump2BackwardRightBlack.add(point);
				return m_PossibleMovesKnightJump2BackwardRightBlack;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn-2;
				m_PossibleMovesKnightJump2BackwardRightBlack.add(point);
				return m_PossibleMovesKnightJump2BackwardRightBlack;
			}
				
		}
		
		public List<int[]> checkKnightJump2BackwardLeftWhite(String startPieceColour,int startRow, int startColumn){
			if(startRow == 0||startColumn<=1){
				return m_PossibleMovesKnightJump2BackwardLeftWhite;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow-1,startColumn-2)){
				return m_PossibleMovesKnightJump2BackwardLeftWhite;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow-1,startColumn-2)){
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn-2;
				m_PossibleMovesKnightJump2BackwardLeftWhite.add(point);
				return m_PossibleMovesKnightJump2BackwardLeftWhite;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow-1;
				point[1]=startColumn-2;
				m_PossibleMovesKnightJump2BackwardLeftWhite.add(point);
				return m_PossibleMovesKnightJump2BackwardLeftWhite;
			}
				
		}
		
		public List<int[]> checkKnightJump2BackwardLeftBlack(String startPieceColour,int startRow, int startColumn){
			if(startRow == 7||startColumn>=6){
				return m_PossibleMovesKnightJump2BackwardLeftBlack;
			}else if(pieceOfOwnColourOnBox(startPieceColour,startRow+1,startColumn+2)){
				return m_PossibleMovesKnightJump2BackwardLeftBlack;
				
				
				
			}else if(pieceofOpposingColourOnBox(startPieceColour,startRow+1,startColumn+2)){
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn+2;
				m_PossibleMovesKnightJump2BackwardLeftBlack.add(point);
				return m_PossibleMovesKnightJump2BackwardLeftBlack;
			}
			
			else{
				int[] point = new int[2];
				point[0]=startRow+1;
				point[1]=startColumn+2;
				m_PossibleMovesKnightJump2BackwardLeftBlack.add(point);
				return m_PossibleMovesKnightJump2BackwardLeftBlack;
			}
				
		}
		
		public void clearDiagonalForwardRightWhite(){
			m_PossibleMovesDiagonalForwardRightWhite.clear();
		}
		
		public void clearDiagonalBackwardRightWhite(){
			m_PossibleMovesDiagonalForwardRightWhite.clear();
		}
		public void clearDiagonalForwardLeftWhite(){
			m_PossibleMovesDiagonalForwardLeftWhite.clear();
		}
		
		public void clearDiagonalBackwardLeftWhite(){
			m_PossibleMovesDiagonalBackwardLeftWhite.clear();
		}
		
		public void clearHorizontalRightWhite(){
			m_PossibleMovesHorizontalRightWhite.clear();
		}
		
		public void clearHorizontalLeftWhite(){
			m_PossibleMovesHorizontalLeftWhite.clear();
		}
		
		public void clearVerticalForwardWhite(){
			m_PossibleMovesVerticalForwardWhite.clear();
		}
		
		public void clearVerticalBackwardWhite(){
			m_PossibleMovesVerticalBackwardWhite.clear();
		}
		
		public void clearSingleBoxVerticalForwardWhite(){
			m_PossibleMovesSingleBoxVerticalForwardWhite.clear();
		}
		
		public void clearSingleBoxVerticalBackwardWhite(){
			m_PossibleMovesSingleBoxVerticalBackwardWhite.clear();
		}
		
		public void clearSingleBoxHorizontalRightWhite(){
			m_PossibleMovesSingleBoxHorizontalRightWhite.clear();
		}
		
		public void clearSingleBoxHorizontalLeftWhite(){
			m_PossibleMovesSingleBoxHorizontalLeftWhite.clear();
		}
		
		public void clearSingleBoxDiagonalForwardRightWhite(){
			m_PossibleMovesSingleBoxDiagonalForwardRightWhite.clear();
		}
		
		public void clearSingleBoxDiagonalForwardLeftWhite(){
			m_PossibleMovesSingleBoxDiagonalForwardLeftWhite.clear();
		}
		
		public void clearSingleBoxDiagonalBackwardRightWhite(){
			m_PossibleMovesSingleBoxDiagonalBackwardRightWhite.clear();
		}
		
		public void clearSingleBoxDiagonalBackwardLeftWhite(){
			m_PossibleMovesSingleBoxDiagonalBackwardLeftWhite.clear();
		}
		
		public void clearPawnVerticalForwardWhite(){
			m_PossibleMovesPawnVerticalForwardWhite.clear();
		}
		
		public void clearPawnVerticalForwardFirstMoveWithPieceWhite(){
			m_PossibleMovesPawnVerticalForwardFirstMoveWithPieceWhite.clear();
		}
		
		public void clearPawnDiagonalForwardRightWhite(){
			m_PossibleMovesPawnDiagonalForwardRightWhite.clear();
		}
		
		public void clearPawnDiagonalForwardLeftWhite(){
			m_PossibleMovesPawnDiagonalForwardLeftWhite.clear();
		}
		
		public void clearKnightJump1ForwardRightWhite(){
			m_PossibleMovesKnightJump1ForwardRightWhite.clear();
		}
		
		public void clearKnightJump1ForwardLeftWhite(){
			m_PossibleMovesKnightJump1ForwardLeftWhite.clear();
		}
		
		public void clearKnightJump1BackwardRightWhite(){
			m_PossibleMovesKnightJump1BackwardRightWhite.clear();
		}
		
		public void clearKnightJump1BackwardLeftWhite(){
			m_PossibleMovesKnightJump1BackwardLeftWhite.clear();
		}
		
		public void clearKnightJump2ForwardRightWhite(){
			m_PossibleMovesKnightJump2ForwardRightWhite.clear();
		}
		
		public void clearKnightJump2ForwardLeftWhite(){
			m_PossibleMovesKnightJump2ForwardLeftWhite.clear();
		}
		
		public void clearKnightJump2BackwardRightWhite(){
			m_PossibleMovesKnightJump2BackwardRightWhite.clear();
		}
		
		public void clearKnightJump2BackwardLeftWhite(){
			m_PossibleMovesKnightJump2BackwardLeftWhite.clear();
		}
		
		public void clearDiagonalForwardRightBlack(){
			m_PossibleMovesDiagonalForwardRightBlack.clear();
		}

		public void clearDiagonalBackwardRightBlack(){
			m_PossibleMovesDiagonalForwardRightBlack.clear();
		}
		public void clearDiagonalForwardLeftBlack(){
			m_PossibleMovesDiagonalForwardLeftBlack.clear();
		}

		public void clearDiagonalBackwardLeftBlack(){
			m_PossibleMovesDiagonalBackwardLeftBlack.clear();
		}

		public void clearHorizontalRightBlack(){
			m_PossibleMovesHorizontalRightBlack.clear();
		}

		public void clearHorizontalLeftBlack(){
			m_PossibleMovesHorizontalLeftBlack.clear();
		}

		public void clearVerticalForwardBlack(){
			m_PossibleMovesVerticalForwardBlack.clear();
		}

		public void clearVerticalBackwardBlack(){
			m_PossibleMovesVerticalBackwardBlack.clear();
		}

		public void clearSingleBoxVerticalForwardBlack(){
			m_PossibleMovesSingleBoxVerticalForwardBlack.clear();
		}

		public void clearSingleBoxVerticalBackwardBlack(){
			m_PossibleMovesSingleBoxVerticalBackwardBlack.clear();
		}

		public void clearSingleBoxHorizontalRightBlack(){
			m_PossibleMovesSingleBoxHorizontalRightBlack.clear();
		}

		public void clearSingleBoxHorizontalLeftBlack(){
			m_PossibleMovesSingleBoxHorizontalLeftBlack.clear();
		}

		public void clearSingleBoxDiagonalForwardRightBlack(){
			m_PossibleMovesSingleBoxDiagonalForwardRightBlack.clear();
		}

		public void clearSingleBoxDiagonalForwardLeftBlack(){
			m_PossibleMovesSingleBoxDiagonalForwardLeftBlack.clear();
		}

		public void clearSingleBoxDiagonalBackwardRightBlack(){
			m_PossibleMovesSingleBoxDiagonalBackwardRightBlack.clear();
		}

		public void clearSingleBoxDiagonalBackwardLeftBlack(){
			m_PossibleMovesSingleBoxDiagonalBackwardLeftBlack.clear();
		}

		public void clearPawnVerticalForwardBlack(){
			m_PossibleMovesPawnVerticalForwardBlack.clear();
		}

		public void clearPawnVerticalForwardFirstMoveWithPieceBlack(){
			m_PossibleMovesPawnVerticalForwardFirstMoveWithPieceBlack.clear();
		}

		public void clearPawnDiagonalForwardRightBlack(){
			m_PossibleMovesPawnDiagonalForwardRightBlack.clear();
		}

		public void clearPawnDiagonalForwardLeftBlack(){
			m_PossibleMovesPawnDiagonalForwardLeftBlack.clear();
		}

		public void clearKnightJump1ForwardRightBlack(){
			m_PossibleMovesKnightJump1ForwardRightBlack.clear();
		}
		
		public void clearKnightJump1ForwardLeftBlack(){
			m_PossibleMovesKnightJump1ForwardLeftBlack.clear();
		}

		public void clearKnightJump1BackwardRightBlack(){
			m_PossibleMovesKnightJump1BackwardRightBlack.clear();
		}

		public void clearKnightJump1BackwardLeftBlack(){
			m_PossibleMovesKnightJump1BackwardLeftBlack.clear();
		}

		public void clearKnightJump2ForwardRightBlack(){
			m_PossibleMovesKnightJump2ForwardRightBlack.clear();
		}

		public void clearKnightJump2ForwardLeftBlack(){
			m_PossibleMovesKnightJump2ForwardLeftBlack.clear();
		}

		public void clearKnightJump2BackwardRightBlack(){
			m_PossibleMovesKnightJump2BackwardRightBlack.clear();
		}

		public void clearKnightJump2BackwardLeftBlack(){
			m_PossibleMovesKnightJump2BackwardLeftBlack.clear();
		}
		
}

package chess;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JPanel implements MouseListener {

	private ChessBoard m_chessBoard;
	
	private boolean m_firstSelect=true;
	private boolean m_secondSelect=false;
	
	
	private int[] firstCoord = new int[2];
	
	private List<int[]> m_possibleMoves = new ArrayList<int[]>();
	
	private int m_turn = 1;
	private boolean m_whiteInCheck = false;
	private boolean m_blackInCheck = false;
	
	private List<int[]> possibleMoveLeadToCheck = new ArrayList<int[]>();
	
	
	JLabel turnLbl = new JLabel("Turn:");
	JLabel turnLblPlayer=new JLabel("White");
	
	public Window(){
		
		turnLbl.setSize(20,20);
		turnLblPlayer.setSize(20,20);
		this.add(turnLbl);
		this.add(turnLblPlayer);
		turnLbl.setBounds(415,20,40,40);
		turnLblPlayer.setBounds(415,40,40,40);
		
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int boxColumn = e.getX()/50;
		int boxRow = 7-(((e.getY()-30)/50));
		
		
		
		
		
		
		if(m_chessBoard.m_boxes[boxRow][boxColumn].m_piece!=null&&m_firstSelect){
			
			if(m_turn%2!=0&&m_chessBoard.m_boxes[boxRow][boxColumn].m_piece.getColour().equals("white")||m_turn%2==0&&m_chessBoard.m_boxes[boxRow][boxColumn].m_piece.getColour().equals("black")){
			
			m_chessBoard.m_boxes[boxRow][boxColumn].setSelected(true);
			repaint();
			
			//
			
			firstCoord[0] = boxRow;
			firstCoord[1] = boxColumn;
			
			if(m_turn%2!=0){
				//System.out.println(m_possibleMoves.toString());
				m_possibleMoves.clear();
				m_possibleMoves = getPossibleMoves("white",boxRow, boxColumn);
				
				
			}else{
				//System.out.println(m_possibleMoves.toString());
				m_possibleMoves.clear();
				m_possibleMoves = getPossibleMoves("black",boxRow, boxColumn);
				
				
			}
			
			if(!m_possibleMoves.isEmpty()){
					for(int[] possibleMove: m_possibleMoves){
				
					
					int possibleRow = possibleMove[0];
					int possibleColumn = possibleMove[1];
					
					
					m_chessBoard.m_boxes[possibleRow][possibleColumn].setPossible(true);
					
					//System.out.println("possible " + possibleRow + " " + possibleColumn);
					
				}
					
				
					
					
			}else{
				System.out.println("No moves");
				
			}
			
			
			
			}
		}
		
		
		//If its the second select and its in the possible moves list.
		if(m_secondSelect){
			
			
			
			if(m_chessBoard.m_boxes[boxRow][boxColumn].getPossible()){
				m_chessBoard.movePiece(m_chessBoard.m_boxes[firstCoord[0]][firstCoord[1]].m_piece.getColour(), firstCoord[0], firstCoord[1], boxRow, boxColumn);
				
				if(m_chessBoard.m_boxes[boxRow][boxColumn].m_piece instanceof Pawn){
					if(boxRow-firstCoord[0]==2||firstCoord[0]-boxRow==2){
						((Pawn)m_chessBoard.m_boxes[boxRow][boxColumn].m_piece).setMovedTwo(true);
						System.out.println("movedtwo");
					}
				}
				
				if(m_chessBoard.m_boxes[boxRow][boxColumn].m_piece instanceof Pawn){
					if(!((Pawn)m_chessBoard.m_boxes[boxRow][boxColumn].m_piece).hadFirstMove()){
						((Pawn) m_chessBoard.m_boxes[boxRow][boxColumn].m_piece).completedFirstMove();
					}
					
					if(m_turn%2!=0&&m_chessBoard.m_boxes[boxRow-1][boxColumn].m_piece!=null){
						if(m_chessBoard.m_boxes[boxRow-1][boxColumn].m_piece instanceof Pawn){
							if(((Pawn)m_chessBoard.m_boxes[boxRow-1][boxColumn].m_piece).justMovedTwo()){
								m_chessBoard.m_boxes[boxRow-1][boxColumn].m_piece=null;
								
							}
						}
					}
					
					if(m_turn%2==0&&m_chessBoard.m_boxes[boxRow+1][boxColumn].m_piece!=null){
						if(m_chessBoard.m_boxes[boxRow+1][boxColumn].m_piece instanceof Pawn){
							if(((Pawn)m_chessBoard.m_boxes[boxRow+1][boxColumn].m_piece).justMovedTwo()){
								m_chessBoard.m_boxes[boxRow+1][boxColumn].m_piece=null;
								
							}
						}
					}
					
				}
				
				for(int i = 0; i < 8; i++){
					for(int j = 0; j < 8; j++){
						if(m_chessBoard.m_boxes[i][j].m_piece!=null){
							if(m_chessBoard.m_boxes[i][j].m_piece instanceof Pawn){
								if(m_turn%2!=0 &&m_chessBoard.m_boxes[i][j].m_piece.getColour().equals("black")||m_turn%2==0 &&m_chessBoard.m_boxes[i][j].m_piece.getColour().equals("white")){
									((Pawn)m_chessBoard.m_boxes[i][j].m_piece).setMovedTwo(false);
									
								}
								
							}
						}
						
					}
					
				}
				
				
//If I am still in check after i have moved i lose.
				
				//Check for if we have opposing player in check. Brute force by checking all possible moves our pieces will have 
				//at the beginning of next turn. If there is a king on that box the opposing player is in check.
				
				
				for(int i = 0; i < 8; i++){
					for(int j = 0; j < 8; j++){
						if(m_chessBoard.m_boxes[i][j].m_piece!=null){
							if(m_chessBoard.m_boxes[i][j].m_piece.getColour()=="white"&&m_turn%2!=0){
								
								possibleMoveLeadToCheck= getPossibleMoves("white", i, j);
								if(!possibleMoveLeadToCheck.isEmpty()){
									for(int[] possibleMove: possibleMoveLeadToCheck){
								
									
									int possibleRow = possibleMove[0];
									int possibleColumn = possibleMove[1];
									
									
									if(m_chessBoard.m_boxes[possibleRow][possibleColumn].m_piece!=null){
										if(m_chessBoard.m_boxes[possibleRow][possibleColumn].m_piece.getColour().equals("black")&&m_chessBoard.m_boxes[possibleRow][possibleColumn].m_piece instanceof King){
											System.out.println("blackincheck");
											m_blackInCheck = true;
											break;
											
										}
									}
									
									
									
								}
								
								
							}else if(m_chessBoard.m_boxes[i][j].m_piece.getColour()=="black"&&m_turn%2==0){
								
								possibleMoveLeadToCheck= getPossibleMoves("black", i, j);
								if(!m_possibleMoves.isEmpty()){
									for(int[] possibleMove: possibleMoveLeadToCheck){
								
									
									int possibleRow = possibleMove[0];
									int possibleColumn = possibleMove[1];
									
									if(m_chessBoard.m_boxes[possibleRow][possibleColumn].m_piece!=null){
										if(m_chessBoard.m_boxes[possibleRow][possibleColumn].m_piece.getColour().equals("white")&&m_chessBoard.m_boxes[possibleRow][possibleColumn].m_piece instanceof King){
											System.out.println("whiteincheck");
											m_whiteInCheck = true;
											break;
											
										}
									}
									
									
									
								}
								
							}
						}
					}
				}
			}	
		}
				//m_possibleMoves.clear();
				possibleMoveLeadToCheck.clear();
				
				
				
				m_turn= m_turn+1;
				
				if(m_turn%2==0){
					turnLblPlayer.setText("Black");
				}else{
					turnLblPlayer.setText("White");
				}
				
				m_chessBoard.m_boxes[firstCoord[0]][firstCoord[1]].setSelected(false);
				for(int[] possibleMove: m_possibleMoves){
				
					int possibleRow = possibleMove[0];
					int possibleColumn = possibleMove[1];
					
					
					
					m_chessBoard.m_boxes[possibleRow][possibleColumn].setPossible(false);
					repaint();
					
					
				}
				
				
				m_possibleMoves.clear();
				repaint();
				
			}else{
			m_chessBoard.m_boxes[firstCoord[0]][firstCoord[1]].setSelected(false);
			System.out.println("Deselected");
			
			for(int[] possibleMove: m_possibleMoves){
				
				int possibleRow = possibleMove[0];
				int possibleColumn = possibleMove[1];
				
				
				
				m_chessBoard.m_boxes[possibleRow][possibleColumn].setPossible(false);
				
				
				
				repaint();
				
				
			}
			
			}
			
			
			
			
			
		}
		
		
		
		if(m_firstSelect == true){
			m_firstSelect = false;
			m_secondSelect = true;
		} else{
			m_firstSelect = true;
			m_secondSelect = false;
		}
		
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private List<int[]> getPossibleMoves(String colour,int boxRow, int boxColumn){
		
		
		List<int[]> possibleMoves = new ArrayList<int[]>();
		
		
		if(colour.equals("white")){
			

			
			if(m_chessBoard.m_boxes[boxRow][boxColumn].m_piece instanceof Pawn){
				//Clear the arraylist from previous uses of this class
				m_chessBoard.clearPawnDiagonalForwardLeftWhite();
				m_chessBoard.clearPawnDiagonalForwardRightWhite();
				m_chessBoard.clearPawnVerticalForwardWhite();
				m_chessBoard.clearPawnVerticalForwardFirstMoveWithPieceWhite();
				m_chessBoard.clearPawnEnPassantLeftWhite();
				m_chessBoard.clearPawnEnPassantRightWhite();
				
				possibleMoves.addAll(m_chessBoard.checkPawnEnPassantLeftWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkPawnEnPassantRightWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkPawnForwardDiagonalRightWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkPawnForwardDiagonalLeftWhite(colour, boxRow, boxColumn));
				
				
				if(!((Pawn) m_chessBoard.m_boxes[boxRow][boxColumn].m_piece).hadFirstMove()){
					possibleMoves.addAll(m_chessBoard.checkPawnForwardFirstMoveWithPieceWhite(colour, boxRow, boxColumn));
					
					
				}
				else{
					
					possibleMoves.addAll(m_chessBoard.checkPawnForwardOneBoxWhite(colour, boxRow, boxColumn));
					
				}
				
				
				return possibleMoves;
				
			}
			
			else if(m_chessBoard.m_boxes[boxRow][boxColumn].m_piece instanceof Rook){
				
				m_chessBoard.clearHorizontalLeftWhite();
				m_chessBoard.clearHorizontalRightWhite();
				m_chessBoard.clearVerticalForwardWhite();
				m_chessBoard.clearVerticalBackwardWhite();
				
				possibleMoves.addAll(m_chessBoard.checkHorizontalLeftWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkHorizontalRightWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkVerticalForwardWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkVerticalBackwardWhite(colour, boxRow, boxColumn));
				
				return possibleMoves;
			}
			
			else if(m_chessBoard.m_boxes[boxRow][boxColumn].m_piece instanceof Knight){
				m_chessBoard.clearKnightJump1BackwardLeftWhite();
				m_chessBoard.clearKnightJump1BackwardRightWhite();
				m_chessBoard.clearKnightJump1ForwardRightWhite();
				m_chessBoard.clearKnightJump1ForwardLeftWhite();
				m_chessBoard.clearKnightJump2BackwardLeftWhite();
				m_chessBoard.clearKnightJump2BackwardRightWhite();
				m_chessBoard.clearKnightJump2ForwardLeftWhite();
				m_chessBoard.clearKnightJump2ForwardRightWhite();
				
				possibleMoves.addAll(m_chessBoard.checkKnightJump1BackwardLeftWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkKnightJump1BackwardRightWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkKnightJump1ForwardLeftWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkKnightJump1ForwardRightWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkKnightJump2BackwardLeftWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkKnightJump2BackwardRightWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkKnightJump2ForwardLeftWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkKnightJump2ForwardRightWhite(colour, boxRow, boxColumn));
				
				return possibleMoves;
			}
			
			else if(m_chessBoard.m_boxes[boxRow][boxColumn].m_piece instanceof Bishop){
				
				m_chessBoard.clearDiagonalBackwardLeftWhite();
				m_chessBoard.clearDiagonalBackwardRightWhite();
				m_chessBoard.clearDiagonalForwardLeftWhite();
				m_chessBoard.clearDiagonalForwardRightWhite();
				
				possibleMoves.addAll(m_chessBoard.checkBackwardDiagonalLeftWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkBackwardDiagonalRightWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkForwardDiagonalLeftWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkForwardDiagonalRightWhite(colour, boxRow, boxColumn));
				
				return possibleMoves;
				
			}
			
			else if(m_chessBoard.m_boxes[boxRow][boxColumn].m_piece instanceof Queen){
				
				m_chessBoard.clearHorizontalLeftWhite();
				m_chessBoard.clearHorizontalRightWhite();
				m_chessBoard.clearVerticalForwardWhite();
				m_chessBoard.clearVerticalBackwardWhite();
				m_chessBoard.clearDiagonalBackwardLeftWhite();
				m_chessBoard.clearDiagonalBackwardRightWhite();
				m_chessBoard.clearDiagonalForwardLeftWhite();
				m_chessBoard.clearDiagonalForwardRightWhite();
				
				
				possibleMoves.addAll(m_chessBoard.checkBackwardDiagonalLeftWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkBackwardDiagonalRightWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkForwardDiagonalLeftWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkForwardDiagonalRightWhite(colour, boxRow, boxColumn));
				
				possibleMoves.addAll(m_chessBoard.checkHorizontalLeftWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkHorizontalRightWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkVerticalForwardWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkVerticalBackwardWhite(colour, boxRow, boxColumn));
				return possibleMoves;
			}
			
			//Must ensure that place king is moving to will not result in checkmate.
			else if(m_chessBoard.m_boxes[boxRow][boxColumn].m_piece instanceof King){
				m_chessBoard.clearSingleBoxDiagonalBackwardLeftWhite();
				m_chessBoard.clearSingleBoxDiagonalBackwardRightWhite();
				m_chessBoard.clearSingleBoxDiagonalForwardLeftWhite();
				m_chessBoard.clearSingleBoxDiagonalForwardRightWhite();
				m_chessBoard.clearSingleBoxHorizontalRightWhite();
				m_chessBoard.clearSingleBoxHorizontalLeftWhite();
				m_chessBoard.clearSingleBoxVerticalForwardWhite();
				m_chessBoard.clearSingleBoxVerticalBackwardWhite();
				
				possibleMoves.addAll(m_chessBoard.checkDiagonalBackwardLeftOneBoxWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkDiagonalBackwardRightOneBoxWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkDiagonalForwardRightOneBoxWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkDiagonalForwardLeftOneBoxWhite(colour, boxRow, boxColumn));
				
				possibleMoves.addAll(m_chessBoard.checkHorizontalRightOneBoxWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkHorizontalLeftOneBoxWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkVerticalForwardOneBoxWhite(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkVerticalBackwardOneBoxWhite(colour, boxRow, boxColumn));
				return possibleMoves;
			}
			else{
				return possibleMoves;
			}
			//If the piece is black in colour.
		}else{
			
			if(m_chessBoard.m_boxes[boxRow][boxColumn].m_piece instanceof Pawn){
				m_chessBoard.clearPawnDiagonalForwardLeftBlack();
				m_chessBoard.clearPawnDiagonalForwardRightBlack();
				m_chessBoard.clearPawnVerticalForwardBlack();
				m_chessBoard.clearPawnVerticalForwardFirstMoveWithPieceBlack();
				m_chessBoard.clearPawnEnPassantLeftBlack();
				m_chessBoard.clearPawnEnPassantRightBlack();
				
				possibleMoves.addAll(m_chessBoard.checkPawnEnPassantLeftBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkPawnEnPassantRightBlack(colour, boxRow, boxColumn));
				
				possibleMoves.addAll(m_chessBoard.checkPawnForwardDiagonalRightBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkPawnForwardDiagonalLeftBlack(colour, boxRow, boxColumn));
				
				
				if(!((Pawn) m_chessBoard.m_boxes[boxRow][boxColumn].m_piece).hadFirstMove()){
					possibleMoves.addAll(m_chessBoard.checkPawnForwardFirstMoveWithPieceBlack(colour, boxRow, boxColumn));
					((Pawn) m_chessBoard.m_boxes[boxRow][boxColumn].m_piece).completedFirstMove();
				}
				else{
					possibleMoves.addAll(m_chessBoard.checkPawnForwardOneBoxBlack(colour, boxRow, boxColumn));
				}
				
				return possibleMoves;
				
			}
			
			else if(m_chessBoard.m_boxes[boxRow][boxColumn].m_piece instanceof Rook){
				
				m_chessBoard.clearHorizontalLeftBlack();
				m_chessBoard.clearHorizontalRightBlack();
				m_chessBoard.clearVerticalForwardBlack();
				m_chessBoard.clearVerticalBackwardBlack();
				
				possibleMoves.addAll(m_chessBoard.checkHorizontalLeftBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkHorizontalRightBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkVerticalForwardBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkVerticalBackwardBlack(colour, boxRow, boxColumn));
				
				return possibleMoves;
			}
			
			else if(m_chessBoard.m_boxes[boxRow][boxColumn].m_piece instanceof Knight){
				
				m_chessBoard.clearKnightJump1BackwardLeftBlack();
				m_chessBoard.clearKnightJump1BackwardRightBlack();
				m_chessBoard.clearKnightJump1ForwardRightBlack();
				m_chessBoard.clearKnightJump1ForwardLeftBlack();
				m_chessBoard.clearKnightJump2BackwardLeftBlack();
				m_chessBoard.clearKnightJump2BackwardRightBlack();
				m_chessBoard.clearKnightJump2ForwardLeftBlack();
				m_chessBoard.clearKnightJump2ForwardRightBlack();
				
				possibleMoves.addAll(m_chessBoard.checkKnightJump1BackwardLeftBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkKnightJump1BackwardRightBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkKnightJump1ForwardLeftBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkKnightJump1ForwardRightBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkKnightJump2BackwardLeftBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkKnightJump2BackwardRightBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkKnightJump2ForwardLeftBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkKnightJump2ForwardRightBlack(colour, boxRow, boxColumn));
				
				return possibleMoves;
			}
			
			else if(m_chessBoard.m_boxes[boxRow][boxColumn].m_piece instanceof Bishop){
				
				m_chessBoard.clearDiagonalBackwardLeftBlack();
				m_chessBoard.clearDiagonalBackwardRightBlack();
				m_chessBoard.clearDiagonalForwardLeftBlack();
				m_chessBoard.clearDiagonalForwardRightBlack();
				
				possibleMoves.addAll(m_chessBoard.checkBackwardDiagonalLeftBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkBackwardDiagonalRightBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkForwardDiagonalLeftBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkForwardDiagonalRightBlack(colour, boxRow, boxColumn));
				
				return possibleMoves;
				
			}
			
			else if(m_chessBoard.m_boxes[boxRow][boxColumn].m_piece instanceof Queen){
				
				m_chessBoard.clearDiagonalBackwardLeftBlack();
				m_chessBoard.clearDiagonalBackwardRightBlack();
				m_chessBoard.clearDiagonalForwardLeftBlack();
				m_chessBoard.clearDiagonalForwardRightBlack();
				m_chessBoard.clearHorizontalLeftBlack();
				m_chessBoard.clearHorizontalRightBlack();
				m_chessBoard.clearVerticalForwardBlack();
				m_chessBoard.clearVerticalBackwardBlack();
				
				possibleMoves.addAll(m_chessBoard.checkBackwardDiagonalLeftBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkBackwardDiagonalRightBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkForwardDiagonalLeftBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkForwardDiagonalRightBlack(colour, boxRow, boxColumn));
				
				possibleMoves.addAll(m_chessBoard.checkHorizontalLeftBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkHorizontalRightBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkVerticalForwardBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkVerticalBackwardBlack(colour, boxRow, boxColumn));
				return possibleMoves;
			}
			
			//Must ensure that place king is moving to will not result in checkmate.
			else if(m_chessBoard.m_boxes[boxRow][boxColumn].m_piece instanceof King){
				
				m_chessBoard.clearSingleBoxDiagonalBackwardLeftBlack();
				m_chessBoard.clearSingleBoxDiagonalBackwardRightBlack();
				m_chessBoard.clearSingleBoxDiagonalForwardLeftBlack();
				m_chessBoard.clearSingleBoxDiagonalForwardRightBlack();
				m_chessBoard.clearSingleBoxHorizontalRightBlack();
				m_chessBoard.clearSingleBoxHorizontalLeftBlack();
				m_chessBoard.clearSingleBoxVerticalForwardBlack();
				m_chessBoard.clearSingleBoxVerticalBackwardBlack();
				
				possibleMoves.addAll(m_chessBoard.checkDiagonalBackwardLeftOneBoxBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkDiagonalBackwardRightOneBoxBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkDiagonalForwardRightOneBoxBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkDiagonalForwardLeftOneBoxBlack(colour, boxRow, boxColumn));
				
				possibleMoves.addAll(m_chessBoard.checkHorizontalRightOneBoxBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkHorizontalLeftOneBoxBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkVerticalForwardOneBoxBlack(colour, boxRow, boxColumn));
				possibleMoves.addAll(m_chessBoard.checkVerticalBackwardOneBoxBlack(colour, boxRow, boxColumn));
				return possibleMoves;
			}
			else{
				return possibleMoves;
			}
			
			
		}
		
	}
	
}
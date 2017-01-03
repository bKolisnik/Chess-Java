package chess;

import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game{

	private ChessBoard m_chessBoard;
	private Player m_player1;
	private Player m_player2;
	
	
	private Boolean m_playerWhiteTurn = true;
	
	
	public Game(ChessBoard chessBoard, Player player1, Player player2){
		m_chessBoard = chessBoard;
		m_player1 = player1;
		m_player2 = player2;
		
		initializePieces();
		
		//Start gameloop
		
		
		
	}
	
	private void initializePieces(){
		initializeWhitePieces();
		initializeBlackPieces();
		
	}
	
	private void initializeBlackPieces(){
		
		
		m_chessBoard.m_boxes[7][0].m_piece = new Rook("black");
		m_chessBoard.m_boxes[7][1].m_piece = new Knight("black");
		m_chessBoard.m_boxes[7][2].m_piece = new Bishop("black");
		m_chessBoard.m_boxes[7][3].m_piece = new King("black");
		m_chessBoard.m_boxes[7][4].m_piece = new Queen("black");
		m_chessBoard.m_boxes[7][5].m_piece = new Bishop("black");
		m_chessBoard.m_boxes[7][6].m_piece = new Knight("black");
		m_chessBoard.m_boxes[7][7].m_piece = new Rook("black");
		
		for(int i = 0; i < 8; i++){
			m_chessBoard.m_boxes[6][i].m_piece = new Pawn("black");
		}
		
	}
	
	private void initializeWhitePieces(){
		m_chessBoard.m_boxes[0][0].m_piece = new Rook("white");
		m_chessBoard.m_boxes[0][1].m_piece = new Knight("white");
		m_chessBoard.m_boxes[0][2].m_piece = new Bishop("white");
		m_chessBoard.m_boxes[0][3].m_piece = new King("white");
		m_chessBoard.m_boxes[0][4].m_piece = new Queen("white");
		m_chessBoard.m_boxes[0][5].m_piece = new Bishop("white");
		m_chessBoard.m_boxes[0][6].m_piece = new Knight("white");
		m_chessBoard.m_boxes[0][7].m_piece = new Rook("white");
		
		for(int i = 0; i < 8; i++){
			m_chessBoard.m_boxes[1][i].m_piece = new Pawn("white");
		}
		
	}
	
	
	public static void main(String[] args){
		Player player1 = new Player("white");
		Player player2 = new Player("black");
		Window window = new Window();
		ChessBoard chessBoard = new ChessBoard(window);
		
		window.setChessBoard(chessBoard);
		Game game = new Game(chessBoard,player1,player2);
		
		
		Frame frame = new Frame(chessBoard,window);
		
		
		
		
		
		//Implement turns
		
	}
	
}


/*
onePlaysNext = true;

 

public void switchPlayer() {

    onePlaysNext = !onePlaysNext

}

 

//Player 1:

if (onePlaysNext) {

    //player 1 acts.

    switchPlayer();

}   

 

Player 2:
if (!onePlaysNext) {

    //player 2 acts.

    switchPlayer();

}*/


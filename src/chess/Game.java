package chess;

public class Game {

	private ChessBoard m_chessBoard;
	private Player m_player1;
	private Player m_player2;
	
	
	public Game(ChessBoard chessBoard, Player player1, Player player2){
		m_chessBoard = chessBoard;
		m_player1 = player1;
		m_player2 = player2;
		
		initializePieces();
		
	}
	
	private void initializePieces(){
		initializeWhitePieces();
		initializeBlackPieces();
		
	}
	
	private void initializeBlackPieces(){
		
		
		m_chessBoard.m_boxes[7][0].m_Piece = new Rook("black");
		m_chessBoard.m_boxes[7][1].m_Piece = new Knight("black");
		m_chessBoard.m_boxes[7][2].m_Piece = new Bishop("black");
		m_chessBoard.m_boxes[7][3].m_Piece = new Queen("black");
		m_chessBoard.m_boxes[7][4].m_Piece = new King("black");
		m_chessBoard.m_boxes[7][5].m_Piece = new Bishop("black");
		m_chessBoard.m_boxes[7][6].m_Piece = new Knight("black");
		m_chessBoard.m_boxes[7][7].m_Piece = new Rook("black");
		
		for(int i = 0; i < 8; i++){
			m_chessBoard.m_boxes[6][i].m_Piece = new Pawn("black");
		}
		
	}
	
	private void initializeWhitePieces(){
		m_chessBoard.m_boxes[0][0].m_Piece = new Rook("white");
		m_chessBoard.m_boxes[0][1].m_Piece = new Knight("white");
		m_chessBoard.m_boxes[0][2].m_Piece = new Bishop("white");
		m_chessBoard.m_boxes[0][3].m_Piece = new Queen("white");
		m_chessBoard.m_boxes[0][4].m_Piece = new King("white");
		m_chessBoard.m_boxes[0][5].m_Piece = new Bishop("white");
		m_chessBoard.m_boxes[0][6].m_Piece = new Knight("white");
		m_chessBoard.m_boxes[0][7].m_Piece = new Rook("white");
		
		for(int i = 0; i < 8; i++){
			m_chessBoard.m_boxes[1][i].m_Piece = new Pawn("white");
		}
		
	}
	
	
	public static void main(String[] args){
		Player player1 = new Player();
		Player player2 = new Player();
		ChessBoard chessBoard = new ChessBoard();
		
		Game game = new Game(chessBoard,player1,player2);
		
		
	}
	
}

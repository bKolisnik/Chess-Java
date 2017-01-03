package chess;

import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Frame {
	public Frame(ChessBoard chessBoard, JPanel window){
		
		JLabel turnLbl = new JLabel("Turn:");
		JLabel turnLblPlayer=new JLabel("White");
		
		
		URL url;
		JFrame frame = new JFrame("Chess");
		frame.setSize(500,440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		System.out.println("Launched");
		
		//this
		url = this.getClass().getResource("Chess_Pieces_SpriteSmall.png");
		
		if(url==null){
			System.out.println("No file found");
			System.exit(0);
		}
		
		BufferedImage chessSheet = null;
		
		
		try {
			chessSheet = ImageIO.read(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedImage whitePawn = chessSheet.getSubimage(250, 0, 50, 50);
		BufferedImage blackPawn = chessSheet.getSubimage(250, 50, 50, 50);
		BufferedImage whiteRook = chessSheet.getSubimage(200, 0, 50, 50);
		BufferedImage blackRook = chessSheet.getSubimage(200,50 ,50 ,50 );
		BufferedImage whiteKnight = chessSheet.getSubimage(150, 0, 50, 50);
		BufferedImage blackKnight = chessSheet.getSubimage(150, 50, 50, 50);
		BufferedImage whiteBishop = chessSheet.getSubimage(100, 0, 50, 50);
		BufferedImage blackBishop = chessSheet.getSubimage(100, 50, 50, 50);
		BufferedImage whiteQueen = chessSheet.getSubimage(50, 0, 50, 50);
		BufferedImage blackQueen = chessSheet.getSubimage(50, 50, 50, 50);
		BufferedImage whiteKing = chessSheet.getSubimage(0, 0, 50, 50);
		BufferedImage blackKing = chessSheet.getSubimage(0, 50, 50, 50);
		
		
		for(int i = 0; i < 8; i++){
			
			chessBoard.m_boxes[1][i].m_piece.setImage(whitePawn);
		}
		
		for(int i = 0; i < 8; i++){
			
			chessBoard.m_boxes[1][i].m_piece.setImage(whitePawn);
		}
		
		for(int i = 0; i < 8; i++){
			
			chessBoard.m_boxes[6][i].m_piece.setImage(blackPawn);
		}
		
		chessBoard.m_boxes[0][0].m_piece.setImage(whiteRook);
		chessBoard.m_boxes[0][1].m_piece.setImage(whiteKnight);
		chessBoard.m_boxes[0][2].m_piece.setImage(whiteBishop);
		chessBoard.m_boxes[0][3].m_piece.setImage(whiteKing);
		chessBoard.m_boxes[0][4].m_piece.setImage(whiteQueen);
		chessBoard.m_boxes[0][5].m_piece.setImage(whiteBishop);
		chessBoard.m_boxes[0][6].m_piece.setImage(whiteKnight);
		chessBoard.m_boxes[0][7].m_piece.setImage(whiteRook);
		
		chessBoard.m_boxes[7][0].m_piece.setImage(blackRook);
		chessBoard.m_boxes[7][1].m_piece.setImage(blackKnight);
		chessBoard.m_boxes[7][2].m_piece.setImage(blackBishop);
		chessBoard.m_boxes[7][3].m_piece.setImage(blackKing);
		chessBoard.m_boxes[7][4].m_piece.setImage(blackQueen);
		chessBoard.m_boxes[7][5].m_piece.setImage(blackBishop);
		chessBoard.m_boxes[7][6].m_piece.setImage(blackKnight);
		chessBoard.m_boxes[7][7].m_piece.setImage(blackRook);
		
		frame.setIconImage(whitePawn);
		frame.setVisible(true);
		
		window.setBounds(0, 0, frame.WIDTH, frame.HEIGHT);
		window.setLayout(null);
		frame.add(window);
		//turnLbl.setSize(20,20);
		//turnLblPlayer.setSize(20,20);
		//window.add(turnLbl);
		//window.add(turnLblPlayer);
		//turnLbl.setBounds(415,20,40,40);
		//turnLblPlayer.setBounds(415,40,40,40);
		
		frame.getContentPane().repaint();
		frame.validate();
		
		frame.addMouseListener((MouseListener) window);
	}
}

package chess;

public class Player {

	private String m_colour;
	private int m_turn = 0;
	
	public Player(String colour){
		m_colour = colour;
	}
	
	public int getTurn(){
		return m_turn;
	}
	
	public void incrementTurnByOne(){
		m_turn++;
		
	}
	
	public void setTurn(int turn){
		m_turn = turn;
	}
}

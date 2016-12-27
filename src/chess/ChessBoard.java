package chess;

public class ChessBoard {
	
	Box[][] m_boxes;
	Boolean m_rowEven;
	
	//0 for black, 1 for white
	
	public ChessBoard(){
		
		initializeBoard();
		
	}
	
	private void initializeChessBoard(){
		initializeBoard();
		//initializePieces();
	}
	
	private void initializePieces(){
		
		
		
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
	
}

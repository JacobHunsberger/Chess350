package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class ChessViewTaken extends JPanel {
	
	final int size = 4;
	
	private TakenPiecesPanel white;
	
	private TakenPiecesPanel black;
	
	public ChessViewTaken() {
		setLayout(new GridLayout(2, 0));
		white = new TakenPiecesPanel();
		black = new TakenPiecesPanel();
	}
	
	public void setPiece(JButton piece, int row, int column,
			int select) { 
		TakenPiecesPanel player = null;
		
		if (select == 0) {
			player = white;
		} else {
			player = black;
		}
		
		try {
			remove(player);
		} catch (NullPointerException e) {
			
		}
		
		player.setPiece(piece, row, column);
		add(player);
		player.revalidate();
		player.repaint();
	}
	
}

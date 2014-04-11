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
		setLayout(new GridLayout(2, 1));
		white = new TakenPiecesPanel();
		black = new TakenPiecesPanel();
		add(white);
		add(black);
	}
	
	public void setWhiteTaken(JButton piece, int row, int column) {
		try {
			remove(white);
			remove(black);
		} catch (NullPointerException e) {
			
		}
		white.setPiece(piece, row, column);
		add(white);
		add(black);
		refresh();
	}
	
	public void setBlackTaken(JButton piece, int row, int column) {
		try {
			remove(white);
			remove(black);
		} catch (NullPointerException e) {
			
		}
		black.setPiece(piece, row, column);
		add(white);
		add(black);
		refresh();
	}
	
	public void refresh() {
		white.refresh();
		black.refresh();
	}	
}

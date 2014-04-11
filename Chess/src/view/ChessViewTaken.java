package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	public void setWhiteTaken(JLabel piece, int row, int column) {
		try {
			remove(white);
			remove(black);
		} catch (NullPointerException e) {
			
		}
		white.setPiece(piece, row, column);
		add(white);
		add(black);
	}
	
	public void setBlackTaken(JLabel piece, int row, int column) {
		try {
			remove(white);
			remove(black);
		} catch (NullPointerException e) {
			
		}
		black.setPiece(piece, row, column);
		add(white);
		add(black);
	}
	
	public JLabel getWhiteTaken(int row, int column) {
		return white.getPiece(row, column);
	}
	
	public JLabel getBlackTaken(int row, int column) {
		return black.getPiece(row, column);
	}
	
	public void refresh() {
		white.refresh();
		black.refresh();
	}	
}

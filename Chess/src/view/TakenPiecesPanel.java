package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class TakenPiecesPanel extends JPanel {
	
	private final int size = 4;
	
	private JLabel[][] takenPieces;
	
	public TakenPiecesPanel () {
		setLayout(new GridLayout(size, size));
		takenPieces = new JLabel[size][size];
	}
	
	public void setPiece (JLabel piece, int row, int column) {
		try {
			remove(takenPieces[row][column]);
		} catch (NullPointerException e) {
			
		}
		
		takenPieces[row][column] = piece;
		add(takenPieces[row][column]);
	}
	
	public JLabel getPiece (int row, int column) {
		return takenPieces[row][column];
	}
	
	public void refresh() {
		revalidate();
		repaint();
	}
}

package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;

public class TakenPiecesPanel extends JPanel {
	
	private final int size = 4;
	
	private JButton[][] takenPieces;
	
	public TakenPiecesPanel () {
		setLayout(new GridLayout(size, size));
		takenPieces = new JButton[size][size];
	}
	
	public void setPiece (JButton piece, int row, int column) {
		try {
			remove(takenPieces[row][column]);
		} catch (NullPointerException e) {
			
		}
		
		takenPieces[row][column] = piece;
		add(takenPieces[row][column]);
	}
}

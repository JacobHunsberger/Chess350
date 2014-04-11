package view;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class TakenPiecesPanel extends JPanel {
	
	private final int size = 4;
	
	private JButton[][] takenPieces;
	
	public TakenPiecesPanel () {
		setLayout(new GridLayout(size, size));
		takenPieces = new JButton[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				JButton temp = new JButton();
				temp.setPreferredSize(new Dimension(50, 50));
				temp.setBackground(Color.WHITE);
				setPiece(temp, i, j);
			}
		}
		refresh();
	}
	
	public void setPiece (JButton piece, int row, int column) {
		try {
			remove(takenPieces[row][column]);
		} catch (NullPointerException e) {
			
		}
		
		takenPieces[row][column] = piece;
		add(takenPieces[row][column]);
	}
	
	public void refresh() {
		revalidate();
		repaint();
	}
}

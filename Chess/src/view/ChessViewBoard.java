package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;

public class ChessViewBoard extends JPanel {
	
	final int size = 8;
	
	private JButton[][] board;
	
	public ChessViewBoard() {
		setLayout(new GridLayout(size, size));
		board = new JButton[size][size];
	}
	
	public void setPieceButton(JButton piece, int row, int column) {
		try {
			remove(board[row][column]);
		} catch (NullPointerException e) {
			
		}
		board[row][column] = piece;
		add(board[row][column]);
	}
	
	public JButton getPieceButton(int row, int column) {
		return board[row][column];
	}
	
	public void refresh() {
		revalidate();
		repaint();
	}
}

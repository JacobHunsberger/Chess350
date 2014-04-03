package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class ChessViewBoard extends JPanel {
	
	final static int size = 8;
	
	private JButton[][] board;
	
	public ChessViewBoard() {
		setLayout(new GridLayout(size, size));
		setSize(500, 500);
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
}

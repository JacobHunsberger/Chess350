package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;

public class ChessViewBoard extends JPanel {
	
	final int size = 8;
	/**
	 * 2D JButton for the board.
	 */
	private JButton[][] board;
	/**
	 * Constructor for the board to set it up.
	 */
	public ChessViewBoard() {
		setLayout(new GridLayout(size, size));
		board = new JButton[size][size];
	}
	/**
	 * 
	 * @param piece to set.
	 * @param row the row of the piece.
	 * @param column the column of piece.
	 */
	public final void setPieceButton(final JButton piece, final int row, 
			final int column) {
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

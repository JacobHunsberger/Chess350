package view;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridLayout;
/**
 * Class for the view of the board.
 * @author Jacob Hunsberger, Jon Powers, Jared Thomas
 *
 */
@SuppressWarnings("serial")
public class ChessViewBoard extends JPanel {
	/**
	 * Size of the board.
	 */
	private final int size = 8;
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
			//e.printStackTrace();
		}
		board[row][column] = piece;
		add(board[row][column]);
	}
	/**
	 * 
	 * @param row of the piece.
	 * @param column of the piece.
	 * @return JButton for hte piece.
	 */
	public final JButton getPieceButton(final int row, final int column) {
		return board[row][column];
	}
	/**
	 * Refresh the board.
	 */
	public final void refresh() {
		revalidate();
		repaint();
	}
}

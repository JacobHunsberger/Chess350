package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

/**
 * The main class to start the game.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
@SuppressWarnings("serial")
public final class ChessView extends JFrame {
	/**
	 * The menu bar.
	 */
	private ChessMenu menu;
	/**
	 * Local copy of the board.
	 */
	private ChessViewBoard board;
	/**
	 * Local copy of the chess view taken.
	 */
	private ChessViewTaken taken;
	
	/**
	 * Constructor for ChessView Class.
	 */
	public ChessView() {
		this.setResizable(false);
		menu = new ChessMenu();
		board = new ChessViewBoard();
		taken = new ChessViewTaken();
		final int sevenHun = 700, fiveHun = 500;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menu);
		setLayout(new BorderLayout());
		getContentPane().add(board, BorderLayout.CENTER);
		getContentPane().add(taken, BorderLayout.EAST);
		pack();
		setSize(sevenHun, fiveHun);
	}
	/**
	 * 
	 * @param button to set the piece at. 
	 * @param row of the button.
	 * @param column of the button.
	 */
	public void setPieceButton(final JButton button, 
			final int row, final int column) {
		board.setPieceButton(button, row, column);
	}
	/**
	 * Get the button for the piece.
	 * @param row the row of the piece.
	 * @param column the column of the piece.
	 * @return JButton for the piece.
	 */
	public JButton getPieceButton(final int row, final int column) {
		return board.getPieceButton(row, column);
	}
	/**
	 * White only.
	 * @param piece for the label.
	 * @param row of the piece.
	 * @param column of the piece.
	 */
	public void setWhiteLabel(final JLabel piece, 
			final int row, final int column) {
		taken.setWhiteTaken(piece, row, column);
	}
	/**
	 * 
	 * @param row of the jlabel.
	 * @param column of the jlabel.
	 * @return JLabel white.
	 */
	public JLabel getWhiteLabel(final int row, final int column) {
		return taken.getWhiteTaken(row, column);
	}
	/**
	 * Black only.
	 * @param piece jlabel for the piece.
	 * @param row of the piece.
	 * @param column of the piece.
	 */
	public void setBlackLabel(final JLabel piece, 
			final int row, final int column) {
		taken.setBlackTaken(piece, row, column);
	}
	/**
	 * Black only.
	 * @param row of the label.
	 * @param column of the jlabel.
	 * @return JLabel of the piece.
	 */
	public JLabel getBlackLabel(final int row, final int column) {
		return taken.getBlackTaken(row, column);
	}
	
	/**
	 * Call after setting pieces.
	 */
	public void refresh() {
		board.refresh();
		taken.refresh();
	}
}
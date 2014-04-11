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
	
	private ChessViewBoard board;
	
	private ChessViewTaken taken;
	
	/**
	 * Constructor for ChessView Class.
	 */
	public ChessView() {
		menu = new ChessMenu();
		board = new ChessViewBoard();
		taken = new ChessViewTaken();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menu);
		setLayout(new BorderLayout());
		getContentPane().add(board, BorderLayout.CENTER);
		getContentPane().add(taken, BorderLayout.EAST);
		pack();
		setSize(700,500);
	}
	
	public void setPieceButton(JButton button, int row, int column) {
		board.setPieceButton(button, row, column);
	}
	
	public JButton getPieceButton(int row, int column) {
		return board.getPieceButton(row, column);
	}
	
	public void setWhiteLabel(JLabel piece, int row, int column) {
		taken.setWhiteTaken(piece, row, column);
	}
	
	public JLabel getWhiteLabel(int row, int column) {
		return taken.getWhiteTaken(row, column);
	}
	
	public void setBlackLabel(JLabel piece, int row, int column) {
		taken.setBlackTaken(piece, row, column);
	}
	
	public JLabel getBlackLabel(int row, int column) {
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
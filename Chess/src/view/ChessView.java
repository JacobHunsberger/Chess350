package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;

import presenter.ChessPresenter;

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
		getContentPane().add(board, BorderLayout.WEST);
		getContentPane().add(taken, BorderLayout.EAST);
		pack();
		setSize(500,500);
		setVisible(true);
	}
	
	public void setPieceButton(JButton button, int row, int column) {
		board.setPieceButton(button, row, column);
	}
	
	public JButton getPieceButton(int row, int column) {
		return board.getPieceButton(row, column);
	}
	
	public void setPieceTaken(JButton piece, int row, int column,
			int select) {
		taken.setPiece(piece, row, column, select);
	}
	
	/**
	 * Call after setting pieces.
	 */
	public void refresh() {
		board.revalidate();
		board.repaint();
		taken.revalidate();
		taken.repaint();
	}
}
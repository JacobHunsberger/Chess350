package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

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
		getContentPane().add(board, BorderLayout.CENTER);
		getContentPane().add(taken, BorderLayout.EAST);
		pack();
		setSize(700,500);
		setVisible(true);
		taken.refresh();
	}
	
	public void setPieceButton(JButton button, int row, int column) {
		board.setPieceButton(button, row, column);
	}
	
	public JButton getPieceButton(int row, int column) {
		return board.getPieceButton(row, column);
	}
	
	public void setWhiteTaken(JButton piece, int row, int column) {
		taken.setWhiteTaken(piece, row, column);
	}
	
	public void setBlackTaken(JButton piece, int row, int column) {
		taken.setBlackTaken(piece, row, column);
	}
	
	/**
	 * Call after setting pieces.
	 */
	public void refresh() {
		board.revalidate();
		board.repaint();
		taken.refresh();
	}
}
/**
 * 
 */
package presenter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.ChessModel;
import model.Move;
import model.Player;
/**
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 *
 */
@SuppressWarnings("serial")
public class ChessPresenterBoard extends JPanel {

	/**
	 *  Create model for the board.
	 */
	private ChessModel model;
	/**
	 * 2D array of Jbuttons for the board.
	 */
	private JButton[][] buttonBoard;
	/**
	 * For the select or the place phase of the pieces.
	 */
	private Boolean select;
	/**
	 *  Local from space int.
	 */
	private JButton fromSpace;
	/**
	 *  Local from row int.
	 */
	private int fromRow;
	/**
	 *  Local to row int.
	 */
	private int toRow;
	/**
	 *  Local from column int.
	 */
	private int fromColumn;
	/**
	 *  Local to column int.
	 */
	private int toColumn;
	/**
	 * Constructor for ChessViewBoard.
	 */
	public ChessPresenterBoard() {
		final int eight = 8;
		setLayout(new GridLayout(eight, eight));
		buttonBoard = new JButton[eight][eight];
		model = new ChessModel();
		updateBoard();
		select = false;
		//localCheck = false;
	}
	/**
	 *  Local class buttonListener implements Actionlistener for all JButtons.
	 */
	private class ButtonListener implements ActionListener {
		/**
		 * Method handles action.
		 * @param e The Action.
		 */
		public void actionPerformed(final ActionEvent e) {
			final int eight = 8;
			if (!select) {
				fromSpace = (JButton) e.getSource();
				for (int i = 0; i < eight; i++) {
					for (int j = 0; j < eight; j++) {
						if (buttonBoard[i][j] == fromSpace) {
							if (model.pieceAt(i, j) != null) {
								if (model.pieceAt(i, j).player().
										equals(model.currentPlayer())) {
									select = true;
									fromRow = i;
									fromColumn = j;
									i = eight;
									j = eight;
								}
							}
						}
					}
				}
			} else {
				fromSpace = (JButton) e.getSource();
				for (int i = 0; i < eight; i++) {
					for (int j = 0; j < eight; j++) {
						if (buttonBoard[i][j] == fromSpace) {
								select = true;
								toRow = i;
								toColumn = j;
								i = eight;
								j = eight;

						}
					}
				}
				Move m = new Move(fromRow, fromColumn, toRow, toColumn);
				if (!model.inCheck(m)) {
					model.move(m);
//					if (model.inCheck(m)) {
//						int[] temp = model.findKing(model.currentPlayer());
//						updateBoard();
//						highlightCheck(temp[1], temp[0]);
//					} else {
//						updateBoard();
//					}
					updateBoard();		
				}
				select = false;	
			}
		}
	}
	/**
	 * Highlights a check.
	 * @param row Row to highlight.
	 * @param col Column to highlight.
	 */
	private void highlightCheck(final int row, final int col) {
		buttonBoard[row][col].setBackground(Color.yellow);
		revalidate();
		repaint();
	}
	/**
	 *  Refreshes the board with new graphics for JButtons.
	 */
	private void updateBoard() {
		final int eight = 8;
		final int fifty = 50;
		final float hue = 24.0f;
		final float sat = 0.42f;
		final float bright = 0.30f;
		ButtonListener buttonListener = new ButtonListener();
		int count = 0;		
		removeAll();
		for (int i = 0; i < eight; i++) {
			for (int k = 0; k < eight; k++) {
				buttonBoard[i][k] = new JButton();
				buttonBoard[i][k].addActionListener(buttonListener);
				buttonBoard[i][k].setPreferredSize(new Dimension(fifty, fifty));
				if (((count++) % 2) == 1) {
					buttonBoard[i][k].setBackground(Color.getHSBColor(
							hue, sat, bright));
				} else {
					buttonBoard[i][k].setBackground(Color.white);
				}
				if (model.pieceAt(i, k) != null) {
					setImage(buttonBoard[i][k], i, k, model.pieceAt(
							i, k).player());
				}
				add(buttonBoard[i][k]);
			}
			count--;
		}
		revalidate();
		repaint();
	}
	/**
	 * Sets the image.
	 * @param b button
	 * @param r row
	 * @param c column
	 * @param p player
	 */
	private void setImage(final JButton b, final int r,
			final int c, final Player p) {
		try {
			ImageIcon is = null;
			if (model.pieceAt(r, c).player() == p) {
				if (model.pieceAt(r, c).type() == "king") {
					is = new ImageIcon(getClass().getResource(
							"images/King/king" + p.toString() + ".png"));
				} else if (model.pieceAt(r, c).type() == "queen") {
					is = new ImageIcon(getClass().getResource(
							"images/Queen/queen" + p.toString() + ".png"));
				} else if (model.pieceAt(r, c).type() == "bishop") {
					is = new ImageIcon(getClass().getResource(
							"images/Bishop/bishop" + p.toString() + ".png"));
				} else if (model.pieceAt(r, c).type() == "rook") {
					is = new ImageIcon(getClass().getResource(
							"images/Rook/rook" + p.toString() + ".png"));
				} else if (model.pieceAt(r, c).type() == "knight") {
					is = new ImageIcon(getClass().getResource(
							"images/Knight/knight" + p.toString() + ".png"));
				} else if (model.pieceAt(r, c).type() == "pawn") {
					is = new ImageIcon(getClass().getResource(
							"images/Pawn/pawn" + p.toString() + ".png"));
				}
			}
			b.setIcon(is);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
}

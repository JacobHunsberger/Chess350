package presenter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.ChessView;
import model.ChessModel;
import model.Move;
import model.Player;

/**
 * The view class to build the view of the game.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
public class ChessPresenter {

	final int size = 8;
	
	private ChessView view;
	
	private ChessModel model;
	
	private boolean select;
	
	/**
	 *  Selected space.
	 */
	private JButton space;
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
	 * Constructor for ChessPresenter Class.
	 */
	public ChessPresenter(ChessModel m, ChessView v) {
		model = m;
		view = v;
		updateBoard();
		select = false;
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!select) {
				space = (JButton) e.getSource();
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						if (space == view.getPieceButton(i, j)) {
							if (model.pieceAt(i, j) != null) {
								if (model.pieceAt(i, j).player().
									equals(model.currentPlayer())) {
									select = true;
									fromRow = i;
									fromColumn = j;
									i = size;
									j = size;
								}
							}
						}
					}
				}
			} else {
				space = (JButton) e.getSource();
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						if (space == view.getPieceButton(i, j)) {
								select = true;
								toRow = i;
								toColumn = j;
								i = size;
								j = size;
						}
					}
				}
				Move m = new Move(fromRow, fromColumn, toRow, toColumn);
				if (!model.inCheck(m)) {
					model.move(m);
					int[] temp = model.findKing(model.currentPlayer());
					Move tempMove = new Move(m.getToRow(), m.getToColumn(), 
							temp[1], temp[0]);
					model.cyclePlayer();
					updateBoard();
					if (model.isValidMove(tempMove)) {
						highlightCheck(temp[1], temp[0]);
					}
					model.cyclePlayer();
//					if (model.inCheck(m)) {
//						int[] temp = model.findKing(model.currentPlayer());
//						updateBoard();
//						highlightCheck(temp[1], temp[0]);
//					} else {
//						updateBoard();
//					}
					//updateBoard();		
				}
				select = false;	
			}
		}
	}
	
	private void updateBoard() {
		final float hue = 24.0f;
		final float sat = 0.42f;
		final float bright = 0.30f;
		
		ButtonListener buttonListener = new ButtonListener();
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				space = new JButton();
				space.addActionListener(buttonListener);
				space.setPreferredSize(new Dimension(50, 50));
				
				if ((j % 2) == (i % 2)) {
					space.setBackground(Color.getHSBColor(
							hue, sat, bright));
				} else {
					space.setBackground(Color.white);
				}
				
				if (model.pieceAt(i,  j) != null) {
					setImage(space, i, j, model.pieceAt(
							i, j).player());
				}
				view.setPieceButton(space, i, j);
			}
		}
		view.refresh();
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
	
	/**
	* Highlights a check.
	* @param row Row to highlight.
	* @param col Column to highlight.
	*/
	private void highlightCheck(final int row, final int col) {
		view.getPieceButton(row, col).setBackground(Color.yellow);
	}
}

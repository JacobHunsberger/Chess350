package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;
/**
 * Class to help take care of taken pieces.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 *
 */
@SuppressWarnings("serial")
public class ChessViewTaken extends JPanel {
	/**
	 * Size of the panel.
	 */
	//private final int size = 4;
	/**
	 * Pieces taken for white.
	 */
	private TakenPiecesPanel white;
	/**
	 * Pieces taen for black.
	 */
	private TakenPiecesPanel black;
	/**
	 * Constructor for class, sets up the panel.
	 */
	public ChessViewTaken() {
		setLayout(new GridLayout(2, 1));
		white = new TakenPiecesPanel();
		black = new TakenPiecesPanel();
		add(white);
		add(black);
	}
	/**
	 * White only.
	 * @param piece the piece needed.
	 * @param row the row for the piece.
	 * @param column the column of the piece.
	 */
	public final void setWhiteTaken(final JLabel piece, final int row, 
			final int column) {
		try {
			remove(white);
			remove(black);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		white.setPiece(piece, row, column);
		add(white);
		add(black);
	}
	/**
	 * Black only.
	 * @param piece the piece needed.
	 * @param row the row of the piece.
	 * @param column the column of the piece.
	 */
	public final void setBlackTaken(final JLabel piece, final int row, 
			final int column) {
		try {
			remove(white);
			remove(black);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		black.setPiece(piece, row, column);
		add(white);
		add(black);
	}
	/**
	 * White only.
	 * @param row of the piece.
	 * @param column of the piece.
	 * @return JLabel for the piece taken.
	 */
	public final JLabel getWhiteTaken(final int row, final int column) {
		return white.getPiece(row, column);
	}
	/**
	 * Black only.
	 * @param row of the piece.
	 * @param column of the piece.
	 * @return JLabel of the piece.
	 */
	public final JLabel getBlackTaken(final int row, final int column) {
		return black.getPiece(row, column);
	}
	/**
	 * Refreshes the game.
	 */
	public final void refresh() {
		white.refresh();
		black.refresh();
	}	
}
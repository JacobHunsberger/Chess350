package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;
/**
 * Class to handel the pieces taken.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 *
 */
@SuppressWarnings("serial")
public class TakenPiecesPanel extends JPanel {
	/**
	 * Size of the panel. 
	 */
	private final int size = 4;
	/**
	 * 2D array of taken pieces.
	 */
	private JLabel[][] takenPieces;
	/**
	 * Constructor for class, sets the size.
	 */
	public TakenPiecesPanel() {
		setLayout(new GridLayout(size, size));
		takenPieces = new JLabel[size][size];
	}
	/**
	 * 
	 * @param piece the JLabel of pieces.
	 * @param row inside the JLabel.
	 * @param column inside the JLabel.
	 */
	public final void setPiece(final JLabel piece, final int row, 
			final int column) {
		try {
			remove(takenPieces[row][column]);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		takenPieces[row][column] = piece;
		add(takenPieces[row][column]);
	}
	/**
	 * 
	 * @param row of the piece.
	 * @param column of the piece.
	 * @return JLabel the peices taken.
	 */
	public final JLabel getPiece(final int row, final int column) {
		return takenPieces[row][column];
	}
	/**
	 * Refreshes the board.
	 */
	public final void refresh() {
		revalidate();
		repaint();
	}
}
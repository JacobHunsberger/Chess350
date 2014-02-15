package model;

/**
 * A pawn in a game of chess.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
public class Pawn extends ChessPiece {

	private int direction;
	private int startingRow;

	/**
	 * Constructs a new Pawn object
	 * @param color the player that owns this piece.
	 */
	public Pawn(final Player color) {
		super(color);
		direction = (this.player() == Player.WHITE ? 1 : -1);
		startingRow = (this.player() == Player.WHITE ? 1 : 6);
	}

	/**
	 * @return String "pawn"
	 */
	public final String type() {
		return "pawn";
	}

	/**
	 * @param move input the move of the pawn
	 * @param board input the board to check the move
	 * @return boolean true or false if the move is valid
	 */
	public final boolean isValidMove(final Move move, final IChessBoard board) {
		if (!super.isValidMove(move, board)) {
			return false;
		}

		// Pawn can only move one square at at time.
		// In their first move, they can move two squares if they want.
		// Pawn can only move straight forward.
		// Pawn can only capture one of the enemy by approaching diagonally
		
		// TODO
		return false;
	}

}

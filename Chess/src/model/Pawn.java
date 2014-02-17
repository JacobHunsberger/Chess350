package model;

/**
 * A pawn in a game of chess.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
public class Pawn extends ChessPiece {
	private int direction;
	private int startingRow;
	private boolean firstMove;

	/**
	 * Constructs a new Pawn object
	 * @param color the player that owns this piece.
	 */
	public Pawn(final Player color) {
		super(color);
		direction = (this.player() == Player.WHITE ? 1 : -1);
		startingRow = (this.player() == Player.WHITE ? 1 : 6);
		firstMove = false;
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
		// Pawn can only capture one of the enemy by approaching diagonally.
		
		if (firstMove) {
			// On the first move, a pawn may move 2 spaces vertically
			if (move.getToRow() == (startingRow + (2 * direction)) && 
				move.getFromColumn() == move.getToColumn()) {
				// Both spaces must be empty
				if (board.pieceAt(move.getToRow(), move.getToColumn()) == null &&
					board.pieceAt(move.getToRow(), move.getToColumn() - 1) == null) {
					
					firstMove = false;		// First move taken
					return true;			// Special move was valid
				}
			}
		}
		
		// If the pawn didn't take the special move, 
		// then it must make an ordinary move.
		
		// Pawn must move forward 1 row
	    if ((move.getFromRow() + direction) != move.getToRow()) {
	    	return false;
	    }
	    
	    // When moving straight forward, space must be empty
	    if (move.getToColumn() == move.getFromColumn()) {
	    	if (board.pieceAt(move.getToRow(), move.getToColumn()) != null) {
	    		return false;
	    	}
	    }
		
	    // Pawn may only move 1 space diagonally
	    if (Math.abs(move.getToRow() - move.getFromRow()) > 1) {
	    	return false;
	    }
	    
	    // If pawn moved diagonally, space cannot be empty or occupied
	    // by the same color (already checked in super)
	    if (board.pieceAt(move.getToRow(), move.getToColumn()) == null) {
    		return false;
    	}
		
		firstMove = false;
		return true;
	}
}

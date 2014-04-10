package model;
/**
 * A pawn in a game of chess.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
public class Pawn extends ChessPiece {
	/**
	 * int recording the direction of the piece.
	 */
	private int direction;
	/**
	 * int recording the starting row of the piece.
	 */
	private int startingRow;
	/**
	 * boolean recording the first move, true or false. 
	 */
	private boolean firstMove;
	/**
	 * Indicates that pawn may be taken en passant on the next turn.
	 */
	private boolean enPassant;
	/**
	 * Constructs a new Pawn object.
	 * @param color the player that owns this piece.
	 */
	public Pawn(final Player color) {
		super(color);
		if (this.player() == Player.WHITE) {
			direction = 1;
			startingRow = 1;
		} else {
			direction = -1;
			final int blackStart = 6;
			startingRow = blackStart;
		}
		firstMove = true;
		enPassant = false;
	}
	/**
	 * Copy constructor.
	 * @param aPawn Pawn to copy.
	 */
	public Pawn(final Pawn aPawn) {
		this(aPawn.player());
		
		firstMove = aPawn.firstMove();
		enPassant = aPawn.enPassant();
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
			if (move.getToRow() == (startingRow + (2 * direction)) 
					&& move.getFromColumn() == move.getToColumn()) {	
				if (board.pieceAt(move.getToRow(),
						move.getToColumn()) == null) {
					firstMove = false;		// First move taken
					enPassant = true;
					return true;			// Special move was valid
				}
			}
		}
		// If the pawn didn't make the special move, 
		// then it must make an ordinary move.
		// Pawn must move forward 1 row
	    if ((move.getFromRow() + direction) != move.getToRow()) {
	    	return false;
	    }
	    // Pawn may only move 1 space diagonally
	    if (Math.abs(move.getToColumn() - move.getFromColumn()) > 1) {
	    	return false;
	    }
	    // When moving straight forward, space must be empty
	    if (move.getToColumn() == move.getFromColumn()) {
	    	if (board.pieceAt(move.getToRow(), move.getToColumn()) != null) {
	    		return false;
	    	}
	    } else if (board.pieceAt(move.getToRow(), move.getToColumn()) == null) {
    		return false;
    		// If pawn moved diagonally, space cannot be empty or occupied
    	    // by the same color (already checked in super)
    	}
	    
	    enPassant = false;
		firstMove = false;
		return true;
	}
	/**
	 * This method returns true is the pawn can be promoted based on location.
	 * This should be called after the move of the pawn.
	 * @param row of the Pawn.
	 * @return boolean depending on if the pawn can be promoted
	 */
	public final boolean isPromotion(final int row) {
		final int seven = 7;
		if (this.player().equals(Player.WHITE)) {
			if (row == seven) {
				return true;
			}
			return false;
		} else {
			if (row == 0) {
				return true;
			}
			return false;
		}
	}
	/**
	 * Determines first move status.
	 * @return True if first move.
	 */
	public final boolean firstMove() {
		return firstMove;
	}
	public void setFirstMove(boolean f) {
		firstMove = f;
	}
	/**
	 * Returns true if pawn has just moved forward two spaces.
	 * @return True if pawn can be removed by en passant rule.
	 */
	public final boolean enPassant() {
		return enPassant;
	}
}
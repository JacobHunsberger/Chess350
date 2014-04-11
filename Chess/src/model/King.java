package model;

/**
 * The King in a game of chess.
 * @author Jacob Hunsberger, Jon Powers, Jared Thomas
 */
public class King extends ChessPiece {

	/**
	 * True/false for the first move.
	 */
	private boolean firstMove;
	
    /**
     * Constructs a new King object.
     *
     * @param color the player that owns this piece.
     */
    public King(final Player color) {
        super(color);
        firstMove = true;
    }
    
    /**
     * Copy constructor.
     * 
     * @param aKing King to copy.
     */
    public King(final King aKing) {
    	this(aKing.player());
    	firstMove = aKing.firstMove();
    }
    
    /**
     * This class returns a string for the king piece.
     * @return String 'king'
     */
    public final String type() {
        return "king";
    }

    /**
     * This class checks the move for the king chess piece.
     * @return boolean true or false if the move is valid.
     * @param move the move of the piece
     * @param board the board the piece is on
     */
    public final boolean isValidMove(final Move move, final IChessBoard board) {
        if (!super.isValidMove(move, board)) {
            return false;
        }

        // King can only move one square at a time but he can move
        // forward, backward, left, right, and diagonally.

        if (Math.abs(move.getToRow() - move.getFromRow()) > 1) {
            return false;
        }

        if (Math.abs(move.getFromColumn() - move.getToColumn()) > 1) {
            return false;
        }

        return true;
    }
    /**
     * Determines if this King has moved.
     * @return true if king has not moved.
     */
    public final boolean firstMove() {
    	return firstMove;
    }
    /**
     * Set status that this King has moved.
     * @param f Status of this King.
     */
    public void setFirstMove(boolean f) {
    	firstMove = f;
    }
}

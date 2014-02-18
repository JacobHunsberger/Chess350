package model;

/**
 * The King in a game of chess.
 *
 * @author Jacob Hunsberger, Jon Powers, Jared Thomas
 */
public class King extends ChessPiece {

	private boolean firstMove;
	
    /**
     * Constructs a new King object.
     *
     * @param color the player that owns this piece.
     */
    protected King(final Player color) {
        super(color);
        firstMove = true;
    }

    @Override
    public String type() {
        return "king";
    }

    @Override
    public boolean isValidMove(final Move move, final IChessBoard board) {
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

        firstMove = false;
        return true;
    }
    
    public boolean firstMove() {
    	return firstMove;
    }
}

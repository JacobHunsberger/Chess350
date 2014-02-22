package model;

/**
 * A Rook in a game of chess.
 *
 * @author Jacob Hunsberger, Jon Powers, Jared Thomas
 */
public class Rook extends ChessPiece {
	/**
	 * boolean to record first move or not.
	 */
	private boolean firstMove;
	
    /**
     * Constructor a new Rook object.
     *
     * @param color the Player that owns this piece.
     */
    public Rook(final Player color) {
        super(color);
        firstMove = true;
    }

    /**
     * @return String 'rook'
     */
    public final String type() {
        return "rook";
    }

    /**
     * @return boolean is the move valid or not
     * @param move the move of the piece
     * @param board the board the piece moves on
     */
    public final boolean isValidMove(final Move move, final IChessBoard board) {
        int fromRow = move.getFromRow();
        int toRow =   move.getToRow();
        int fromCol = move.getFromColumn();
        int toCol =   move.getToColumn();
        if (!super.isValidMove(move, board)) {
            return false;
        }
        // Rook can move forward, backward, left, and right as many
        // spaces as desired without jumping over other chess pieces;
        // can't move diagonally
        // Rooks can only move horizontally
        if (fromRow != toRow && fromCol != toCol) {
            return false;
        }
        // Vertical move
        if (fromRow == toRow) {
        	// Check empty spaces
        	for (int i = Math.min(fromCol, toCol) + 1;
        		     i < Math.max(fromCol, toCol); i++) {
        		// TODO change check to match board model
        		if (board.pieceAt(fromRow, i) != null) {
        			return false;
        		}
        	}
        } else if (fromCol == toCol) {
        	// Horizontal move
        	// Check empty spaces
        	for (int i = Math.min(fromRow, toRow) + 1;
        			 i < Math.max(fromRow, toRow); i++) {
        		// TODO change check to match board model
        		if (board.pieceAt(i, fromCol) != null) {
        			return false;
        		}
        	}
        }

        firstMove = false;
        return true;
    }
    /**
     * This is used for the castle move with the king.
     * @return boolean first move true or false
     */
    public final boolean firstMove() {
    	return firstMove;
    }
}

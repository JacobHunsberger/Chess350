package model;

/**
 * A Rook in a game of chess.
 *
 * @author Jacob Hunsberger, Jon Powers, Jared Thomas
 */
public class Rook extends ChessPiece {

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

    @Override
    public String type() {
        return "Rook";
    }

    @Override
    public boolean isValidMove(final Move move, final IChessBoard board) {
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
        }
        
        // Horizontal move
        else if (fromCol == toCol) {
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
    
    public boolean firstMove() {
    	return firstMove;
    }
}

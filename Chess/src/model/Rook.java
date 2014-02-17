package model;

/**
 * A Rook in a game of chess.
 *
 * @author Jacob Hunsberger, Jon Powers, Jared Thomas
 */
public class Rook extends ChessPiece {

    /**
     * Constructor a new Rook object.
     *
     * @param color the Player that owns this piece.
     */
    public Rook(final Player color) {
        super(color);
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

        // Moved diagonally
        if (fromRow != toRow) {
            return false;
        }

        if (fromCol != toCol) {
            return false;
        }

        // Vertical move
        if (fromRow == toRow) {
        	for (int i = Math.min(fromCol, toCol) + 1;
        		     i < Math.max(fromCol, toCol); i++) {
        		// TODO change check to match board model
        		if (board.pieceAt(fromRow, i) != null) {
        			return false;
        		}
        	}
        }
        
        // Horizontal move
        if (fromCol == toCol) {
        	for (int i = Math.min(fromCol, toCol) + 1;
        			 i < Math.max(fromCol, toCol); i++) {
        		// TODO change check to match board model
        		if (board.pieceAt(i, fromCol) != null) {
        			return false;
        		}
        	}
        }

        return true;
    }
}

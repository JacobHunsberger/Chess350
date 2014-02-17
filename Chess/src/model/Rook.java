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
        if (!super.isValidMove(move, board)) {
            return false;
        }

        // Rook can move forward, backward, left, and right as many
        // spaces as desired without jumping over other chess pieces;
        // can't move diagonally

        // Cannot move diagonally.
        if (move.getFromRow() != move.getToRow()) {
            return false;
        }

        if (move.getFromRow() != move.getToRow()) {
            return false;
        }

        // Cannot jump over spaces.

        // Vertical move.

        return false;
    }
}

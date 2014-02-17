package model;

/**
 * Class that represents a chess piece.
 *
 * @author Jacob Hunsberger, Jon Powers, Jared Thomas
 */
public abstract class ChessPiece implements IChessPiece {
    /**
     * The owner of this chess piece.
     */
    private Player owner;
    
    /**
     * Constructs a new ChessPiece
     *
     * @param color Color of new piece.
     */
    protected ChessPiece(final Player color) {
        this.owner = color;
    }

    /**
     * Returns the owner of this piece.
     *
     * @return owner Owner of this piece.
     */
    public final Player player() {
        return owner;
    }

    /**
     * Returns the type of this chess piece.
     *
     * @return Type of this chess piece.
     */
    public abstract String type();

    /**
     * Returns whether the piece at location [move.fromRow, move.fromColumn]
     * is allowed to move to location [move.fromRow, move.fromColumn].
     *
     * Note:  Pieces don't store their own location
     * (because doing so would be redundant).
     * Therefore, the [move.fromRow, move.fromColumn] component of move
     * is necessary. The this object must be the piece at location
     * [move.fromRow, move.fromColumn]. (This method makes no sense otherwise.)
     *
     * @param move  a Move object describing the move to be made.
     * @param board the chess board in which this piece resides.
     * @return true if the proposed move is valid, false otherwise.
     */
    public boolean isValidMove(Move move, IChessBoard board) {
        // Cannot move to same space.
        if (move.getToRow() == move.getFromRow()
                && move.getToColumn() == move.getFromColumn()) {
            return false;
        }
        try {
            // Cannot take piece of your own color.
            if (board.pieceAt(move.getToRow(), move.getToColumn()).
                player().equals(this.player())) {
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        } catch (NullPointerException n) {
            return false;
        }
        return true;
    }
}

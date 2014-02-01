package model;

/**
 * Describes a piece for a game of chess (King, Queen, Rook, etc.)
 *
 * @author Zachary Kurmas
 */

public interface IChessPiece {

   /**
    * Return the player that owns this piece.
    *
    * @return the player that owns this piece.
    */
   Player player();

   /**
    * Return the type of this piece ("King", "Queen", "Rook", etc.).
    * Note:  In this case "type" refers to the game of chess, not the type of the Java class.
    *
    * @return the type of this piece
    */
   String type();

   /**
    * Returns whether the piece at location [move.fromRow, move.fromColumn]
    * is allowed to move to location [move.fromRow, move.fromColumn].
    *
    * Note:  Pieces don't store their own location (because doing so would be redundant).
    * Therefore, the [move.fromRow, move.fromColumn] component of move
    * is necessary. The this object must be the piece at location
    * [move.fromRow, move.fromColumn]. (This method makes no sense otherwise.)
    *
    * @param move  a Move object describing the move to be made.
    * @param board the chess board in which this piece resides.
    * @return true if the proposed move is valid, false otherwise.
    * @throws IndexOutOfBoundsException if either [move.fromRow, move.fromColumn]
    *			or [move.toRow,move.toColumn] don't represent valid locations on the board.
    * @throws IllegalArgumentException  if this object isn't the piece at
    *						location [move.fromRow, move.fromColumn].
    */
   boolean isValidMove(Move move, IChessBoard board);
}

package model;

/**
 * The Move class that records the move of each piece in chess.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */

public class Move {

	/**
	 * Keep the from Row of the piece.
	 * @category int
	 */
	private int fromRow;
	/**
	 * Keep the from Column of the piece.
	 * @category int
	 */
	private int fromColumn;
	/**
	 * Keep the to Row of the piece.
	 * @category int
	 */
	private int toRow;
	/**
	 * Keep the to Column of the piece.
	 * @category int
	 */
	private int toColumn;

	/**
	 * Set the value of the Move of the piece.
	 * @param fR int
	 * @param fC int
	 * @param tR int
	 * @param tC int
	 */
	public Move(final int fR, final int fC, final int tR, 
			final int tC) {
      this.fromRow = fR;
      this.fromColumn = fC;
      this.toRow = tR;
      this.toColumn = tC;
   }
   /**
    * Method to return the fromRow of the piece.
    * @return int
    */
   public final int getFromRow() {
   	return fromRow;
   }
   /**
    * Method to return the toRow of the piece.
    * @return int
    */
   public final int getToRow() {
   	return toRow;
   }
   /**
    * Method to return the fromColumn of the piece.
    * @return int
    */
   public final int getFromColumn() {
   	return fromColumn;
   }
   /**
    * Method to return the toColumn of the piece.
    * @return int
    */
   public final int getToColumn() {
   	return toColumn;
   }
}

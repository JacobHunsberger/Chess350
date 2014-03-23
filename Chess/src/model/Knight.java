package model;

/**
 * The Knight in a game of chess.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
public class Knight extends ChessPiece {

	/**
	 * Constructs a new Knight object.
	 * @param color the player that owns this piece
	 */
	public Knight(final Player color) {
		super(color);
	}
	
	/**
	 * Copy constructor.
	 * @param aKnight Knight to copy.
	 */
	public Knight(final Knight aKnight) {
		this(aKnight.player());
	}

	/**
	 * Returns string for th class knight.
	 * @return String 'knight'
	 */
	public final String type() {
		return "knight";
	}

	/**
	 * This method checks if the move is valid or not for the Knight.
	 * @param board the borad that the piece moves on
	 * @param move the move of the piece
	 * @return boolean true or false if the move is valid
	 */
	public final boolean isValidMove(final Move move, final IChessBoard board) {
		int toRow = move.getToRow();
		int toColumn = move.getToColumn();
		int fromRow = move.getFromRow();
		int fromColumn = move.getFromColumn();
		
		if (!super.isValidMove(move, board)) {
			return false;
			} else if (!(((Math.abs(fromColumn - toColumn) == 2)
				&& (Math.abs(fromRow - toRow) == 1)) 
				|| ((Math.abs(fromColumn - toColumn) == 1) 
				&& (Math.abs(fromRow - toRow) == 2)))) {
			return false;
		}
		return true;
	}
}

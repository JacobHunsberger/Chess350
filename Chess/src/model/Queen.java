package model;

/**
 * A Queen in a game of chess.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
public class Queen extends ChessPiece {
	
	/**
	 * Constructs a new Queen object.
	 * @param color the player that owns this piece
	 */
	public Queen(final Player color) {
		super(color);
	}

	/**
	 * Method is to identify the queen class.
	 * @return String 'queen'
	 */
	public final String type() {
		return "queen";
	}

	/**
	 * Is valid the move for the Queen.
	 * @return boolean ture or false if the move is valid
	 * @param move the move of the piece
	 * @param board the board that the pice moves on
	 */
	public final boolean isValidMove(final Move move, final IChessBoard board) {
		int toRow = move.getToRow();
		int toColumn = move.getToColumn();
		int fromRow = move.getFromRow();
		int fromColumn = move.getFromColumn();
		
		if (!super.isValidMove(move, board)) {
			return false;
		}
		// check for not horizontal, vertical, or diagonal moves
		if ((Math.abs(fromColumn - toColumn) != Math.abs(fromRow - toRow))
				&& (!(fromRow - toRow != 0 && fromColumn - toColumn == 0)
				&& !(fromColumn - toColumn != 0 && fromRow - toRow == 0))) {
			return false;
		} else {
			// Queen can move vertically, horizontally, and diagonally
			// as many spaces desired without jumping over other chess pieces
			// vertical
			if (fromColumn == toColumn) {
				int dy = (toRow - fromRow) / Math.abs(toRow - fromRow);
				for (int i = 1; i <= Math.abs(toRow - fromRow); i++) {
					// verify no pieces on point unless at 2nd point
					if (board.pieceAt(fromRow + dy * i, toColumn) != null 
							&& (fromRow + dy * i != toRow)) {
						return false;
					}
				}
				return true;
			} else if (fromRow == toRow) {
				// horizontal
				int dx = (toColumn - fromColumn) 
						/ Math.abs(toColumn - fromColumn);
				for (int i = 1; i < Math.abs(toColumn - fromColumn); i++) {
					// verify no pieces at point unless at 2nd point
					if (board.pieceAt(toRow, fromColumn + dx * i) 
							!= null && (fromColumn + dx * i != toColumn)) {
						return false;
					}
				}
				return true;
			} else {
				// diagonal
				int dx = (toColumn - fromColumn) 
						/ Math.abs(toColumn - fromColumn);
				int dy = (toRow - fromRow) / Math.abs(toRow - fromRow);
				// check equal distance in rows and columns
				if (Math.abs(toColumn - fromColumn) 
						!= Math.abs(toRow - fromRow)) {
					return false;
				}
				for (int i = 1; i < Math.abs(toColumn - fromColumn); i++) {
					// verify no pieces at point
					if (board.pieceAt(fromRow + i * dy, fromColumn + i * dx) 
							!= null) {
						return false;
					}
				}
				return true;
			}
		} 
	}
}
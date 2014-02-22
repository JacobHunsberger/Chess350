package model;

/**
 * A Bishop in a game of chess.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
public class Bishop extends ChessPiece {

/**
* Constructs a new Bishop object.
* @param color the player that owns this piece
*/
    protected Bishop(final Player color) {
        super(color);
    }
/**
 * A Bishop has the type String.
 * Used for identification.
 * @return String identifying the Bishop
 */
	public final String type() {
		return "Bishop";
	}
/**
 * Method check if move is valid for Bishop.
 * @param move for the move of the Bishop
 * @param board of type IChessBoard
 * @return boolean if the move is valid or not
 */
	public final boolean isValidMove(final Move move, final IChessBoard board) {
		int toRow = move.getToRow();
		int toColumn = move.getToColumn();
		int fromRow = move.getFromRow();
		int fromColumn = move.getFromColumn();
		//Check the isValidMove from the super class.
		if (!super.isValidMove(move, board)) {
			return false;
		}
		
		/* Bishops cannot move horizontally
		or vertically... also cannot move 2,1*/
		if (Math.abs(fromColumn - toColumn) != Math.abs(fromRow - toRow)) {
			return false;
		}
		
		// diagonal
		int dx = (toColumn - fromColumn) / Math.abs(toColumn - fromColumn);
		int dy = (toRow - fromRow) / Math.abs(toRow - fromRow);
		for (int i = 1; i < Math.abs(toColumn - fromColumn); i++) {
			// verify no pieces at point
			if (board.pieceAt(fromRow + i * dy, fromColumn + i * dx) != null) {
				return false;
			}
		}
		return true;
	}
}


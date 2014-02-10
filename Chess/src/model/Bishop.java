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
	@Override
	public final String type() {
		return "bishop";
	}
/**
 * Method check if move is valid for Bishop.
 * @param move for the move of the Bishop
 * @param board of type IChessBoard
 * @return boolean if the move is valid or not
 */
	@Override
	public final boolean isValidMove(final Move move, final IChessBoard board) {
		if (!super.isValidMove(move, board)) {
			return false; 
			} else if (move.getFromColumn() == move.getToColumn() 
				|| move.getFromRow() == move.getToRow()) {
			return false;
		}
		
		// Bishop can move diagonally only just as many spaces as desired
		// without jumping over other chess pieces
		
		// TODO
		return false;
	}
}


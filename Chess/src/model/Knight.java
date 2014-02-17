package model;

/**
 * The Knight in a game of chess
 * 
 * @author Jared Thomas
 */
public class Knight extends ChessPiece {

	/**
	 * Constructs a new Knight object
	 * 
	 * @param color the player that owns this piece
	 */
	protected Knight(Player color) {
		super(color);
	}

	@Override
	public String type() {
		return "Knight";
	}

	@Override
	public boolean isValidMove(Move move, IChessBoard board) {
		int toRow = move.getToRow();
		int toColumn = move.getToColumn();
		int fromRow = move.getFromRow();
		int fromColumn = move.getFromColumn();
		
		if (!super.isValidMove(move, board)) {
			return false;
		}
		// verify move is only 2 by 1 or 1 by 2
		else if(!(((Math.abs(fromColumn - toColumn) == 2) &&
				(Math.abs(fromRow - toRow) == 1)) ||
				((Math.abs(fromColumn - toColumn) == 1) &&
			    (Math.abs(fromRow - toRow) == 2)))   ) {
			return false;
		}
		
			
		// Knight can jump over another chess piece or pieces
		// Can move two squares either forward, backward, left, or right
		// and then left or right one square.

		return true;
	}

}

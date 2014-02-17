package model;

/**
 * A Queen in a game of chess
 * 
 * @author Jared Thomas
 */
public class Queen extends ChessPiece {
	
	/**
	 * Constructs a new Queen object
	 * 
	 * @param color the player that owns this piece
	 */
	protected Queen(Player color) {
		super(color);
	}

	@Override
	public String type() {
		return "Queen";
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
		// check for not horizontal, vertical, or diagonal moves
		else if((Math.abs(fromColumn- toColumn) != Math.abs(fromRow - toRow))&&
				(!(fromRow - toRow != 0 && fromColumn - toColumn == 0) &&
				!(fromColumn - toColumn != 0 && fromRow - toRow == 0))) {
			return false;
		}
		// Queen can move vertically, horizontally, and diagonally
		// as many spaces desired without jumping over other chess pieces
		else
		{
			// vertical
			if(fromColumn == toColumn) {
				int dy = (toRow - fromRow) / (toRow - fromRow);
				for(int i = 1; i <= Math.abs(toRow - fromRow); i++) {
					// verify no pieces on point unless at 2nd point
					if(board.pieceAt(fromRow + dy * i, toColumn) != null &&
							(fromRow + dy * i != toRow)) {
						return false;
					}
				}
				return true;
			}
			
			// horizontal
			else if(fromRow == toRow) {
				int dx = (toColumn - fromColumn) / (toColumn - fromColumn);
				for(int i = 1; i < Math.abs(toColumn - fromColumn); i++) {
					// verify no pieces at point unless at 2nd point
					if(board.pieceAt(toRow, fromColumn + dx * i) != null &&
							(fromColumn + dx * i != toColumn)) {
						return false;
					}
				}
				return true;
			}
			
			// diagonal
			else {
				int dx = (toColumn - fromColumn) / (toColumn - fromColumn);
				int dy = (toRow - fromRow) / (toRow - fromRow);
				for(int i = 1; i < Math.abs(toColumn - fromColumn);i++) {
					// verify no pieces at point unless 2nd point
					if(board.pieceAt(fromRow + i * dy, fromColumn + i * dx) !=
							null && (fromColumn + dx * i != toColumn)) {
						return false;
					}
				}
				return true;
			}
		} 
	}

}

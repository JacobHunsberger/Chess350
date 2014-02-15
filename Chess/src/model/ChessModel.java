package model;

/**
 * Chess model for the game.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */

public class ChessModel implements IChessModel {

	/**
	 * 
	 */
	private IChessPiece[][] board;
	/**
	 * 
	 */
	private Player currentPlayer;
	
	/**
	 * @return boolean true or false if the game is complete
	 */
	public final boolean isComplete() {
		// TODO
		return false;
	}

	/**
	 * @param move input the move of the piece
	 * @return boolean true or false if the move is valid
	 */
	public final boolean isValidMove(final Move move) {
		// TODO
		return false;
	}

	/**
	 * This method moves the piece if it is valid.
	 * @param move input the move of the piece
	 */
	public final void move(final Move move) {
		if (isValidMove(move)) {
			board[move.getToRow()][move.getToColumn()]
					= pieceAt(move.getFromRow(), move.getFromColumn());
			board[move.getFromRow()][move.getFromColumn()] 
					= null;
		}
	}
	/**
	 * @return boolean true or false if the king is in check
	 */
	public final boolean inCheck() {
		// TODO
		return false;
	}

	/**
	 * 
	 * @return Player
	 */
	public final Player currentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * This method tells you if there is a piece at a spot you specify.
	 * @param column an int representing the column
	 * @param row an int representing the row
	 * @return IChessPiece 
	 */
	@SuppressWarnings("null")
	public final IChessPiece pieceAt(final int row, final int column) {
		try {
			return board[row][column];
		} catch (IndexOutOfBoundsException q) {
			System.out.println("IndexOutOfBoundsException pieceAt() "
					+ "ChessModel " + q.getMessage());
			return board[(Integer) null][(Integer) null];
		}
	}
}

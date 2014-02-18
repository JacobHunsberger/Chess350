package model;

/**
 * Chess model for the game.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */

public class ChessModel implements IChessModel {

	/**
	 * A 2D array of IChessPieces to make up the board.
	 */
	private ChessBoard board = new ChessBoard();
	
	/**
	 * The currect player kept in the model.
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
			board.set(board.pieceAt(move.getFromRow(), move.getFromColumn()), 
					move.getToRow(), move.getToColumn());
		}
	}
	/**
	 * This method checks if the King is in check.
	 * @return boolean true or false if the king is in check.
	 * @param move input the last move made.
	 */
	public final boolean inCheck(final Move move) {
		int [] temp;
		//First get the color of the piece that moved last.
		if (pieceAt(move.getToRow(), move.getToColumn()).player() == Player.WHITE) {
			//Then use that to find the king of the opposite player.
			temp = findKing(Player.BLACK);
		} else {
			temp = findKing(Player.WHITE);
		}
		//Now use isValidMove to try and move the piece to the king.
		if (pieceAt(move.getToRow(),move.getToColumn()).isValidMove(new Move(move.getToRow(), 
				move.getToColumn(), temp[0], temp[1]), board)) {
			return true;
		} else if (pieceAt(move.getToRow(), move.getToColumn()).type() == "king") {
			//This part checks if the king you moved is now in check.
			if (boardCheckHelper(move.getToRow(), move.getToColumn(), pieceAt(move.getToRow(), 
					move.getToColumn()).player())) {
				return true;
			} else {
				return false;
			}
		} else {
			//This part checks if a piece moved and now a different piece gets the other king in check.
			if (boardCheckHelper(temp[0], temp[1], pieceAt(temp[0],temp[1]).player())) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * This helper method is used for instance that the king was tha last piece moved.
	 * @param r the row of the King.
	 * @param c the column of the King.
	 * @param p the player of the King that might be in check.
	 * @return boolean if the king is in check.
	 */
	private final boolean boardCheckHelper(int r, int c, Player p) {
		for(int i = 0; i < 8; i++){
			for(int k = 0; k < 8; k++){
				if (pieceAt(i,k).isValidMove(new Move(i,k,r,c), board) && pieceAt(i,k).player() != p) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Helper method to find the king piece.
	 * @return int[] an array with the values of row and column of the king
	 * @param player input the player
	 */
	private final int[] findKing(Player player) {
		for(int i = 0; i < 8; i++){
			for(int k = 0; k < 8; k++){
				if(pieceAt(i,k).type() == "king" && pieceAt(i,k).player() == player){
					int[] t = new int[2]; 
					t[0] = i;
					t[1] = k;
					System.out.println(i +" " + k); //Helps verify my row and column
					return t;
				}
			}
		}
		int[] t = new int[0];
		return t;
	}
	/**
	 * Return the current player.
	 * @return Player
	 */
	public final Player currentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * This method tells you if there is a piece at a spot you specify.
	 * @param column an int representing the column
	 * @param row an int representing the row
	 * @return IChessPiece Piece at the spot
	 */
	public final IChessPiece pieceAt(final int row, final int column) {
		IChessPiece chessPiece = null;
		try {
			chessPiece = board.pieceAt(row, column);
		} catch (IndexOutOfBoundsException q) {
			System.out.println("IndexOutOfBoundsException pieceAt() "
					+ "ChessModel " + q.getMessage());
		}
		return chessPiece;
	}
}

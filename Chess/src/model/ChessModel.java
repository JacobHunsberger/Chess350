package model;

/**
 * Chess model for the game.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */

public final class ChessModel implements IChessModel {
	/**
	 * A 2D array of IChessPieces to make up the board.
	 */
	private ChessBoard board;
	/**
	 * The currect player kept in the model.
	 */
	private Player currentPlayer;
	
	/**
	 * Set the board up.
	 */
	public ChessModel() {
		board.setBoard();
		currentPlayer = Player.WHITE;
		board = new ChessBoard();
	}
	
	/**
	 * @return boolean true or false if the game is complete
	 */
	public boolean isComplete() {
		final int shortest = 7;
		if (board.getMoveLength() < shortest) {
			return false; //Shortest checkmate is 4 move checkmate
		}
		//First the king must be in check
		int[] temp = findKing(currentPlayer());
		Move m = new Move(board.getMove(board.getMoveLength() - 1).getToRow(), 
				board.getMove(board.getMoveLength() - 1).getToColumn(), 
				temp[1], temp[0]);
		if (!inCheck(m)) {
			return false;
		}
		//Can the king move out of check
		final int bounds = 3;
		for (int i = 0; i < bounds; i++) {
			for (int k = 0; k < bounds; k++) {
				try {
					if (pieceAt(temp[1], temp[0]).isValidMove(new Move(temp[0], 
							temp[1], i, k), board)) {
						ChessBoard tempBoard = board;
						Move tempMove = new Move(temp[0], temp[1], i, k);
						move(tempMove);
						if (inCheck(tempMove)) {
							board = tempBoard;
							return false;
						}
						board = tempBoard;
					}
				} catch (NullPointerException e) {
				}
			}
		}
		//Can a piece take the checkMaker
		IChessPiece checkMakers = pieceTakePiece(temp);
		int[] foundPiece = pieceAtReverse(checkMakers);
		if (boardCheckHelper(foundPiece[0], foundPiece[1], 
				checkMakers.player())) {
			return false;
		}
		//Can we block the checkMaker
		if (canBlock(pieceAt(temp[0], temp[1]), checkMakers)) {
			return false;
		}
		//If all the fails then we have checkmate
		return true;
	}

	/**
	 * 
	 * @param defending assumes this piece is the one you want to protect
	 * @param taker assumes this piece is the taker 
	 * @return boolean true or false for blocking.
	 */
	private boolean canBlock(final IChessPiece defending, 
			final IChessPiece taker) {
		/* If the taker piece is a knight you can't block a knight.
		 * You also can't block a pawn or a King because they only can
		 * take one one spot at a time. 
		*/
		if (taker.type() == "knight" || taker.type() == "king" 
				|| taker.type() == "pawn") {
			return false;
		}
		int[] defPoint = pieceAtReverse(defending);
		int[] takePoint = pieceAtReverse(taker);
		// Make sure there is space between the two pieces to block each other
		if (Math.abs(defPoint[0] - takePoint[0]) < 2 
				|| Math.abs(defPoint[1] - takePoint[1]) < 2) {
			return false;
		}
		// Is this a horozontal or vertial move
		if (defPoint[0] == takePoint[0] || defPoint[1] == takePoint[1]) {
				if (defPoint[0] > takePoint[0] || defPoint[0] < takePoint[0]) {
					return blockCheckmate1(defPoint[0], defPoint[1]);
				}
				if (defPoint[1] < takePoint[1] || defPoint[1] > takePoint[1]) {
					return blockCheckmate1(defPoint[1], defPoint[0]);
				}
			}
		// Now we know that the move is diagonal
		if (defPoint[0] > takePoint[0]) {
			return blockCheckmate2(defPoint[0], defPoint[1], takePoint[0]);
		}
		if (defPoint[0] < takePoint[0]) {
			return blockCheckmate2(takePoint[0], defPoint[1], defPoint[0]);
		}
		return true;
	}
	/**
	 * 
	 * @param point1 row 
	 * @param point2 column
	 * @param point3 row of other piece
	 * @return boolean
	 */
	private boolean blockCheckmate2(final int point1, 
			final int point2, final int point3) {
		int dist = point1 - point3;
		Move m;
		final int max = 8;
		for (int i = 0; i < max; i++) {
			for (int k = 0; k < max; k++) {
				while (dist > 0) {
					m = new Move(i, k, point1 - dist, point2 - dist);
					if (pieceAt(i, k).player() == currentPlayer()) {
						if (pieceAt(i, k).isValidMove(m, board)) {
							return true;
						}
					}
					dist--;
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @param point1 row
	 * @param point2 column
	 * @return boolean
	 */
	private boolean blockCheckmate1(final int point1, final int point2) {
		int dist = point1 - point2;
		Move m;
		final int max = 8;
		for (int i = 0; i < max; i++) {
			for (int k = 0; k < max; k++) {
				while (dist > 0) {
					m = new Move(i, k, point1 - dist, point2);
					if (pieceAt(i, k).player() == currentPlayer()) {
						if (pieceAt(i, k).isValidMove(m, board)) {
							return true;
						}
					}
					dist--;
				}
			}
		}
		return false;
	}
	/**
	 * Input the piece that can be taken. This could be used for the king 
	 * or for stategy for the A.I.
	 * @param h row and column array
	 * @return int[] the piece that can take another piece
	 */
	private IChessPiece pieceTakePiece(final int[] h) {
		IChessPiece temp = null;
		Move m;
		final int max = 8;
		for (int i = 0; i < max; i++) {
			for (int k = 0; k < max; k++) {
				m = new Move(i, k, h[0] , h[1]);
				try {
					if (pieceAt(i, k).isValidMove(m, board)) {
						temp = pieceAt(i, k);
					}
				} catch (NullPointerException e) {
					
				}
			}
		}
		return temp;
	}
	/**
	 * @param move input the move of the piece
	 * @return boolean true or false if the move is valid
	 */
	public boolean isValidMove(final Move move) {
		//Make sure the peice you move is your piece.
		if (pieceAt(move.getFromRow(), move.getFromColumn()).player()
				!= currentPlayer()) {
			return false;
		}
		return pieceAt(move.getFromRow(), move.getFromColumn())
				.isValidMove(move, board);
	}

	/**
	 * This method moves the piece if it is valid.
	 * @param move input the move of the piece
	 */
	public void move(final Move move) {
		if (isValidMove(move)) {
			board.move(move);
			currentPlayer = currentPlayer.next();
			}
	}
	/**
	 * This method checks if the King is in check.
	 * @return boolean true or false if the king is in check.
	 * @param move input the last move made.
	 */
	public boolean inCheck(final Move move) {
		int [] temp;
		//First get the color of the piece that moved last.
		if (pieceAt(move.getFromRow(), move.getFromColumn()).player() 
				== Player.WHITE) {
			//Then use that to find the king of the opposite player.
			temp = findKing(Player.BLACK);
		} else {
			temp = findKing(Player.WHITE);
		}
		//Now use isValidMove to try and move the piece to the king.
		if (pieceAt(move.getToRow(), move.getToColumn())
				.isValidMove(new Move(move.getToRow(), 
				move.getToColumn(), temp[0], temp[1]), board)) {
			return true;
		} else if (pieceAt(move.getToRow(), move.getToColumn())
				.type() == "king") {
			//This part checks if the king you moved is now in check.
			if (boardCheckHelper(move.getToRow(), move.getToColumn(),
					pieceAt(move.getToRow(), move.getToColumn()).player())) {
				return true;
			} else {
				return false;
			}
		} else {
			//This part checks if a piece moved and now a different 
			//piece gets the other king in check.
			if (boardCheckHelper(temp[0], temp[1], 
					pieceAt(temp[0], temp[1]).player())) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Method is used for instance that the king was tha last piece moved.
	 * @param r the row of the King.
	 * @param c the column of the King.
	 * @param p the player of the King that might be in check.
	 * @return boolean if the king is in check.
	 */
	private boolean boardCheckHelper(final int r, final int c, final Player p) {
		final int max = 8;
		for (int i = 0; i < max; i++) {
			for (int k = 0; k < max; k++) {
				try {
					if (pieceAt(i, k).isValidMove(new Move(i, k, r, c), board) 
							&& pieceAt(i, k).player() != p) {
						return true;
					}
				} catch (NullPointerException e) {
					
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
	private int[] findKing(final Player player) {
		final int max = 8;
		for (int i = 0; i < max; i++) {
			for (int k = 0; k < max; k++) {
				try {
					if (pieceAt(k, i).type() == "king" 
							&& pieceAt(k, i).player() == player) {
						int[] t = new int[2]; 
						t[0] = i;
						t[1] = k;
						System.out.println(i + " " + k); 
						//Helps verify my row and column
						return t;
					}
				} catch (NullPointerException e) {
					
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
	public Player currentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * This method tells you if there is a piece at a spot you specify.
	 * @param column an int representing the column
	 * @param row an int representing the row
	 * @return IChessPiece Piece at the spot
	 */
	public IChessPiece pieceAt(final int row, final int column) {
		IChessPiece chessPiece = null;
		try {
			chessPiece = board.pieceAt(row, column);
		} catch (IndexOutOfBoundsException q) {
			System.out.println("IndexOutOfBoundsException pieceAt() "
					+ "ChessModel " + q.getMessage());
		}
		return chessPiece;
	}
	/**
	 * 
	 * @param p the piece specified
	 * @return int[] the row and column of r=the piece
	 */
	private int[] pieceAtReverse(final IChessPiece p) {
		int[] temp = new int[2];
		final int max = 8;
		for (int i = 0; i < max; i++) {
			for (int k = 0; k < max; k++) {
				try {
					if (pieceAt(i, k).equals(p)) {
						temp[0] = i;
						temp[1] = k;
					}
				} catch (NullPointerException e) {
					
				}
			}
		}
		return temp;
	}
}

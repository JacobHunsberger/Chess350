package model;

import view.ChessViewSide;
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
	 * Indicates that en passant may be taken.
	 */
	private boolean enPassant;
	/**
	 * Valid column for en passant.
	 */
	private int enPassantColumn;
	/**
	 * Valide row for en passant.
	 */
	private int enPassantRow;
	
	/**
	 * Set the board up.
	 */
	public ChessModel() {
		board = new ChessBoard();
		board.setBoard();
		currentPlayer = Player.WHITE;
		enPassant = false;
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
		if (!inCheck(currentPlayer())) {
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
						if (inCheck(currentPlayer())) {
							board = tempBoard;
							return false;
						}
						board = tempBoard;
					}
				} catch (NullPointerException e) {
					System.out.println(i + " " + k);
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
					System.out.println(i + " " + k);
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
		//Make sure the piece you move is your piece.
		IChessPiece piece = pieceAt(move.getFromRow(), move.getFromColumn());
		if (piece == null) {
			return false;
		}
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
			enPassant = false;
			specialMove(move);
		}
		// check for casteling
		if (isValidCastle(move)) {
			currentPlayer = currentPlayer.next();
			return;
		}
		// add special move handler here for castling
		// check first and second piece is rook and or king
		// verify spaces king moves are check free
		// move rook to position
		
		if (isValidEnPassant(move)) {
			board.move(move);
			// Remove opponent piece
			board.unset(move.getFromRow(), move.getToColumn());
			currentPlayer = currentPlayer.next();
		}
	}
	/**
	 * 
	 * @param move
	 */
	private void specialMove(final Move move) {
		IChessPiece tempPiece = pieceAt(move.getToRow(), move.getToColumn());
        if (tempPiece != null) {
        	if (tempPiece.type() == "king") {
        		return;
        	}
        }
		if (((Pawn) tempPiece).isPromotion(move.getToRow())) {
			promotePawn(tempPiece);
		}
		
		IChessPiece piece = pieceAt(move.getToRow(), move.getToColumn());
		if (piece.type() == "pawn") {
			enPassant = ((Pawn) piece).enPassant();
			enPassantColumn = move.getToColumn();
			if (piece.player() == Player.WHITE) {
				enPassantRow = move.getToRow() - 1;
			} else {
				enPassantRow = move.getToRow() + 1;
			}
		}
	}
	
	private void promotePawn(IChessPiece p) {
		ChessViewSide temp = new ChessViewSide();
		p = temp.promotion(p.player());
	}
	/**
	 * This method checks if the King is in check.
	 * This has been modified to simply check if the king is in check for the
	 * player specified.  It is assumed that you call this method after the 
	 * move has been made. 
	 * @return boolean true or false if the king is in check.
	 * @param p The player for the king you want to check is in check.
	 */
	public boolean inCheck(final Player p) {
		int [] temp = null;
		int eight = 8;
		try {
				temp = findKing(p);
		} catch (NullPointerException e) {}
		//Now use isValidMove to try and move any piece to the king.
		try {
			for (int i = 0; i < eight; i++) {
				for (int k = 0; k < eight; k++) {
					ChessModel tempModel = this;
					tempModel.cyclePlayer();
					if (tempModel.isValidMove(new Move(i, 
									k, temp[1], temp[0]))) {
						tempModel.cyclePlayer();
						return true;
					} else {
						tempModel.cyclePlayer();
					}
				}
			}
			/*if (pieceAt(move.getToRow(), move.getToColumn())
					.isValidMove(new Move(move.getToRow(), 
							move.getToColumn(), temp[1], temp[0]), board)) {
				return true;
			} else if (pieceAt(move.getToRow(), move.getToColumn())
					.type() == "king") {
					return boardCheckHelper(move.getToRow(), move.getToColumn(),
							pieceAt(move.getToRow(), move.getToColumn()).player());
			} else {
				//This part checks if a piece moved and now a different 
				//piece gets the other king in check.
					return boardCheckHelper(temp[0], temp[1], 
							pieceAt(temp[0], temp[1]).player());
				}*/
			} catch (NullPointerException e) {}
		return false;
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
					System.out.println(i + " " + k);
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
	public int[] findKing(final Player player) {
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
					System.out.println(i + " " + k);
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
	private void cyclePlayer() {
		currentPlayer = currentPlayer().next();
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
					System.out.println(i + " " + k);
				}
			}
		}
		return temp;
	}
	
	/**
	 * This method moves the piece if it is valid.
	 * @param move input the move of the piece
	 * @return boolean True if move is valid castle move.
	 */
	private boolean isValidCastle(final Move move) {
		// check for rook and king pieces
		IChessPiece piece1 = pieceAt(move.getFromRow(), move.getFromColumn());
		IChessPiece piece2 = pieceAt(move.getToRow(), move.getToColumn());
		
		// either piece does not exist
		if (piece1 == null || piece2 == null) {
			return false;
		}
		String s1 = piece1.type();
		String s2 = piece2.type();
		// pieces must be on the same team
		if (piece1.player() != piece2.player()) {
			return false;
		}
		// check king and rook
		if (!(piece1.type() == "king" && piece2.type() == "rook" 
				|| piece1.type() == "rook" && piece2.type() == "king")) {
			return false;
		}
		// check no pieces in between
		int start = move.getFromColumn();
		int stop = move.getToColumn();
		int dx = (stop - start) / Math.abs(stop - start);
		int checkPosition = start + dx;
		for (int i = 1; i < Math.abs(stop - start); i++) {
			// no pieces between rook and king
			if (pieceAt(move.getToRow(), checkPosition) != null) {
				return false;
			}
			checkPosition += dx;
		}
		// check first move
		King king;
		Rook rook;
		if (piece1.type() == "king") {
			king = (King) piece1;
			rook = (Rook) piece2;
		}
		else {
			rook = (Rook) piece1;
			king = (King) piece2;
		}
		if (!(king.firstMove() && rook.firstMove())) {
			return false;
		}
		
		// move pieces
		Move m1;
		Move m2;
		if (start > 0 && start < 7) {
			if (stop < start) {

			    m1 = new Move(move.getFromRow(), start, move.getFromRow(),
			    		start - 2);
			    m2 = new Move(move.getFromRow(), stop, move.getFromRow(),
			    		start - 1);
			}
			else {
				m1 = new Move(move.getFromRow(), start, move.getFromRow(),
						start + 2);
			    m2 = new Move(move.getFromRow(), stop, move.getFromRow(),
			    		start + 1);
			}
		}
		else {
			if (start < stop) {
				m1 = new Move(move.getFromRow(), stop,
						move.getFromRow(), stop - 2);
			    m2 = new Move(move.getFromRow(), start,
			    		move.getFromRow(), stop - 1);
			}
			else {
				m1 = new Move(move.getFromRow(), stop,
						move.getFromRow(), stop + 2);
			    m2 = new Move(move.getFromRow(), start,
			    		move.getFromRow(), stop + 1);
			}
		}
		board.move(m1);
		board.move(m2);

		return true;
	}
	
	/**
	 * Determines if en passant can be used to capture a pawn.
	 * 
	 * Rules: If a pawn moves 2 spaces on its first move,
	 * 		  an opponent pawn may capture it as if it had only
	 *        moved 1 space and was in that space.  This may
	 *        only be done on the next move.
	 *        
	 * @param move Move to make en passant.
	 * @return True if en passant can be made on this turn.
	 */
	private boolean isValidEnPassant(final Move move) {
		if (!enPassant) {
			return false;
		}
		
		IChessPiece neighbor = 
				pieceAt(move.getFromRow(), move.getToColumn());
		
		IChessPiece piece = 
				pieceAt(move.getFromRow(), move.getFromColumn());
		
		if (piece == null || neighbor == null) {
			return false;
		}
		
		if (piece.type() != "pawn" || neighbor.type() != "pawn") {
			return false;
		}
		
		if (move.getToRow() == enPassantRow
				&& move.getToColumn() == enPassantColumn) {
			return true;
		}
		
		return false;
	}
	/**
	 * 
	 * @return
	 */
	public ChessBoard getCopyBoard () {
		ChessBoard temp = board;
		return temp;
	}
}
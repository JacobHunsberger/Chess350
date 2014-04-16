package model;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * Chess model for the game.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */

public final class ChessModel implements IChessModel {
	/**
	 * A 2D array of IChessPieces to make up the board.
	 */
	private ChessBoard board, undoBoard;
	/**
	 * The currect player kept in the model.
	 */
	private Player currentPlayer, undoPlayer;
	/**
	 * Indicates that en passant may be taken.
	 */
	private boolean enPassant, undoEnPassant;
	/**
	 * Valid column for en passant.
	 */
	private int enPassantColumn, undoEnPassantColumn;
	/**
	 * Valide row for en passant.
	 */
	private int enPassantRow, undoEnPassantRow;
	/**
	 * Dimension of board.
	 */
	private final int size = 8;
	/**
	 * White taken pieces.
	 */
	private ArrayList<IChessPiece> whiteTaken;
	/**
	 * Black taken pieces.
	 */
	private ArrayList<IChessPiece> blackTaken;
	/**
	 * Does not save undo.
	 */
	private boolean undo;
	
	/**
	 * Set the board up.
	 */
	public ChessModel() {
		board = undoBoard = new ChessBoard();
		board.setBoard();
		currentPlayer = undoPlayer = Player.WHITE;
		enPassant = undoEnPassant = false;
		
		whiteTaken = new ArrayList<IChessPiece>();
		blackTaken = new ArrayList<IChessPiece>();
		
		undo = true;
	}
	
	/**
	 * @return boolean true or false if the game is complete
	 */
	public boolean isComplete() {
		ChessPiece piece = null;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				piece = (ChessPiece) pieceAt(i, j);
				
				if (piece != null) {
					if (moveOutOfCheck(piece, i, j)) {
						return false;
					}
				}
				
			}
		}
		// Check if castling is valid move,
		// then check if castling is out of check.
		return true;
	}
	/**
	 * 
	 * @param piece the piece that may move.
	 * @param fromRow the from row of the piece
	 * @param fromColumn the from column of the piece
	 * @return boolean true or false if you can move out of check 
	 */
	private boolean moveOutOfCheck(final ChessPiece piece,
			final int fromRow, final int fromColumn) {
		if (piece.player() != currentPlayer()) {
			return false;
		}
		Move m = null;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				m = new Move(fromRow, fromColumn, i, j);
				if (isValidMove(m)) {
					if (!inCheck(m)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @param defending assumes this piece is the one you want to protect
	 * @param taker assumes this piece is the taker 
	 * @return boolean true or false for blocking.
	 */
	/**
	private boolean canBlock(final IChessPiece defending, 
			final IChessPiece taker) {
		/* If the taker piece is a knight you can't block a knight.
		 * You also can't block a pawn or a King because they only can
		 * take one one spot at a time. 
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
	}*/
	/**
	 * 
	 * @param point1 row 
	 * @param point2 column
	 * @param point3 row of other piece
	 * @return boolean
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
	}*/
	/**
	 * 
	 * @param point1 row
	 * @param point2 column
	 * @return boolean
	 */
	/*
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
	 * or for strategy for the A.I.
	 * @param h row and column array
	 * @return the piece that can take another piece
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
	}*/
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
		if (piece.player() != currentPlayer()) {
			return false;
		}
		return piece.isValidMove(move, board);
	}
	
	private void saveUndo() {
		if (!undo) {
			return;
		}
		undoBoard = new ChessBoard();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				try {
					if (board.pieceAt(i, j).type() == "pawn") {
						undoBoard.set(new Pawn((Pawn) board.pieceAt(i, j)), i, j);
					} else if (board.pieceAt(i, j).type() == "rook") {
						undoBoard.set(new Rook((Rook) board.pieceAt(i, j)), i, j);
					} else if (board.pieceAt(i, j).type() == "bishop") {
						undoBoard.set(new Bishop((Bishop) board.pieceAt(i, j)), 
								i, j);
					} else if (board.pieceAt(i, j).type() == "queen") {
						undoBoard.set(new Queen((Queen) board.pieceAt(i, j)), i, j);
					} else if (board.pieceAt(i, j).type() == "king") {
						undoBoard.set(new King((King) board.pieceAt(i, j)), i, j);
					} else {
						undoBoard.set(new Knight((Knight) board.pieceAt(i, j)), 
								i, j);
					}	
				} catch (NullPointerException npe) {
					board.set(null, i, j);
				}
			}
		}
		
		undoPlayer = currentPlayer();
		undoEnPassant = enPassant;
		undoEnPassantRow = enPassantRow;
		undoEnPassantColumn = enPassantColumn;
	}

	/**
	 * Copies a board.
	 * @return Chessboard
	 */
	private ChessBoard copyBoard() {
		final int eight = 8;
		ChessBoard temp = board;
		board = new ChessBoard();
		for (int i = 0; i < eight; i++) {
			for (int k = 0; k < eight; k++) {
				try {
					if (temp.pieceAt(i, k).type() == "pawn") {
						board.set(new Pawn((Pawn) temp.pieceAt(i, k)), i, k);
					} else if (temp.pieceAt(i, k).type() == "rook") {
						board.set(new Rook((Rook) temp.pieceAt(i, k)), i, k);
					} else if (temp.pieceAt(i, k).type() == "bishop") {
						board.set(new Bishop((Bishop) temp.pieceAt(i, k)), 
								i, k);
					} else if (temp.pieceAt(i, k).type() == "queen") {
						board.set(new Queen((Queen) temp.pieceAt(i, k)), i, k);
					} else if (temp.pieceAt(i, k).type() == "king") {
						board.set(new King((King) temp.pieceAt(i, k)), i, k);
					} else {
						board.set(new Knight((Knight) temp.pieceAt(i, k)), 
								i, k);
					}	
				} catch (NullPointerException npe) {
					board.set(null, i, k);
				}
			}
		}
		return temp;
	}
	/**
	 * This method moves the piece if it is valid.
	 * @param move input the move of the piece
	 */
	public void move(final Move move) {	
		saveUndo();
		
		if (isValidMove(move)) {
			board.move(move);
			specialMove(move);
			nextTurn();
		}
		
		if (isValidCastle(move)) {
			nextTurn();
		}
		
		if (isValidEnPassant(move)) {
			board.move(move);
			// Remove opponent piece
			board.unset(move.getFromRow(), move.getToColumn());
			enPassant = false;
			nextTurn();
		}
	}
	
	/**
	 * Updates turn.
	 */
	private void nextTurn() {
		currentPlayer = currentPlayer.next();
		setTaken();
	}
	
	private void setTaken() {
		countTakenPieces(Player.WHITE);
		countTakenPieces(Player.BLACK);
	}
	
	/**
	 * Records what pieces were taken since the last time
	 * recordPieces() was called.
	 */
	private void countTakenPieces(Player p) {
		ArrayList<IChessPiece> player = null;
		if (p == Player.WHITE) {
			player = whiteTaken;
		} else {
			player = blackTaken;
		}
		
		player.clear();
		
		final int maxPawns = 8;
		final int maxRoyal = 1;
		final int maxRegular = 2;
		
		int pawns = 0, rooks = 0, knights = 0, kings = 0,
				queens = 0, bishops = 0;
		
		IChessPiece piece = null;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				piece = pieceAt(i, j);
				try {
					if (piece.player() == p) {
						if (piece.type().equals("pawn")) {
							pawns++;
						}
						if (piece.type().equals("rook")) {
							rooks++;
						}
						if (piece.type().equals("knight")) {
							knights++;
						}
						if (piece.type().equals("bishop")) {
							bishops++;
						}
						if (piece.type().equals("king")) {
							kings++;
						}
						if (piece.type().equals("queen")) {
							queens++;
						}
					}
				} catch (NullPointerException e) {
					
				}
			}
		}
		
		// Count pawns
		for (int i = pawns; i < maxPawns; i++) {
			player.add(new Pawn(p));
		}
		// Count rooks
		for (int i = rooks; i < maxRegular; i++) {
			player.add(new Rook(p));
		}
		// Count knights
		for (int i = knights; i < maxRegular; i++) {
			player.add(new Knight(p));
		}
		// Count bishops
		for (int i = bishops; i < maxRegular; i++) {
			player.add(new Bishop(p));
		}
		// Count kings
		for (int i = kings; i < maxRoyal; i++) {
			player.add(new King(p));
		}
		// Count queens
		for (int i = queens; i < maxRoyal; i++) {
			player.add(new Queen(p));
		}
	}
	/**
	 * White taken.
	 * @return Iterator<IChessPiece>
	 */
	public Iterator<IChessPiece> getWhiteTaken() {
		return whiteTaken.iterator();
	}
	/**
	 * Black taken.
	 * @return Iterator<IChessPiece>
	 */
	public Iterator<IChessPiece> getBlackTaken() {
		return blackTaken.iterator();
	}
	
	/**
	 * Method handles special move.
	 * @param move Move to test for special move.
	 */
	private void specialMove(final Move move) {
		IChessPiece piece = pieceAt(move.getToRow(), move.getToColumn());
		
        enPassant = false;
		if (piece.type().equals("pawn")) {
			Pawn pawn = (Pawn) piece;
			
//			if (pawn.isPromotion(move.getToRow())) {
//				//piece = promotePawn(piece);
//				board.unset(move.getToRow(), move.getToColumn());
//				board.set(piece, move.getToRow(), move.getToColumn());
//			}
	
			if (pawn.firstMove()) {
				if (pawn.enPassant()) {
					enPassant = true;	// Next move may be en passant
				}
				
				pawn.setFirstMove(false);		// This pawn has moved
				
				enPassantColumn = move.getToColumn();
				if (pawn.player() == Player.WHITE) {
					enPassantRow = move.getToRow() - 1;
				} else {
					enPassantRow = move.getToRow() + 1;
				}
			}
		} else if (piece.type().equals("king")) {
			((King) piece).setFirstMove(false);
		} else if (piece.type().equals("rook")) {
			((Rook) piece).setFirstMove(false);
		}
	}
	/**
	 * Method promotes pawn.
	 * @return IChessPiece the piece that the pawn was promoted to.
	 * @param p Piece to promote.
	 */
	//private IChessPiece promotePawn(final IChessPiece p) {
	//	ChessPresenterSide temp = new ChessPresenterSide(0);
	//	return temp.promotion(p.player());
	//}
	/**
	 * This method checks if the King is in check.
	 * This has been modified to simply check if the king is in check for the
	 * player specified.  It is assumed that you call this method after the 
	 * move has been made. 
	 * @param m The player for the king you want to check is in check.
	 * @return boolean true or false if the king is in check.
	 */
	public boolean inCheck(final Move m) {
		int [] temp = null;
		final int eight = 8;
		
		undo = false;
		
		// Save everything from the real model.
		ChessBoard tempBoard    = copyBoard();
		boolean tempEnPassant   = enPassant;
		int tempEnPassantColumn = enPassantColumn;
		int tempEnPassantRow    = enPassantRow;
		
		// Store the color of the player trying to move
		Player tempPlayer = currentPlayer;	
		
		move(m);
		
		// Find the king of the correct color
		if (tempPlayer != currentPlayer) {
			currentPlayer = currentPlayer.next();
		}
		try {
			temp = findKing(currentPlayer());
		} catch (NullPointerException e) {
			return false;
		}
		
		// Check if opposite color can move to king
		if (tempPlayer == currentPlayer) {
			currentPlayer = currentPlayer.next();
		}
		try {
			for (int i = 0; i < eight; i++) {
				for (int k = 0; k < eight; k++) {
					if (isValidMove(new Move(i, k, temp[1], temp[0]))) {
						currentPlayer = tempPlayer;
						board = tempBoard;
						enPassant = tempEnPassant;
						enPassantRow = tempEnPassantRow;
						enPassantColumn = tempEnPassantColumn;
						undo = true;
						return true;
					}
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		// Restore the real model
		currentPlayer = tempPlayer;
		board = tempBoard;
		enPassant = tempEnPassant;
		enPassantRow = tempEnPassantRow;
		enPassantColumn = tempEnPassantColumn;
		undo = true;
		return false;
		
	}
	
	/**
	 * Method is used for instance that the king was tha last piece moved.
	 * @param r the row of the King.
	 * @param c the column of the King.
	 * @param p the player of the King that might be in check.
	 * @return boolean if the king is in check.
	 *//*
	private boolean boardCheckHelper(final int r, final int c, final Player p) {
		final int max = 8;
		for (int i = 0; i < max; i++) {
			for (int k = 0; k < max; k++) {
				if (pieceAt(i, k) != null) {
					if (pieceAt(i, k).isValidMove(new Move(i, k, r, c), board) 
							&& pieceAt(i, k).player() != p) {
						return true;
					}
				}
			}
		}
		return false;
	}*/
	/**
	 * Helper method to find the king piece.
	 * @return int[] an array with the values of row and column of the king
	 * @param player input the player
	 */
	public int[] findKing(final Player player) {
		final int max = 8;
		for (int i = 0; i < max; i++) {
			for (int k = 0; k < max; k++) {
				if (pieceAt(k, i) != null) {
					if (pieceAt(k, i).type() == "king" 
							&& pieceAt(k, i).player() == player) {
						int[] t = new int[2]; 
						t[0] = i;
						t[1] = k;
						//Helps verify my row and column
						return t;
					}
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
	 * Cycles the player.
	 */
	public void cyclePlayer() {
		currentPlayer = currentPlayer().next();
	}

	/**
	 * Returns the chess piece at this part of the board.
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
	 *//*
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
	*/
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
		} else {
			rook = (Rook) piece1;
			king = (King) piece2;
		}
		if (!(king.firstMove() && rook.firstMove())) {
			return false;
		} else {
			king.setFirstMove(false);
			rook.setFirstMove(false);
		}
		
		// move pieces
		Move m1;
		Move m2;
		
		if (start < stop) {
			m1 = new Move(move.getFromRow(), stop,
					move.getFromRow(), stop - 2);
		    m2 = new Move(move.getFromRow(), start,
		    		move.getFromRow(), stop - 1);
		} else {
			m1 = new Move(move.getFromRow(), stop,
					move.getFromRow(), stop + 2);
		    m2 = new Move(move.getFromRow(), start,
		    		move.getFromRow(), stop + 1);
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
		// true if the last pawn moved had moved two spaces.
		if (!enPassant) {
			return false;
		}
		
		IChessPiece neighbor = 
				pieceAt(move.getFromRow(), move.getToColumn());
		
		IChessPiece piece = 
				pieceAt(move.getFromRow(), move.getFromColumn());
		
		// Both pieces must exist
		if (piece == null || neighbor == null) {
			return false;
		}
		
		// Both pieces must be pawns.
		if (piece.type() != "pawn" || neighbor.type() != "pawn") {
			return false;
		}
		
		// Pawn must move to correct location
		if (move.getToRow() == enPassantRow
				&& move.getToColumn() == enPassantColumn) {
			return true;
		}
		
		return false;
	}

	/**
	 * Undoes the previous valid move.
	 */
	public void undo() {
		board = undoBoard;
		setTaken();
		currentPlayer = undoPlayer;
		enPassant = undoEnPassant;
		enPassantRow = undoEnPassantRow;
		enPassantColumn = undoEnPassantColumn;
	}
}
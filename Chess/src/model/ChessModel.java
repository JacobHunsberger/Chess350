package model;

import java.util.ArrayList;
import java.util.Iterator;

import presenter.ChessPresenterSide;
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
	 * Dimension of board.
	 */
	private final int size = 8;
	/**
	 * White pieces.
	 */
	private ArrayList<IChessPiece> whitePieces;
	/**
	 * Black pieces.
	 */
	private ArrayList<IChessPiece> blackPieces;
	/**
	 * White taken pieces.
	 */
	private ArrayList<IChessPiece> whiteTaken;
	/**
	 * Black taken pieces.
	 */
	private ArrayList<IChessPiece> blackTaken;
	
	/**
	 * Set the board up.
	 */
	public ChessModel() {
		board = new ChessBoard();
		board.setBoard();
		currentPlayer = Player.WHITE;
		enPassant = false;
		
		whitePieces = new ArrayList<IChessPiece>();
		blackPieces = new ArrayList<IChessPiece>();
		whiteTaken = new ArrayList<IChessPiece>();
		blackTaken = new ArrayList<IChessPiece>();
		
		recordPieces();		// Start of game.
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
						if (inCheck(m)) {
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
	 * or for strategy for the A.I.
	 * @param h row and column array
	 * @return the piece that can take another piece
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
		if (piece.player() != currentPlayer()) {
			return false;
		}
		return piece.isValidMove(move, board);
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
						board.set(new Bishop((Bishop) temp.pieceAt(i, k)), i, k);
					} else if (temp.pieceAt(i, k).type() == "queen") {
						board.set(new Queen((Queen) temp.pieceAt(i, k)), i, k);
					} else if (temp.pieceAt(i, k).type() == "king") {
						board.set(new King((King) temp.pieceAt(i, k)), i, k);
					} else if (temp.pieceAt(i, k).type() == "knight") {
						board.set(new Knight((Knight) temp.pieceAt(i, k)), i, k);
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
		if (isValidMove(move)) {
			board.move(move);
			currentPlayer = currentPlayer.next();
			enPassant = false;
			specialMove(move);
			//if(pieceAt(move.getToRow(),move.getToColumn()).type().equals("pawn")) {
			//	((Pawn) pieceAt(move.getToRow(),move.getToColumn())).setFirstMove(false);
			//}
		}
		// check for castling
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
		
		recordTakenPieces();
	}
	
	/**
	 * Records what pieces were taken since the last time
	 * recordPieces() was called.
	 */
	private void recordTakenPieces() {
		IChessPiece piece = null;
		whiteTaken.clear();
		blackTaken.clear();
		
		for (int i = 0; i < whitePieces.size(); i++) {
			piece = whitePieces.get(i);
			if (!contains(piece)) {
				whiteTaken.add(piece);
			}
		}
		
		for (int i = 0; i < blackPieces.size(); i++) {
			piece = blackPieces.get(i);
			if (!contains(piece)) {
				blackTaken.add(piece);
			}
		}
	}
	
	/**
	 * Records what pieces are currently on the board.
	 */
	private void recordPieces() {
		ChessPiece piece = null;
		whitePieces.clear();
		blackPieces.clear();
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				piece = (ChessPiece) board.pieceAt(i, j);
				if (piece != null) {
					if (piece.player() == Player.WHITE) {
						whitePieces.add(piece);
					} else {
						blackPieces.add(piece);
					}
				}
			}
		}
	}
	
	/**
	 * Determines if a piece is on the board.
	 * 
	 * @return true if piece is on the board, otherwise false.
	 */
	private boolean contains(IChessPiece piece) {
		IChessPiece temp = null;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				temp = board.pieceAt(i, j);
				if (piece == temp) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public Iterator<IChessPiece> getWhiteTaken() {
		return whiteTaken.iterator();
	}
	
	public Iterator<IChessPiece> getBlackTaken() {
		return blackTaken.iterator();
	}
	
	/**
	 * Method handles special move.
	 * @param move Move to test for special move.
	 */
	private void specialMove(final Move move) {
		IChessPiece tempPiece = pieceAt(move.getToRow(), move.getToColumn());
        if (tempPiece != null) {
        	if (tempPiece.type() != "pawn") {
        		return;
        	}
        }
		if (((Pawn) tempPiece).isPromotion(move.getToRow())) {
			tempPiece = promotePawn(tempPiece);
			board.unset(move.getToRow(), move.getToColumn());
			board.set(tempPiece, move.getToRow(), move.getToColumn());
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
	/**
	 * Method promotes pawn.
	 * @return IChessPiece the piece that the pawn was promoted to.
	 * @param p Piece to promote.
	 */
	private IChessPiece promotePawn(final IChessPiece p) {
		ChessPresenterSide temp = new ChessPresenterSide(0);
		return temp.promotion(p.player());
	}
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
				if (pieceAt(i, k) != null) {
					if (pieceAt(i, k).isValidMove(new Move(i, k, r, c), board) 
							&& pieceAt(i, k).player() != p) {
						return true;
					}
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
		}
		
		// move pieces
		Move m1;
		Move m2;
		final int seven = 7;
		if (start > 0 && start < seven) {
			if (stop < start) {

			    m1 = new Move(move.getFromRow(), start, move.getFromRow(),
			    		start - 2);
			    m2 = new Move(move.getFromRow(), stop, move.getFromRow(),
			    		start - 1);
			} else {
				m1 = new Move(move.getFromRow(), start, move.getFromRow(),
						start + 2);
			    m2 = new Move(move.getFromRow(), stop, move.getFromRow(),
			    		start + 1);
			}
		} else {
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
	 * @return board returned.
	 */
	public ChessBoard getCopyBoard() {
		ChessBoard temp = board;
		return temp;
	}
	
	public void printBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				try {
					System.out.print(board.pieceAt(i, j).type().substring(0,4) + " ");
				} catch (NullPointerException e) {
					System.out.print("     ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
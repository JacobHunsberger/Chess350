package model;

/**
 * Chess model for the game.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */

public class ChessModel implements IChessModel {

	/**
	 * A 2D array of IChessPieces to make up the board.
	 */
	ChessBoard board = new ChessBoard();
	private ChessModel(){
		board.setBoard();
	}
	
	/**
	 * The currect player kept in the model.
	 */
	private Player currentPlayer;
	
	/**
	 * @return boolean true or false if the game is complete
	 */
	public final boolean isComplete() {
		if (board.getMoveLength() < 7){ //Shortest checkmate is 4 move checkmate
			return false;
		}
		//First the king must be in check
		int[] temp = findKing(currentPlayer());
		if (!inCheck(board.getMove(board.getMoveLength() - 1))) {
			return false;
		}
		//Can the king move out of check
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				if(pieceAt(temp[0],temp[1]).isValidMove(new Move(temp[0],temp[1],i,k), board)) {
					ChessBoard tempBoard = board;
					Move tempMove = new Move(temp[0],temp[1],i,k);
					move(tempMove);
					if (inCheck(tempMove)) {
						board = tempBoard;
						return false;
					}
					board = tempBoard;
				}
			}
		}
		//Can a piece take the checkMaker
		IChessPiece checkMakers = pieceTakePiece(temp);
		int[] foundPiece = pieceAtReverse(checkMakers);
		if (boardCheckHelper(foundPiece[0],foundPiece[1],checkMakers.player())) {
			return false;
		}
		//Can we block the checkMaker
		if (canBlock(pieceAt(temp[0],temp[1]),checkMakers)) {
			return false;
		}
		//If all the fails then we have checkmate
		return true;
	}

	/**
	 * 
	 * @param defending assumes this piece is the one you want to protect
	 * @param taker assumes this piece is the taker 
	 * @return
	 */
	private final boolean canBlock(IChessPiece defending, IChessPiece taker) {
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
				|| Math.abs(defPoint[1] - takePoint[1]) < 2 ) {
			return false;
		}
		// Is this a horozontal or vertial move
		if (defPoint[0] == takePoint[0] || defPoint[1] == takePoint[1]) {
				if (defPoint[0] > takePoint[0] || defPoint[0] < takePoint[0]) {
					return blockCheckmate1(defPoint[0],defPoint[1]);
				}
				if (defPoint[1] < takePoint[1] || defPoint[1] > takePoint[1]) {
					return blockCheckmate1(defPoint[1],defPoint[0]);
				}
			}
		// Now we know that the move is diagonal
		if (defPoint[0] > takePoint[0]) {
			return blockCheckmate2(defPoint[0],defPoint[1],takePoint[0]);
		}
		if (defPoint[0] < takePoint[0]) {
			return blockCheckmate2(takePoint[0],defPoint[1],defPoint[0]);
		}
		return true;
	}
	/**
	 * 
	 * @param point1
	 * @param point2
	 * @return
	 */
	private final boolean blockCheckmate2 (int point1, int point2, int point3) {
		int dist = point1 - point3;
		Move m;
		for(int i = 0; i < 8; i++){
			for(int k = 0; k < 8; k++){
				while (dist > 0) {
					m = new Move(i,k,point1 - dist,point2 - dist);
					if (pieceAt(i,k).player() == currentPlayer()) {
						if (pieceAt(i,k).isValidMove(m, board) == true) {
							return true;
						}
					}
					dist --;
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @param point1
	 * @param point2
	 * @return
	 */
	private final boolean blockCheckmate1 (int point1, int point2) {
		int dist = point1 - point2;
		Move m;
		for(int i = 0; i < 8; i++){
			for(int k = 0; k < 8; k++){
				while (dist > 0) {
					m = new Move(i,k,point1 - dist,point2);
					if (pieceAt(i,k).player() == currentPlayer()) {
						if (pieceAt(i,k).isValidMove(m, board) == true) {
							return true;
						}
					}
					dist --;
				}
			}
		}
		return false;
	}
	/**
	 * Input the piece that can be taken. This could be used for the king 
	 * or for stategy for the A.I.
	 * @param p
	 * @return int[] the piece that can take another piece
	 */
	private final IChessPiece pieceTakePiece (int[] h) {
		IChessPiece temp = null;
		Move m;
		for(int i = 0; i < 8; i++){
			for(int k = 0; k < 8; k++){
				m = new Move(i,k,h[0],h[1]);
				if (pieceAt(i,k).isValidMove(m, board)) {
					temp = pieceAt(i,k);
				}
			}
		}
		return temp;
	}
	/**
	 * @param move input the move of the piece
	 * @return boolean true or false if the move is valid
	 */
	public final boolean isValidMove(final Move move) {
		//Make sure the peice you move is your piece.
		if (pieceAt(move.getFromRow(),move.getFromColumn()).player() != currentPlayer()) {
			return false;
		}
		//castling - to be implemented later 
		//if (pieceAt(move.getFromRow(),move.getFromColumn()).type() == "king") {
			// Check if the king is on it's first move
			//if () {		
			//}
		//}
		return pieceAt(move.getFromRow(),move.getFromColumn()).isValidMove(move, board);
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
	private final int[] pieceAtReverse(IChessPiece p) {
		int[] temp = new int[2];
		for(int i = 0; i < 8; i++){
			for(int k = 0; k < 8; k++){
				if (pieceAt(i,k).equals(p)) {
					temp[0] = i;
					temp[1] = k;
				}
			}
		}
		return temp;
	}
}

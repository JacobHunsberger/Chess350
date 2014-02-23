package model;

import java.util.ArrayList;

/**
 * Creates the chess board in a game of chess.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */

public class ChessBoard implements IChessBoard {
	
	/**
	 * A 2D array of chess pieces in a board.
	 */
	private IChessPiece[][] board;
	
	/**
	 * The number of rows on the board.
	 */
	private final int numRows = 8;
	/**
	 * The number of columns on the board.
	 */
	private final int numColumns = 8;
	/**
	 * keep track of the moves in an array list.
	 */
	private ArrayList<Move> allMoves = new ArrayList<Move>();
	
	/**
	 * Constructor for the board that creats the board.
	 */
	public ChessBoard() {
		board = new ChessPiece[numRows][numColumns];
	}
	
	/**
	 * Returns the number of rows.
	 * @return int the number of rows.
	 */
	public final int numRows() {
		return numRows;
	}

	/**
	 * The get method for the columns.
	 * @return int columns
	 */
	public final int numColumns() {
		return numColumns;
	}
	/**
	 * This is the get method for the board.
	 * @return the board
	 */
	public final IChessPiece[][] getBoard() {
		return board;
	}
	/**
	 * Retrun the piece on the board.
	 * @return IChessPiece the piece at where specified
	 * @param row the row of the piece
	 * @param column the column of the piece
	 */
	public final IChessPiece pieceAt(final int row, final int column) {
		return board[row][column];
	}

	/**
	 * Actually moves the chesspiece.
	 * @param move the move of the piece
	 */
	public final void move(final Move move) {
		IChessPiece piece = 
				board[move.getFromRow()][move.getFromColumn()];
		unset(move.getFromRow(), move.getFromColumn());
		set(piece, move.getToRow(), move.getToColumn());
		updateMoveList(move);
	}
	/**
	 * Update the move list.
	 * @param move the move of the piece
	 */
	private void updateMoveList(final Move move) {
		allMoves.add(move);
	}
	/**
	 * get the move you need from the list.
	 * @param x the move you need
	 * @return Move the move you specified
	 */
	protected final Move getMove(final int x) {
		return allMoves.get(x);
	}
	/**
	 * 
	 * @return int the length 
	 */
	protected final int getMoveLength() {
		return allMoves.size();
	}
	
	/**
	 * Set the piece on the board.
	 * @param piece the piece to be set
	 * @param row the row for the piece
	 * @param column the column for the piece
	 */
	public final void set(final IChessPiece piece, 
			final int row, final int column) {
		board[row][column] = piece;
	}

	/**
	 * Unset the piece from the board.
	 * @param row the row of the piece
	 * @param column the column of the piece
	 */
	public final void unset(final int row, final int column) {
		board[row][column] = null;
	}
	
	/**
	 * Sets up this board for a traditional game of Chess.
	 */
	public final void setBoard() {
		Player p = Player.WHITE;
		int row = 0;
		final int three = 3, four = 4, five = 5, six = 6,
				seven = 7;
		for (int i = 0; i < 2; i++) {
			set(new Rook(p), row, 0);
			set(new Knight(p), row, 1);
			set(new Bishop(p), row, 2);
			set(new Queen(p), row, three);
			set(new King(p), row, four);
			set(new Bishop(p), row, five);
			set(new Knight(p), row, six);
			set(new Rook(p), row, seven);
			row = seven;
			p = p.next();
		}
		row = 1;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < numColumns; j++) {
				set(new Pawn(p), row, j);
			}
			row = six;
			p = p.next();
		}
		for (int i = 2; i <= five; i++) {
			for (int j = 0; j < numColumns; j++) {
				set(null, i, j);
			}
		}
	}
}

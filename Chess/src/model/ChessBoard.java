package model;

import java.util.ArrayList;

public class ChessBoard implements IChessBoard {
	// add instance and/or class variables as needed
	// add other (public or private) methods as needed
	private IChessPiece[][] board;
	
	private final int numRows = 8;
	private final int numColumns = 8;
	ArrayList<Move> allMoves = new ArrayList<Move>();
	
	public ChessBoard() {
		board = new ChessPiece[numRows][numColumns];
	}
	
	@Override
	public final int numRows() {
		return numRows;
	}

	@Override
	public final int numColumns() {
		return numColumns;
	}

	public final IChessPiece[][] getBoard() {
		return board;
	}
	
	@Override
	public IChessPiece pieceAt(int row, int column) {
		return board[row][column];
	}

	@Override
	public void move(Move move) {
		IChessPiece piece = 
				board[move.getFromRow()][move.getFromColumn()];
		unset(move.getFromRow(), move.getFromColumn());
		set(piece, move.getToRow(), move.getToColumn());
		updateMoveList(move);
	}

	private void updateMoveList(Move move){
		allMoves.add(move);
	}
	protected Move getMove(int x){
		return allMoves.get(x);
	}
	protected int getMoveLength(){
		return allMoves.size();
	}
	@Override
	public void set(IChessPiece piece, int row, int column) {
		board[row][column] = piece;
	}

	@Override
	public void unset(int row, int column) {
		board[row][column] = null;
	}
	
	/**
	 * Sets up this board for a traditional game of Chess.
	 */
	public void setBoard() {
		Player p = Player.WHITE;
		int row = 0;
		
		for (int i = 0; i < 2; i++) {
			set(new Rook(p), row, 0);
			set(new Knight(p), row, 1);
			set(new Bishop(p), row, 2);
			set(new Queen(p), row, 3);
			set(new King(p), row, 4);
			set(new Bishop(p), row, 5);
			set(new Knight(p), row, 6);
			set(new Rook(p), row, 7);
			
			row = 7;
			p.next();
		}
		
		p.next();
		row = 1;
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < numColumns; j++) {
				set(new Pawn(p), row, j);
			}
			
			row = 6;
			p.next();
		}
	}
}

package model;

public class ChessBoard implements IChessBoard {

	// add instance and/or class variables as needed
	// add other (public or private) methods as needed
	private IChessPiece[][] board;
	
	private final int numRows = 8;
	private final int numColumns = 8;
	
	public ChessBoard() {
		board = new ChessPiece[numRows][numColumns];
	}
	
	@Override
	public int numRows() {
		return numRows;
	}

	@Override
	public int numColumns() {
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
		IChessPiece chessPiece = 
				board[move.getFromRow()][move.getFromColumn()];
		unset(move.getFromRow(), move.getFromColumn());
		board[move.getToRow()][move.getToColumn()] = chessPiece;
	}

	@Override
	public void set(IChessPiece piece, int row, int column) {
		board[row][column] = piece;
	}

	@Override
	public void unset(int row, int column) {
		board[row][column] = null;
	}
}
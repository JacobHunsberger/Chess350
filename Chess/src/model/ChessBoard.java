package model;

public class ChessBoard implements IChessBoard {

	// add instance and/or class variables as needed
	// add other (public or private) methods as needed
	private IChessPiece[][] board;
	
	@Override
	public int numRows() {
		// TODO
		return 8;
	}

	@Override
	public int numColumns() {
		// TODO
		return 8;
	}

	public final IChessPiece[][] getBoard() {
		return board;
	}
	@Override
	public IChessPiece pieceAt(int row, int column) {
		// TODO
		return null;
	}

	@Override
	public void move(Move move) {
		// TODO
	}

	@Override
	public void set(IChessPiece piece, int row, int column) {
		// TODO
	}

	@Override
	public void unset(int row, int column) {
		// TODO
	}
}
package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChessBoardTest {

	@Test
	public void testNumRows() {
		IChessBoard board = new ChessBoard();
		assertEquals(8, board.numRows());
	}
	
	@Test
	public void testNumColumns() {
		IChessBoard board = new ChessBoard();
		assertEquals(8, board.numColumns());
	}

	@Test
	public void testPieceAt() {
		IChessBoard board = new ChessBoard();
		((ChessBoard) board).setBoard();
		
		assertTrue(board.pieceAt(1, 0).type().equals("Pawn"));
		assertTrue(board.pieceAt(6, 4).type().equals("Pawn"));
		assertTrue(board.pieceAt(0, 0).type().equals("Rook"));
		assertTrue(board.pieceAt(7, 1).type().equals("Knight"));
		assertTrue(board.pieceAt(0, 5).type().equals("Bishop"));
		assertTrue(board.pieceAt(7, 4).type().equals("King"));
		assertTrue(board.pieceAt(0, 3).type().equals("Queen"));
	}
	
	@Test
	public void testMove() {
		IChessBoard board = new ChessBoard();
		
		// Move to an empty space
		IChessPiece piece = new Rook(Player.WHITE);
		board.set(piece, 0, 0);
		Move move = new Move(0, 0, 2, 3);
		board.move(move);
		assertEquals(null, board.pieceAt(0, 0));
		assertEquals(piece, board.pieceAt(2, 3));
		
		// Move back to the original space
		move = new Move(2, 3, 0, 0);
		board.move(move);
		assertEquals(null, board.pieceAt(2,3));
		assertEquals(piece, board.pieceAt(0, 0));
		
		// Move to an occupied space
		IChessPiece piece2 = new Knight(Player.BLACK);
		board.set(piece2, 4, 5);
		move = new Move(0, 0, 4, 5);
		board.move(move);
		assertEquals(piece, board.pieceAt(4, 5));
	}
	
	@Test
	public void testSet() {
		IChessBoard board = new ChessBoard();
		
		// Add first piece
		IChessPiece pawn = new Pawn(Player.WHITE);
		assertEquals(null, board.pieceAt(0,  0));
		board.set(pawn, 0, 0);
		assertEquals(pawn, board.pieceAt(0, 0));
		
		// Add second piece in same row
		IChessPiece rook = new Rook(Player.BLACK);
		assertEquals(null, board.pieceAt(0, 1));
		board.set(rook, 0, 1);
		assertEquals(rook, board.pieceAt(0, 1));
		
		// Add third piece in same column
		IChessPiece knight = new Knight(Player.BLACK);
		board.set(knight, 1, 1);
		assertEquals(knight, board.pieceAt(1,1));
		
		// Add fourth piece in different row and column
		IChessPiece king = new Bishop(Player.WHITE);
		board.set(king, 5, 6);
		assertEquals(king, board.pieceAt(5, 6));
		
		// Overwrite piece
		IChessPiece bishop = new Bishop(Player.WHITE);
		assertTrue(board.pieceAt(0, 0) == pawn);
		board.set(bishop, 0, 0);
		assertEquals(bishop, board.pieceAt(0, 0));
		assertEquals(rook, board.pieceAt(0, 1));
		assertEquals(knight, board.pieceAt(1, 1));
	}
	
	public void testUnset() {
		IChessBoard board = new ChessBoard();
		((ChessBoard) board).setBoard();
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.unset(i, j);
				assertEquals(null, board.pieceAt(i, j));
			}
		}
	}
}

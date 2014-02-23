package modeltester;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import model.Bishop;
import model.ChessBoard;
import model.IChessBoard;
import model.IChessPiece;
import model.Knight;
import model.Move;
import model.Pawn;
import model.Player;
import model.Rook;

import org.junit.Test;
/**
 * Test the class ChessBoard.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
public class ChessBoardTest {
	/**
	 * Test numRows.
	 */
	@Test
	public final void testNumRows() {
		final int eight = 8;
		IChessBoard board = new ChessBoard();
		assertEquals(eight, board.numRows());
	}
	/**
	 * Test numColumns.
	 */
	@Test
	public final void testNumColumns() {
		final int eight = 8;
		IChessBoard board = new ChessBoard();
		assertEquals(eight, board.numColumns());
	}
	/**
	 * Test pieceAt.
	 */
	@Test
	public final void testPieceAt() {
		IChessBoard board = new ChessBoard();
		((ChessBoard) board).setBoard();
		final int six = 6, four = 4, five = 5, seven = 7, three = 3;
		assertTrue(board.pieceAt(1, 0).type().equals("pawn"));
		assertTrue(board.pieceAt(six, four).type().equals("pawn"));
		assertTrue(board.pieceAt(0, 0).type().equals("rook"));
		assertTrue(board.pieceAt(seven, 1).type().equals("knight"));
		assertTrue(board.pieceAt(0, five).type().equals("bishop"));
		assertTrue(board.pieceAt(seven, four).type().equals("king"));
		assertTrue(board.pieceAt(0, three).type().equals("queen"));
	}
	/**
	 * Test move.
	 */
	@Test
	public final void testMove() {
		IChessBoard board = new ChessBoard();
		final int three = 3, four = 4, five = 5;
		// Move to an empty space
		IChessPiece piece = new Rook(Player.WHITE);
		board.set(piece, 0, 0);
		Move move = new Move(0, 0, 2, three);
		board.move(move);
		assertEquals(null, board.pieceAt(0, 0));
		assertEquals(piece, board.pieceAt(2, three));
		
		// Move back to the original space
		move = new Move(2, three, 0, 0);
		board.move(move);
		assertEquals(null, board.pieceAt(2, three));
		assertEquals(piece, board.pieceAt(0, 0));
		
		// Move to an occupied space
		IChessPiece piece2 = new Knight(Player.BLACK);
		board.set(piece2, four, five);
		move = new Move(0, 0, four, five);
		board.move(move);
		assertEquals(piece, board.pieceAt(four, five));
	}
	/**
	 * Test Set.
	 */
	@Test
	public final void testSet() {
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
		assertEquals(knight, board.pieceAt(1, 1));
		final int five = 5, six = 6;
		// Add fourth piece in different row and column
		IChessPiece king = new Bishop(Player.WHITE);
		board.set(king, five, six);
		assertEquals(king, board.pieceAt(five, six));
		
		// Overwrite piece
		IChessPiece bishop = new Bishop(Player.WHITE);
		board.set(bishop, 0, 0);
		assertEquals(bishop, board.pieceAt(0, 0));
		assertEquals(rook, board.pieceAt(0, 1));
		assertEquals(knight, board.pieceAt(1, 1));
	}
	/**
	 * Test unSet.
	 */
	@Test
	public final void testUnset() {
		IChessBoard board = new ChessBoard();
		((ChessBoard) board).setBoard();
		final int eight = 8;
		for (int i = 0; i < eight; i++) {
			for (int j = 0; j < eight; j++) {
				board.unset(i, j);
				assertEquals(null, board.pieceAt(i, j));
			}
		}
	}
	/**
	 * Test getBoard.
	 */
	@Test
	public final void testGetBoard() {
		IChessBoard board = new ChessBoard();
		IChessPiece[][] get = ((ChessBoard) board).getBoard();
		
		assertEquals(get[0][1], board.pieceAt(0, 1));
	}
}

package modeltester;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.ChessBoard;
import model.IChessBoard;
import model.IChessPiece;
import model.Move;
import model.Pawn;
import model.Player;
import model.Queen;

import org.junit.Test;
/**
 * Test the class Queen.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
public class QueenTest {
	/**
	 * Test the type.
	 */
	@Test
	public final void testType() {
		IChessPiece queen = new Queen(Player.WHITE);
		assertEquals("queen", queen.type());
	}
	/**
	 * Test isValidMove.
	 */
	@Test
	public final void testIsValidMove() {
		IChessPiece queen = new Queen(Player.WHITE);
		IChessBoard board = new ChessBoard();
		final int three = 3, negtwo = -2;
		int r = three, c = three;
		Move move = null;
		board.set(queen, r, c);
		
		// Test basic movement
		for (int i = negtwo; i <= 2; i++) {
			for (int j = negtwo; j <= 2; j++) {
				move = new Move(r, c, r + i, c + j);
				
				if (i == 0 && j == 0) {
					assertFalse(queen.isValidMove(move, board));
				} else if (i == 0 || j == 0) {
					assertTrue(queen.isValidMove(move, board));
				} else if (Math.abs(i) == Math.abs(j)) {
					assertTrue(queen.isValidMove(move, board));
				} else {
					assertFalse(queen.isValidMove(move, board));
				}
				
			}
		}
		
		// Test horizontal and vertical jumping
		board.set(new Pawn(Player.BLACK), r, 1);
		move = new Move(r, c, r, 0);
		assertFalse(queen.isValidMove(move, board));		
		move = new Move(r, c, r, 2);
		assertTrue(queen.isValidMove(move, board));
		
		board.set(new Pawn(Player.WHITE), 1, c);
		move = new Move(r, c, 0, c);
		assertFalse(queen.isValidMove(move, board));
		move = new Move(r, c, 2, c);
		assertTrue(queen.isValidMove(move, board));
		final int five = 5, six = 6, four = 4;
		board.set(new Pawn(Player.BLACK), r, five);
		move = new Move(r, c, r, six);
		assertFalse(queen.isValidMove(move, board));
		move = new Move(r, c, r, four);
		assertTrue(queen.isValidMove(move, board));
		
		board.set(new Pawn(Player.WHITE), five, c);
		move = new Move(r, c, six, c);
		assertFalse(queen.isValidMove(move, board));
		move = new Move(r, c, four, c);
		assertTrue(queen.isValidMove(move, board));
		
		// Test diagonal jumping
		board.set(new Pawn(Player.BLACK), 1, 1);
		move = new Move(r, c, 0, 0);
		assertFalse(queen.isValidMove(move, board));
		move = new Move(r, c, 2, 2);
		assertTrue(queen.isValidMove(move, board));
		
		board.set(new Pawn(Player.WHITE), 1, five);
		move = new Move(r, c, 0, six);
		assertFalse(queen.isValidMove(move, board));
		move = new Move(r, c, 2, four);
		assertTrue(queen.isValidMove(move, board));
		
		board.set(new Pawn(Player.BLACK), five, five);
		move = new Move(r, c, six, six);
		assertFalse(queen.isValidMove(move, board));
		move = new Move(r, c, four, four);
		assertTrue(queen.isValidMove(move, board));
		
		board.set(new Pawn(Player.WHITE), five, 1);
		move = new Move(r, c, six, 0);
		assertFalse(queen.isValidMove(move, board));
		move = new Move(r, c, four, 2);
		assertTrue(queen.isValidMove(move, board));
	}
}
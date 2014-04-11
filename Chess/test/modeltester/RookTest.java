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
import model.Rook;

import org.junit.Test;
/**
 * Test the class Rook.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
public class RookTest {
	/**
	 * Test the type.
	 */
	@Test
	public final void testType() {
		Rook rook = new Rook(Player.WHITE);
		assertEquals("rook", rook.type());
		rook.setFirstMove(false);
		assertFalse(rook.firstMove());
		Rook rookCopy = new Rook((Rook) rook);
		assertEquals(rook.player(), rookCopy.player());
	}
	/**
	 * Test isValidMove.
	 */
	@Test
	public final void testIsValidMove() {
		IChessPiece rook = new Rook(Player.WHITE);
		IChessBoard board = new ChessBoard();
		final int three = 3, negtwo = -2;
		int r = three, c = three;
		Move move = null;
		board.set(rook, r, c);
		
		// Test basic movement
		for (int i = negtwo; i <= 2; i++) {
			for (int j = negtwo; j <= 2; j++) {
				move = new Move(r, c, r + i, c + j);
				
				if (i == 0 && j == 0) {
					assertFalse(rook.isValidMove(move, board));
				} else if (i == 0 || j == 0) {
					assertTrue(rook.isValidMove(move, board));
				} else {
					assertFalse(rook.isValidMove(move, board));
				}
			}
		}
		// Test jumping
		board.set(new Pawn(Player.BLACK), r, 1);
		move = new Move(r, c, r, 0);
		assertFalse(rook.isValidMove(move, board));		
		move = new Move(r, c, r, 2);
		assertTrue(rook.isValidMove(move, board));
		
		board.set(new Pawn(Player.WHITE), 1, c);
		move = new Move(r, c, 0, c);
		assertFalse(rook.isValidMove(move, board));
		move = new Move(r, c, 2, c);
		assertTrue(rook.isValidMove(move, board));
		final int five = 5, six = 6, four = 4;
		board.set(new Pawn(Player.BLACK), r, five);
		move = new Move(r, c, r, six);
		assertFalse(rook.isValidMove(move, board));
		move = new Move(r, c, r, four);
		assertTrue(rook.isValidMove(move, board));
		
		board.set(new Pawn(Player.WHITE), five, c);
		move = new Move(r, c, six, c);
		assertFalse(rook.isValidMove(move, board));
		move = new Move(r, c, four, c);
		assertTrue(rook.isValidMove(move, board));
	}
}

package modeltester;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import model.Bishop;
import model.ChessBoard;
import model.IChessBoard;
import model.IChessPiece;
import model.Move;
import model.Pawn;
import model.Player;

import org.junit.Test;

/**
 * Test the class Bishop.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
public class BishopTest {
	/**
	 * Test the type.
	 */
	@Test
	public final void testType() {
		IChessPiece bishop = new Bishop(Player.WHITE);
		assertEquals("bishop", bishop.type());
	}
	/**
	 * Test the isValidMove.
	 */
	@Test
	public final void testIsValidMove() {
		IChessPiece bishop = new Bishop(Player.WHITE);
		IChessBoard board = new ChessBoard();
		final int three = 3;
		int r = three, c = three;
		Move move = null;
		board.set(bishop, r, c);
		final int negtwo = -2;
		// Test basic movement
		for (int i = negtwo; i <= 2; i++) {
			for (int j = negtwo; j <= 2; j++) {
				move = new Move(r, c, r + i, c + j);
				
				if (i == 0 && j == 0) {
					assertFalse(bishop.isValidMove(move, board));
				} else if (Math.abs(i) == Math.abs(j)) {
					assertTrue(bishop.isValidMove(move, board));
				} else {
					assertFalse(bishop.isValidMove(move, board));
				}
				
			}
		}
		
		// Test jumping
		board.set(new Pawn(Player.BLACK), 1, 1);
		move = new Move(r, c, 0, 0);
		assertFalse(bishop.isValidMove(move, board));
		move = new Move(r, c, 2, 2);
		assertTrue(bishop.isValidMove(move, board));
		final int five = 5, six = 6, four = 4;
		board.set(new Pawn(Player.WHITE), 1, five);
		move = new Move(r, c, 0, six);
		assertFalse(bishop.isValidMove(move, board));
		move = new Move(r, c, 2, four);
		assertTrue(bishop.isValidMove(move, board));
		
		board.set(new Pawn(Player.BLACK), five, five);
		move = new Move(r, c, six, six);
		assertFalse(bishop.isValidMove(move, board));
		move = new Move(r, c, four, four);
		assertTrue(bishop.isValidMove(move, board));
		
		board.set(new Pawn(Player.WHITE), five, 1);
		move = new Move(r, c, six, 0);
		assertFalse(bishop.isValidMove(move, board));
		move = new Move(r, c, four, 2);
		assertTrue(bishop.isValidMove(move, board));
	}
}

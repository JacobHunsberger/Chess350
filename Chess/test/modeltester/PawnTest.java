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
 * Test the class Pawn.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
public class PawnTest {
	/**
	 * Test the type.
	 */
	@Test
	public final void testType() {
		IChessPiece pawn = new Pawn(Player.WHITE);
		assertEquals("pawn", pawn.type());
	}
	/**
	 * Test isValidMove.
	 */
	@Test
	public final void testIsValidMove() {
		IChessPiece pawn = new Pawn(Player.WHITE);
		IChessBoard board = new ChessBoard();
		Move move = null;
		final int three = 3, negtwo = -2;
		int r = three, c = three;
		
		board.set(pawn, 1, c);
		
		// Test special move
		move = new Move(1, c, r, c);
		assertTrue(pawn.isValidMove(move, board));
		
		// Test movement
		for (int i = negtwo; i <= 2; i++) {
			for (int j = negtwo; j <= 2; j++) {
				move = new Move(r, c, r + i, c + j);
				
				if (i == 1 && j == 0) {
					assertTrue(pawn.isValidMove(move, board));
				} else {
					assertFalse(pawn.isValidMove(move, board));
				}
			}
		}
		
		// Test capturing 
		board.set(new Rook(Player.BLACK), r + 1, c - 1);
		move = new Move(r, c, r + 1, c - 1);
		assertTrue(pawn.isValidMove(move, board));
		
		move = new Move(r, c, r + 1, c + 1);
		assertFalse(pawn.isValidMove(move, board));
		
		board.set(new Rook(Player.BLACK), r + 1, c + 1);
		move = new Move(r, c, r + 1, c + 1);
		assertTrue(pawn.isValidMove(move, board));
		
		board.set(new Rook(Player.WHITE), r + 1, c - 1);
		move = new Move(r, c, r + 1, c - 1);
		assertFalse(pawn.isValidMove(move, board));
		
		// BLACK
		board = new ChessBoard();
		pawn = new Pawn(Player.BLACK);
		final int six = 6, four = 4;
		board.set(pawn, six, c);
		r = four;
		c = four;
		
		// Test special move
		move = new Move(six, c, r, c);
		assertTrue(pawn.isValidMove(move, board));
		
		// Test movement
		for (int i = negtwo; i <= 2; i++) {
			for (int j = negtwo; j <= 2; j++) {
				move = new Move(r, c, r + i, c + j);
				
				if (i == -1 && j == 0) {
					assertTrue(pawn.isValidMove(move, board));
				} else {
					assertFalse(pawn.isValidMove(move, board));
				}
			}
		}
		
		// Test capturing 
		board.set(new Rook(Player.WHITE), r - 1, c - 1);
		move = new Move(r, c, r - 1, c - 1);
		assertTrue(pawn.isValidMove(move, board));
		
		move = new Move(r, c, r - 1, c + 1);
		assertFalse(pawn.isValidMove(move, board));
		
		board.set(new Rook(Player.WHITE), r - 1, c + 1);
		move = new Move(r, c, r - 1, c + 1);
		assertTrue(pawn.isValidMove(move, board));
		
		board.set(new Rook(Player.BLACK), r - 1, c - 1);
		move = new Move(r, c, r - 1, c - 1);
		assertFalse(pawn.isValidMove(move, board));
	}
}

package modeltester;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.ChessBoard;
import model.IChessBoard;
import model.IChessPiece;
import model.Knight;
import model.Move;
import model.Player;

import org.junit.Test;
/**
 * Test the class Knight.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
public class KnightTest {
	/**
	 * Test the type.
	 */
	@Test
	public final void testType() {
		IChessPiece piece = new Knight(Player.WHITE);
		assertEquals("knight", piece.type());
	}
	/**
	 * Test isValidMove.
	 */
	@Test
	public final void testIsValidMove() {
		IChessPiece knight = new Knight(Player.WHITE);
		IChessBoard board = new ChessBoard();
		final int three = 3, negtwo = -2;
		int r = three, c = three;
		Move move = null;
		board.set(knight, r, c);
		
		// Test basic movement
		for (int i = negtwo; i <= 2; i++) {
			for (int j = negtwo; j <= 2; j++) {
				move = new Move(r, c, r + i, c + j);
				
				if (i == 0 && j == 0) {
					assertFalse(knight.isValidMove(move, board));
				} else if ((Math.abs(i) == 2) && Math.abs(j) == 1) {
					assertTrue(knight.isValidMove(move, board));
				} else if ((Math.abs(j) == 2) && Math.abs(i) == 1) {
					assertTrue(knight.isValidMove(move, board));
				} else {
					assertFalse(knight.isValidMove(move, board));
				}
			}
		}
	}
}

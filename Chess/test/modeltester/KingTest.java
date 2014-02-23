package modeltester;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.ChessBoard;
import model.IChessBoard;
import model.IChessPiece;
import model.King;
import model.Move;
import model.Player;

import org.junit.Test;
/**
 * Test the class King.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
public class KingTest {
	/**
	 * Test isValidMove.
	 */
	@Test
	public final void testType() {
		IChessPiece king = new King(Player.WHITE);
		assertEquals("king", king.type());
	}
	/**
	 * Test isValidMove.
	 */
	@Test
	public final void testIsValidMove() {
		IChessPiece king = new King(Player.WHITE);
		IChessBoard board = new ChessBoard();
		final int three = 3, negtwo = -2;
		int r = three, c = three;
		Move move = null;
		
		for (int i = negtwo; i <= 2; i++) {
			for (int j = negtwo; j <= 2; j++) {
				move = new Move(r, c, r + i, c + j);
				
				if (Math.abs(i) > 1 || Math.abs(j) > 1) {
					assertFalse(king.isValidMove(move, board));
				}
				
				if (Math.abs(i) == 1 && Math.abs(j) == 1) {
					assertTrue(king.isValidMove(move, board));
				}
				
				if (i == 0 && j == 0) {
					assertFalse(king.isValidMove(move, board));
				}
			}
		}
		
		// INDEX OUT OF BOUNDS
		move = new Move(0, 0, -1, -1);
		assertFalse(king.isValidMove(move, board));
	}
	/**
	 * Test first move.
	 */
	@Test
	public final void testFirstMove() {
		IChessPiece king = new King(Player.WHITE);
		IChessBoard board = new ChessBoard();
		
		assertTrue(((King) king).firstMove());
		
		board.set(king, 0, 0);
		Move move = new Move(0, 0, 1, 0);
		king.isValidMove(move, board);
		
		assertFalse(((King) king).firstMove());
	}
}

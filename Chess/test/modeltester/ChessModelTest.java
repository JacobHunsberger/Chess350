package modeltester;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.ChessModel;
import model.Player;
import model.Move;

import org.junit.Test;
/**
 * Test the class ChessModel.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
public class ChessModelTest {
	/**
	 * Test the current Player.
	 */
	@Test
	public final void testPlayer() {
		ChessModel chess = new ChessModel();
		final Player p = Player.WHITE;
		assertEquals(p, chess.currentPlayer());
		final Move m = new Move(1, 1, 2, 1);
		chess.move(m);
		assertFalse(p == chess.currentPlayer());
	}
	/**
	 * Test the inCheck Method.
	 */
	@Test
	public final void testInCheck() {
		ChessModel chess = new ChessModel();
		final int three = 3, four = 4, five = 5, six = 6, seven = 7;
		Move m = new Move(1, four, 2, four);
		chess.move(m); //move white pawn down 1.
		m = new Move(six, 0, five, 0);
		chess.move(m); //move a black pawn up 1.
		m = new Move(0, three, four, seven);
		chess.move(m); //move white queen in position for check.
		m = new Move(five, 0, four, 0);
		chess.move(m); //move a black pawn up 1.
		m = new Move(four, seven, six, five);
		chess.move(m); //move white queen to position check.
		m = new Move(six, five, seven, four);
		assertTrue(chess.inCheck(m));
	}
	/**
	 * Test isComplete method.
	 */
	@Test
	public final void testIsComplete() {
		ChessModel chess = new ChessModel();
		final int three = 3, four = 4, five = 5, six = 6, seven = 7;
		Move m = new Move(1, four, 2, four);
		chess.move(m); //move white pawn down 1.
		m = new Move(six, 0, five, 0);
		chess.move(m); //move a black pawn up 1.
		m = new Move(0, three, four, seven);
		chess.move(m); //move white queen in position for check.
		m = new Move(five, 0, four, 0);
		chess.move(m); //move a black pawn up 1.
		m = new Move(0, five, three, 2);
		chess.move(m); //move white bishop in position to support check.
		m = new Move(four, 0, three, 0);
		chess.move(m); //move a black pawn up 1.
		m = new Move(four, seven, six, five);
		chess.move(m); //move white queen to position checkmate.
		m = new Move(six, five, seven, four);
		assertTrue(chess.isComplete());
	}
}

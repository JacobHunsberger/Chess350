package modeltester;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.IChessModel;
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
		Player p = Player.WHITE;
		assertEquals(p, chess.currentPlayer());
		final Move m = new Move(1, 1, 2, 1);
		chess.move(m);
		p = Player.BLACK;
		assertEquals(p, chess.currentPlayer());
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
		//assertTrue(chess.inCheck(m));
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
	/**
	 * Test move.
	 */
	@Test
	public final void testMove() {
		IChessModel model = new ChessModel();
		
		final int three = 3, five = 5, six = 6, seven = 7;
		
		// Move white pawn forward 2
		Move move = new Move(1, 0, three, 0);
		model.move(move);
		assertEquals("pawn", model.pieceAt(three, 0).type());
		assertEquals(null, model.pieceAt(1, 0));
		
		// Move black pawn forward 1
		move = new Move(six, seven, five, seven);
		model.move(move);
		assertEquals("pawn", model.pieceAt(five, seven).type());
		assertEquals(null, model.pieceAt(six, seven));
	}
	/**
	 * Test isValidMove.
	 */
	@Test
	public final void testIsValidMove() {
		IChessModel model = new ChessModel();
		
		final int three = 3, five = 5, six = 6, seven = 7;
		
		// Attempt to move a white rook from start
		Move move = new Move(0, 0, 0, three);
		assertFalse(model.isValidMove(move));
		
		// Attempt to move a white knight from start
		move = new Move(0, 1, 2, 2);
		assertTrue(model.isValidMove(move));
		
		model.move(move);
		
		// Attempt to move a black bishop from start
		move = new Move(seven, five, five, three);
		assertFalse(model.isValidMove(move));
		
		// Attempt to move a black knight from start
		move = new Move(seven, six, five, five);
		assertTrue(model.isValidMove(move));	
	}
	/**
	 * Test pieceAt.
	 */
	@Test
	public final void testPieceAt() {
		IChessModel model = new ChessModel();
		
		final int six = 6, nine = 9;
		
		assertEquals("pawn", model.pieceAt(1, 0).type());
		assertEquals(Player.WHITE, model.pieceAt(1, 0).player());
		
		assertEquals("pawn", model.pieceAt(six, 0).type());
		assertEquals(Player.BLACK, model.pieceAt(six, 0).player());
		
		// Index out of bounds
		model.pieceAt(-1, nine);
	}
	/**
	 * Test pieceAt.
	 */
	@Test
	public final void testisValidCastle() {
		IChessModel model = new ChessModel();
		
		final int zero = 0, one = 1, two = 2, three = 3, four = 4, five = 5, six = 6, seven = 7;
		
		// Move white knight
		Move move = new Move(zero, six, two, seven);
		model.move(move);
		assertEquals("knight", model.pieceAt(two, seven).type());
		assertEquals(null, model.pieceAt(zero, six));
		
		// Move black pawn forward 
		move = new Move(six, seven, five, seven);
		model.move(move);
		assertEquals("pawn", model.pieceAt(five, seven).type());
		assertEquals(null, model.pieceAt(six, seven));
		
		//white pawn
		move = new Move(one, six, two, six);
		model.move(move);
		assertEquals("pawn", model.pieceAt(two, six).type());
		assertEquals(null, model.pieceAt(one, six));
		
		// Move black pawn forward 
		move = new Move(six, six, five, six);
		model.move(move);
		assertEquals("pawn", model.pieceAt(five, six).type());
		assertEquals(null, model.pieceAt(six, six));
		
		// white bishop
		move = new Move(zero, five, one, six);
		model.move(move);
		assertEquals("bishop", model.pieceAt(one, six).type());
		assertEquals(null, model.pieceAt(zero, five));
		
		// Move black pawn forward 
		move = new Move(five, six, four, six);
		model.move(move);
		assertEquals("pawn", model.pieceAt(four, six).type());
		assertEquals(null, model.pieceAt(five, six));
		
		// CASTLE testing
		move = new Move(zero, four, zero, seven);
		model.move(move);
		assertEquals("king", model.pieceAt(zero, six).type());
		assertEquals("rook", model.pieceAt(zero, five).type());
		
		// Move black pawn forward 
		move = new Move(five, seven, four, seven);
		model.move(move);
		assertEquals("pawn", model.pieceAt(four, seven).type());
		assertEquals(null, model.pieceAt(five, seven));
		
	}
	/**
	 * Test enPassant.
	 */
	@Test
	public final void testEnPassant() {
		IChessModel chess = new ChessModel();
		final int three = 3, four = 4, five = 5, six = 6, seven = 7;
		
		Move m = new Move(1, five, three, five);
		chess.move(m); //move white pawn down 2.
		m = new Move(six, six, four, six);
		chess.move(m); //move black pawn up 2.
		m = new Move(1, 0, 2, 0);
		chess.move(m); //move a white pawn.
		m = new Move(four, six, three, six);
		chess.move(m); //move black pawn up 1.
		m = new Move(1, seven, three, seven);
		chess.move(m); //move white pawn down 2.
		
		// En passant should be valid for the right white pawn.
		m = new Move(three, six, 2, five);
		chess.move(m); //invalid
		m = new Move(three, six, four, five);
		chess.move(m); //invalid
		m = new Move(three, six, four, seven);
		chess.move(m); //invalid
		m = new Move(three, six, 2, seven);
		chess.move(m); //valid
		
		assertEquals(null, chess.pieceAt(three, six));
		assertEquals(null, chess.pieceAt(three, seven));
		assertEquals("pawn", chess.pieceAt(three, five).type());
		assertEquals("pawn", chess.pieceAt(2, seven).type());		
	}
}

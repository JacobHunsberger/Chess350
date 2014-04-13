package modeltester;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import model.IChessModel;
import model.ChessModel;
import model.IChessPiece;
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
		IChessModel model = new ChessModel();
		
		final int three = 3, four = 4, five = 5, six = 6, seven = 7;
		
		// Move white pawn
		Move m = new Move(1, three, three, three);
		model.move(m);
		
		// Move black pawn
		m = new Move(six, four, four, four);
		model.move(m);
		
		// Move white bishop to check white
		m = new Move(0, 2, four, six);
		model.move(m);
		
		// Try to move without getting out of check.
		m = new Move(six, three, five, three);
		assertTrue(model.inCheck(m));
		
		// Move out of check
		m = new Move(seven, four, six, four);
		assertFalse(model.inCheck(m));
	}
	/**
	 * Test isComplete method.
	 */
	@Test
	public final void testIsComplete() {
		IChessModel model;
		
		final int three = 3, four = 4, six = 6, seven = 7;
		
		// Fool's mate
		model = new ChessModel();
		
		// Move white pawn
		Move m = new Move(1, 2, 2, 2);
		model.move(m);
		
		// Move black pawn
		m = new Move(six, three, four, three);
		model.move(m);
		
		// Move white pawn
		m = new Move(1, 1, three, 1);
		model.move(m);
		// Move black queen to checkmate white
		m = new Move(seven, four, three, 0);
		model.move(m);
		
		assertTrue(model.isComplete());
		
		// Test check but not mate
		model = new ChessModel();
		
		// Move white pawn
		m = new Move(1, three, three, three);
		model.move(m);
		
		// Move black pawn
		m = new Move(six, four, four, four);
		model.move(m);
		
		// Move white bishop to check white
		m = new Move(0, 2, four, six);
		model.move(m);
		
		assertFalse(model.isComplete());	
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
		
		// Move white knight out
		move = new Move(0, 1, 2, 0);
		model.move(move);
		
		// Move black rook forward
		move = new Move(seven, seven, six, seven);
		model.move(move);
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
		
		final int three = 3, four = 4, five = 5, six = 6;
		
		// Move white knight
		Move move = new Move(0, 1, 2, 0);
		model.move(move);
		
		// Move black pawn forward 
		move = new Move(six, 0, five, 0);
		model.move(move);
		
		// Move white pawn
		move = new Move(1, 1, 2, 1);
		model.move(move);
		
		// Move black pawn forward 
		move = new Move(six, 2, five, 2);
		model.move(move);
		
		// Move white bishop
		move = new Move(0, 2, 1, 1);
		model.move(move);
		
		// Move black pawn forward 
		move = new Move(five, 2, four, 2);
		model.move(move);
		
		// CASTLE testing
		move = new Move(0, 0, 0, three);
		model.move(move);
		assertEquals("king", model.pieceAt(0, 1).type());
		assertEquals("rook", model.pieceAt(0, 2).type());
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
	
	/**
	 * Test iterator functionality.
	 */
	@Test
	public final void testIterator() {
		IChessModel model = new ChessModel();
		
		final int three = 3, four = 4, five = 5, six = 6;
		
		Iterator<IChessPiece> i = ((ChessModel) model).getWhiteTaken();
		
		assertFalse(i.hasNext());
		
		// Take a black piece.
		Move m = new Move(1, four, three, four);
		model.move(m);
		
		m = new Move(six, 1, four, 1);
		model.move(m);
		
		m = new Move(0, five, four, 1);
		model.move(m);
		
		i = ((ChessModel) model).getBlackTaken();
		
		assertTrue(i.hasNext());
	}
}

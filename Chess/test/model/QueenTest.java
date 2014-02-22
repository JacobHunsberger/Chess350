package model;

import static org.junit.Assert.*;
import model.ChessBoard;
import model.IChessBoard;
import model.IChessPiece;
import model.Move;
import model.Pawn;
import model.Player;
import model.Queen;

import org.junit.Test;

public class QueenTest {

	@Test
	public void testType() {
		IChessPiece queen = new Queen(Player.WHITE);
		assertEquals("Queen", queen.type());
	}
	
	@Test
	public void testIsValidMove() {
		IChessPiece queen = new Queen(Player.WHITE);
		IChessBoard board = new ChessBoard();
		
		int r = 3, c = 3;
		Move move = null;
		board.set(queen, r, c);
		
		// Test basic movement
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				move = new Move(r, c, r + i, c + j);
				
				if (i == 0 && j == 0) {
					assertFalse(queen.isValidMove(move, board));
				}
				else if (i == 0 || j == 0) {
					assertTrue(queen.isValidMove(move, board));
				}
				else if (Math.abs(i) == Math.abs(j)) {
					assertTrue(queen.isValidMove(move, board));
				}
				else {
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
		
		board.set(new Pawn(Player.BLACK), r, 5);
		move = new Move(r, c, r, 6);
		assertFalse(queen.isValidMove(move, board));
		move = new Move(r, c, r, 4);
		assertTrue(queen.isValidMove(move, board));
		
		board.set(new Pawn(Player.WHITE), 5, c);
		move = new Move(r, c, 6, c);
		assertFalse(queen.isValidMove(move, board));
		move = new Move(r, c, 4, c);
		assertTrue(queen.isValidMove(move, board));
		
		// Test diagonal jumping
		board.set(new Pawn(Player.BLACK), 1, 1);
		move = new Move(r, c, 0, 0);
		assertFalse(queen.isValidMove(move, board));
		move = new Move(r, c, 2, 2);
		assertTrue(queen.isValidMove(move, board));
		
		board.set(new Pawn(Player.WHITE), 1, 5);
		move = new Move(r, c, 0, 6);
		assertFalse(queen.isValidMove(move, board));
		move = new Move(r, c, 2, 4);
		assertTrue(queen.isValidMove(move, board));
		
		board.set(new Pawn(Player.BLACK), 5, 5);
		move = new Move(r, c, 6, 6);
		assertFalse(queen.isValidMove(move,board));
		move = new Move(r, c, 4, 4);
		assertTrue(queen.isValidMove(move, board));
		
		board.set(new Pawn(Player.WHITE), 5, 1);
		move = new Move(r, c, 6, 0);
		assertFalse(queen.isValidMove(move, board));
		move = new Move(r, c, 4, 2);
		assertTrue(queen.isValidMove(move,board));
	}
}

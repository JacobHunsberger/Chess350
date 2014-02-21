package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BishopTest {

	@Test
	public void testType() {
		IChessPiece bishop = new Bishop(Player.WHITE);
		assertEquals("Bishop", bishop.type());
	}
	
	@Test
	public void testIsValidMove() {
		IChessPiece bishop = new Bishop(Player.WHITE);
		IChessBoard board = new ChessBoard();
		
		int r = 3, c = 3;
		Move move = null;
		board.set(bishop, r, c);
		
		// Test basic movement
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				move = new Move(r, c, r + i, c + j);
				
				if (i == 0 && j == 0) {
					assertFalse(bishop.isValidMove(move, board));
				}
				else if (Math.abs(i) == Math.abs(j)) {
					assertTrue(bishop.isValidMove(move, board));
				}
				else {
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
		
		board.set(new Pawn(Player.WHITE), 1, 5);
		move = new Move(r, c, 0, 6);
		assertFalse(bishop.isValidMove(move, board));
		move = new Move(r, c, 2, 4);
		assertTrue(bishop.isValidMove(move, board));
		
		board.set(new Pawn(Player.BLACK), 5, 5);
		move = new Move(r, c, 6, 6);
		assertFalse(bishop.isValidMove(move,board));
		move = new Move(r, c, 4, 4);
		assertTrue(bishop.isValidMove(move, board));
		
		board.set(new Pawn(Player.WHITE), 5, 1);
		move = new Move(r, c, 6, 0);
		assertFalse(bishop.isValidMove(move, board));
		move = new Move(r, c, 4, 2);
		assertTrue(bishop.isValidMove(move,board));
	}
}

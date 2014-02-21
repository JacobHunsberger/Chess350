package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class KingTest {

	@Test
	public void testType() {
		IChessPiece king = new King(Player.WHITE);
		assertEquals("King", king.type());
	}
	
	@Test
	public void testIsValidMove() {
		IChessPiece king = new King(Player.WHITE);
		IChessBoard board = new ChessBoard();
		
		int r = 3, c = 3;
		Move move = null;
		
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
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
	}
}

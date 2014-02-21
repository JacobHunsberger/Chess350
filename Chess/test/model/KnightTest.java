package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class KnightTest {

	@Test
	public void testType() {
		IChessPiece piece = new Knight(Player.WHITE);
		assertEquals("Knight", piece.type());
	}
	
	@Test
	public void testIsValidMove() {
		IChessPiece knight = new Knight(Player.WHITE);
		IChessBoard board = new ChessBoard();
		
		int r = 3, c = 3;
		Move move = null;
		board.set(knight, r, c);
		
		// Test basic movement
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				move = new Move(r, c, r + i, c + j);
				
				if (i == 0 && j == 0) {
					assertFalse(knight.isValidMove(move, board));
				}
				else if ((Math.abs(i) == 2) && Math.abs(j) == 1) {
					assertTrue(knight.isValidMove(move, board));
				}
				else if ((Math.abs(j) == 2) && Math.abs(i) == 1) {
					assertTrue(knight.isValidMove(move, board));
				}
				else {
					assertFalse(knight.isValidMove(move, board));
				}
			}
		}
	}
}

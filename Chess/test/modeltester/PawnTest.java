package modeltester;

import static org.junit.Assert.*;
import model.ChessBoard;
import model.IChessBoard;
import model.IChessPiece;
import model.Move;
import model.Pawn;
import model.Player;
import model.Rook;

import org.junit.Test;

public class PawnTest {

	@Test
	public void testType() {
		IChessPiece pawn = new Pawn(Player.WHITE);
		assertEquals("pawn", pawn.type());
	}
	
	@Test
	public void testIsValidMove() {
		IChessPiece pawn = new Pawn(Player.WHITE);
		IChessBoard board = new ChessBoard();
		Move move = null;
		
		int r = 3, c = 3;
		
		board.set(pawn, 1, c);
		
		// Test special move
		move = new Move(1, c, r, c);
		assertTrue(pawn.isValidMove(move, board));
		
		// Test movement
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				move = new Move(r, c, r + i, c + j);
				
				if (i == 1 && j == 0) {
					assertTrue(pawn.isValidMove(move, board));
				}
				else {
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
		board.set(pawn, 6, c);
		
		r = 4;
		c = 4;
		
		// Test special move
		move = new Move(6, c, r, c);
		assertTrue(pawn.isValidMove(move, board));
		
		// Test movement
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				move = new Move(r, c, r + i, c + j);
				
				if (i == -1 && j == 0) {
					assertTrue(pawn.isValidMove(move, board));
				}
				else {
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

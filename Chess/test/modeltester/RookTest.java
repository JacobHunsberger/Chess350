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

public class RookTest {

	@Test
	public void testType() {
		IChessPiece rook = new Rook(Player.WHITE);
		assertEquals("rook", rook.type());
	}
	
	@Test
	public void testIsValidMove() {
		IChessPiece rook = new Rook(Player.WHITE);
		IChessBoard board = new ChessBoard();
		
		int r = 3, c = 3;
		Move move = null;
		board.set(rook, r, c);
		
		// Test basic movement
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				move = new Move(r, c, r + i, c + j);
				
				if (i == 0 && j == 0) {
					assertFalse(rook.isValidMove(move, board));
				}
				else if (i == 0 || j == 0) {
					assertTrue(rook.isValidMove(move, board));
				}
				else {
					assertFalse(rook.isValidMove(move, board));
				}
				
			}
		}
		
		// Test jumping
		board.set(new Pawn(Player.BLACK), r, 1);
		move = new Move(r, c, r, 0);
		assertFalse(rook.isValidMove(move, board));		
		move = new Move(r, c, r, 2);
		assertTrue(rook.isValidMove(move, board));
		
		board.set(new Pawn(Player.WHITE), 1, c);
		move = new Move(r, c, 0, c);
		assertFalse(rook.isValidMove(move, board));
		move = new Move(r, c, 2, c);
		assertTrue(rook.isValidMove(move, board));
		
		board.set(new Pawn(Player.BLACK), r, 5);
		move = new Move(r, c, r, 6);
		assertFalse(rook.isValidMove(move, board));
		move = new Move(r, c, r, 4);
		assertTrue(rook.isValidMove(move, board));
		
		board.set(new Pawn(Player.WHITE), 5, c);
		move = new Move(r, c, 6, c);
		assertFalse(rook.isValidMove(move, board));
		move = new Move(r, c, 4, c);
		assertTrue(rook.isValidMove(move, board));
	}
}

package model;

/**
 * A Bishop in a game of chess.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
public class Bishop extends ChessPiece {

/**
* Constructs a new Bishop object.
* @param color the player that owns this piece
*/
    protected Bishop(final Player color) {
        super(color);
    }
/**
 * A Bishop has the type String.
 * Used for identification.
 * @return String identifying the Bishop
 */
	@Override
	public final String type() {
		return "bishop";
	}
/**
 * Method check if move is valid for Bishop.
 * @param move for the move of the Bishop
 * @param board of type IChessBoard
 * @return boolean if the move is valid or not
 */
	@Override
	public final boolean isValidMove(final Move move, final IChessBoard board) {
		//Check the isValidMove from the super class.
		if (!super.isValidMove(move, board)) {
			return false; 
			// else if (move.getFromColumn() == move.getToColumn() 
				//|| move.getFromRow() == move.getToRow()) {
				/*
				 * The to or from column or the to or from row cannot 
				 * be the same for a diagonal move.
				 */
			//	return false;
			//} <-- not sure if this is needed. may be achomplised 
			// from the below code
			} else if (Math.abs(move.getFromColumn() - move.getToColumn()) 
					!= Math.abs(move.getFromRow() - move.getToRow())) {
				//The move has to be perfectly diagonal
				return false;
			} else {
				if (move.getFromRow() < move.getToRow() && move.getFromColumn()
						< move.getToColumn()) {
					int i = move.getFromRow() + 1;
					int c = move.getFromColumn() + 1;
					while (i < move.getToRow()){
						/**if(board[i][c].type() != "blank"){
							System.out.println("Problem, piece at row:"
							 + i + " column:" + c);
							return false;
						}
						else{
							i++;
							c++;
							System.out.println("Nothing at row:" 
							+ i + " column:" + c);
						}*/
					}
				} else if (move.getFromRow() > move.getToRow() 
						&& move.getFromColumn() < move.getToColumn()) {
					int i = move.getToRow() + 1;
					int c = move.getToColumn() - 1;
					while (i < move.getFromRow()){
						/**if(board[i][c].type() != "blank"){
							System.out.println("Problem, piece at row:"
							 + i + " column:" + c);
							return false;
						}
						else{
							i++;
							c--;
							System.out.println("Nothing at row:"
							 + i + " column:" + c);
						}*/
					}
				}
				else if (move.getFromRow() < move.getToRow() 
						&& move.getFromColumn() > move.getToColumn()) {
					int i = move.getFromRow() +1;
					int c = move.getFromColumn() - 1;
					while (i < move.getToRow()){
						/**if(board[i][c].type() != "blank"){
							System.out.println("Problem, piece at row:"
							 + i + " column:" + c);
							return false;
						}
						else{
							i++;
							c--;
							System.out.println("Nothing at row:" 
							+ i + " column:" + c);
						}*/
					}
				}
				else if (move.getFromRow() > move.getToRow()
						&& move.getFromColumn() > move.getToColumn()) {
					int i = move.getToRow() + 1;
					int c = move.getToColumn() + 1;
					while (i < move.getFromRow()){
						/**if(board[i][c].type() != "blank"){
							System.out.println("Problem, piece at row:" 
							+ i + " column:" + c);
							return false;
						}
						else{
							i++;
							c++;
							System.out.println("Nothing at row:" + i 
							+ " column:" + c);
						}*/
					}
				}
			}
		
		// Bishop can move diagonally only just as many spaces as desired
		// without jumping over other chess pieces
	
		return true;
	}
}


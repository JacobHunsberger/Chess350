/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.ChessModel;
import model.Move;
import model.Player;
/**
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 *
 */
@SuppressWarnings("serial")
public class ChessViewBoard extends JPanel {

	ChessModel model;
	JButton[][] buttonBoard;
	private Boolean select;
	private JButton fromSpace;
	private JButton toSpace;
	private int fromRow;
	private int toRow;
	private int fromColumn;
	private int toColumn;
	private ArrayList<Integer> moves = new ArrayList<Integer>();
	
	public ChessViewBoard() {
		setLayout(new GridLayout(8,8));
		buttonBoard = new JButton[8][8];
		//ButtonListener buttonListener = new ButtonListener();
		model = new ChessModel();
		//int count = 0;
		/*for (int i = 0; i < 8; i++) {
			for (int k = 0; k < 8; k++) {
				buttonBoard[i][k] = new JButton();
				buttonBoard[i][k].addActionListener(buttonListener);
				buttonBoard[i][k].setPreferredSize(new Dimension(50, 50));
				if(((count++) % 2) == 1)
				{
					buttonBoard[i][k].setBackground(Color.getHSBColor(24.0f, 0.42f, 0.30f));
				}
				else {
					buttonBoard[i][k].setBackground(Color.white);
				}
				try{
					setImage(buttonBoard[i][k], i, k, model.pieceAt(i, k).player());
				} catch (NullPointerException e) {}
				add(buttonBoard[i][k]);
			}
			count--;
		}*/
		updateBoard();
		select = false;
	}
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (select == false) {
				fromSpace = (JButton) e.getSource();
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (buttonBoard[i][j] == fromSpace) {
							try {
								if (model.pieceAt(i, j).player().
										equals(model.currentPlayer())) {
									select = true;
									fromRow = i;
									fromColumn = j;
									i = 8;
									j = 8;
								}
							} catch (NullPointerException npe) {
								// Clicked an empty space
							}
						}
					}
				}
			} else {
				toSpace = (JButton) e.getSource();
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (buttonBoard[i][j] == toSpace) {
							select = false;
							toRow = i;
							toColumn = j;
							i = 8;
							j = 8;
						}
					}
				}
				Move m = new Move(fromRow, fromColumn, toRow, toColumn);
				model.move(m);
				updateBoard();
			}
		}
	}
	private void updateBoard() {
		ButtonListener buttonListener = new ButtonListener();
		int count = 0;
		for (int i = 0; i < 8; i++) {
			for (int k = 0; k < 8; k++) {
				buttonBoard[i][k] = new JButton();
				buttonBoard[i][k].addActionListener(buttonListener);
				buttonBoard[i][k].setPreferredSize(new Dimension(50, 50));
				if(((count++) % 2) == 1)
				{
					buttonBoard[i][k].setBackground(Color.getHSBColor(24.0f, 0.42f, 0.30f));
				}
				else {
					buttonBoard[i][k].setBackground(Color.white);
				}
				try{
					setImage(buttonBoard[i][k], i, k, model.pieceAt(i, k).player());
				} catch (NullPointerException e) {}
				add(buttonBoard[i][k]);
			}
			count--;
		}
	}
	private void setImage(JButton b, int r, int c, Player p) {
		try {
			ImageIcon is = null;
			if (model.pieceAt(r, c).player() == p) {
				if (model.pieceAt(r, c).type() == "king") {
					is = new ImageIcon(getClass().getResource(
							"images/King/king" + p.toString() +".png"));
				} else if (model.pieceAt(r, c).type() == "queen") {
					is = new ImageIcon(getClass().getResource(
							"images/Queen/queen" + p.toString() +".png"));
				} else if (model.pieceAt(r, c).type() == "bishop") {
					is = new ImageIcon(getClass().getResource(
							"images/Bishop/bishop" + p.toString() +".png"));
				} else if (model.pieceAt(r, c).type() == "rook") {
					is = new ImageIcon(getClass().getResource(
							"images/Rook/rook" + p.toString() +".png"));
				} else if (model.pieceAt(r, c).type() == "knight") {
					is = new ImageIcon(getClass().getResource(
							"images/Knight/knight" + p.toString() +".png"));
				} else if (model.pieceAt(r, c).type() == "pawn") {
					is = new ImageIcon(getClass().getResource(
							"images/Pawn/pawn" + p.toString() +".png"));
				}
			}
			b.setIcon(is);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
}
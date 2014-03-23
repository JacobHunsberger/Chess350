/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Bishop;
import model.IChessPiece;
import model.Knight;
import model.Player;
import model.Queen;
import model.Rook;

/**
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 *
 */
@SuppressWarnings("serial")
public class ChessViewSide extends JPanel {

	private String message;
	final int numButtons = 4;
    JRadioButton[] radioButtons = new JRadioButton[numButtons];
	private JPanel top, bottom;
	private IChessPiece promote;
	private Player tempPlayer;
    
    /**
	 * 
	 */
	public ChessViewSide() {
		top = new JPanel();
		bottom = new JPanel();
		promote = null;
		this.setPreferredSize(new Dimension(200, 400));
		this.setBackground(Color.gray);
		revalidate();
		repaint();
	}
	
	public IChessPiece promotion(Player p) {
		ItemListener item = null;
		message = "What would you like your Pawn promotion to be?";
		radioButtons[0] = new JRadioButton("Queen");
		radioButtons[1] = new JRadioButton("Rook");
		radioButtons[2] = new JRadioButton("Bishop");
		radioButtons[3] = new JRadioButton("Knight");
		radioButtons[0].addItemListener(item);
		radioButtons[1].addItemListener(item);
		radioButtons[2].addItemListener(item);
		radioButtons[3].addItemListener(item);
		tempPlayer = p;
		setLayout(new GridLayout(2,2));
		add(radioButtons[0]);
		add(radioButtons[1]);
		add(radioButtons[2]);
		add(radioButtons[3]);
		while (promote == null){
			
		}
		return promote;
	}
	private void updateSide() {
		//top
	}
	public void itemStateChanged(ItemEvent e) {
	    if (e.getStateChange() == ItemEvent.SELECTED) {
	        for (int i = 0; i < 4; i++) {
	        	if (e.equals(radioButtons[i])) {
	        		if (radioButtons[i].getText().equals("Queen")) {
	        			promote = new Queen(tempPlayer);
	        		} else if (radioButtons[i].getText().equals("Rook")) {
	        			promote = new Rook(tempPlayer);
	        		} else if (radioButtons[i].getText().equals("Bishop")) {
	        			promote = new Bishop(tempPlayer);
	        		} else {
	        			promote = new Knight(tempPlayer);
	        		}
	        	}
	        }
	        removeAll();
	    }
	    else if (e.getStateChange() == ItemEvent.DESELECTED) {
	        // Your deselected code here.
	    }
	}
}

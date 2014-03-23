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

	/**
	 * number of buttons.
	 */
	private final int numButtons = 4;
	/**
	 * List of radiobuttons.
	 */
    private JRadioButton[] radioButtons = new JRadioButton[numButtons];
	/**
	 * Chesspiece to promote.
	 */
    private IChessPiece promote;
	
    /**
     * Temporary player.
     */
    private Player tempPlayer;
    
    /**
	 * 
	 */
	public ChessViewSide() {
		final int twohundy = 200;
		final int fourhundy = 400;
		promote = null;
		this.setPreferredSize(new Dimension(twohundy, fourhundy));
		this.setBackground(Color.gray);
		revalidate();
		repaint();
	}
	
	/**
	 * Piece to promote.
	 * @param p Player to promote piece of.
	 * @return IChessPiece piece to promote to.
	 */
	public final IChessPiece promotion(final Player p) {
		final int zero = 0;
		final int one = 1;
		final int two = 2;
		final int three = 3;
		ItemListener item = null;
		radioButtons[zero] = new JRadioButton("Queen");
		radioButtons[one] = new JRadioButton("Rook");
		radioButtons[two] = new JRadioButton("Bishop");
		radioButtons[three] = new JRadioButton("Knight");
		radioButtons[zero].addItemListener(item);
		radioButtons[one].addItemListener(item);
		radioButtons[two].addItemListener(item);
		radioButtons[three].addItemListener(item);
		tempPlayer = p;
		setLayout(new GridLayout(two, two));
		add(radioButtons[zero]);
		add(radioButtons[one]);
		add(radioButtons[two]);
		add(radioButtons[three]);
		return promote;
	}
	/**
	 * Item state changed.
	 * @param e item event.
	 */
	public final void itemStateChanged(final ItemEvent e) {
		final int four = 4;
	    if (e.getStateChange() == ItemEvent.SELECTED) {
	        for (int i = 0; i < four; i++) {
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
	    } /* else if (e.getStateChange() == ItemEvent.DESELECTED) {
	        // Your deselected code here.
	    }*/
	}
}

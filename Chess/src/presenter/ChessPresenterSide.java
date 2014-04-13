/**
 * 
 */
package presenter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;

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
public class ChessPresenterSide extends JPanel {

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
     * Internal JPanels to the side.
     */
    private JPanel top, bottom;
    /**
     * 
     */
    private boolean tempBoolean = false;
    /**
	 * Main constructor for ChessPresenterSide.
	 */
	public ChessPresenterSide() {
		tempBoolean = true;
		setUpPanel();
	}
	/**
	 * 
	 */
	private void updateSide() {
		revalidate();
		repaint();
	}
	/**
	 * 
	 */
	private void setUpPanel() {
		setLayout(new BorderLayout());
		final int twohundy = 200;
		final int fourhundy = 400;
		promote = null;
		top = new JPanel();
		bottom = new JPanel();
		top.setPreferredSize(new Dimension(twohundy, twohundy));
		top.setBackground(Color.blue);
		bottom.setPreferredSize(new Dimension(twohundy, twohundy));
		bottom.setBackground(Color.green);
		setPreferredSize(new Dimension(twohundy, fourhundy));
		add(top, BorderLayout.NORTH);
		add(bottom, BorderLayout.SOUTH);
		updateSide();
	}
	/**
	 * Constructor to setup the pawn Promotion selection.
	 * @param temp determines how the contructor functions.
	 */
	public ChessPresenterSide(final int temp) {
		if (temp == 0) {
			setUpPanel();
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
			top.setLayout(new GridLayout(two, two));
			top.add(radioButtons[zero]);
			top.add(radioButtons[one]);
			top.add(radioButtons[two]);
			top.add(radioButtons[three]);
			remove(top);
			add(top, BorderLayout.NORTH);
			updateSide();
		}
	}
	/**
	 * Piece to promote.
	 * @param p Player to promote piece of.
	 * @return IChessPiece piece to promote to.
	 */
	public final IChessPiece promotion(final Player p) {
		//final int zero = 0;
		//final int one = 1;
		//final int two = 2;
		//final int three = 3;
		//ItemListener item = null;
		//radioButtons[zero] = new JRadioButton("Queen");
		//radioButtons[one] = new JRadioButton("Rook");
		//radioButtons[two] = new JRadioButton("Bishop");
		//radioButtons[three] = new JRadioButton("Knight");
		//radioButtons[zero].addItemListener(item);
		//radioButtons[one].addItemListener(item);
		//radioButtons[two].addItemListener(item);
		//radioButtons[three].addItemListener(item);
		tempPlayer = p;
		//setLayout(new GridLayout(two, two));
		//top.add(radioButtons[zero]);
		//top.add(radioButtons[one]);
		//top.add(radioButtons[two]);
		//top.add(radioButtons[three]);
		updateSide();
		//while (radioButtons[0].getI) {
		//	System.out.println("waiting");
		//}
		return promote = new Queen(tempPlayer);
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
	        top.removeAll();
	        try {
				getClass().getConstructor().newInstance();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
	        tempBoolean = true;
	    } /* else if (e.getStateChange() == ItemEvent.DESELECTED) {
	        // Your deselected code here.
	    }*/
	}
}

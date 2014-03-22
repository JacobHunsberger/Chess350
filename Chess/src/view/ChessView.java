package view;
import java.awt.Dimension;

import javax.swing.JApplet;

/**
 * The view class to build the view of the game.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
@SuppressWarnings("serial")
public class ChessView extends JApplet {

	ChessViewSide cvs;
	ChessViewBoard cvb;
	public ChessView() {	
		this.setSize(500, 500);
		cvs = new ChessViewSide();
		cvb = new ChessViewBoard();
		getContentPane().add(cvb);
		getContentPane().setVisible(true);
		this.setPreferredSize(new Dimension(500,500));
	}	
	
	/**
	 * Starts the chess Gui in applet view.
	 * @param frame the frame for the whole game of chess
	 */
		// add formatting for frame

}

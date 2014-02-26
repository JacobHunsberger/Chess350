package view;

import java.applet.Applet;

import javax.swing.JInternalFrame;

/**
 * The view class to build the view of the game.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
@SuppressWarnings("serial")
public class ChessView extends Applet {

	/**
	 * Starts the chess Gui in applet view.
	 * @param frame the frame for the whole game of chess
	 */
	public ChessView(final JInternalFrame frame) {
		ChessViewSide cvs = new ChessViewSide();
		ChessViewBoard cvb = new ChessViewBoard();
		frame.add(cvb);
		frame.add(cvs);
		// TODO Auto-generated constructor stub, add formatting for frame
	}

}

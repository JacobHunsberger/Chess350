package view;
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
		cvs = new ChessViewSide();
		cvb = new ChessViewBoard();
		getContentPane().add(cvb);
		getContentPane().setVisible(true);
	}	
	
	/**
	 * Starts the chess Gui in applet view.
	 * @param frame the frame for the whole game of chess
	 */
		// add formatting for frame

}

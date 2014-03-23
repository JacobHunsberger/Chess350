package view;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JPanel;

/**
 * The view class to build the view of the game.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
@SuppressWarnings("serial")
public class ChessView extends JApplet {

	ChessViewSide cvs;
	ChessViewBoard cvb;
	JPanel panel;
	public ChessView() {
		
		panel = new JPanel();
		cvs = new ChessViewSide();
		cvb = new ChessViewBoard();
		panel.add(cvb, BorderLayout.WEST);
		panel.add(cvs, BorderLayout.EAST);
		getContentPane().add(panel);
		getContentPane().setVisible(true);
		this.setSize(new Dimension(500,700));
	}	
	
	/**
	 * Starts the chess Gui in applet view.
	 * @param frame the frame for the whole game of chess
	 */
		// add formatting for frame

}

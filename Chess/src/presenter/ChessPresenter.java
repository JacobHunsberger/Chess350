package presenter;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JPanel;

/**
 * The view class to build the view of the game.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
@SuppressWarnings("serial")
public class ChessPresenter extends JApplet {

	/**
	 * cvs View Side.
	 */
	private ChessPresenterSide cvs;
	/**
	 * cvb Chess Board.
	 */
	private ChessPresenterBoard cvb;
	/**
	 * panel Jpael.
	 */
	private JPanel panel;
	
	/**
	 * Constructor.
	 */
	public ChessPresenter() {
		final int fivehundy = 500;
		final int sevenhundy = 700;
		panel = new JPanel();
		cvs = new ChessPresenterSide();
		cvb = new ChessPresenterBoard();
		panel.add(cvb, BorderLayout.WEST);
		panel.add(cvs, BorderLayout.EAST);
		getContentPane().add(panel);
		getContentPane().setVisible(true);
		this.setSize(new Dimension(fivehundy, sevenhundy));
	}	
	/**
	 * Starts the chess Gui in applet view.
	 * @param frame the frame for the whole game of chess
	 */
		// add formatting for frame

}

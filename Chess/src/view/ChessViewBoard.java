/**
 * 
 */
package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 *
 */
@SuppressWarnings("serial")
public class ChessViewBoard extends JPanel {

	/**
	 * 
	 */
	public ChessViewBoard() {
		setLayout(new GridLayout(8,8));
		JButton[][] buttonBoard = new JButton[8][8];
		ButtonListener buttonListener = new ButtonListener();
		for (int i = 0; i < 8; i++) {
			for (int k = 0; k < 8; k++) {
				buttonBoard[i][k] = new JButton();
				buttonBoard[i][k].addActionListener(buttonListener);
				buttonBoard[i][k].setPreferredSize(new Dimension(30, 30));
				add(buttonBoard[i][k]);
			}
		}
	}
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		
		}
	}
}
package view;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import presenter.Main;
/**
 * Menu for the game.
 * @author Jacob Hunsberger, Jon Powers, Jared Thomas
 *
 */
@SuppressWarnings("serial")
public class ChessMenu extends JMenuBar {

	/**
	 * So that the Menu can reset the frame.
	 */
	private JFrame localFrame;
	/**
	 * JMenu items.
	 */
	private JMenu menu, help;
	/**
	 * Menu items to choose from.
	 */
	private JMenuItem exit, about, newGame, rules;
	/**
	 * Menu listener to add to items.
	 */
	private MenuListener menuLis;
	/**
	 * Constructor for class to setup the menu bar.
	 */
	public ChessMenu() {
		menuLis = new MenuListener();
		menu = new JMenu("File");
		help = new JMenu("Help");
		exit = new JMenuItem("Exit");
		exit.addActionListener(menuLis);
		about = new JMenuItem("About");
		about.addActionListener(menuLis);
		newGame = new JMenuItem("New");
		newGame.addActionListener(menuLis);
		rules = new JMenuItem("Rules");
		rules.addActionListener(menuLis);
		help.add(about);
		help.add(rules);
		help.getPopupMenu().setLightWeightPopupEnabled(false);
		menu.add(newGame);
		menu.add(exit);
		menu.getPopupMenu().setLightWeightPopupEnabled(false);
		add(menu);
		add(help);
	}
	/**
	 * So the menu can reset it.
	 * @param f the chess view frame.
	 */
	public final void giveJFrame(final JFrame f) {
		localFrame = f;
	}	
	/**
	 * Private class to handel the listener.
	 * @author Jacob Hunsberger, Jon Powers, Jared Thomas
	 *
	 */
	private class MenuListener implements ActionListener {
		/**
		 * Get the action for the menu item.
		 * @param e the event that happened.
		 */
		public void actionPerformed(final ActionEvent e) {
			JMenuItem source = (JMenuItem) (e.getSource());
			if (source.equals(exit)) {
				System.exit(0);
			} else if (source.equals(newGame)) {
				localFrame.removeAll();
				localFrame.dispose();
				localFrame = new ChessView();
				Main main = new Main();
				main.giveNewFrame(localFrame);
			} else if (source.equals(rules)) {
				final String urlString = 
						"http://en.wikipedia.org/wiki/Rules_of_chess";
				try {
					Desktop.getDesktop().browse(new URL(urlString).toURI());
				} catch (Exception ex) {
			        //ex.printStackTrace();
			    }
			} else if (source.equals(about)) {
				final String displayString = "Version: 1.3\nRelease: 2014\n"
						+ "\nDevelopers:\nJacob Hunsberger\nJon Powers\nJar"
						+ "ed Thomas";
				final String titleString = "About";
				final int messageType = 1;
				JOptionPane.showMessageDialog(null, displayString,
						titleString, messageType);
			}
        }
	}
}

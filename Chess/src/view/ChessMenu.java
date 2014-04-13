package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 * Menu for the game.
 * @author Jacob Hunsberger, Jon Powers, Jared Thomas
 *
 */
@SuppressWarnings("serial")
public class ChessMenu extends JMenuBar {

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
		newGame = new JMenuItem("New");
		newGame.addActionListener(menuLis);
		rules = new JMenuItem("Rules");
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
				
			} else if (source.equals(rules)) {
				
			} else if (source.equals(about)) {
				
			}
        }
	}
}

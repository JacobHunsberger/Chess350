package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import presenter.ChessPresenter;

public class ChessMenu extends JMenuBar{

	JMenu menu, help;
	JMenuItem exit, about, newGame, rules;
	MenuListener menuLis;
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
	private class MenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JMenuItem source = (JMenuItem) (e.getSource());
			if (source.equals(exit)) {
				System.exit(0);
			} else if (source.equals(newGame)) {
				ChessView.reset();
				String[] temp = new String[0];
				ChessView.main(temp);
			} else if (source.equals(rules)) {
				//TODO needs to be implemented.
			} else if (source.equals(about)) {
				//TODO needs to be implemented.
			}
        }
	}
}

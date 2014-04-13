package presenter;

import view.ChessView;
import model.ChessModel;
/**
 * Class to start the game.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 *
 */
public final class Main {

	/**
	 * Default Constructor.
	 */
	private Main() {
		
	}
	/**
	 * Main method to invoke the game.
	 * @param args default starter
	 */
	public static void main(final String[] args) {
		ChessView view = new ChessView();
		ChessModel model = new ChessModel();
		new ChessPresenter(model, view);
	}
}
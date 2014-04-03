package presenter;

import view.ChessView;
import model.ChessModel;

public class Main {

	public static void main(String[] args) {
		ChessView view = new ChessView();
		ChessModel model = new ChessModel();
		new ChessPresenter(model, view);
	}

}

package ca.uqam.inf5153.model;

import ca.uqam.inf5153.enums.StatusDeCase;
import ca.uqam.inf5153.exceptions.LocationAlreadyHitException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Grille {

	private ObjectProperty<GrilleState> state = new SimpleObjectProperty<GrilleState>();

	public Matrix<SimpleObjectProperty<StatusDeCase>> getMatrice() {
		return state.get().matrice;
	}

	public void setMatrice(Matrix<SimpleObjectProperty<StatusDeCase>> matrice) {
		this.state.get().matrice = matrice;
	}

	public ObjectProperty<StatusDeCase> obtenirStatusDeCase(int ligne, int col) {
		return state.get().matrice.elemAt(ligne, col);
	}

	public Grille(int nbLignes, int nbCol) {
		this.state.set(new GrilleState());
		this.state.get().matrice = new Matrix<SimpleObjectProperty<StatusDeCase>>(nbLignes, nbCol);
	}

	public void recevoirTorpille(Point position, Navire navire) throws LocationAlreadyHitException {
		StatusDeCase currentStatus = this.state.get().matrice.elemAt(position.ligne, position.col).get();
		switch (currentStatus) {
		case PLACEE:
			if (navire.uneCaseRestante()) {
				this.state.get().matrice.defineAt(StatusDeCase.COULE, position.ligne, position.col);
			} else {
				this.state.get().matrice.defineAt(StatusDeCase.TOUCHE, position.ligne, position.col);
			}
			break;
		case VIDE:
			this.state.get().matrice.defineAt(StatusDeCase.MANQUE, position.ligne, position.col);
			break;
		default:
			throw new LocationAlreadyHitException();
		}
	}

	public void marquerPositionTorpille(Point position, StatusDeCase reponse) {
		this.state.get().matrice.defineAt(reponse, position.ligne, position.col);
	}

	public StatusDeCase determinerStatusDeCase(Point position) {
		return this.state.get().matrice.elemAt(position.ligne, position.col).get();
	}

	public GrilleMemento createMemento() {
		return new GrilleMemento(state.get().clone());
	}

	public void setMemento(GrilleMemento memento) {
		state.get().replaceAll(memento.getState().matrice);
	}

}

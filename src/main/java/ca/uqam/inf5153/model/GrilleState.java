package ca.uqam.inf5153.model;

import ca.uqam.inf5153.enums.StatusDeCase;
import javafx.beans.property.SimpleObjectProperty;

public class GrilleState implements Cloneable {

	public Matrix<SimpleObjectProperty<StatusDeCase>> matrice = new Matrix<SimpleObjectProperty<StatusDeCase>>(10, 10);

	@Override
	public GrilleState clone() {
		GrilleState resultat = new GrilleState();
		resultat.matrice = this.matrice.clone();
		return resultat;
	}

	public void replaceAll(Matrix<SimpleObjectProperty<StatusDeCase>> matrice) {
		this.matrice.replaceAll(matrice);
	}

}

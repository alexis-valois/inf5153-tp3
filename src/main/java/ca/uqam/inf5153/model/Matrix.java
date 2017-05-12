package ca.uqam.inf5153.model;

import java.util.ArrayList;
import java.util.List;

import ca.uqam.inf5153.enums.StatusDeCase;
import javafx.beans.property.SimpleObjectProperty;

public class Matrix<T extends SimpleObjectProperty<StatusDeCase>> implements Cloneable {

	public List<List<T>> elements = new ArrayList<List<T>>();
	private int nbLignes = 0;
	private int nbCol = 0;

	@SuppressWarnings("unchecked")
	public Matrix(int nbLignes, int nbCol) {
		this.nbLignes = nbLignes;
		this.nbCol = nbCol;
		for (int i = 0; i < nbLignes; i++) {
			this.elements.add(new ArrayList<T>());
			for (int j = 0; j < nbCol; j++) {
				this.elements.get(i).add((T) new SimpleObjectProperty<StatusDeCase>(StatusDeCase.VIDE));
			}

		}
	}

	public void replaceAll(Matrix<SimpleObjectProperty<StatusDeCase>> matrice) {
		int ligne = matrice.nbLignes;
		int col = matrice.nbCol;
		assert (ligne < nbLignes);
		assert (col < nbCol);
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < col; j++) {
				this.defineAt(matrice.elements.get(i).get(j).get(), i, j);
			}
		}
	}

	public void defineAt(StatusDeCase elem, int ligne, int col) {
		assert (ligne < nbLignes);
		assert (col < nbCol);
		this.elements.get(ligne).get(col).set(elem);
	}

	public T elemAt(int ligne, int col) {
		assert (ligne < nbLignes);
		assert (col < nbCol);
		return elements.get(ligne).get(col);
	}

	@Override
	public Matrix<T> clone() {
		Matrix<T> resultat = new Matrix<T>(nbLignes, nbCol);
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbCol; j++) {
				StatusDeCase elem = this.elemAt(i, j).get();
				resultat.defineAt(elem, i, j);
			}
		}
		return resultat;
	}
}

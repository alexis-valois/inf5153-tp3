package ca.uqam.inf5153.model;

public class Point {

	public int col = -1;
	public int ligne = -1;;

	public Point(int ligne, int col) {
		this.col = col;
		this.ligne = ligne;
	}

	@Override
	public String toString() {
		return "Colonne: " + this.col + " Ligne: " + this.ligne;
	}
}

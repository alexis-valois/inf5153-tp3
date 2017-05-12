package ca.uqam.inf5153.model;

public class Croiseur extends Navire {

	public Croiseur() {
		insertCases();
	}

	@Override
	public int getNbCases() {
		return 4;
	}

}

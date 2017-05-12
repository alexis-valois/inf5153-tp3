package ca.uqam.inf5153.model;

public class Torpilleur extends Navire {

	public Torpilleur() {
		insertCases();
	}

	@Override
	public int getNbCases() {
		return 2;
	}

}

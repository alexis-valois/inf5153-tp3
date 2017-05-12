package ca.uqam.inf5153.model;

public class SousMarin extends Navire {

	public SousMarin() {
		insertCases();
	}

	@Override
	public int getNbCases() {
		return 3;
	}
}

package ca.uqam.inf5153.model;

public class EmptyNavire extends Navire {

	public EmptyNavire() {
		insertCases();
	}

	@Override
	public int getNbCases() {
		return 0;
	}
}

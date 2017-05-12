package ca.uqam.inf5153.model;

public class PorteAvion extends Navire {

	public PorteAvion() {
		insertCases();
	}

	@Override
	public int getNbCases() {
		return 5;
	}

}

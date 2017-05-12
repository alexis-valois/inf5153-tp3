package ca.uqam.inf5153.enums;

public enum EtatPartie {
	EN_COURS("En cours"), TERMINEE("Terminée"), NON_DEBUTEE("Non débutée");

	private String name;

	EtatPartie(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}

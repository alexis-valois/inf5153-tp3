package ca.uqam.inf5153.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Difficulte")
@XmlEnum
public enum Difficulte {
	DEBUTANT("Débutant"), AVANCE("Avancé");

	private String name;

	Difficulte(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}

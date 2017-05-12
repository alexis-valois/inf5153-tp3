package ca.uqam.inf5153.intelligenceartificielle;

import ca.uqam.inf5153.exceptions.LocationAlreadyHitException;
import ca.uqam.inf5153.model.Joueur;

public interface IAStrategy {

	public void lancer(Joueur cible) throws LocationAlreadyHitException;
}

package ca.uqam.inf5153.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ca.uqam.inf5153.enums.JoueursLocation;
import ca.uqam.inf5153.enums.StatusDeCase;
import ca.uqam.inf5153.model.Grille;
import ca.uqam.inf5153.model.GrilleMemento;
import ca.uqam.inf5153.model.Joueur;
import ca.uqam.inf5153.model.Partie;
import ca.uqam.inf5153.model.Point;
import ca.uqam.inf5153.model.Tour;

public class TourServiceTest {

	private Partie partie = Partie.obtenirInstance();

	@Test
	public void testGrilleMemento() {
		Partie partie = Partie.obtenirInstance();
		TourService tourService = TourService.obtenirInstance();
		Joueur attaquant = Joueur.obtenirInstance(JoueursLocation.HUMAIN);
		Joueur opposant = Joueur.obtenirInstance(JoueursLocation.OPPOSANT);

		Grille grilleJoueur = attaquant.grille.get();
		Grille grilleOpposant = opposant.grille.get();

		grilleJoueur.marquerPositionTorpille(new Point(0, 0), StatusDeCase.MANQUE);
		grilleOpposant.marquerPositionTorpille(new Point(0, 0), StatusDeCase.MANQUE);

		// tour0
		tourService.ajouterTour(attaquant, opposant);

		grilleJoueur.marquerPositionTorpille(new Point(0, 1), StatusDeCase.MANQUE);
		grilleOpposant.marquerPositionTorpille(new Point(0, 1), StatusDeCase.MANQUE);

		// tour1
		tourService.ajouterTour(attaquant, opposant);

		Tour tour0 = partie.tours.get().get(0);

		GrilleMemento joueurHumainTour0 = tour0.grilleJoueurHumain.get();
		GrilleMemento joueurOpposantTour0 = tour0.grilleOpposant.get();

		grilleJoueur.setMemento(joueurHumainTour0);
		grilleOpposant.setMemento(joueurOpposantTour0);

		assertEquals(grilleJoueur.determinerStatusDeCase(new Point(0, 1)), StatusDeCase.VIDE);
		assertEquals(grilleOpposant.determinerStatusDeCase(new Point(0, 1)), StatusDeCase.VIDE);

		Tour tour1 = partie.tours.get().get(1);

		GrilleMemento joueurHumainTour1 = tour1.grilleJoueurHumain.get();
		GrilleMemento joueurOpposantTour1 = tour1.grilleOpposant.get();

		grilleJoueur.setMemento(joueurHumainTour1);
		grilleOpposant.setMemento(joueurOpposantTour1);

		assertEquals(grilleJoueur.determinerStatusDeCase(new Point(0, 1)), StatusDeCase.MANQUE);
		assertEquals(grilleOpposant.determinerStatusDeCase(new Point(0, 1)), StatusDeCase.MANQUE);
	}

}

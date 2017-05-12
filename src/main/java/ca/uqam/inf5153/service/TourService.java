package ca.uqam.inf5153.service;

import ca.uqam.inf5153.model.Joueur;
import ca.uqam.inf5153.model.Partie;
import ca.uqam.inf5153.model.Tour;

public class TourService {
	private static TourService instance;
	public Partie partie = Partie.obtenirInstance();

	private TourService() {

	}

	public static TourService obtenirInstance() {
		if (instance == null) {
			instance = new TourService();
		}

		return instance;
	}

	public void ajouterTour(Joueur humain, Joueur artificiel) {
		Tour tour = new Tour();
		tour.grilleJoueurHumain.set(humain.grille.get().createMemento());
		tour.grilleOpposant.set(artificiel.grille.get().createMemento());

		partie.tours.get().add(tour);
	}

}

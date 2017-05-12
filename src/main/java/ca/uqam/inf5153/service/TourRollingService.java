package ca.uqam.inf5153.service;

import ca.uqam.inf5153.enums.JoueursLocation;
import ca.uqam.inf5153.model.Joueur;
import ca.uqam.inf5153.model.Partie;
import ca.uqam.inf5153.model.Tour;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class TourRollingService extends Service<Void> {
	Partie partie = Partie.obtenirInstance();
	Joueur joueurHumain = partie.opposants.get().get(JoueursLocation.HUMAIN);
	Joueur joueurOpposant = partie.opposants.get().get(JoueursLocation.OPPOSANT);

	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				for (Tour tour : partie.tours.get()) {
					joueurHumain.grille.get().setMemento(tour.grilleJoueurHumain.get());
					joueurOpposant.grille.get().setMemento(tour.grilleOpposant.get());
					Thread.sleep(800);
				}
				return null;
			}

		};
	}

}

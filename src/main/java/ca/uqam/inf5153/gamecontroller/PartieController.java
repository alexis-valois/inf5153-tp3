package ca.uqam.inf5153.gamecontroller;

import ca.uqam.inf5153.model.Partie;
import ca.uqam.inf5153.model.Tour;
import ca.uqam.inf5153.service.PersistanceService;

public class PartieController {

	private static PartieController instance;
	private PersistanceService persistanceService = PersistanceService.obtenirInstance();

	private PartieController() {

	}

	private Partie partie = Partie.obtenirInstance();

	public static PartieController obtenirInstance() {
		if (instance == null) {
			instance = new PartieController();
		}
		return instance;
	}

	public Tour visualiserTour(int numTour) {
		return partie.tours.get().get(numTour);
	}

	public boolean sauvegarderPartieXml(String chemin) {
		return persistanceService.jaxbObjectToXML(partie, chemin);
	}

	public Partie chargerPartieXml(String chemin) {
		return persistanceService.jaxbXMLToObject(chemin);
	}

}

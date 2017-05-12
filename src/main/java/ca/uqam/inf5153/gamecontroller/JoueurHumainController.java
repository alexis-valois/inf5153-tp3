package ca.uqam.inf5153.gamecontroller;

import ca.uqam.inf5153.enums.JoueursLocation;
import ca.uqam.inf5153.exceptions.LocationAlreadyHitException;
import ca.uqam.inf5153.exceptions.NavireChevauchantException;
import ca.uqam.inf5153.exceptions.NavireHorsGrilleException;
import ca.uqam.inf5153.model.Joueur;
import ca.uqam.inf5153.model.Navire;
import ca.uqam.inf5153.model.Partie;
import ca.uqam.inf5153.model.Point;
import ca.uqam.inf5153.service.TourService;

/***
 *
 * @author Alexis Code source provient en partie du site :
 *         https://blogs.oracle.com/jmxetc/entry/
 *         connecting_scenebuilder_edited_fxml_to
 */

public class JoueurHumainController {

	public Partie partie = Partie.obtenirInstance();
	private Joueur opposant = partie.opposants.get().get(JoueursLocation.OPPOSANT);
	private Joueur humain = partie.opposants.get().get(JoueursLocation.HUMAIN);
	private static JoueurHumainController instance = null;
	private TourService tourService = TourService.obtenirInstance();

	private JoueurHumainController() {

	}

	public static JoueurHumainController obtenirInstance() {
		if (instance == null) {
			instance = new JoueurHumainController();
		}
		return instance;
	}

	public void lancerTorpille(Point destination) throws LocationAlreadyHitException {
		opposant.recevoirTorpille(destination);
		tourService.ajouterTour(humain, opposant);
	}

	public void placerNavire(Navire navire) throws NavireHorsGrilleException, NavireChevauchantException {
		partie.opposants.get().get(JoueursLocation.HUMAIN).placerNavire(navire);
	}

	public boolean isGagnant() {
		return false;
	}

}

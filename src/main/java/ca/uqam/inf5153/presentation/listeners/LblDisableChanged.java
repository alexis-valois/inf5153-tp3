package ca.uqam.inf5153.presentation.listeners;

import ca.uqam.inf5153.enums.EtatPartie;
import ca.uqam.inf5153.model.Joueur;
import ca.uqam.inf5153.model.Navire;
import ca.uqam.inf5153.model.Partie;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class LblDisableChanged implements ChangeListener<Boolean> {

	private Joueur joueur;
	private Partie partie = Partie.obtenirInstance();

	public LblDisableChanged(Joueur joueur) {

		this.joueur = joueur;
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		boolean toutCoules = true;
		for (Navire navire : joueur.navires.get()) {
			if (!navire.coule.get()) {
				toutCoules = false;
				break;
			}
		}
		if (toutCoules) {
			partie.gagnant.set(joueur.opposant.get());
			joueur.opposant.get().isGagnant = true;
			partie.etat.set(EtatPartie.TERMINEE);
		}

	}

}

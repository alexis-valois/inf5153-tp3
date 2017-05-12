package ca.uqam.inf5153.presentation.listeners;

import ca.uqam.inf5153.enums.EtatPartie;
import ca.uqam.inf5153.enums.JoueursLocation;
import ca.uqam.inf5153.model.Partie;
import ca.uqam.inf5153.utils.MessageUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;

public class PartieEtatChanged implements ChangeListener<EtatPartie> {

	private MessageUtils messageUtils = new MessageUtils();
	private Partie partie = Partie.obtenirInstance();
	private MenuItem menuItem;

	public PartieEtatChanged(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	@Override
	public void changed(ObservableValue<? extends EtatPartie> arg0, EtatPartie arg1, EtatPartie arg2) {
		if (arg2 == EtatPartie.TERMINEE) {
			menuItem.disableProperty().set(false);
			String gagnant = "";
			if (partie.opposants.get().get(JoueursLocation.HUMAIN).isGagnant) {
				gagnant = "Vous avez gagné !";
			}

			if (partie.opposants.get().get(JoueursLocation.OPPOSANT).isGagnant) {
				gagnant = "Vous avez perdu.";
			}

			messageUtils.afficherMessage("Partie terminée", "Partie terminée", "La partie est terminée : " + gagnant,
					AlertType.INFORMATION);
		}

	}

}

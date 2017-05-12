package ca.uqam.inf5153.presentation.jfxcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import ca.uqam.inf5153.action.CurrentWindowCloseAction;
import ca.uqam.inf5153.enums.Difficulte;
import ca.uqam.inf5153.model.Partie;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class NouvellePartieController implements Initializable {

	private Partie partie = Partie.obtenirInstance();
	@FXML
	private ToggleGroup joueurDifficulteGroup;

	@FXML
	private RadioButton debutantOpt;

	@FXML
	private RadioButton avanceOpt;

	@FXML
	private TitledPane difficultePane;

	@FXML
	private Button btnOk;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeRadioButtonsData();
		initializeChoices();
		setOnDifficulteGroupValueChanged();
		btnOk.setOnAction(new CurrentWindowCloseAction());

	}

	private void setOnDifficulteGroupValueChanged() {
		joueurDifficulteGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
				if (joueurDifficulteGroup.getSelectedToggle() != null) {
					partie.diff.set((Difficulte) arg2.getUserData());
				}
			}
		});
	}

	private void initializeRadioButtonsData() {
		debutantOpt.setUserData(Difficulte.DEBUTANT);
		avanceOpt.setUserData(Difficulte.AVANCE);
	}

	private void initializeChoices() {
		initializeDifficulte();
	}

	private void initializeDifficulte() {
		switch (partie.diff.get()) {
		case AVANCE:
			joueurDifficulteGroup.selectToggle(avanceOpt);
			break;
		case DEBUTANT:
			joueurDifficulteGroup.selectToggle(debutantOpt);
			break;
		}
	}
}

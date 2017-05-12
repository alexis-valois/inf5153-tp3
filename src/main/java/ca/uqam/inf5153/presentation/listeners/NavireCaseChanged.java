package ca.uqam.inf5153.presentation.listeners;

import ca.uqam.inf5153.enums.StatusDeCase;
import ca.uqam.inf5153.model.Navire;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class NavireCaseChanged implements ChangeListener<StatusDeCase> {

	private Navire navire;

	public NavireCaseChanged(Navire navire) {
		this.navire = navire;
	}

	@Override
	public void changed(ObservableValue<? extends StatusDeCase> observable, StatusDeCase oldValue,
			StatusDeCase newValue) {
		boolean allTouches = true;
		for (ObjectProperty<StatusDeCase> uneCase : navire.cases) {
			if (!(uneCase.get() == StatusDeCase.TOUCHE || uneCase.get() == StatusDeCase.COULE)) {
				allTouches = false;
				break;
			}
		}

		this.navire.coule.set(allTouches);

	}

}

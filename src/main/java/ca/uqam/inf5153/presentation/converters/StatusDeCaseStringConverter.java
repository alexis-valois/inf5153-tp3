package ca.uqam.inf5153.presentation.converters;

import ca.uqam.inf5153.enums.StatusDeCase;
import javafx.util.StringConverter;

public class StatusDeCaseStringConverter extends StringConverter<StatusDeCase> {

	private boolean isOpposant = false;

	public StatusDeCaseStringConverter(boolean isOpposant) {
		this.isOpposant = isOpposant;
	}

	@Override
	public String toString(StatusDeCase object) {
		switch (object) {
		case TOUCHE:
			return "-fx-background-color: red;";
		case MANQUE:
			return "-fx-background-color: white;";
		case COULE:
			return "-fx-background-color: blue;";
		case VIDE:
			return "-fx-background-color: grey;";
		case PLACEE:
			if (isOpposant) {
				return "-fx-background-color: grey;";
			} else {
				return "-fx-background-color: green;";
			}

		default:
			return "";
		}
	}

	@Override
	public StatusDeCase fromString(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}

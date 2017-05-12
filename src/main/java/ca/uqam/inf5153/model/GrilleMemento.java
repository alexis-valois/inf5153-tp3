package ca.uqam.inf5153.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public final class GrilleMemento {

	private ObjectProperty<GrilleState> state = new SimpleObjectProperty<GrilleState>();

	GrilleMemento(GrilleState state) {
		this.state.set(new GrilleState());
		setState(state);
	}

	void setState(GrilleState state) {
		this.state.get().replaceAll(state.matrice);
	}

	GrilleState getState() {
		return state.get();
	}
}

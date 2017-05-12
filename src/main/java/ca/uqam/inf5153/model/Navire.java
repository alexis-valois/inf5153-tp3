package ca.uqam.inf5153.model;

import java.util.ArrayList;
import java.util.List;

import ca.uqam.inf5153.enums.Direction;
import ca.uqam.inf5153.enums.StatusDeCase;
import ca.uqam.inf5153.presentation.listeners.NavireCaseChanged;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public abstract class Navire {

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	public BooleanProperty coule = new SimpleBooleanProperty();
	public ObjectProperty<Point> position = new SimpleObjectProperty<Point>();
	public ObjectProperty<Direction> direction = new SimpleObjectProperty<Direction>();
	public List<ObjectProperty<StatusDeCase>> cases = new ArrayList<ObjectProperty<StatusDeCase>>();

	public abstract int getNbCases();

	public boolean uneCaseRestante() {
		int nbCasesRestantes = this.getNbCases();
		for (ObjectProperty<StatusDeCase> uneCase : cases) {
			if (uneCase.get() == StatusDeCase.TOUCHE) {
				nbCasesRestantes--;
			}
		}
		return (nbCasesRestantes == 1);
	}

	public boolean englobe(Point position) {
		if (direction.get() == Direction.VERTICAL) {
			if (position.col != this.position.get().col) {
				return false;
			}

			if (position.ligne < this.position.get().ligne
					|| position.ligne >= (this.position.get().ligne + this.getNbCases())) {
				return false;
			}

			return true;

		} else {
			if (position.ligne != this.position.get().ligne) {
				return false;
			}

			if (position.col < this.position.get().col || position.col >= (this.position.get().col + getNbCases())) {
				return false;
			}

			return true;
		}
	}

	protected void insertCases() {
		for (int i = 0; i < this.getNbCases(); i++) {
			ObjectProperty<StatusDeCase> uneCase = new SimpleObjectProperty<StatusDeCase>();
			uneCase.addListener(new NavireCaseChanged(this));
			this.cases.add(uneCase);
		}
	}

	public Navire() {
		coule.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (coule.get()) {
					for (ObjectProperty<StatusDeCase> uneCase : cases) {
						uneCase.set(StatusDeCase.COULE);
					}
				}
			}

		});
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coule == null) ? 0 : coule.hashCode());
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Navire other = (Navire) obj;
		if (coule == null) {
			if (other.coule != null)
				return false;
		} else if (!coule.equals(other.coule))
			return false;
		if (direction != other.direction)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

}

package ca.uqam.inf5153.model;

import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

@XmlRootElement(name = "Tour")
public class Tour {

	private static int compteurTours = 0;
	private IntegerProperty numTour = new SimpleIntegerProperty();
	public ObjectProperty<GrilleMemento> grilleJoueurHumain = new SimpleObjectProperty<GrilleMemento>();
	public ObjectProperty<GrilleMemento> grilleOpposant = new SimpleObjectProperty<GrilleMemento>();

	public Tour() {
		this.numTour.set(Tour.incrementerTour());
	}

	private static int incrementerTour() {
		compteurTours++;
		return compteurTours;
	}
}

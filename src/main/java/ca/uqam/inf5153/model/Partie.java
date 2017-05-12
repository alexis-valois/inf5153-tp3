package ca.uqam.inf5153.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import ca.uqam.inf5153.enums.Difficulte;
import ca.uqam.inf5153.enums.EtatPartie;
import ca.uqam.inf5153.enums.JoueursLocation;
import ca.uqam.inf5153.intelligenceartificielle.Hasard;
import ca.uqam.inf5153.intelligenceartificielle.IAStrategy;
import ca.uqam.inf5153.intelligenceartificielle.Minimax;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlRootElement(name = "Partie")
@XmlAccessorType(XmlAccessType.FIELD)
public class Partie {

	public ObjectProperty<Difficulte> diff = new SimpleObjectProperty<Difficulte>(Difficulte.DEBUTANT);
	public ObjectProperty<Joueur> gagnant = new SimpleObjectProperty<Joueur>();
	public ObjectProperty<ObservableList<Joueur>> opposants = new SimpleObjectProperty<ObservableList<Joueur>>();
	public ObjectProperty<List<Tour>> tours = new SimpleObjectProperty<List<Tour>>();
	public ObjectProperty<EtatPartie> etat = new SimpleObjectProperty<EtatPartie>();

	private static Partie instance;

	public IAStrategy getStrageyAttaque() {
		switch (getDiff()) {
		case DEBUTANT:
			return new Hasard();
		case AVANCE:
			return new Minimax();
		default:
			return new Hasard();
		}
	}

	public static Partie obtenirInstance() {
		if (instance == null) {
			instance = new Partie();
		}
		return instance;

	}

	private Partie() {
		Joueur joueurLocal = Joueur.obtenirInstance(JoueursLocation.HUMAIN);
		Joueur joueurDistant = Joueur.obtenirInstance(JoueursLocation.OPPOSANT);
		joueurLocal.opposant.set(joueurDistant);
		joueurDistant.opposant.set(joueurLocal);
		this.opposants.set(FXCollections.observableArrayList(joueurLocal, joueurDistant));
		this.tours.set(new ArrayList<Tour>());
		this.etat.set(EtatPartie.NON_DEBUTEE);
	}

	public boolean isTerminee() {
		return (this.etat.get() == EtatPartie.TERMINEE);
	}

	@XmlElement(name = "Diff")
	public Difficulte getDiff(){
		return this.diff.get();
	}

	@XmlElement(name = "Gagnant")
	public Joueur getGagnant(){
		return this.gagnant.get();
	}

	@XmlElement(name = "Tours")
	public List<Tour> getTours(){
		return this.tours.get();
	}

	@XmlTransient
    public ObservableList<Joueur> getOpposantObservable() {
        return opposants.get();
    }

}

package ca.uqam.inf5153.model;

import java.util.ArrayList;
import java.util.Random;

import javax.xml.bind.annotation.XmlRootElement;

import ca.uqam.inf5153.enums.Direction;
import ca.uqam.inf5153.enums.StatusDeCase;
import ca.uqam.inf5153.exceptions.LocationAlreadyHitException;
import ca.uqam.inf5153.exceptions.NavireChevauchantException;
import ca.uqam.inf5153.exceptions.NavireHorsGrilleException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

@XmlRootElement(name = "Joueur")
public class Joueur {

	public ObjectProperty<Joueur> opposant = new SimpleObjectProperty<Joueur>();
	public ObjectProperty<Grille> grille = new SimpleObjectProperty<Grille>();
	public SimpleListProperty<Navire> navires = new SimpleListProperty<Navire>(
			FXCollections.observableList(new ArrayList<Navire>()));
	public boolean isGagnant = false;

	private static Joueur[] instances = new Joueur[2];

	public void recevoirTorpille(Point position) throws LocationAlreadyHitException {
		Navire navire = determinerNavire(position);
		this.grille.get().recevoirTorpille(position, navire);
	}

	protected Navire determinerNavire(Point position) {
		for (Navire navire : navires) {
			if (navire.englobe(position)) {
				return navire;
			}
		}
		return new EmptyNavire();
	}

	protected Joueur() {
		navires.add(new Croiseur());
		navires.add(new PorteAvion());
		navires.add(new SousMarin());
		navires.add(new Torpilleur());
		navires.add(new ContreTorpilleur());
		grille.set(new Grille(10, 10));
	}

	public void placerNavire(Navire navire) throws NavireChevauchantException, NavireHorsGrilleException {
		assertNotNavireHorsGrille(navire);
		assertNotNavireChevauchant(navire);
		mettreEnGrille(navire);
	}

	private void mettreEnGrille(Navire navire) {
		for (int i = 0; i < navire.getNbCases(); i++) {
			int ligne = navire.position.get().ligne;
			int col = navire.position.get().col;
			if (navire.direction.get() == Direction.HORIZONTALE) {
				navire.cases.get(i).bindBidirectional(grille.get().getMatrice().elements.get(ligne).get(col + i));
				grille.get().getMatrice().elements.get(ligne).get(col + i).set(StatusDeCase.PLACEE);
			} else {
				navire.cases.get(i).bindBidirectional(grille.get().getMatrice().elements.get(ligne + i).get(col));
				grille.get().getMatrice().defineAt(StatusDeCase.PLACEE, navire.position.get().ligne + i,
						navire.position.get().col);
			}
		}
	}

	public static Joueur obtenirInstance(int joueurLocation) {
		switch (joueurLocation) {
		case 0:
			if (instances[0] == null) {
				instances[0] = new Joueur();
			}
			return instances[0];
		case 1:
			if (instances[1] == null) {
				instances[1] = new Joueur();
				instances[1].placementAleatoireNavires();
			}
			return instances[1];
		}
		return null;

	}

	private void placementAleatoireNavires() {
		Random rnd = new Random();
		for (Navire navire : this.navires) {
			boolean place = false;
			while (!place) {
				try {
					int col = rnd.nextInt(9);
					int ligne = rnd.nextInt(9);
					navire.position.set(new Point(ligne, col));
					if (rnd.nextBoolean()) {
						navire.direction.set(Direction.HORIZONTALE);
					} else {
						navire.direction.set(Direction.VERTICAL);
					}
					placerNavire(navire);
					place = true;
				} catch (Exception e) {
				}
			}
		}

	}

	private void assertNotNavireChevauchant(Navire navire) throws NavireChevauchantException {
		int col = navire.position.get().col;
		int ligne = navire.position.get().ligne;
		switch (navire.direction.get()) {
		case HORIZONTALE:
			for (int i = 0; i < navire.getNbCases(); i++) {
				if (this.grille.get().getMatrice().elemAt(ligne, col + i).get() == StatusDeCase.PLACEE) {
					throw new NavireChevauchantException();
				}
			}
			break;
		case VERTICAL:
			for (int i = 0; i < navire.getNbCases(); i++) {
				if (this.grille.get().getMatrice().elemAt(ligne + i, col).get() == StatusDeCase.PLACEE) {
					throw new NavireChevauchantException();
				}
			}
			break;
		}
	}

	private void assertNotNavireHorsGrille(Navire navire) throws NavireHorsGrilleException {
		switch (navire.direction.get()) {
		case HORIZONTALE:
			if ((navire.position.get().col + navire.getNbCases()) > 10) {
				throw new NavireHorsGrilleException();
			}
			break;
		case VERTICAL:
			if ((navire.position.get().ligne + navire.getNbCases()) > 10) {
				throw new NavireHorsGrilleException();
			}
			break;
		}
	}

	public Navire getNavireFromType(Class<?> clazz) {
		for (Navire navire : navires.get()) {
			if (navire.getClass().equals(clazz)) {
				return navire;
			}
		}
		return new EmptyNavire();
	}

}

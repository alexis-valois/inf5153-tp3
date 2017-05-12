package ca.uqam.inf5153.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ca.uqam.inf5153.enums.StatusDeCase;

public class GrilleTest {

	@Test
	public void testMemento() {
		Grille grille = new Grille(10, 10);
		grille.marquerPositionTorpille(new Point(4, 6), StatusDeCase.PLACEE);

		GrilleMemento backup = grille.createMemento();

		// grille.getMatrice().defineAt(StatusDeCase.TOUCHE, 4, 6);
		grille.marquerPositionTorpille(new Point(4, 6), StatusDeCase.TOUCHE);

		grille.setMemento(backup);

		assertEquals(grille.determinerStatusDeCase(new Point(4, 6)), StatusDeCase.PLACEE);
	}

}

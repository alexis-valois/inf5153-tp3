package ca.uqam.inf5153.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.uqam.inf5153.enums.Direction;
import ca.uqam.inf5153.enums.StatusDeCase;

public class NavireTest {

	@Test
	public void testEnglobe() {
		Navire sousMarin = new SousMarin();
		sousMarin.position.set(new Point(0, 0));
		sousMarin.direction.set(Direction.HORIZONTALE);

		assertTrue(sousMarin.englobe(new Point(0, 0)));
		assertTrue(sousMarin.englobe(new Point(0, 1)));
		assertTrue(sousMarin.englobe(new Point(0, 2)));

		assertFalse(sousMarin.englobe(new Point(0, 3)));
		assertFalse(sousMarin.englobe(new Point(1, 0)));

		Navire porteAvion = new PorteAvion();
		porteAvion.position.set(new Point(5, 9));
		porteAvion.direction.set(Direction.VERTICAL);

		assertTrue(porteAvion.englobe(new Point(5, 9)));
		assertTrue(porteAvion.englobe(new Point(6, 9)));
		assertTrue(porteAvion.englobe(new Point(7, 9)));
		assertTrue(porteAvion.englobe(new Point(8, 9)));
		assertTrue(porteAvion.englobe(new Point(9, 9)));

		assertFalse(porteAvion.englobe(new Point(4, 9)));
		assertFalse(porteAvion.englobe(new Point(9, 8)));

	}

	@Test
	public void testUneCaseRestante() {
		Navire porteAvion = new PorteAvion();
		porteAvion.position.set(new Point(0, 0));
		porteAvion.direction.set(Direction.HORIZONTALE);
		porteAvion.cases.get(0).set(StatusDeCase.TOUCHE);
		porteAvion.cases.get(2).set(StatusDeCase.TOUCHE);
		porteAvion.cases.get(3).set(StatusDeCase.TOUCHE);

		assertFalse(porteAvion.uneCaseRestante());

		porteAvion.cases.get(4).set(StatusDeCase.TOUCHE);

		assertTrue(porteAvion.uneCaseRestante());
	}

}

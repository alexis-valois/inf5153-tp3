package ca.uqam.inf5153.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.uqam.inf5153.enums.Direction;

public class JoueurTest {

	@Test
	public void testDeterminerNavire() {
		Joueur joueur = new Joueur();
		for (Navire navire : joueur.navires.get()) {
			if (navire instanceof Torpilleur) {
				navire.position.set(new Point(0, 0));
				navire.direction.set(Direction.HORIZONTALE);
			}
			if (navire instanceof ContreTorpilleur) {
				navire.position.set(new Point(2, 0));
				navire.direction.set(Direction.VERTICAL);
			}
			if (navire instanceof PorteAvion) {
				navire.position.set(new Point(2, 2));
				navire.direction.set(Direction.HORIZONTALE);
			}
			if (navire instanceof SousMarin) {
				navire.position.set(new Point(3, 2));
				navire.direction.set(Direction.VERTICAL);
			}
			if (navire instanceof Croiseur) {
				navire.position.set(new Point(9, 6));
				navire.direction.set(Direction.HORIZONTALE);
			}
		}
		assertTrue(joueur.determinerNavire(new Point(0, 9)) instanceof EmptyNavire);
		assertTrue(joueur.determinerNavire(new Point(1, 3)) instanceof EmptyNavire);
		assertTrue(joueur.determinerNavire(new Point(0, 1)) instanceof Torpilleur);
		assertTrue(joueur.determinerNavire(new Point(4, 0)) instanceof ContreTorpilleur);
		assertTrue(joueur.determinerNavire(new Point(2, 2)) instanceof PorteAvion);
		assertTrue(joueur.determinerNavire(new Point(9, 9)) instanceof Croiseur);
		assertTrue(joueur.determinerNavire(new Point(4, 2)) instanceof SousMarin);

	}

}

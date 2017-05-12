package ca.uqam.inf5153.intelligenceartificielle;

import java.util.Random;

import ca.uqam.inf5153.exceptions.LocationAlreadyHitException;
import ca.uqam.inf5153.model.Joueur;
import ca.uqam.inf5153.model.Point;

public class Hasard implements IAStrategy {

	@Override
	public void lancer(Joueur cible) throws LocationAlreadyHitException {
		Thread traitementDiffere = new Thread() {
			@Override
			public void run() {
				Point destination = randomizePoint();
				boolean lanceComplete = false;
				while (!lanceComplete) {
					try {
						Thread.sleep(400);
						cible.recevoirTorpille(destination);
						lanceComplete = true;
					} catch (LocationAlreadyHitException e) {
						destination = randomizePoint();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		traitementDiffere.start();

	}

	private Point randomizePoint() {
		Random rand = new Random();
		int ligne = rand.nextInt(10);
		int col = rand.nextInt(10);
		Point point = new Point(ligne, col);
		return point;
	}

}

package ca.uqam.inf5153.intelligenceartificielle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.uqam.inf5153.enums.StatusDeCase;
import ca.uqam.inf5153.exceptions.LocationAlreadyHitException;
import ca.uqam.inf5153.model.Joueur;
import ca.uqam.inf5153.model.Point;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ca.uqam.inf5153.model.Grille;


public class Minimax implements IAStrategy {
	
	public List<ObjectProperty<StatusDeCase>> cases = new ArrayList<ObjectProperty<StatusDeCase>>();
	public ObjectProperty<Grille> grille = new SimpleObjectProperty<Grille>();
	static Point destination=null;
	static Point dernierTouche=null;

	@Override
	public void lancer(Joueur cible) throws LocationAlreadyHitException {
		grille = cible.grille;
		StatusDeCase status;
		Point destinationR;
		Point destinationL;
		Point destinationH;
		Point destinationB;
		boolean lanceComplete = false;
		int RL = 0;
		int test=0;
		int haut=0;
		int bas=0;
		int droite=0;
		int gauche=0;
		
		while (!lanceComplete) {

			if(destination==null){
				destination = randomizePoint();
			}else{

				
				haut=(destination.ligne)+1;
				bas=(destination.ligne)-1;
				droite=(destination.col)+1;
				gauche=(destination.col)-1;
				if(haut>10){
					haut-=2;
				}else if(haut<0){
					haut+=1;
				}else if(bas>10){
					bas-=2;
				}else if(bas<0){
					bas+=1;
				}else if(droite>10){
					droite-=2;
				}else if(droite<0){
					droite+=1;
				}else if(gauche>10){
					gauche-=2;
				}else if(gauche<0){
					gauche+=1;
				}
				destinationR = new Point((haut-1),droite);
				destinationL = new Point((haut-1),gauche);
				destinationH = new Point(haut,(gauche+1));
				destinationB = new Point(bas,(gauche+1));
				
				
				//Si haut et bas sont vide alors bateau en position horizontale
				if(grille.get().determinerStatusDeCase(destinationB)==StatusDeCase.VIDE && grille.get().determinerStatusDeCase(destinationH)==StatusDeCase.VIDE){
					System.out.println("bateau horizontale");
					destination.col = destination.col+1;
					RL=1;
				}
				
				//Si droite et gauche vide alors bateau en position verticale
				else if(grille.get().determinerStatusDeCase(destinationR)==StatusDeCase.VIDE && grille.get().determinerStatusDeCase(destinationL)==StatusDeCase.VIDE){
					System.out.println("bateau vertical");
					destination.ligne = destination.ligne+1;
					RL=2;
				}
				
				
				//Si bateau horizontale
				if(RL==1){
					status = grille.get().determinerStatusDeCase(destination);
					
					while(!(status == StatusDeCase.PLACEE)){
						if(destination.col<10 && destination.col > 0){
							destination.col = destination.col-1;
							status=grille.get().determinerStatusDeCase(destination);
						}else{
							break;
						}
					}
				}
				//Si bateau verticale
				else if(RL==2){
					status = grille.get().determinerStatusDeCase(destination);
					
					while(!(status == StatusDeCase.PLACEE)){
						if(destination.ligne<10 && destination.ligne > 0){
							destination.ligne = destination.ligne-1;
							status=grille.get().determinerStatusDeCase(destination);
						}else{
							break;
						}
					}
				}
				
			}
			
			
			//Verifie que la destination n'ai pas deja ete touche.
			status = grille.get().determinerStatusDeCase(destination);
			if(status==grille.get().determinerStatusDeCase(destination).PLACEE || status==grille.get().determinerStatusDeCase(destination).VIDE){
				cible.recevoirTorpille(destination);
				lanceComplete = true;
				status = grille.get().determinerStatusDeCase(destination);
			}else{
				status = grille.get().determinerStatusDeCase(destination);
				destination = randomizePoint();
			}
			
			
			if(status==StatusDeCase.TOUCHE){
				dernierTouche=destination;
			}else if(status==StatusDeCase.VIDE || status==StatusDeCase.PLACEE){
				
			}else{
				destination=null;
			}
			
			
		}

	}
	

	private Point randomizePoint() {
		Random rand = new Random();
		int ligne = rand.nextInt(10);
		int col = rand.nextInt(10);
		Point point = new Point(ligne, col);
		return point;
	}


}

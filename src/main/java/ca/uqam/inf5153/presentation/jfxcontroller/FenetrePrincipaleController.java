package ca.uqam.inf5153.presentation.jfxcontroller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import ca.uqam.inf5153.action.ApplicationCloseAction;
import ca.uqam.inf5153.action.DialogShowAction;
import ca.uqam.inf5153.action.VisualiserPartieAction;
import ca.uqam.inf5153.enums.Direction;
import ca.uqam.inf5153.enums.EtatPartie;
import ca.uqam.inf5153.enums.JoueursLocation;
import ca.uqam.inf5153.enums.StatusDeCase;
import ca.uqam.inf5153.exceptions.LocationAlreadyHitException;
import ca.uqam.inf5153.exceptions.NavireChevauchantException;
import ca.uqam.inf5153.exceptions.NavireHorsGrilleException;
import ca.uqam.inf5153.gamecontroller.JoueurHumainController;
import ca.uqam.inf5153.gamecontroller.PartieController;
import ca.uqam.inf5153.model.ContreTorpilleur;
import ca.uqam.inf5153.model.Croiseur;
import ca.uqam.inf5153.model.Joueur;
import ca.uqam.inf5153.model.KeyValuePair;
import ca.uqam.inf5153.model.Navire;
import ca.uqam.inf5153.model.Partie;
import ca.uqam.inf5153.model.Point;
import ca.uqam.inf5153.model.PorteAvion;
import ca.uqam.inf5153.model.SousMarin;
import ca.uqam.inf5153.model.Torpilleur;
import ca.uqam.inf5153.presentation.converters.StatusDeCaseStringConverter;
import ca.uqam.inf5153.presentation.listeners.LblDisableChanged;
import ca.uqam.inf5153.presentation.listeners.PartieEtatChanged;
import ca.uqam.inf5153.service.TourService;
import ca.uqam.inf5153.utils.MessageUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

public class FenetrePrincipaleController implements Initializable {
	private PartieController partieController = PartieController.obtenirInstance();
	private Partie partie = Partie.obtenirInstance();
	private Joueur joueurHumain = partie.opposants.get().get(JoueursLocation.HUMAIN);
	private Joueur joueurArtificiel = partie.opposants.get().get(JoueursLocation.OPPOSANT);
	private JoueurHumainController joueurHumainController = JoueurHumainController.obtenirInstance();
	private MessageUtils messageUtils = new MessageUtils();
	private TourService tourService = TourService.obtenirInstance();
	final FileChooser fileChooser = new FileChooser();

	@FXML
	private MenuItem closeMenuItem;

	@FXML
	private MenuItem nouvellePartieMenuItem;

	@FXML
	private MenuItem difficulteMenuItem;

	@FXML
	private MenuItem sauvegarderPartieMenuItem;

	@FXML
	private MenuItem visualiserPartieMenuItem;

	@FXML
	private MenuItem chargerPartieMenuItem;

	@FXML
	private Label difficulteLbl;

	@FXML
	private Label lblCroiseurOpposant;

	@FXML
	private Label lblTorpilleurOpposant;

	@FXML
	private Label lblContreTorpilleurOpposant;

	@FXML
	private Label lblPorteAvionOpposant;

	@FXML
	private Label lblSousMarinOpposant;

	@FXML
	private Label lblCroiseurLocal;

	@FXML
	private Label lblEtatPartie;

	@FXML
	private Label lblTorpilleurLocal;

	@FXML
	private Label lblContreTorpilleurLocal;

	@FXML
	private Label lblPorteAvionLocal;

	@FXML
	private Label lblSousMarinLocal;

	@FXML
	private ChoiceBox<Navire> navireBox;

	@FXML
	private ChoiceBox<KeyValuePair> lineChoiceBox;

	@FXML
	private ChoiceBox<KeyValuePair> columnChoiceBox;

	@FXML
	private ChoiceBox<KeyValuePair> directionChoiceBox;

	@FXML
	private GridPane grilleJoueur;

	@FXML
	private GridPane grilleOpposant;

	@FXML
	private Button placerNavireButton;

	@FXML
	private Button debuterPartieButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initialiserColumnsChoices();
		initialiserLinesChoices();
		initialiserDirectionChoices();
		etablirLiaisonsDonneesVersUI(partie, joueurHumain, joueurArtificiel);
		definirMenuItemsAction();
		definirBoutonsAction();
		definirGridElementOpposantAction();
		definirLblListeners();
		definirEtatPartieListener();
		DialogShowAction choixDifficulte = new DialogShowAction("Nouvelle partie", "NouvellePartieConfigView");
		choixDifficulte.handle(new ActionEvent());
	}

	private void definirEtatPartieListener() {
		partie.etat.addListener(new PartieEtatChanged(visualiserPartieMenuItem));

	}

	private void initialiserColumnsChoices() {
		columnChoiceBox.getItems().add(new KeyValuePair(0, "A"));
		columnChoiceBox.getItems().add(new KeyValuePair(1, "B"));
		columnChoiceBox.getItems().add(new KeyValuePair(2, "C"));
		columnChoiceBox.getItems().add(new KeyValuePair(3, "D"));
		columnChoiceBox.getItems().add(new KeyValuePair(4, "E"));
		columnChoiceBox.getItems().add(new KeyValuePair(5, "F"));
		columnChoiceBox.getItems().add(new KeyValuePair(6, "G"));
		columnChoiceBox.getItems().add(new KeyValuePair(7, "H"));
		columnChoiceBox.getItems().add(new KeyValuePair(8, "I"));
		columnChoiceBox.getItems().add(new KeyValuePair(9, "J"));
	}

	private void initialiserLinesChoices() {
		lineChoiceBox.getItems().add(new KeyValuePair(0, "1"));
		lineChoiceBox.getItems().add(new KeyValuePair(1, "2"));
		lineChoiceBox.getItems().add(new KeyValuePair(2, "3"));
		lineChoiceBox.getItems().add(new KeyValuePair(3, "4"));
		lineChoiceBox.getItems().add(new KeyValuePair(4, "5"));
		lineChoiceBox.getItems().add(new KeyValuePair(5, "6"));
		lineChoiceBox.getItems().add(new KeyValuePair(6, "7"));
		lineChoiceBox.getItems().add(new KeyValuePair(7, "8"));
		lineChoiceBox.getItems().add(new KeyValuePair(8, "9"));
		lineChoiceBox.getItems().add(new KeyValuePair(9, "10"));
	}

	private void initialiserDirectionChoices() {
		directionChoiceBox.getItems().add(new KeyValuePair(Direction.HORIZONTALE, Direction.HORIZONTALE.toString()));
		directionChoiceBox.getItems().add(new KeyValuePair(Direction.VERTICAL, Direction.VERTICAL.toString()));
	}

	private void etablirLiaisonsDonneesVersUI(Partie partie, Joueur joueurHumain, Joueur joueurArtificiel) {
		difficulteLbl.textProperty().bind(partie.diff.asString());
		lblEtatPartie.textProperty().bind(partie.etat.asString());

		lblCroiseurOpposant.disableProperty().bind(joueurArtificiel.getNavireFromType(Croiseur.class).coule);
		lblTorpilleurOpposant.disableProperty().bind(joueurArtificiel.getNavireFromType(Torpilleur.class).coule);
		lblContreTorpilleurOpposant.disableProperty()
				.bind(joueurArtificiel.getNavireFromType(ContreTorpilleur.class).coule);
		lblSousMarinOpposant.disableProperty().bind(joueurArtificiel.getNavireFromType(SousMarin.class).coule);
		lblPorteAvionOpposant.disableProperty().bind(joueurArtificiel.getNavireFromType(PorteAvion.class).coule);

		lblCroiseurLocal.disableProperty().bind(joueurHumain.getNavireFromType(Croiseur.class).coule);
		lblTorpilleurLocal.disableProperty().bind(joueurHumain.getNavireFromType(Torpilleur.class).coule);
		lblContreTorpilleurLocal.disableProperty().bind(joueurHumain.getNavireFromType(ContreTorpilleur.class).coule);
		lblSousMarinLocal.disableProperty().bind(joueurHumain.getNavireFromType(SousMarin.class).coule);
		lblPorteAvionLocal.disableProperty().bind(joueurHumain.getNavireFromType(PorteAvion.class).coule);

		etablirLiaisonGrille(grilleJoueur, joueurHumain, false);
		etablirLiaisonGrille(grilleOpposant, joueurArtificiel, true);
		ObservableList<Navire> copyNavires = FXCollections.observableArrayList(joueurHumain.navires.get());

		navireBox.itemsProperty().set(copyNavires);

	}

	private void etablirLiaisonGrille(GridPane grille, Joueur joueur, boolean isOpposant) {
		for (Node n : grille.getChildren()) {
			if (n instanceof Pane) {
				Pane pane = (Pane) n;
				Integer col = GridPane.getColumnIndex(n);
				Integer ligne = GridPane.getRowIndex(n);
				StringConverter<StatusDeCase> converter = new StatusDeCaseStringConverter(isOpposant);
				if (col != null && ligne != null) {
					pane.styleProperty().bindBidirectional(joueur.grille.get().obtenirStatusDeCase(ligne, col),
							converter);
				}
			}
		}
	}

	private void definirMenuItemsAction() {
		closeMenuItem.setOnAction(new ApplicationCloseAction());
		difficulteMenuItem.setOnAction(new DialogShowAction("Nouvelle partie", "NouvellePartieConfigView"));
		visualiserPartieMenuItem.setOnAction(new VisualiserPartieAction());
	}

	private void definirBoutonsAction() {
		sauvegarderPartieMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
				fileChooser.getExtensionFilters().add(extFilter);
				File file = fileChooser.showSaveDialog(null);

				Boolean result = partieController.sauvegarderPartieXml(file.getAbsolutePath());
			}
		});

		chargerPartieMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
				fileChooser.getExtensionFilters().add(extFilter);
				File file = fileChooser.showOpenDialog(null);

				Partie result = partieController.chargerPartieXml(file.getAbsolutePath());
			}
		});

		debuterPartieButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				if (!partie.isTerminee()) {
					if (navireBox.itemsProperty().get().isEmpty()) {
						partie.etat.set(EtatPartie.EN_COURS);
						messageUtils.afficherMessage("Partie débutée", "Partie débutée",
								"La partie va débuter. À vous de commencer !", AlertType.CONFIRMATION);
					} else {
						messageUtils.afficherMessage("Erreur partie", "Erreur de partie",
								"Vous devez placer tous les navires avant de débuter une partie !", AlertType.ERROR);
					}
				} else {
					messageUtils.afficherMessage("Erreur partie", "Erreur de partie",
							"La partie est terminé. Vous devez créer une nouvelle partie avec la débuter !",
							AlertType.ERROR);
				}

			}

		});

		placerNavireButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				if (valeurManquantePourPlacementNavire()) {
					messageUtils.afficherMessage("Valeur manquante", "Erreur de placement",
							"Vous devez renseigner tous les champs pour le placement d'un navire !", AlertType.ERROR);
				} else {
					placerLeNavire();
				}

			}
		});

	}

	private void definirLblListeners() {
		lblTorpilleurLocal.disabledProperty().addListener(new LblDisableChanged(joueurHumain));
		lblContreTorpilleurLocal.disabledProperty().addListener(new LblDisableChanged(joueurHumain));
		lblSousMarinLocal.disabledProperty().addListener(new LblDisableChanged(joueurHumain));
		lblPorteAvionLocal.disabledProperty().addListener(new LblDisableChanged(joueurHumain));
		lblCroiseurLocal.disabledProperty().addListener(new LblDisableChanged(joueurHumain));

		lblTorpilleurOpposant.disabledProperty().addListener(new LblDisableChanged(joueurArtificiel));
		lblContreTorpilleurOpposant.disabledProperty().addListener(new LblDisableChanged(joueurArtificiel));
		lblSousMarinOpposant.disabledProperty().addListener(new LblDisableChanged(joueurArtificiel));
		lblPorteAvionOpposant.disabledProperty().addListener(new LblDisableChanged(joueurArtificiel));
		lblCroiseurOpposant.disabledProperty().addListener(new LblDisableChanged(joueurArtificiel));
	}

	private void definirGridElementOpposantAction() {

		for (Node node : grilleOpposant.getChildren()) {
			if (node instanceof Pane) {
				node.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						if (partie.etat.get() == EtatPartie.EN_COURS) {
							int col = GridPane.getColumnIndex(node);
							int ligne = GridPane.getRowIndex(node);

							try {
								joueurHumainController.lancerTorpille(new Point(ligne, col));
								partie.getStrageyAttaque().lancer(joueurHumain);
								tourService.ajouterTour(joueurHumain, joueurArtificiel);
							} catch (LocationAlreadyHitException e) {
								messageUtils.afficherMessage("Erreur lancé", "Erreur de lancé",
										"Vous avez déjà effectué un lancé sur la case choisie !", AlertType.ERROR);
							}
						} else {
							messageUtils.afficherMessage("Erreur Partie", "Erreur de Partie",
									"Vous ne pouvez pas effectuer d'attaque si la partie n'est pas en cours !",
									AlertType.ERROR);
						}

					}

				});
			}
		}
	}

	private boolean valeurManquantePourPlacementNavire() {
		return ((navireBox.getSelectionModel().getSelectedItem() == null)
				|| (lineChoiceBox.getSelectionModel().getSelectedItem() == null)
				|| (columnChoiceBox.getSelectionModel().getSelectedItem() == null)
				|| (directionChoiceBox.getSelectionModel().getSelectedItem() == null));
	}

	private void placerLeNavire() {
		Navire navire = navireBox.getSelectionModel().selectedItemProperty().get();
		int ligne = (int) lineChoiceBox.getValue().getKey();
		int col = (int) columnChoiceBox.getValue().getKey();
		Direction direction = (Direction) directionChoiceBox.getValue().getKey();
		navire.position.set(new Point(ligne, col));
		try {
			navire.direction.set(direction);
			joueurHumainController.placerNavire(navire);
			navireBox.getItems().remove(navire);
		} catch (NavireHorsGrilleException e1) {
			messageUtils.afficherMessage("Navire hors grille", "Erreur de placement",
					"Le navire dépassera de la grille !", AlertType.ERROR);
		} catch (NavireChevauchantException e2) {
			messageUtils.afficherMessage("Navire chevauchant", "Erreur de placement",
					"Le navire en chevauchera un autre !", AlertType.ERROR);
		}
	}
}

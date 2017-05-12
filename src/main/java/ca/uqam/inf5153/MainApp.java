package ca.uqam.inf5153;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/***
 *
 * @author Alexis Code en provenance d'un tutoriel qui se trouve � l'adresse
 *         suivante : http://code.makery.ch/library/javafx-8-tutorial/fr/part1/
 */
public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Battleship");

		initRootLayout();

		showFenetrePrincipale();
	}

	private void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("presentation/view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showFenetrePrincipale() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("presentation/view/FenetrePrincipaleView.fxml"));
			AnchorPane fenetrePrincipale = (AnchorPane) loader.load();
			rootLayout.setCenter(fenetrePrincipale);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

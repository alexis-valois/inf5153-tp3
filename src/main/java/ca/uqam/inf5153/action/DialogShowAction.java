package ca.uqam.inf5153.action;

import java.io.IOException;

import ca.uqam.inf5153.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DialogShowAction implements EventHandler<ActionEvent> {

	private static String viewPackage = "presentation/view/";
	private String title;
	private String viewName;
	private static String viewExt = ".fxml";

	public DialogShowAction(String title, String viewName) {
		this.title = title;
		this.viewName = viewName;
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			String resourceLocation = this.viewPackage + this.viewName + this.viewExt;
			Stage dialog = new Stage();
			dialog.setResizable(false);
			dialog.setAlwaysOnTop(true);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(resourceLocation));
			AnchorPane fenetreJoueursInfos = (AnchorPane) loader.load();

			Scene scene = new Scene(fenetreJoueursInfos);
			dialog.setScene(scene);
			dialog.setTitle(this.title);
			dialog.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

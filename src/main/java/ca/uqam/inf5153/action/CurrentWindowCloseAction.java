package ca.uqam.inf5153.action;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;

public class CurrentWindowCloseAction implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.close();
	}

}

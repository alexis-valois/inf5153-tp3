package ca.uqam.inf5153.action;

import ca.uqam.inf5153.service.TourRollingService;
import javafx.concurrent.Service;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class VisualiserPartieAction implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		Service<Void> tourRollingService = new TourRollingService();
		tourRollingService.start();
	}

}

package ca.uqam.inf5153.action;


import ca.uqam.inf5153.service.ApplicationShutdownService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ApplicationCloseAction implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		ApplicationShutdownService shutdownService = new ApplicationShutdownService();
		shutdownService.shutdown();
	}

}

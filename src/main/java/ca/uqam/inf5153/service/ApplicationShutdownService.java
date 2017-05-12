package ca.uqam.inf5153.service;

import javafx.application.Platform;

public class ApplicationShutdownService {
	public void shutdown() {
		Platform.exit();
	}
}

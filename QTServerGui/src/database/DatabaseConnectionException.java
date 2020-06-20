package database;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class DatabaseConnectionException extends Exception{

	private static final long serialVersionUID = 1L;

	public DatabaseConnectionException()
	{
		 Platform.runLater(() -> {
		        Alert dialog = new Alert(AlertType.ERROR, "Connection Failed!", ButtonType.OK);
		        dialog.show();
		    });
	}
}

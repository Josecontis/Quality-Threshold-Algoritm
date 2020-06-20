package database;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class EmptyTypeException extends Exception{

	private static final long serialVersionUID = 1L;

	public EmptyTypeException()
	{
		 Platform.runLater(() -> {
		        Alert dialog = new Alert(AlertType.ERROR, "Error field type!", ButtonType.OK);
		        dialog.show();
		    });
	}
}

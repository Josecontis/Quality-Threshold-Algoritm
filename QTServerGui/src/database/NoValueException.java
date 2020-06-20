package database;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Giuseppe
 * model value not found in resultset
 */
public class NoValueException extends Exception{

	private static final long serialVersionUID = 1L;

	public NoValueException(String string) 
	{
		 Platform.runLater(() -> {
		        Alert dialog = new Alert(AlertType.ERROR, string+"founded", ButtonType.OK);
		        dialog.show();
		    });
	}

}

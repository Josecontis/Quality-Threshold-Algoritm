package database;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * 
 * @author Giuseppe
 * model empty resultset
 */
public class EmptySetException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public EmptySetException()
	{
		 Platform.runLater(() -> {
		        Alert dialog = new Alert(AlertType.ERROR, "Empty resultset!", ButtonType.OK);
		        dialog.show();
		    });
	}

}

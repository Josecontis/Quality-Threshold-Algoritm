package data;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class EmptyDatasetException extends Exception{

	private static final long serialVersionUID = 1L;

	public EmptyDatasetException ()
	{
		 Platform.runLater(() -> {
		        Alert dialog = new Alert(AlertType.ERROR, "Empty dataset!", ButtonType.OK);
		        dialog.show();
		    });
	}
}

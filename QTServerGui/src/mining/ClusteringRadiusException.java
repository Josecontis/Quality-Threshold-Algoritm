package mining;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ClusteringRadiusException extends Exception{

	private static final long serialVersionUID = 1L;

	public ClusteringRadiusException()
	{
	    Platform.runLater(() -> {
	        Alert dialog = new Alert(AlertType.ERROR, "14 tuples in one cluster!", ButtonType.OK);
	        dialog.show();
	    });
	}

}

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Giuseppe
 */
public class LoginGui extends Application {

	@FXML
	private TextField txtl1;

	@FXML
	private PasswordField txtl2;

	@FXML
	private TextField txtl3;

	@FXML
	private TextField txtl4;

	/**
	 * Method that open new Login window to 
	 * put command for Gui interface
	 * @param primaryStage: stage to create a new window 
	 * @throws IOException
	 */
	@Override
	public void start(Stage primaryStage) throws Exception { 

		Parent panel= FXMLLoader.load(getClass().getResource("/XML/LOGXML.fxml")); 
		Scene s=new Scene(panel,500,500);
		primaryStage.setTitle("Quality Threshold Client login");

		primaryStage.getIcons().add(new Image("img/icon3.png"));
		primaryStage.setScene(s);
		primaryStage.show();

	}

	public static void main(String[] args) {

		launch(args);

	}

	/**
	 * Method that control the access from users and open
	 * new window to put command for cluster algorithm.
	 * @throws Exception
	 */
	@FXML
	void logged() throws Exception {

		if(txtl1.getText().equals("MapUser"))
		{
			if(txtl2.getText().equals("map")) 
			{
				if(txtl3.getText().equals("127.0.0.1") || txtl3.getText().equals("localhost"))
				{
					if(txtl4.getText().equals("8080")) 
					{
						txtl3.getText();
						Integer.parseInt(txtl4.getText());
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Quality Threshold Login Information");
						alert.setHeaderText(null);
						alert.setContentText("Correct login credentials!");
						Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
						stage.getIcons().add(new Image("img/icon3.png"));
						alert.showAndWait();

						FXMLLoader loader=new FXMLLoader(getClass().getResource("/XML/CXML.fxml")); 
						Parent panel= loader.load();
						MainTestGui m=loader.getController();
						m.ip=txtl3.getText();
						m.port=new Integer(txtl4.getText()).intValue();
						m.initConnection();
						Scene s=new Scene(panel,1364,703);
						Stage primaryStage= new Stage();
						primaryStage.setTitle("Quality Threshold Client");

						primaryStage.getIcons().add(new Image("img/icon3.png"));
						primaryStage.setScene(s);
						primaryStage.show();

					}
					else
					{
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Quality Threshold Login Error");
						alert.setHeaderText("Incorrect login credentials!");
						alert.setContentText("Insert correct server connection Port (8080)");
						Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
						stage.getIcons().add(new Image("img/icon3.png"));
						alert.showAndWait();
					}

				}
				else 
				{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Quality Threshold Login Error");
					alert.setHeaderText("Incorrect login credentials!");
					alert.setContentText("Insert correct local Ip address");
					Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
					stage.getIcons().add(new Image("img/icon3.png"));
					alert.showAndWait();
				}

			}
			else
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Quality Threshold Login Error");
				alert.setHeaderText("Incorrect login credentials!");
				alert.setContentText("Insert correct Password");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("img/icon3.png"));
				alert.showAndWait();
			}
		}
		else 
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Quality Threshold Login Error");
			alert.setHeaderText("Incorrect login credentials!");
			alert.setContentText("Insert correct Username");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("img/icon3.png"));
			alert.showAndWait();
		}
	}

	/**
	 * method for close the window open
	 * @throws IOException
	 */
	@FXML
	private void terminate() {

		System.exit(0);
	}

	/**
	 * Method for reset the content of textfields: txtl1,txtl2,txtl3,txtl4
	 */
	@FXML
	void resetLogin() {
		txtl1.clear();
		txtl2.clear();
		txtl3.clear();
		txtl4.clear();
	}
}

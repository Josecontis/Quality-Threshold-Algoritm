
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * @author Giuseppe
 */
public class MainTestGui implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	String ip = "";
	int port = 0;

	@FXML
	private TextArea areadb;

	@FXML
	private TextField txtdb1;

	@FXML
	private TextField txtdb2;

	@FXML
	private TextArea areaf;

	@FXML
	private TextField txtf2;

	@FXML
	private TextField txtf1;

	/**
	 * Method that initialize socket and streams for connection with server 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void init()throws IOException, ClassNotFoundException {
		try
		{
			InetAddress addr = InetAddress.getByName(ip); //ip
			Socket socket = new Socket(addr,port); //Port
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		}
		catch (IOException e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Quality Threshold Client Error");
			alert.setHeaderText(null);
			alert.setContentText("Connection Error!");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("img/icon3.png"));
			alert.showAndWait();
			System.exit(0);
		}
	}

	/**
	 * Method that do all operations requested on database and write 
	 * on stream one number for each request to server.
	 * @throws SocketException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void learningFromDBAction() throws SocketException, IOException, ClassNotFoundException
	{
		init();
		double radius=1.0;
		String tableName;
		try
		{
			radius = Double.parseDouble(txtdb2.getText());
			if (radius <= 0)
				throw new NumberFormatException("The radius entered is <= 0");
		}
		catch (NumberFormatException e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Quality Threshold Client Error");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("img/icon3.png"));
			alert.showAndWait();
			return;
		}
		tableName = txtdb1.getText();
		out.writeObject(1);
		out.writeObject(tableName);
		String result = (String) in.readObject();
		if (!result.equals("OK"))
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Quality Threshold Client Error");
			alert.setHeaderText(null);
			alert.setContentText("database read error (TableName)");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("img/icon3.png"));
			alert.showAndWait();
			return;
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Quality Threshold Client Information");
			alert.setHeaderText(null);
			alert.setContentText("Operation succesfully complete!");
			alert.showAndWait();

			out.writeObject(2);
			out.writeObject(radius);
			String result2 = (String) in.readObject();
			if (!result2.equals("OK"))
			{
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Quality Threshold Client Error");
				alert.setHeaderText(null);
				alert.setContentText("database read error (Radius)");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("img/icon3.png"));
				alert.showAndWait();
				return;
			}
			else
			{
				int numCluster = (int) in.readObject();
				String clusters = (String) in.readObject();
				areadb.setText("number of Clusters: " + numCluster + "\n" + clusters);

				out.writeObject(3);
				String result3 = (String) in.readObject();
				if (!result3.equals("OK"))
				{
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Quality Threshold Client Error");
					alert.setHeaderText(null);
					alert.setContentText("Saving error");
					Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
					stage.getIcons().add(new Image("img/icon3.png"));
					alert.showAndWait();
					return;
				}
				else
				{
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Quality Threshold Client Information");
					alert.setHeaderText(null);
					alert.setContentText("File saving operation successful!");
					alert.showAndWait();
				}

			}

		}

	}

	/**
	 * Method that do all operations requested on file and write 
	 * on stream one number for each request to server.
	 * @throws SocketException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void learningFromFileAction() throws SocketException, IOException, ClassNotFoundException
	{
		init();
		out.writeObject(4);

		String fileName = txtf1.getText();
		out.writeObject(fileName);

		double radius = Double.parseDouble(txtf2.getText());
		out.writeObject(radius);
		String result = (String) in.readObject();
		if (!result.equals("OK"))
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Quality Threshold Client Error");
			alert.setHeaderText(null);
			alert.setContentText("error reading from file");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("img/icon3.png"));
			alert.showAndWait();
			return;
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Quality Threshold Client Information");
			alert.setHeaderText(null);
			alert.setContentText("Operation succesfully complete!");
			alert.showAndWait();
			areaf.setText((String)in.readObject());
		}

	}

	/**
	 * Method that send to server an advertisement (client is on!).
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void initConnection() throws IOException, ClassNotFoundException
	{
		init();
		out.writeObject(0);

	}

	/**
	 * Method to close window and send to server an advertisement (client is off!).
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@FXML
	private void stopConnection() throws IOException, ClassNotFoundException {
		init();
		out.writeObject(5);

		in.close();
		out.close();
		System.exit(0);
	}

	/**
	 * Method for reset the content of textarea: areaf 
	 * and textfield: txtf1,txtf2
	 */
	@FXML
	private void resetFile() {
		areaf.clear();
		txtf2.clear();
		txtf1.clear();
	}

	/**
	 * Method for reset the content of textarea: areadb 
	 * and textfield: txtdb1,txtdb2
	 */
	@FXML
	private void resetDataBase() {
		areadb.clear();
		txtdb2.clear();
		txtdb1.clear();
	}

	/**
	 * Method to execute when the button of database window is clicked.
	 */
	@FXML
	void actionDatabase() {
		try
		{
			learningFromDBAction();
		}
		catch (SocketException e1)
		{
			System.out.println(e1);

		}
		catch (FileNotFoundException e1)
		{
			System.out.println(e1);

		}
		catch (IOException e1)
		{
			System.out.println(e1);

		}
		catch (ClassNotFoundException e1)
		{
			System.out.println(e1);

		}
	}

	/**
	 * Method to execute when the button of file window is clicked.
	 */
	@FXML
	void actionFile() {
		try
		{
			learningFromFileAction();
		}
		catch (SocketException e1)
		{
			System.out.println(e1);

		}
		catch (FileNotFoundException e1)
		{
			System.out.println(e1);

		}
		catch (IOException e1)
		{
			System.out.println(e1);

		}
		catch (ClassNotFoundException e1)
		{
			System.out.println(e1);

		}
	}

}


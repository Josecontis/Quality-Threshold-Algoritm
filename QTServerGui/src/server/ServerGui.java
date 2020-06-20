package server;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import data.Data;
import data.EmptyDatasetException;
import database.DatabaseConnectionException;
import database.DbAccess;
import database.EmptySetException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import mining.ClusteringRadiusException;
import mining.QTMiner;

/** 
 * @author Giuseppe
 */
public class ServerGui extends Application implements Serializable{

	private static final long serialVersionUID = 1L;
	private static int PORT = 8080;
	private Data data = null;
	private QTMiner qt = null;
	private String tabella = "";
	private double radius;

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private int numberOfC;

	@FXML
	private TextArea areasrv;

	@FXML
	private TextField txtsrv;

	/**
	 * method to start threadserver and handle the connection 
	 * with multiple clients
	 * @throws IOException
	 */
	@FXML
	private void runServer() throws IOException {
		
		ServerSocket s = new ServerSocket (PORT);
		areasrv.setText("Server Started... \n"+s+"\n");
		new Thread(() -> 
		{
			try 
			{ 
				while(true) 
				{
					// Si blocca finchè non si verifica una connessione:
					socket = s.accept();
					clientService(socket);
				}
			}
			catch (IOException e2) 
			{
				try 
				{
					socket.close();
				} 
				catch (IOException e)
				{
					e.printStackTrace(System.err);
				}
			}
			finally 
			{
				try 
				{
					s.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * Method to handle clients requests identified by number(0,1,2,3,4,5) and when a client is connected or no.
	 * @param socket: socket of connection
	 */
	private void clientService(Socket socket) {
		new Thread(() -> 
		{	
			try
			{
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
				//ciclo di ricezione dal client e invio di risposta
				while (true)
				{
					switch ((int) in.readObject())
					{
					case 0:
						numberOfC++;
						areasrv.appendText(" >> " + "Client No: " + numberOfC + " started!\n");
						txtsrv.setText(""+numberOfC);
						break;

					case 1:
						areasrv.appendText("Reading from database...\nRequest accepted: "+socket+"\n");
						tabella = (String) in.readObject();
						try
						{
							DbAccess.initConnection();
							out.writeObject("OK");
						}
						catch (DatabaseConnectionException e2)
						{
							e2.printStackTrace();
						}

						data = new Data(tabella);
						DbAccess.closeConnection();
						break;

					case 2:
						radius = (double) in.readObject();
						qt = new QTMiner(radius);
						try
						{
							int numC = qt.compute(data);
							out.writeObject("OK");
							out.writeObject(numC);
							out.writeObject(qt.getC().toString(data));
						}
						catch (ClusteringRadiusException e)
						{
							e.printStackTrace();
						}
						break;

					case 3:
						try
						{
							String fileName = tabella + radius + ".dmp";
							qt.salva(fileName);
							out.writeObject("OK");
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
						break;

					case 4:
						areasrv.appendText("Reading from file...\nRequest accepted: "+socket+"\n");
						String fileNameRead = null;
						try
						{
							String tableName = (String) in.readObject();
							double radiusFile = (double) in.readObject();
							fileNameRead = tableName + radiusFile + ".dmp";
							out.writeObject("OK");
							QTMiner output = new QTMiner(fileNameRead);
							out.writeObject(output.toString());
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
						break;

					case 5:
						areasrv.appendText(" >> " + "Connection aborted!\n");
						numberOfC--;
						txtsrv.setText(""+numberOfC);
						break;
					}
				}
			}
			catch (ClassNotFoundException | IOException | EmptyDatasetException | SQLException | EmptySetException e)
			{
				try 
				{
					in.close();
					out.close();
					socket.close();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}

		}).start();
	}

	/**
	 * method for close the window open
	 * @throws IOException
	 */
	@FXML
	private void terminate() throws IOException {
		System.exit(0);
	}

	/**
	 * Method for reset the content of textarea: areasrv 
	 * and textfield: txtsrv
	 */
	@FXML
	private void reset()
	{
		areasrv.clear();
		txtsrv.clear();
	}

	/**
	 * Method that open new Server window to 
	 * put command for Gui interface.
	 * @param primaryStage: stage to create a new window.
	 * @throws IOException
	 */
	@Override
	public void start(Stage primaryStage) throws Exception { 

		Parent panel= FXMLLoader.load(getClass().getResource("/XML/SRVXML.fxml")); 
		Scene s=new Scene(panel,1364,703);
		primaryStage.setTitle("Quality Threshold Server");

		primaryStage.getIcons().add(new Image("img/icon3.png"));
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args) throws IOException {

		launch(args);

	}
}

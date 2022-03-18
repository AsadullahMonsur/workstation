package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class loginController implements Initializable{
	@FXML
	private static Stage logwin;
	private Stage signwin;
	public void login(ActionEvent event1) throws IOException{
	    Parent root2 = FXMLLoader.load(getClass().getResource("log.fxml"));
	    Scene log = new Scene(root2);
		log.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		logwin = Main.getWindow();
		logwin.setTitle("Log in");
		logwin.setScene(log);
		logwin.show();
	}
	public void signup(ActionEvent event2) throws IOException{
	    Parent root2 = FXMLLoader.load(getClass().getResource("signup.fxml"));
	    Scene log = new Scene(root2);
		log.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage signwin = Main.getWindow();
		signwin.setTitle("Sign up");
		signwin.setScene(log);
		signwin.show();
	}
	public static Stage getLogWindow() {
		return logwin;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

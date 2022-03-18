package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class logPageController implements Initializable{
	@FXML
	private TextField txName;
	@FXML
	private PasswordField txPass;
	@FXML
	private AnchorPane logAnchor;
	@FXML
	private ScrollPane srpane;
	private static Stage logst;
	public void logAction(ActionEvent eventlog) throws IOException {
		if(txName.getText().equals("Sakib")) {
			Parent menu = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
			Scene s = new Scene(menu);
			logst = loginController.getLogWindow();
			logst.setScene(s);
			
			//st.close();
		}
	}
	public static Stage logStage() {
		return logst;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}

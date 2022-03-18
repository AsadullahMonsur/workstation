package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class homePageController implements Initializable {
    @FXML
    private AnchorPane mainhome;
    @FXML
    private static Stage winhome;
	public void Personal_Info(ActionEvent event1) throws IOException{
		winhome = logPageController.logStage();
		AnchorPane info = FXMLLoader.load(getClass().getResource("Personal_Info.fxml"));
		mainhome.getChildren().setAll(info);
		
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

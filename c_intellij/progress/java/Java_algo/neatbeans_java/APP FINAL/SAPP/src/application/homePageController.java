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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class homePageController implements Initializable {
    @FXML
    private AnchorPane mainhome;
    @FXML
    private static Stage winhome;
    @FXML
    private static Stage view;
	public void Personal_Info(ActionEvent event1) throws IOException{
		winhome = logPageController.logStage();
		AnchorPane info = FXMLLoader.load(getClass().getResource("Personal_Info.fxml"));
		mainhome.getChildren().setAll(info);
		
	}
	public void searchbar(ActionEvent eventbc) throws IOException {
		winhome = logPageController.logStage();
		AnchorPane info2 = FXMLLoader.load(getClass().getResource("ProductSearch.fxml"));
		mainhome.getChildren().setAll(info2);
	}
	public void purchaseList(ActionEvent eventbc) throws IOException {
		AnchorPane info2 = FXMLLoader.load(getClass().getResource("PurchaseList.fxml"));
		mainhome.getChildren().setAll(info2);
	}
	public void summary(ActionEvent eventbc) throws IOException {
		AnchorPane info3 = FXMLLoader.load(getClass().getResource("Summary.fxml"));
		mainhome.getChildren().setAll(info3);
	}
	public void exit(ActionEvent eventbc) throws IOException {
		Parent menu = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene s1 = new Scene(menu);
		view = Main.getWindow();
		view.setScene(s1);
		view.show();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

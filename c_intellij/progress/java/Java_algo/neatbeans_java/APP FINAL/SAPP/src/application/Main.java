package application;
	
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	private static Stage window;
	private Scene scene1;
	@FXML
    public Image img;
	static String nm="";
	static String num="";
	static String adType="";
	static String em="";
	static String ad="";
	static String ps="";
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root1  = FXMLLoader.load(getClass().getResource("login.fxml"));
			scene1 = new Scene(root1,1200,700);
			scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window = primaryStage;
			window.setScene(scene1);
			window.setTitle("Shasroyi App");
			window.show();
			
			
			 /*Parent root = FXMLLoader.load(getClass().getResource("Sign_In_Page.fxml"));
	         opening_pane.getChildren().setAll(root);*/
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static Stage getWindow() {
		return window;
	}
	public static Connection getConnection()throws Exception{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3306/shasroyi?serverTimezone=UTC","root","");
			return conn;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Connection failed, try again");
		}
		return null;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

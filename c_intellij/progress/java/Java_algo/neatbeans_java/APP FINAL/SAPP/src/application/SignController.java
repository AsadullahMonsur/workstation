package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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

public class SignController implements Initializable{
	@FXML
	private TextField sname;
	@FXML
	private TextField spass;
	@FXML
	private TextField smail;
	@FXML
	private TextField snum;
	@FXML
	private TextField sadd;
	@FXML
	private Stage signst;
	public void signup(ActionEvent ev) throws IOException{
		String  a = sname.getText();
		String  b = snum.getText();
		String  c = smail.getText();
		String  d = spass.getText();
		String  e = sadd.getText();
		String q2 = "insert INTO `user` VALUES('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"');";
		try {
			Connection c2 = Main.getConnection();
			Statement s = c2.createStatement();
		     s.executeUpdate(q2);
		    JOptionPane.showMessageDialog(null, "Sign up done successfully, go back and Log in");
		}
		catch(Exception f) {
			JOptionPane.showMessageDialog(null, "Error");
		}
		
	}
	public void backbt(ActionEvent ev) throws IOException{
		Parent menu = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene s = new Scene(menu);
		signst = Main.getWindow();
		signst.setScene(s);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
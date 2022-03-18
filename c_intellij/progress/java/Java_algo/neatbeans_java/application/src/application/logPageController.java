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
	private static Scene Mlast;
	public void logAction(ActionEvent eventlog) throws IOException {
		String name = txName.getText();
		String pass = txPass.getText();
		String query1  = "SELECT * FROM `admin` where AdminName='"+name+"' and AdminPass='"+pass+"';";
		String query2  = "SELECT * FROM `user` where UserName='"+name+"' and UserPassword='"+pass+"';";
		try {
			Connection con = Main.getConnection();
			Statement st1 = con.createStatement();
			Statement st2 = con.createStatement();
			ResultSet rs1 = st1.executeQuery(query1);
			ResultSet rs2 = st2.executeQuery(query2);
			int ac = 0;
			int uc = 0;
			while(rs1.next()) {
				  Main.nm = rs1.getString("AdminName");
				  Main.num = rs1.getString("AdminNumber");
				  Main.adType = rs1.getString("AdminType");
				  ac++;
			}
			while(rs2.next()) {
				Main.nm = rs2.getString("UserName");
				Main.num = rs2.getString("UserNumber");
				Main.em = rs2.getString("UserEmail");
				Main.ad = rs2.getString("UserAddress");
				Main.ps = rs2.getString("UserPassword");
				uc++;
				}
			if(ac!=0 && uc==0) {
			    JOptionPane.showMessageDialog(null, "Found your id : "+Main.nm + " , "+Main.num+" , Type: "+ Main.adType);
			    //Code for admin Access
			}
			else if(uc!=0 && ac==0){ // Code for User Access
		        JOptionPane.showMessageDialog(null, "Found your id : Type : User "); 
				Parent menu = FXMLLoader.load(getClass().getResource("homePage.fxml"));
				logst = Main.getWindow();
				Scene s = new Scene(menu);
				logst.setScene(s);
			}
			else{
				JOptionPane.showMessageDialog(null, "No user related to your info, sign up first");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No results");
                        e.printStackTrace();
		}
		
	}
	public void backAction(ActionEvent eventback) throws IOException {
		Parent menu = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene s = new Scene(menu);
		logst = Main.getWindow();
		logst.setScene(s);
	}
	
	public static Stage logStage() {
		return logst;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}

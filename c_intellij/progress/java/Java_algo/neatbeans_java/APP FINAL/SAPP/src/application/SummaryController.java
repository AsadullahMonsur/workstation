package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.text.Text;


public class SummaryController implements Initializable {
	@FXML 
	private Text item;
	@FXML
	private Text amount;
	public void sload(ActionEvent ex) throws IOException{
		try {
			String query = "SELECT ProductPrice FROM `userpurchaselist`where UserName='"+Main.nm+"';";
			String query2 = "SELECT * FROM `userpurchaselist`where UserName='"+Main.nm+"';";
			int r1Counter = 0,r2Counter = 0;
			Connection c = Main.getConnection();
			Statement s1 = c.createStatement();
			Statement s2 = c.createStatement();
			ResultSet S1 = s1.executeQuery(query);
			ResultSet S2 = s2.executeQuery(query2);
			while(S1.next()) {
			String r1 = S1.getString("ProductPrice");
			r1Counter+= Integer.parseInt(r1);
			}
			while(S2.next()) {
			r2Counter++;
			}
			item.setText(""+r2Counter);
			amount.setText(""+r1Counter);
			
		}catch(Exception e) {
			
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

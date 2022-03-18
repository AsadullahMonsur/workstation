package application;


import java.sql.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPurchaseController implements Initializable{

	@FXML
	private TextField pd;
	@FXML
	private TextField pc;
	
	public void addbtn(ActionEvent ev) throws IOException {
		String a = Main.nm;
		String b = pd.getText();
		int c  = Integer.parseInt(pc.getText());
		String q = "insert INTO `userpurchaselist` VALUES('"+a+"','"+b+"','"+c+"');";
		try {
			Connection c2 = Main.getConnection();
			Statement s = c2.createStatement();
		     s.executeUpdate(q);
		    JOptionPane.showMessageDialog(null, "Added to Purchase List");
		    Stage temp = SearchProductController.getPre();
		    temp.close();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Failed To Add");
	}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}

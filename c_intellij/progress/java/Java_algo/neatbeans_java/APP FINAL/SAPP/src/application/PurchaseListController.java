package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PurchaseListController implements Initializable{
    @FXML
    private TableView<Purchase> table2;
    @FXML
    private TableColumn<Purchase, String> Prp; 
    @FXML
    private TableColumn<Purchase, Integer> Prc; 
    
    public ObservableList<Purchase> list2 = FXCollections.observableArrayList();
    Connection cn2;
    Statement st2;
    ResultSet rs2;
    
    public void Loadbt(ActionEvent ev) throws IOException{
    	String query = "SELECT ProductName,ProductPrice FROM `userpurchaselist` where UserName ='"+Main.nm+"';";
    	try {
    	cn2 = Main.getConnection();
        st2 = cn2.createStatement();
    	rs2 = st2.executeQuery(query);
    	while(rs2.next()) {
    		String name = rs2.getString("ProductName");
    		int pr = rs2.getInt("ProductPrice");
    		Purchase s = new Purchase(name,pr);
    		list2.add(s);
    	}
    	}catch(Exception e) {
    		
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Prp.setCellValueFactory(new PropertyValueFactory<Purchase,String>("PrName"));
		Prc.setCellValueFactory(new PropertyValueFactory<Purchase,Integer>("PrPrice"));
		table2.setItems(list2);
	}


}

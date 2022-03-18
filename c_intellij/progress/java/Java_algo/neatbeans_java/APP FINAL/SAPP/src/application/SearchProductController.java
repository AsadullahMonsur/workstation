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

public class SearchProductController implements Initializable{
    @FXML
    private TableView<Search> table1;
    @FXML
    private TableColumn<Search, String> pname; 
    @FXML
    private TableColumn<Search, Integer> pprice; 
    @FXML
    private TableColumn<Search, String> pdetails; 
    @FXML
    private TableColumn<Search, String> pshop; 
    @FXML
    private TableColumn<Search, Integer> pdistance; 
    @FXML
    private TableColumn<Search, String> paddress;
    @FXML
    private TextField searchTx;
    @FXML
    private static Stage Pstage;
    
    public ObservableList<Search> list = FXCollections.observableArrayList();
    Connection cn;
    Statement st1;
    ResultSet rs1;
    public static Stage getPre() {
    	return Pstage;
    }
    
    public void searchBt(ActionEvent ev) throws IOException{
    	String wd = searchTx.getText();
    	String query = "Select * from products where Name like '%"+wd+"%' Order by Price,Distance asc;";
    	try {
    	cn = Main.getConnection();
        st1 = cn.createStatement();
    	rs1 = st1.executeQuery(query);
    	while(rs1.next()) {
    		String name = rs1.getString("Name");
    		int pr = rs1.getInt("Price");
    		String details = rs1.getString("Details");
    		String sname = rs1.getString("ShopName");
    		int Dis = rs1.getInt("Distance");
    		String sadd = rs1.getString("ShopAddress");
    		Search s = new Search(name,pr,details,sname,Dis,sadd);
    		System.out.println(list);
    		list.add(s);
    	}
    	}catch(Exception e) {
    		
    	}
    }
    public void addpurchase(ActionEvent event) throws IOException {
    	Parent pr =  FXMLLoader.load(getClass().getResource("AddPurchase.fxml"));
    	Pstage = new Stage();
    	Scene Pscene = new Scene(pr);
    	Pstage.setScene(Pscene);
    	Pstage.show();
    	
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		pname.setCellValueFactory(new PropertyValueFactory<Search,String>("Name"));
		pprice.setCellValueFactory(new PropertyValueFactory<Search,Integer>("Price"));
		pdetails.setCellValueFactory(new PropertyValueFactory<Search,String>("Details"));
		pshop.setCellValueFactory(new PropertyValueFactory<Search,String>("Shopname"));
		pdistance.setCellValueFactory(new PropertyValueFactory<Search,Integer>("Distance"));
		paddress.setCellValueFactory(new PropertyValueFactory<Search,String>("ShopAddress"));
		table1.setItems(list);
	}

}

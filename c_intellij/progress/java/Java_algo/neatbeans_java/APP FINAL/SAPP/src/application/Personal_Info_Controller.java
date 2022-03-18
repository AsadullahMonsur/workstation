package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class Personal_Info_Controller implements Initializable {
	@FXML
	private Text Uname;
	@FXML
	private Text Unum;
	@FXML
	private Text Umail;
	@FXML
	private Text Uadd;
	@FXML
	private Text Upass;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void load(ActionEvent ev) throws IOException {
        setname(Main.nm);
        setnumber(Main.num);
        setemail(Main.em);
        setaddress(Main.ad);
        setPass(Main.ps);
	}
	
	
	
	
	
	
	
	public void setname(String n) {
		Uname.setText(n);
		
	}
	public void setnumber(String num) {
		Unum.setText(num);
	}
	public void setemail(String em) {
		Umail.setText(em);
		
	}
	public void setaddress(String ad) {
		Uadd.setText(ad);
		
	}
	public void setPass(String ps) {
		Upass.setText(ps);
		
	}

}

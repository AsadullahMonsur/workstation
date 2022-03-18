import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
public class Server_UI_Controller implements Initializable{
    @FXML
    private Button text_changer;
    @FXML
    private Label display_label;
    @Override
    public void initialize(URL location, ResourceBundle resources) {      }
    @FXML
    private void text_changer_method(ActionEvent event) {
        display_label.setText("Changed");
    }
}

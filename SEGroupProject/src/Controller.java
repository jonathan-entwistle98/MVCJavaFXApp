import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;



public class Controller {
	
	@FXML
	private HBox headerDashboard;
	
	@FXML
	private VBox leftAreaDashboard;
	
	@FXML
	private TextFlow metricsText;
	
	@FXML
	private VBox bodyDashboard;
	
	@FXML
	private HBox footerDashboard;
	
	@FXML
	private Label sidebarLabel;
	
	private Model model;
	
	public void initialize(){
		System.out.println("intialized check");
	}

	public void initModel(Model model) {
		this.model = model;
		sidebarLabel.setText("Clicks logged: " + Integer.toString(model.clickLogList.size()));
	}
	
	

}

import java.io.File;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.gluonhq.charm.glisten.control.*;
import javafx.scene.control.Alert;

public class AdDashboard extends Application{
	

	@FXML
	private AnchorPane mainAnchorPane;

	@FXML
	private Accordion accord;

	@FXML
	private TabPane metricsDetailsTabPane;
	
	@FXML
	private TitledPane titletPane;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Button viewAnalytics;
	
	@FXML
	private Button impressions;
	
	@FXML
	private Button clicks;
	
	@FXML
	private Button uniques;
	
	@FXML
	private Button bounces;
	
	@FXML
	private Button cpm;
	
	@FXML
	private Button totalCost;
	
	@FXML
	private Button ctr;
	
	@FXML
	private Button cpc;
	
	@FXML
	private Button cpa;
	
	@FXML
	private Button conversions;
	
	@FXML
	private Button bounceRate;
	
	@FXML
	private Label sidebarLabel1;

	@FXML
	private Label sidebarLabel2;

	@FXML
	private Label sidebarLabel3;

	@FXML
	private Label sidebarLabel4;

	@FXML
	private Label sidebarLabel6;

	@FXML
	private Label sidebarLabel5;

	@FXML
	private Label sidebarLabel7;

	@FXML
	private Label sidebarLabel8;

	@FXML
	private Label sidebarLabel11;

	@FXML
	private Label sidebarLabel10;

	@FXML
	private Label sidebarLabel9;

	@FXML
	private Label NoOfClicksLab;

	@FXML
	private Label NoOfImpressLab;

	@FXML
	private Label NoOfUniqLab;

	@FXML
	private Label NoOfConversLab;

	@FXML
	private Label CTRLab;

	@FXML
	private Label TotCostCampLab;

	@FXML
	private Label CPALab;

	@FXML
	private Label CPCLab;

	@FXML
	private Label BounceRateLab;

	@FXML
	private Label NoOfBouncesLab;

	@FXML
	private Label CPMLab;

	@FXML
	private TitledPane ChartsAndGraphsPane;

	@FXML
	private AnchorPane chGrphAnchor;

	@FXML
	private MenuButton ChartsGraphsDropDown;

	@FXML
	private MenuItem HistogramItem;

	@FXML
	private MenuItem GraphItem;

	@FXML
	private StackedBarChart<?, ?> Histogram;

	@FXML
	private LineChart<?, ?> Graph;

	@FXML
	private TitledPane SettingsPane;

	@FXML
	private AnchorPane SettingsAnchor;
	
	@FXML
	private TextField clickLogTextField;
	
	@FXML
	private TextField impressionLogTextField;
	
	@FXML
	private TextField serverLogTextField;
	
	@FXML
	private ImageView graphLogo;
	

	
	
	private Controller controller; 
	
	private Stage stage;
	
	private File impressionLogFile;
	
	private File clickLogFile;
	
	private File serverLogFile;
	
	private Model model;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	
	/**
	 * Creates a new Scene, adds this scene to the stage and sets the css and fxml files to be used.
	 * Controller and Model classes instantiated, and reference to model passed to controller
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
			
		FXMLLoader loader = new FXMLLoader(getClass().getResource("initialView.fxml"));
		

		// Setting up model and controller
		model = new Model();
		controller = new Controller(model);
		
		loader.setController(this);
		
		Parent root = loader.load();

		primaryStage.setTitle("Dashboard");
		Scene scene = new Scene(root, 800, 600);
		scene.getStylesheets().add("style.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		this.stage = primaryStage;
	//	File file = new File("graphLogo.jpg");
	    Image image = new Image("graphLogo2.jpg");
		graphLogo.setImage(image);
		
	}
	
	
	/**
	 * Uses data from model class to set all required metrics to be added to the sidebar in the view
	 * @param model
	 */
	public void updateOverview() {
		// Gets overview items (clicks, impressions, etc.).
		OverviewItems items = controller.getOverviewItems();
		
		clicks.wrapTextProperty().setValue(true);
		impressions.wrapTextProperty().setValue(true);
		uniques.wrapTextProperty().setValue(true);
		conversions.wrapTextProperty().setValue(true);
		totalCost.wrapTextProperty().setValue(true);
		ctr.wrapTextProperty().setValue(true);
		cpa.wrapTextProperty().setValue(true);
		cpc.wrapTextProperty().setValue(true);
		cpm.wrapTextProperty().setValue(true);
		bounces.wrapTextProperty().setValue(true);
		bounceRate.wrapTextProperty().setValue(true);
		
		clicks.setText("Clicks \n" + items.getClicks());
		impressions.setText("Impressions \n" + items.getImpressions());
		uniques.setText("Unique Clicks \n" + items.getUniques());
		conversions.setText("Conversions \n" + items.getConversions());
		totalCost.setText("Total Cost \n" + round(items.getTotalCost(),4));
		ctr.setText("CTR \n" + round(items.getCTR(),4));
		cpa.setText("CPA \n" + round(items.getCPA(),4));
		cpc.setText("CPC \n" + round(items.getCPC(),4));
		cpm.setText("CPM \n" + round(items.getCPM(),4));
		bounces.setText("Bounces \n" + items.getBounces());
		bounceRate.setText("Bounce Rate \n" + round(items.getBounceRate(),4));
		
		if(items.getBounceRate()==0 || items.getBounces()==0 || items.getCPM()==0 || items.getCPC()==0 || items.getCPA()==0 || items.getCTR()==0){
			divideByZeroError();
		}
	}
	
	public void viewAnalyticsClicked(){
	//	double initialTime = System.currentTimeMillis();
		
		if(clickLogTextField.getText().equals("")
			|| impressionLogTextField.getText().equals("")
			|| serverLogTextField.getText().equals("")){

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Blank Field");
			alert.setHeaderText("Error, please select all files.");
			alert.setContentText("Some fields are left blank.");
			
			alert.showAndWait();
		}
		
		model.loadCSVs(impressionLogFile, clickLogFile, serverLogFile);
		
		FXMLLoader loader2 = new FXMLLoader(getClass().getResource("mainView.fxml"));
		loader2.setController(this);

	//	double finalTime = System.currentTimeMillis();
		
		
		Parent root2;
		try {
			root2 = loader2.load();
			Scene scene = new Scene(root2, 1000, 600);
			scene.getStylesheets().add("style.css");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		updateOverview();	
	}
	
	public void impressionsDetailClicked(){
		FXMLLoader loader3 = new FXMLLoader(getClass().getResource("detailedView.fxml"));
		loader3.setController(this);
		
		Parent root3;
		try {
			root3 = loader3.load();
			Scene scene = new Scene(root3, 1000, 600);
			scene.getStylesheets().add("style.css");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clicksDetailClicked(){
		impressionsDetailClicked();
		metricsDetailsTabPane.getSelectionModel().select(1);	
	}
	
	public void uniquesDetailClicked(){
		impressionsDetailClicked();
		metricsDetailsTabPane.getSelectionModel().select(2);	
	}
	
	public void bouncesDetailClicked(){
		impressionsDetailClicked();
		metricsDetailsTabPane.getSelectionModel().select(3);	
	}
	
	public void cpmDetailClicked(){
		impressionsDetailClicked();
		metricsDetailsTabPane.getSelectionModel().select(4);	
	}
	
	public void totalCostDetailClicked(){
		impressionsDetailClicked();
		metricsDetailsTabPane.getSelectionModel().select(5);	
	}
	
	public void ctrDetailClicked(){
		impressionsDetailClicked();
		metricsDetailsTabPane.getSelectionModel().select(6);	
	}
	
	public void cpcDetailClicked(){
		impressionsDetailClicked();
		metricsDetailsTabPane.getSelectionModel().select(7);	
	}
	
	public void cpaDetailClicked(){
		impressionsDetailClicked();
		metricsDetailsTabPane.getSelectionModel().select(8);	
	}
	
	public void conversionsDetailClicked(){
		impressionsDetailClicked();
		metricsDetailsTabPane.getSelectionModel().select(9);	
	}
	
	public void bounceRateDetailClicked(){
		impressionsDetailClicked();
		metricsDetailsTabPane.getSelectionModel().select(10);	
	}
	
	public void returnToOverviewClicked(){
		FXMLLoader loader2 = new FXMLLoader(getClass().getResource("mainView.fxml"));
		loader2.setController(this);
		
		Parent root2;
		try {
			root2 = loader2.load();
			Scene scene = new Scene(root2, 1000, 600);
			scene.getStylesheets().add("style.css");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		updateOverview();
	}
	
	public void impressionLogFilePickerClicked(){
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setTitle("Select Impression Log");
		  impressionLogFile = fileChooser.showOpenDialog(stage);
		  impressionLogTextField.setText(impressionLogFile.getName());
	}
	
	public void clickLogFilePickerClicked(){
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setTitle("Select Click Log");
		  clickLogFile = fileChooser.showOpenDialog(stage);
		  clickLogTextField.setText(clickLogFile.getName());
	}
	
	public void serverLogFilePickerClicked(){
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setTitle("Select Server Log");
		  serverLogFile = fileChooser.showOpenDialog(stage);
		  serverLogTextField.setText(serverLogFile.getName());
	}
	
	public void divideByZeroError(){
        
        Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("CSV Data Error");
		alert.setHeaderText("Negative Number or Divide by Zero error");
		alert.setContentText("A divide by zero error occurred");
		
		alert.showAndWait();
	}
	
	public static double round(double value, int scale) {
	    return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
	}
	

}

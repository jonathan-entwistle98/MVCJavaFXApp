import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdDashboard extends Application{
	
	@FXML
	private AnchorPane mainAnchorPane;

	@FXML
	private Accordion accord;

	@FXML
	private TitledPane titletPane;

	@FXML
	private AnchorPane anchorPane;

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
	
	private Model model;
	
	private ArrayList<Object> allMetrics = new ArrayList<Object>();
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

	
	/**
	 * Creates a new Scene, adds this scene to the stage and sets the css and fxml files to be used.
	 * Controller and Model classes instantiated, and reference to model passed to controller
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
			
		FXMLLoader loader = new FXMLLoader(getClass().getResource("viewDashboard.fxml"));
		
		Controller controller = new Controller();
		
		loader.setController(this);
		
		Parent root = loader.load();
		
		model = new Model();
		model.loadCSVData();
		controller.setUp(model);
		this.allMetrics = controller.getAllMetrics();
		initModel();	

		primaryStage.setTitle("Dashboard");
		Scene scene = new Scene(root, 1000, 600);
		scene.getStylesheets().add("style.css");
		primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	/**
	 * Uses data from model class to set all required metrics to be added to the sidebar in the view
	 * @param model
	 */
	public void initModel() {
		this.model = model;

		sidebarLabel1.setText("Number of Clicks: " + (model.clickLogList.size()-1));
		sidebarLabel2.setText("Number of Impressions: " + (model.impressionLogList.size()-1));
		sidebarLabel3.setText("Number of unique user clicks: " + allMetrics.get(0));
		sidebarLabel4.setText("Number of conversions: " + allMetrics.get(1));
		sidebarLabel5.setText("Total cost of campaign: " + allMetrics.get(2));
		sidebarLabel6.setText("Click-through-rate (CTR): " + allMetrics.get(3));
		sidebarLabel7.setText("Cost-per-aquisition (CPA): " + allMetrics.get(4));
		sidebarLabel8.setText("Cost-per-click (CPC): " + allMetrics.get(5));
		sidebarLabel9.setText("Cost per-thousand-impressions (CPM): " + allMetrics.get(6));
		sidebarLabel10.setText("Number of bounces: " + allMetrics.get(7));
		sidebarLabel11.setText("Bounce rate: " + allMetrics.get(8));
	}
	

}

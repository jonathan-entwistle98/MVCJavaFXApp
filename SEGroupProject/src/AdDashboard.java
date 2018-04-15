import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.gluonhq.charm.glisten.control.TextField;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

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
	private LineChart<String, Integer> impressionGraph;
	
	@FXML
	private LineChart<String, Integer> clickGraph;
	
	@FXML
	private LineChart<String, Integer> uniqueGraph;
	
	@FXML
	private LineChart<String, Integer> bounceGraph;
	
	@FXML
	private LineChart<String, Integer> CPMGraph;
	
	@FXML
	private LineChart<String, Integer> CPCGraph;
	
	@FXML
	private LineChart<String, Integer> CPAGraph;
	
	@FXML
	private LineChart<String, Integer> CTRGraph;
	
	@FXML
	private LineChart<String, Integer> totalCostGraph;
	
	@FXML
	private LineChart<String, Integer> conversionsGraph;
	
	@FXML
	private NumberAxis impressionYAxis;
	
	@FXML
	private CategoryAxis impressionXAxis;
	
	@FXML
	private NumberAxis clickYAxis;
	
	@FXML
	private CategoryAxis clickXAxis;
	
	@FXML
	private NumberAxis uniqueYAxis;
	
	@FXML
	private CategoryAxis uniqueXAxis;
	
	@FXML
	private NumberAxis bounceYAxis;
	
	@FXML
	private CategoryAxis bounceXAxis;
	
	@FXML
	private NumberAxis CPMYAxis;
	
	@FXML
	private CategoryAxis CPMXAxis;
	
	@FXML
	private NumberAxis CPCYAxis;
	
	@FXML
	private CategoryAxis CPCXAxis;
	
	@FXML
	private NumberAxis CPAYAxis;
	
	@FXML
	private CategoryAxis CPAXAxis;
	
	@FXML
	private NumberAxis CTRYAxis;
	
	@FXML
	private CategoryAxis CTRXAxis;
	
	@FXML
	private NumberAxis totalCostYAxis;
	
	@FXML
	private CategoryAxis totalCostXAxis;
	
	@FXML
	private NumberAxis conversionsYAxis;
	
	@FXML
	private CategoryAxis conversionsXAxis;

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
	

	private OverviewItems items;
	
//	private Controller controller; 
	
	private Stage stage;
	
	private File impressionLogFile;
	
	private File clickLogFile;
	
	private File serverLogFile;
	
//	private Model model;
	
	private DataModel dm;
	
	private OverviewItems overview;
	
	private Series<String, Integer> impressionSeries;
	
	private Series<String, Integer> clickSeries;
	
	private Series<String, Integer> uniqueSeries;
	
	private Series<String, Integer> bounceSeries;
	
	private Series<String, Integer> CPMSeries;
	
	private Series<String, Integer> CPCSeries;
	
	private Series<String, Integer> CPASeries;
	
	private Series<String, Integer> CTRSeries;
	
	private Series<String, Integer> totalCostSeries;
	
	private Series<String, Integer> conversionsSeries;
	
	
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
		
		// New DataModel Set-Up
		File file1 = new File("impression_log.csv");
		File file2 = new File("click_log.csv");
		File file3 = new File("server_log.csv");
		
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		Date start = null;
		Date end = null;
		try {
			start = new SimpleDateFormat(dateFormat).parse("2015-01-01 12:00:00");
			end = new SimpleDateFormat(dateFormat).parse("2015-02-30 13:59:08");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dm = new DataModel();
		dm.init();
		
		// Export campaign to the database by providing the following:
		// 	File file1 = impression_log.CSV
		// 	File file2 = click_log.CSV
		//  File file3 = server_log.CSV
		// Doing so will create a new campaign and allocate it an ID.
		dm.exportCSVs(file1, file2, file3);
		
		// Get IDs of all existing campaigns.
		ArrayList<Integer> campaignIDs = dm.getCampaigns();
		
		// Select campaign by its ID from the database.
		// This selects campaign for loading and returns overview metrics.
		overview = dm.selectCampaign(1);
		
		// Gets data with set date range and stores it in DataModel.
		// Must pass two Date objects (start and end) as parameters.
		dm.fetchData(start, end);
		
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
		
		clicks.setText("Clicks \n" + overview.getClicks());
		impressions.setText("Impressions \n" + overview.getImpressions());
		uniques.setText("Unique Clicks \n" + overview.getUniques());
		conversions.setText("Conversions \n" + overview.getConversions());
		totalCost.setText("Total Cost \n" + round(overview.getTotalCost(),4));
		ctr.setText("CTR \n" + round(overview.getCTR(),4));
		cpa.setText("CPA \n" + round(overview.getCPA(),4));
		cpc.setText("CPC \n" + round(overview.getCPC(),4));
		cpm.setText("CPM \n" + round(overview.getCPM(),4));
		bounces.setText("Bounces \n" + overview.getBounces());
		bounceRate.setText("Bounce Rate \n" + round(overview.getBounceRate(),4));
		
		if(overview.getBounceRate()==0 || overview.getBounces()==0 || overview.getCPM()==0 || overview.getCPC()==0 || overview.getCPA()==0 || overview.getCTR()==0){
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
		
		//model.loadCSVs(impressionLogFile, clickLogFile, serverLogFile);
		
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
	
	public void loadDetailedView() {
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
	
	public void impressionsDetailClicked(){
			loadDetailedView();
			getImpressionsOverTime();
	}
	
	public void clicksDetailClicked(){
		loadDetailedView();
		getClicksOverTime();
		metricsDetailsTabPane.getSelectionModel().select(1);	
	}
	
	public void uniquesDetailClicked(){
		loadDetailedView();
//		getUniquesOverTime();
		metricsDetailsTabPane.getSelectionModel().select(2);	
	}
	
	public void bouncesDetailClicked(){
		loadDetailedView();
//		getBouncesOverTime();
		metricsDetailsTabPane.getSelectionModel().select(3);	
	}
	
	public void cpmDetailClicked(){
		loadDetailedView();
		getCPMsOverTime();
		metricsDetailsTabPane.getSelectionModel().select(4);	
	}
	
	public void totalCostDetailClicked(){
		loadDetailedView();
		getTotalCostOverTime();
		metricsDetailsTabPane.getSelectionModel().select(5);	
	}
	
	public void ctrDetailClicked(){
		loadDetailedView();
		getCTRsOverTime();
		metricsDetailsTabPane.getSelectionModel().select(6);	
	}
	
	public void cpcDetailClicked(){
		loadDetailedView();
		getCPCsOverTime();
		metricsDetailsTabPane.getSelectionModel().select(7);	
	}
	
	public void cpaDetailClicked(){
		loadDetailedView();
		getCPAsOverTime();
		metricsDetailsTabPane.getSelectionModel().select(8);	
	}
	
	public void conversionsDetailClicked(){
		loadDetailedView();
		getConversionsOverTime();
		metricsDetailsTabPane.getSelectionModel().select(9);	
	}
	
	public void bounceRateDetailClicked(){
		loadDetailedView();
//		getBounceRateOverTime();
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
	
	public void getImpressionsOverTime(){
		
		// Returns XYChart.Series<Long(representing Date), Number> for defined metric.
		impressionSeries = dm.getSeries(Metric.IMPRESSIONS);
		
//		impressionXAxis.setAutoRanging(false);
//		double lowerBound = (double)1420130859000L;
//		double upperBound = (double)1421227547000L;
//		impressionXAxis.setLowerBound(lowerBound);
//		impressionXAxis.setUpperBound(upperBound);
//		impressionXAxis.setTickUnit(100000000);
		
//		impressionXAxis.setTickLabelFormatter(new StringConverter<Number>() {
//	        @Override
//	        public String toString(Number number) {
//	        	Long l = number.longValue();
//	        	Date date = new Date(l);
//	        	Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	        	String stringDate = formatter.format(date);
//	            return stringDate;
//	        }
//	        @Override
//	        public Number fromString(String string) {
//                return null;
//            }
//	    });
		
		impressionGraph.getData().add(impressionSeries);
	}
	
	public void getClicksOverTime(){
		
	// Returns XYChart.Series<Long(representing Date), Number> for defined metric.
	clickSeries = dm.getSeries(Metric.CLICKS);
	
//	clickXAxis.setAutoRanging(false);
//	double lowerBound = (double)1420130859000L;
//	double upperBound = (double)1421227547000L;
//	clickXAxis.setLowerBound(lowerBound);
//	clickXAxis.setUpperBound(upperBound);
//	clickXAxis.setTickUnit(100000000);
//	
//	clickXAxis.setTickLabelFormatter(new StringConverter<Number>() {
//        @Override
//        public String toString(Number number) {
//        	Long l = number.longValue();
//        	Date date = new Date(l);
//        	Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        	String stringDate = formatter.format(date);
//            return stringDate;
//        }
//        @Override
//        public Number fromString(String string) {
//            return null;
//        }
//    });
	
	clickGraph.getData().add(clickSeries);
	
	}
	
	public void getCPMsOverTime(){
		
	// Returns XYChart.Series<Long(representing Date), Number> for defined metric.
	CPMSeries = dm.getSeries(Metric.CPM);
	
//	CPMXAxis.setAutoRanging(false);
//	double lowerBound = (double)1420130859000L;
//	double upperBound = (double)1421227547000L;
//	CPMXAxis.setLowerBound(lowerBound);
//	CPMXAxis.setUpperBound(upperBound);
//	CPMXAxis.setTickUnit(100000000);
//	
//	CPMYAxis.setAutoRanging(false);
//	CPMYAxis.setUpperBound(2.0);
//	CPMYAxis.setTickUnit(0.1);
//	
//	CPMXAxis.setTickLabelFormatter(new StringConverter<Number>() {
//        @Override
//        public String toString(Number number) {
//        	Long l = number.longValue();
//        	Date date = new Date(l);
//        	Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        	String stringDate = formatter.format(date);
//            return stringDate;
//        }
//        @Override
//        public Number fromString(String string) {
//            return null;
//        }
//    });
	
	CPMGraph.getData().add(CPMSeries);
	
	}
	
	public void getCPCsOverTime(){
		
	// Returns XYChart.Series<Long(representing Date), Number> for defined metric.
	CPCSeries = dm.getSeries(Metric.CPC);
	
//	CPCXAxis.setAutoRanging(false);
//	double lowerBound = (double)1420130859000L;
//	double upperBound = (double)1421227547000L;
//	CPCXAxis.setLowerBound(lowerBound);
//	CPCXAxis.setUpperBound(upperBound);
//	CPCXAxis.setTickUnit(100000000);
//	
//	CPCXAxis.setTickLabelFormatter(new StringConverter<Number>() {
//        @Override
//        public String toString(Number number) {
//        	Long l = number.longValue();
//        	Date date = new Date(l);
//        	Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        	String stringDate = formatter.format(date);
//            return stringDate;
//        }
//        @Override
//        public Number fromString(String string) {
//            return null;
//        }
//    });
	
	CPCGraph.getData().add(CPCSeries);
	
	}
	
	public void getCTRsOverTime(){
		
	// Returns XYChart.Series<Long(representing Date), Number> for defined metric.
	CTRSeries = dm.getSeries(Metric.CTR);
	
//	CTRXAxis.setAutoRanging(false);
//	double lowerBound = (double)1420130859000L;
//	double upperBound = (double)1421227547000L;
//	CTRXAxis.setLowerBound(lowerBound);
//	CTRXAxis.setUpperBound(upperBound);
//	CTRXAxis.setTickUnit(100000000);
//	
//	CTRYAxis.setAutoRanging(false);
//	CTRYAxis.setUpperBound(0.5);
//	CTRXAxis.setTickUnit(0.005);
//	
//	CTRXAxis.setTickLabelFormatter(new StringConverter<Number>() {
//        @Override
//        public String toString(Number number) {
//        	Long l = number.longValue();
//        	Date date = new Date(l);
//        	Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        	String stringDate = formatter.format(date);
//            return stringDate;
//        }
//        @Override
//        public Number fromString(String string) {
//            return null;
//        }
//    });
	
	CTRGraph.getData().add(CTRSeries);
	
	}
	
	public void getCPAsOverTime(){
		
	// Returns XYChart.Series<Long(representing Date), Number> for defined metric.
	CPASeries = dm.getSeries(Metric.CPA);
	
//	CPAXAxis.setAutoRanging(false);
//	double lowerBound = (double)1420130859000L;
//	double upperBound = (double)1421227547000L;
//	CPAXAxis.setLowerBound(lowerBound);
//	CPAXAxis.setUpperBound(upperBound);
//	CPAXAxis.setTickUnit(100000000);
//	
//	CPAXAxis.setTickLabelFormatter(new StringConverter<Number>() {
//        @Override
//        public String toString(Number number) {
//        	Long l = number.longValue();
//        	Date date = new Date(l);
//        	Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        	String stringDate = formatter.format(date);
//            return stringDate;
//        }
//        @Override
//        public Number fromString(String string) {
//            return null;
//        }
//    });
	
	CPAGraph.getData().add(CPASeries);
	
	}
	
	public void getConversionsOverTime(){
		
	// Returns XYChart.Series<Long(representing Date), Number> for defined metric.
	conversionsSeries = dm.getSeries(Metric.CONVERSIONS);
	
//	conversionsXAxis.setAutoRanging(false);
//	double lowerBound = (double)1420130859000L;
//	double upperBound = (double)1421227547000L;
//	conversionsXAxis.setLowerBound(lowerBound);
//	conversionsXAxis.setUpperBound(upperBound);
//	conversionsXAxis.setTickUnit(100000000);
//	
//	conversionsXAxis.setTickLabelFormatter(new StringConverter<Number>() {
//        @Override
//        public String toString(Number number) {
//        	Long l = number.longValue();
//        	Date date = new Date(l);
//        	Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        	String stringDate = formatter.format(date);
//            return stringDate;
//        }
//        @Override
//        public Number fromString(String string) {
//            return null;
//        }
//    });
	
	conversionsGraph.getData().add(conversionsSeries);
	
	}
	
	public void getTotalCostOverTime(){
		
	// Returns XYChart.Series<Long(representing Date), Number> for defined metric.
	totalCostSeries = dm.getSeries(Metric.TOTAL_COST);
	
//	totalCostXAxis.setAutoRanging(false);
//	double lowerBound = (double)1420130859000L;
//	double upperBound = (double)1421227547000L;
//	totalCostXAxis.setLowerBound(lowerBound);
//	totalCostXAxis.setUpperBound(upperBound);
//	totalCostXAxis.setTickUnit(100000000);
//	
//	totalCostXAxis.setTickLabelFormatter(new StringConverter<Number>() {
//        @Override
//        public String toString(Number number) {
//        	Long l = number.longValue();
//        	Date date = new Date(l);
//        	Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        	String stringDate = formatter.format(date);
//            return stringDate;
//        }
//        @Override
//        public Number fromString(String string) {
//            return null;
//        }
//    });
	
	totalCostGraph.getData().add(totalCostSeries);
	
	}
//	
//	public void getUniquesOverTime(){
//		uniqueSeries = new XYChart.Series();
//		uniqueXAxis.setAutoRanging(false);
//		double lowerBound = (double)1420130859000L;
//		double upperBound = (double)1421227547000L;
//		uniqueXAxis.setLowerBound(lowerBound);
//		uniqueXAxis.setUpperBound(upperBound);
//		uniqueXAxis.setTickUnit(100000000);
//		
//		ArrayList<ArrayList<Object>> uniquesOverTime = items.getClicksOverTime();
//		for(ArrayList<Object> uniqueOverTime : uniquesOverTime){
//			for(int i=0; i<uniqueOverTime.size(); i++){
//				Date d = (Date) uniqueOverTime.get(0);
//				Long longDate = d.getTime();
//				uniqueSeries.getData().add(new XYChart.Data(longDate, uniqueOverTime.get(1)));
//			}
//		}
//		
//		uniqueXAxis.setTickLabelFormatter(new StringConverter<Number>() {
//	        @Override
//	        public String toString(Number number) {
//	        	Long l = number.longValue();
//	        	Date date = new Date(l);
//	        	Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	        	String stringDate = formatter.format(date);
//	            return stringDate;
//	        }
//	        @Override
//	        public Number fromString(String string) {
//                return null;
//            }
//	    });
//		
//		uniqueGraph.getData().add(uniqueSeries);
//	}
//	
//	public void getBouncesOverTime(){
//		bounceSeries = new XYChart.Series();
//		bounceXAxis.setAutoRanging(false);
//		double lowerBound = (double)1420130859000L;
//		double upperBound = (double)1421227547000L;
//		bounceXAxis.setLowerBound(lowerBound);
//		bounceXAxis.setUpperBound(upperBound);
//		bounceXAxis.setTickUnit(100000000);
//		
//		ArrayList<ArrayList<Object>> bouncesOverTime = items.getClicksOverTime();
//		for(ArrayList<Object> bounceOverTime : bouncesOverTime){
//			for(int i=0; i<bounceOverTime.size(); i++){
//				Date d = (Date) bounceOverTime.get(0);
//				Long longDate = d.getTime();
//				bounceSeries.getData().add(new XYChart.Data(longDate, bounceOverTime.get(1)));
//			}
//		}
//		
//		bounceXAxis.setTickLabelFormatter(new StringConverter<Number>() {
//	        @Override
//	        public String toString(Number number) {
//	        	Long l = number.longValue();
//	        	Date date = new Date(l);
//	        	Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	        	String stringDate = formatter.format(date);
//	            return stringDate;
//	        }
//	        @Override
//	        public Number fromString(String string) {
//                return null;
//            }
//	    });
//		
//		bounceGraph.getData().add(bounceSeries);
//	}
	

}
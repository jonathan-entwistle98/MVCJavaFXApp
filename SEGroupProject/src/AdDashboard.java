import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

//import com.gluonhq.charm.glisten.control.TextField;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
	private Label impressions;
	
	@FXML
	private Label clicks;
	
	@FXML
	private Label uniques;
	
	@FXML
	private Label bounces;
	
	@FXML
	private Label cpm;
	
	@FXML
	private Label totalCost;
	
	@FXML
	private Label ctr;
	
	@FXML
	private Label cpc;
	
	@FXML
	private Label cpa;
	
	@FXML
	private Label conversions;
	
	@FXML
	private Label bounceRate;

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
	private LineChart<String, Integer> bounceRateGraph;
	
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
	private BarChart<String, Integer> CPCHistogram;
	
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
	private NumberAxis bounceRateYAxis;
	
	@FXML
	private CategoryAxis bounceRateXAxis;
	
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
	private NumberAxis CPCHistogramYAxis;
	
	@FXML
	private CategoryAxis CPCHistogramXAxis;

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
	
	@FXML
	private Tab impressionTab;
	
	@FXML
	private Tab clickTab;
	
	@FXML
	private Tab uniqueTab;
	
	@FXML
	private Tab bounceTab;
	
	@FXML
	private Tab CPMTab;
	
	@FXML
	private Tab totalCostTab;
	
	@FXML
	private Tab CPCTab;
	
	@FXML
	private Tab CPATab;
	
	@FXML
	private Tab CTRTab;
	
	@FXML
	private Tab conversionsTab;
	
	@FXML
	private Tab bounceRateTab;
	
	@FXML
	private Tab CPCHistogramTab;
	
	@FXML
	private MenuItem loadCampaign;
	

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
	
	private Series<String, Integer> bounceRateSeries;
	
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
			
		FXMLLoader loader = new FXMLLoader(getClass().getResource("graphView.fxml"));
		
		loader.setController(this);
		
		Parent root = loader.load();

		primaryStage.setTitle("Dashboard");
		Scene scene = new Scene(root, 800, 600);
		
		//scene.getStylesheets().add("style.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		this.stage = primaryStage;
	//	File file = new File("graphLogo.jpg");
				
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
		
		clicks.setText("" + overview.getClicks());
		impressions.setText("" + overview.getImpressions());
		uniques.setText("" + overview.getUniques());
		conversions.setText("" + overview.getConversions());
		totalCost.setText("" + round(overview.getTotalCost(),4));
		ctr.setText("" + round(overview.getCTR(),4));
		cpa.setText("" + round(overview.getCPA(),4));
		cpc.setText("" + round(overview.getCPC(),4));
		cpm.setText("" + round(overview.getCPM(),4));
		bounces.setText("" + overview.getBounces());
		bounceRate.setText("" + round(overview.getBounceRate(),4));
		
//		if(overview.getBounceRate()==0 || overview.getBounces()==0 || overview.getCPM()==0 || overview.getCPC()==0 || overview.getCPA()==0 || overview.getCTR()==0){
//			divideByZeroError();
//		}
	}
	
	public void loadCampaignClicked() {
		
		FXMLLoader loader3 = new FXMLLoader(getClass().getResource("startScreen.fxml"));
		loader3.setController(this);
		
		Parent root3;
		try {
			root3 = loader3.load();
			Scene scene = new Scene(root3, 1000, 600);
//			scene.getStylesheets().add("style.css");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void viewAnalyticsClicked(){
		
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		Date start = null;
		Date end = null;
		try {
			start = new SimpleDateFormat(dateFormat).parse("2015-01-01 12:00:00");
			end = new SimpleDateFormat(dateFormat).parse("2015-01-15 13:59:08");
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
		dm.exportCSVs(impressionLogFile, clickLogFile, serverLogFile);
		
		// Get IDs of all existing campaigns.
		ArrayList<Integer> campaignIDs = dm.getCampaigns();
		
		// Select campaign by its ID from the database.
		// This selects campaign for loading and returns overview metrics.
		overview = dm.selectCampaign(1);
		
		// Gets data with set date range and stores it in DataModel.
		// Must pass two Date objects (start and end) as parameters.
		dm.fetchData(start, end);
		
		if(clickLogTextField.getText().equals("")
			|| impressionLogTextField.getText().equals("")
			|| serverLogTextField.getText().equals("")){
		
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Blank Field");
			alert.setHeaderText("Error, please select all files.");
			alert.setContentText("Some fields are left blank.");
			
			alert.showAndWait();
		}
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("graphView.fxml"));
		
		loader.setController(this);
		
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root, 1000, 600);
//			scene.getStylesheets().add("style.css");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		getTotalCostOverTime();
		
		impressionTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
            	if(impressionTab.isSelected()) {
            		getImpressionsOverTime();
            	}
            }
        });
		clickTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
            	if(clickTab.isSelected()) {
            		getClicksOverTime();
            	}
            }
        });
		uniqueTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
            	if(uniqueTab.isSelected()) {
            		getUniquesOverTime();
            	}
            }
        });
		bounceTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
            	if(bounceTab.isSelected()) {
            		getBouncesOverTime();
            	}
            }
        });
		CPMTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
            	if(CPMTab.isSelected()) {
            		getCPMsOverTime();
            	}
            }
        });
		totalCostTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
            	if(totalCostTab.isSelected()) {
            		getTotalCostOverTime();
            	}
            }
        });
		CTRTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
            	if(CTRTab.isSelected()) {
            		getCTRsOverTime();
            	}
            }
        });
		CPCTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
            	if(CPCTab.isSelected()) {
            		getCPCsOverTime();
            	}
            }
        });
		CPATab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
            	if(CPATab.isSelected()) {
            		getCPAsOverTime();
            	}
            }
        });
		conversionsTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
            	if(conversionsTab.isSelected()) {
            		getConversionsOverTime();
            	}
            }
        });
		bounceRateTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
            	if(bounceRateTab.isSelected()) {
            		getBounceRatesOverTime();
            	}
            }
        });
		CPCHistogramTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
            	if(CPCHistogramTab.isSelected()) {
            		getCPCHistogramsOverTime();
            	}
            }
        });
		
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
		impressionGraph.setLegendVisible(false);
		impressionYAxis.setLabel("Impressions");
		impressionXAxis.setLabel("Time (Date)");
		
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
	clickGraph.setLegendVisible(false);
	clickYAxis.setLabel("Clicks");
	clickXAxis.setLabel("Time (Date)");
	
	clickGraph.getData().add(clickSeries);
	System.out.println("Kappa");
	}
	
	public void printClicked() {
		Scene currentScene = stage.getScene();
		WritableImage img = new WritableImage((int)currentScene.getWidth(), (int)currentScene.getHeight());
		WritableImage snap = currentScene.snapshot(img); 
		ImageView printNode = new ImageView(snap);
		printNode.setFitWidth(500);
		printNode.setFitHeight(500);
		PrinterJob job = PrinterJob.createPrinterJob();
		if(job != null){
			job.showPrintDialog(stage); 
			job.printPage(printNode);
			job.endJob();
		}
	}
	
	public void saveAsImageClicked() {
		
			Scene currentScene = stage.getScene();
			WritableImage img = new WritableImage((int)currentScene.getWidth(), (int)currentScene.getHeight());
			currentScene.snapshot(img);   
			FileChooser saveDialog = new FileChooser();
			saveDialog.setTitle("Save image");
			File file = saveDialog.showSaveDialog(stage);
			
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	
	public void getCPMsOverTime(){
		
		CPMSeries = dm.getSeries(Metric.CPM);
		CPMGraph.setLegendVisible(false);
		CPMYAxis.setLabel("CPM");
		CPMXAxis.setLabel("Time (Date)");
		
		CPMYAxis.setAutoRanging(false);
		CPMYAxis.setLowerBound(0);
		CPMYAxis.setUpperBound(2.0);
		CPMYAxis.setTickUnit(0.05);
		
		CPMGraph.getData().add(CPMSeries);
	
	}
	
	public void getCPCsOverTime(){
		
		CPCSeries = dm.getSeries(Metric.CPC);
		CPCGraph.setLegendVisible(false);
		CPCYAxis.setLabel("CPC");
		CPCXAxis.setLabel("Time (Date)");
		
		CPCGraph.getData().add(CPCSeries);
	
	}
	
	public void getCTRsOverTime(){
		
		CTRSeries = dm.getSeries(Metric.CTR);
		CTRGraph.setLegendVisible(false);
		CTRYAxis.setLabel("CTR");
		CTRXAxis.setLabel("Time (Date)");
		
		CTRGraph.getData().add(CTRSeries);
	
	}
	
	public void getCPAsOverTime(){
		
		CPASeries = dm.getSeries(Metric.CPA);
		CPAGraph.setLegendVisible(false);
		CPAYAxis.setLabel("CPA");
		CPAXAxis.setLabel("Time (Date)");
		
		CPAGraph.getData().add(CPASeries);
	
	}
	
	public void getConversionsOverTime(){
		
	conversionsSeries = dm.getSeries(Metric.CONVERSIONS);
	conversionsGraph.setLegendVisible(false);
	conversionsYAxis.setLabel("Conversions");
	conversionsXAxis.setLabel("Time (Date)");
	
	conversionsGraph.getData().add(conversionsSeries);
	
	}
	
	public void getTotalCostOverTime(){
		
		totalCostSeries = dm.getSeries(Metric.TOTAL_COST);
		totalCostGraph.setLegendVisible(false);
		totalCostYAxis.setLabel("Total Cost");
		totalCostXAxis.setLabel("Time (Date)");
		
		totalCostGraph.getData().add(totalCostSeries);
	
	}
	
	public void getUniquesOverTime(){
		uniqueSeries = new XYChart.Series();
		uniqueGraph.setLegendVisible(false);
		uniqueYAxis.setLabel("Uniques");
		uniqueXAxis.setLabel("Time (Date)");
		
		uniqueGraph.getData().add(uniqueSeries);
	}
	
	public void getBouncesOverTime(){
		bounceSeries = new XYChart.Series();
		bounceGraph.setLegendVisible(false);
		bounceYAxis.setLabel("Bounces");
		bounceXAxis.setLabel("Time (Date)");
		
		bounceGraph.getData().add(bounceSeries);
	}
	
	public void getBounceRatesOverTime(){
		bounceRateSeries = new XYChart.Series();
		bounceRateGraph.setLegendVisible(false);
		bounceRateYAxis.setLabel("Bounce Rate");
		bounceRateXAxis.setLabel("Time (Date)");
		
		bounceRateGraph.getData().add(bounceRateSeries);
	}
	
	public void getCPCHistogramsOverTime(){
		
		CPCSeries = dm.getSeries(Metric.CPC);
		CPCHistogram.setLegendVisible(false);
		CPCHistogramYAxis.setLabel("CPC");
		CPCHistogramXAxis.setLabel("Time (Date)");
		
		CPCHistogram.getData().add(CPCSeries);
	
	}
	

}
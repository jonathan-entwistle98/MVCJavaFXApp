import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import javafx.scene.control.ProgressBar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

//import com.gluonhq.charm.glisten.control.TextField;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class AdDashboard extends Application{
	
	private volatile boolean finished = false;
	
	@FXML
	private ToolBar toolbar;
	
	@FXML 
	private StackPane mainStackPane;
	
	@FXML
	private HBox filterPane;
	
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
	private TextField bounceDefinitionTextField;
	
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
	
	@FXML
	private DatePicker fromDatePicker;
	
	@FXML
	private DatePicker toDatePicker;
	
	@FXML
	private ChoiceBox bounceDefinitionChoiceBox;
	
	@FXML
	private ChoiceBox selectCampaignChoiceBox;
	
	@FXML
	private TextField campaignNameTextField;
	
	@FXML
	private StackPane windowStackPane;
	
	@FXML
	private BorderPane graphViewBorderPane;
	
	@FXML
	private BorderPane selectCampaignBorderPane;
	
	@FXML
	private MenuItem appearanceMenuItem;
	
	@FXML
	private MenuItem terminologyMenuItem;
	
	@FXML
	private MenuItem aboutMenuItem;
	
	@FXML
	private ProgressBar progressBarNewCampaign;
	
	@FXML
	private HBox filterHBox1;
	
	@FXML
	private Label filterLabel1;
	
	@FXML
	private Button deleteFilter1;
	
	@FXML
	private HBox filterHBox2;
	
	@FXML
	private Label filterLabel2;
	
	@FXML
	private Button deleteFilter2;
	
	@FXML
	private HBox filterHBox3;
	
	@FXML
	private Label filterLabel3;
	
	@FXML
	private Button deleteFilter3;
	
	@FXML
	private HBox filterHBox4;
	
	@FXML
	private Label filterLabel4;
	
	@FXML
	private Button deleteFilter4;
	
	@FXML
	private HBox filterHBox5;
	
	@FXML
	private Label filterLabel5;
	
	@FXML
	private Button deleteFilter5;
	
	@FXML
	private HBox filterHBox6;
	
	@FXML
	private Label filterLabel6;
	
	@FXML
	private Button deleteFilter6;
	
	@FXML
	private HBox filterHBox7;
	
	@FXML
	private Label filterLabel7;
	
	@FXML
	private Button deleteFilter7;
	
	@FXML
	private HBox filterHBox8;
	
	@FXML
	private Label filterLabel8;
	
	@FXML
	private Button deleteFilter8;
	
	@FXML
	private HBox filterHBox9;
	
	@FXML
	private Label filterLabel9;
	
	@FXML
	private Button deleteFilter9;
	
	@FXML
	private HBox filterHBox10;
	
	@FXML
	private Label filterLabel10;
	
	@FXML
	private Button deleteFilter10;
	
	@FXML
	private CheckBox genderMaleCheckbox;
	
	@FXML
	private CheckBox genderFemaleCheckbox;
	
	@FXML
	private CheckBox ageUnder25Checkbox;
	
	@FXML
	private CheckBox age25To34Checkbox;
	
	@FXML
	private CheckBox age35To44Checkbox;
	
	@FXML
	private CheckBox age45To54Checkbox;
	
	@FXML
	private CheckBox ageOver54Checkbox;
	
	@FXML
	private CheckBox incomeLowCheckbox;
	
	@FXML
	private CheckBox incomeMediumCheckbox;
	
	@FXML
	private CheckBox incomeHighCheckbox;
	
	@FXML
	private CheckBox contextNewsCheckbox;
	
	@FXML
	private CheckBox contextShoppingCheckbox;
	
	@FXML
	private CheckBox contextSocialMediaCheckbox;
	
	@FXML
	private CheckBox contextBlogCheckbox;
	
	@FXML
	private CheckBox contextHobbiesCheckbox;
	
	@FXML
	private CheckBox contextTravelCheckbox;
	
	
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
	
	private Long minDateLong;
	
	private Long maxDateLong;
	
	private Date fromDate;
	
	private Date toDate;
	
	private Date datePickerFromCheck;
	
	private Date datePickerToCheck;
	
	private Date initialMinDate;
	
	private Date initialMaxDate;
	
	private boolean fromPickerChanged;
	
	private boolean toPickerChanged;
	
	private ArrayList<ArrayList<Object>> campaignNamesArrayList;
	
	private DatePicker minDate = null;
	
	private DatePicker maxDate = null;
	
	ArrayList<String> allFilterTexts = new ArrayList<String>();
	
	
	public static void main(String[] args) {
		launch(args);
	}

	
	/**
	 * Creates a new Scene, adds this scene to the stage and sets the css and fxml files to be used.
	 * Controller and Model classes instantiated, and reference to model passed to controller
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		dm = new DataModel();
		dm.init();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("graphView.fxml"));
		
		loader.setController(this);
		
		Parent root = loader.load();

		primaryStage.setTitle("Dashboard");
		primaryStage.setMaximized(true);
		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
		
		//scene.getStylesheets().add("style.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		this.stage = primaryStage;
	//	File file = new File("graphLogo.jpg");
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		fromDate = new SimpleDateFormat(dateFormat).parse("2015-01-01 12:00:00");
		toDate = new SimpleDateFormat(dateFormat).parse("2015-01-15 13:59:08");
        System.out.println("1 fromDate is" + fromDate.toString());
        System.out.println("1 toDate is" + toDate.toString());
		
		ObservableList<String> availableChoices = FXCollections.observableArrayList("Time(seconds)", "Pages Visited"); 
		bounceDefinitionChoiceBox.setItems(availableChoices);
		
		ArrayList<String> campaignNames = new ArrayList<String>();
		campaignNamesArrayList = dm.getCampaignNamesAndIds();
		for(ArrayList<Object> campaignNamesAndIds : campaignNamesArrayList) {
			campaignNames.add((String) campaignNamesAndIds.get(1));
		}		
		ObservableList<String> campaignChoices = FXCollections.observableArrayList(campaignNames);
		selectCampaignChoiceBox.setItems(campaignChoices);
		
		
		filterHBox1.setVisible(false);	
		filterHBox2.setVisible(false);	
		filterHBox3.setVisible(false);	
		filterHBox4.setVisible(false);	
		filterHBox5.setVisible(false);	
		filterHBox6.setVisible(false);	
		filterHBox7.setVisible(false);	
		filterHBox8.setVisible(false);	
		filterHBox9.setVisible(false);	
		filterHBox10.setVisible(false);	
		
		graphViewBorderPane.setDisable(true);
		impressionSeries = new Series<String, Integer>();
		dm.bounceSeconds(1);
				
	}
	
	public void returnToGraphView(){
		graphViewBorderPane.setDisable(false);
		selectCampaignBorderPane.setVisible(false);
	}
	
	public void restrictDatePicker() {
		if(overview != null) {
			LocalDate localDateMaxDate;
			LocalDate localDateMinDate;
			minDateLong = overview.getMinDate();
			maxDateLong = overview.getMaxDate();
			DatePicker overallMinDate = new DatePicker();
			DatePicker overallMaxDate = new DatePicker();
			Date dateMinDate = new Date(minDateLong);
			Date dateMaxDate = new Date(maxDateLong);
			localDateMinDate = dateMinDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			localDateMaxDate = dateMaxDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();			
			overallMinDate.setValue(localDateMinDate);
			overallMaxDate.setValue(localDateMaxDate);
			
			if(fromDate == null) {
				minDate = new DatePicker();
				localDateMinDate = dateMinDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			}else {
				minDate = new DatePicker();
				localDateMinDate = fromDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			}
			if(toDate == null) {
				maxDate = new DatePicker(); // DatePicker, used to define max date available, you can also create another for minimum date
				localDateMaxDate = dateMaxDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			}else {
				maxDate = new DatePicker(); // DatePicker, used to define max date available, you can also create another for minimum date
				localDateMaxDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			}
			
			minDate.setValue(localDateMinDate);
			maxDate.setValue(localDateMaxDate); // Max date available will be 2015-01-01
			
			final Callback<DatePicker, DateCell> dayCellFactory;
			dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
			    @Override
			    public void updateItem(LocalDate item, boolean empty) {
			        super.updateItem(item, empty);
			        if (item.isAfter(overallMaxDate.getValue())) { //Disable all dates after required date
			            setDisable(true);
			            setStyle("-fx-background-color: #ffc0cb;"); //To set background on different color
			        }if(item.isBefore(overallMinDate.getValue())) {
			        	 setDisable(true);
				         setStyle("-fx-background-color: #ffc0cb;"); //To set background on different color
			        }
			    }
			};
			fromDatePicker.setValue(localDateMinDate);
			toDatePicker.setValue(localDateMaxDate);
			System.out.println("Day of week" + fromDatePicker.getValue().getDayOfWeek());
			initialMinDate = Date.from(localDateMinDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			initialMaxDate = Date.from(localDateMaxDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			fromDatePicker.setDayCellFactory(dayCellFactory);
			toDatePicker.setDayCellFactory(dayCellFactory);
		}
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
		
		ObservableList<String> availableChoices = FXCollections.observableArrayList("Time(seconds)", "Pages Visited"); 
		bounceDefinitionChoiceBox.setItems(availableChoices);
		
//		if(overview.getBounceRate()==0 || overview.getBounces()==0 || overview.getCPM()==0 || overview.getCPC()==0 || overview.getCPA()==0 || overview.getCTR()==0){
//			divideByZeroError();
//		}
	}
	
	public void loadCampaignClicked() {
		
		fromDatePicker.setOnAction(event -> {
            LocalDate localDate = fromDatePicker.getValue();
            fromDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
        });
		
		toDatePicker.setOnAction(event -> {
            LocalDate localDate = toDatePicker.getValue();
            toDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            System.out.println("localDate is" + localDate.toString());
            System.out.println("toDate is" + toDate.toString());
        });
		
		graphViewBorderPane.setDisable(true);
		selectCampaignBorderPane.setVisible(true);
		
		restrictDatePicker();
		
		ArrayList<String> campaignNames = new ArrayList<String>();
		campaignNamesArrayList = dm.getCampaignNamesAndIds();
		for(ArrayList<Object> campaignNamesAndIds : campaignNamesArrayList) {
			campaignNames.add((String) campaignNamesAndIds.get(1));
		}		
		ObservableList<String> campaignChoices = FXCollections.observableArrayList(campaignNames);
		selectCampaignChoiceBox.setItems(campaignChoices);
		
	}
	
public void resetDefaultsClicked(){
        
    }
    
    public void applyChangesClicked(){
        
    }
    
    public void terminologyClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("terminologyView.fxml"));
        loader.setController(this);
        Parent termRoot = loader.load();
        
        Stage termStage = new Stage();
        
        termStage.setTitle("Terminology");
        
        Scene termScene = new Scene(termRoot,800,600);
        
        termStage.setScene(termScene);
        termStage.initModality(Modality.WINDOW_MODAL);
        termStage.initOwner(stage);
        termStage.show();
        

    }
    
    public void aboutClicked() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("aboutView.fxml"));
        loader.setController(this);
        Parent aboutRoot = loader.load();
        
        Stage aboutStage = new Stage();
        
        aboutStage.setTitle("About");
        
        Scene aboutScene = new Scene(aboutRoot,700,400);
        
        aboutStage.setScene(aboutScene);
        aboutStage.initModality(Modality.WINDOW_MODAL);
        aboutStage.initOwner(stage);
        aboutStage.show();
        
    }
    
    public void customizeAppearanceClicked() throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("appearanceView.fxml"));
        loader.setController(this);
        Parent appRoot = loader.load();
        
        Stage appStage = new Stage();
        
        appStage.setTitle("Appearance");
        
        Scene appScene = new Scene(appRoot,800,400);
        
        appStage.setScene(appScene);
        appStage.initModality(Modality.WINDOW_MODAL);
        appStage.initOwner(stage);
        appStage.show();
        graphViewBorderPane.setDisable(true);
        
        appStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

             @Override
             public void handle(WindowEvent event) {
                 graphViewBorderPane.setDisable(false);
             }
         });
        
    }
    
	
	public void loadNewCampaignClicked(){

		
		if(clickLogTextField.getText().equals("") || impressionLogTextField.getText().equals("") || serverLogTextField.getText().equals("")){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Blank Field");
				alert.setHeaderText("Error, please select all files.");
				alert.setContentText("Some fields are left blank.");
				
				alert.showAndWait();
		} else {
			
			double[] progress = new double[3];
	    
	        
	        Thread t1 = new Thread(new Runnable() {
		         public void run() {
		        	 while(!finished) {
		        		progressBarNewCampaign.setVisible(true);
		        		DoubleProperty barUpdater = new SimpleDoubleProperty(progress[0]);
		     	        progressBarNewCampaign.progressProperty().bind(barUpdater);
		     			System.out.println(progress[0]);
		     	        barUpdater.set(progress[0]);
		     			try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
						}
		     		}
		         }
		    });  
	        finished = false;
	    	t1.start();

	    	
	    
	    
	    	Thread t2 = new Thread( new Runnable() {
		         public void run() {
		        	
					ArrayList<Integer> before = dm.getCampaigns();
					dm.exportCSVs(impressionLogFile, clickLogFile, serverLogFile, campaignNameTextField.getText(), progress);
					
					ArrayList<Integer> after = dm.getCampaigns();
					after.removeAll(before);
					
					// Select campaign by its ID from the database.
					// This selects campaign for loading and returns overview metrics.
					overview = dm.selectCampaign(after.get(0));
				
					// Gets data with set date range and stores it in DataModel.
					// Must pass two Date objects (start and end) as parameters.
					dm.fetchData(fromDate, toDate);
					
					Platform.runLater(() -> {
						restrictDatePicker();
						getTotalCostOverTime(false, null, null, null, null);
						getImpressionsOverTime(false, null, null, null, null);
						getClicksOverTime(false, null, null, null, null);
						getUniquesOverTime(false, null, null, null, null);
						getBouncesOverTime(false, null, null, null, null);
						getCPMsOverTime(false, null, null, null, null);
						getTotalCostOverTime(false, null, null, null, null);
						getCTRsOverTime(false, null, null, null, null);
						getCPCsOverTime(false, null, null, null, null);
						getCPAsOverTime(false, null, null, null, null);
						getConversionsOverTime(false, null, null, null, null);
						getBounceRatesOverTime(false, null, null, null, null);
						getCPCHistogramsOverTime(false, null, null, null, null);
						
						allFilterTexts.clear();
						filterLabel1.setText("Original Graph - No Filters");
						allFilterTexts.add("Original Graph - No Filters");
						filterHBox1.setVisible(true);
						
						fromDatePicker.setOnAction(event -> {
				            LocalDate localDate = fromDatePicker.getValue();
				            fromDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				            
				        });
						
						toDatePicker.setOnAction(event -> {
				            LocalDate localDate = toDatePicker.getValue();
				            toDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				            System.out.println("localDate is" + localDate.toString());
				            System.out.println("toDate is" + toDate.toString());
				        });
						
						graphViewBorderPane.setDisable(false);
						selectCampaignBorderPane.setVisible(false);
						
						updateOverview();
					});
					finished = true;					
					
		        }
	    	});
	    	preventDatePickerDoubleClick();
	    	t2.start();
		}
		}
	
	public void preventDatePickerDoubleClick() {
		restrictDatePicker();
		fromDatePicker.setOnAction(event -> {
			
            LocalDate localDate = fromDatePicker.getValue();
            fromDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            if(fromDate == datePickerFromCheck || fromDate == initialMaxDate) {
            	fromPickerChanged = false;
            }else {
            	fromPickerChanged = true;
            	datePickerFromCheck = fromDate;
            }
        });
		
		toDatePicker.setOnAction(event -> {
            LocalDate localDate = toDatePicker.getValue();
            toDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            if(toDate == datePickerToCheck || toDate == initialMinDate) {
            	toPickerChanged = false;
            }else {
            	toPickerChanged = true;
            	datePickerToCheck = toDate;
            }
        });
	}
	
	public void loadExistingCampaignClicked(){
		
		if(selectCampaignChoiceBox.getValue().toString()!="" || selectCampaignChoiceBox.getValue().toString()!=null) {
			String campaignName = selectCampaignChoiceBox.getValue().toString();
			int campaignId = 0;
			ArrayList<String> campaignNames = new ArrayList<String>();
			for(ArrayList<Object> campaignNamesAndIds : campaignNamesArrayList) {
				String campaignNameFromList = (String) campaignNamesAndIds.get(1);
				campaignNames.add(campaignNameFromList);
				if(campaignNameFromList.equals(campaignName)){
					campaignId = (int) campaignNamesAndIds.get(0);
				}
			}
			overview = dm.selectCampaign(campaignId);
		}	
		
		// Gets data with set date range and stores it in DataModel.
		// Must pass two Date objects (start and end) as parameters.
		
		dm.fetchData(fromDate, toDate);
		restrictDatePicker();
		
		getTotalCostOverTime(false, null, null, null, null);
		getImpressionsOverTime(false, null, null, null, null);
		getClicksOverTime(false, null, null, null, null);
		getUniquesOverTime(false, null, null, null, null);
		getBouncesOverTime(false, null, null, null, null);
		getCPMsOverTime(false, null, null, null, null);
		getTotalCostOverTime(false, null, null, null, null);
		getCTRsOverTime(false, null, null, null, null);
		getCPCsOverTime(false, null, null, null, null);
		getCPAsOverTime(false, null, null, null, null);
		getConversionsOverTime(false, null, null, null, null);
		getBounceRatesOverTime(false, null, null, null, null);
		getCPCHistogramsOverTime(false, null, null, null, null);
		
		allFilterTexts.clear();
		filterLabel1.setText("Original Graph - No Filters");
		allFilterTexts.add("Original Graph - No Filters");
		filterHBox1.setVisible(true);
		
		fromDatePicker.setOnAction(event -> {
            LocalDate localDate = fromDatePicker.getValue();
            fromDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
        });
		
		toDatePicker.setOnAction(event -> {
            LocalDate localDate = toDatePicker.getValue();
            toDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            System.out.println("localDate is" + localDate.toString());
            System.out.println("toDate is" + toDate.toString());
        });
		
		graphViewBorderPane.setDisable(false);
		selectCampaignBorderPane.setVisible(false);
		
		updateOverview();
		
		preventDatePickerDoubleClick();
		deleteAllFiltersClicked();
		addFilterClicked();
		
	}
	
	public void bounceDefinitionDataEntered() {
		int bounceChoiceIndex = bounceDefinitionChoiceBox.getSelectionModel().getSelectedIndex();
		if(!bounceDefinitionTextField.getText().matches("[0-9]+")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Input");
			alert.setHeaderText("Error. Please enter a valid bounce definition number");
			alert.setContentText("Only positive numbers are valid inputs");
			
			alert.showAndWait();
		}else {
			
			int bounceSeconds = Integer.parseInt(bounceDefinitionTextField.getText());
			int bouncePages = Integer.parseInt(bounceDefinitionTextField.getText());
			
			if(bounceChoiceIndex == 0) {
				dm.bounceSeconds(bounceSeconds);
				System.out.println(1);
			}else if(bounceChoiceIndex == 1) {
				System.out.println(2);
				dm.bouncePages(bouncePages);
			}
			
			getBouncesOverTime(false, null, null, null, null);
			getBounceRatesOverTime(false, null, null, null, null);
		}
	}
	
	public void datePickerButtonClicked() {
		
		if(fromPickerChanged || toPickerChanged) {
	        System.out.println("inpicker toDate is" + toDate.toString());
			dm.fetchData(fromDate, toDate);
			
			getTotalCostOverTime(false, null, null, null, null);
			getImpressionsOverTime(false, null, null, null, null);
			getClicksOverTime(false, null, null, null, null);
			getUniquesOverTime(false, null, null, null, null);
			getBouncesOverTime(false, null, null, null, null);
			getCPMsOverTime(false, null, null, null, null);
			getTotalCostOverTime(false, null, null, null, null);
			getCTRsOverTime(false, null, null, null, null);
			getCPCsOverTime(false, null, null, null, null);
			getCPAsOverTime(false, null, null, null, null);
			getConversionsOverTime(false, null, null, null, null);
			getBounceRatesOverTime(false, null, null, null, null);
			getCPCHistogramsOverTime(false, null, null, null, null);
		}
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
	
	public void addFilterClicked() {
		if(!filterHBox1.isVisible()) {
			filterHBox1.setVisible(true);
		}else if(!filterHBox2.isVisible()) {
			filterHBox2.setVisible(true);
		}else if(!filterHBox3.isVisible()) {
			filterHBox3.setVisible(true);
		}else if(!filterHBox4.isVisible()) {
			filterHBox4.setVisible(true);
		}else if(!filterHBox5.isVisible()) {
			filterHBox5.setVisible(true);
		}else if(!filterHBox6.isVisible()) {
			filterHBox6.setVisible(true);
		}else if(!filterHBox7.isVisible()) {
			filterHBox7.setVisible(true);
		}else if(!filterHBox8.isVisible()) {
			filterHBox8.setVisible(true);
		}else if(!filterHBox9.isVisible()) {
			filterHBox9.setVisible(true);
		}else if(!filterHBox10.isVisible()) {
			filterHBox10.setVisible(true);
		}
		ArrayList<Gender> genderList = new ArrayList<Gender>();
		ArrayList<Income> incomeList = new ArrayList<Income>();
		ArrayList<Age> ageList = new ArrayList<Age>();
		ArrayList<Context> contextList = new ArrayList<Context>();
		
		ArrayList<String> genderListString = new ArrayList<String>();
		ArrayList<String> incomeListString = new ArrayList<String>();
		ArrayList<String> ageListString = new ArrayList<String>();
		ArrayList<String> contextListString = new ArrayList<String>();
		
		if(genderMaleCheckbox.isSelected()) {
			genderList.add(Gender.MALE);
			genderListString.add("Male");
		}if(genderFemaleCheckbox.isSelected()) {
			genderList.add(Gender.FEMALE);
			genderListString.add("Female");
		}if(incomeLowCheckbox.isSelected()) {
			incomeList.add(Income.LOW);
			incomeListString.add("Low");
		}if(incomeMediumCheckbox.isSelected()) {
			incomeList.add(Income.MEDIUM);
			incomeListString.add("Medium");
		}if(incomeHighCheckbox.isSelected()) {
			incomeList.add(Income.HIGH);
			incomeListString.add("High");
		}if(ageUnder25Checkbox.isSelected()) {
			ageList.add(Age.L25);
			ageListString.add("Under 25");
		}if(age25To34Checkbox.isSelected()) {
			ageList.add(Age.A25TO34);
			ageListString.add("25-34");
		}if(age35To44Checkbox.isSelected()) {
			ageList.add(Age.A35TO44);
			ageListString.add("35-44");
		}if(age45To54Checkbox.isSelected()) {
			ageList.add(Age.A45TO54);
			ageListString.add("45-54");
		}if(ageOver54Checkbox.isSelected()) {
			ageList.add(Age.M54);
			ageListString.add("Over 54");
		}if(contextNewsCheckbox.isSelected()) {
			contextList.add(Context.NEWS);
			contextListString.add("News");
		}if(contextShoppingCheckbox.isSelected()) {
			contextList.add(Context.SHOPPING);
			contextListString.add("Shopping");
		}if(contextSocialMediaCheckbox.isSelected()) {
			contextList.add(Context.SOCIAL_MEDIA);
			contextListString.add("Social Media");
		}if(contextBlogCheckbox.isSelected()) {
			contextList.add(Context.BLOG);
			contextListString.add("Blog");
		}if(contextHobbiesCheckbox.isSelected()) {
			contextList.add(Context.HOBBIES);
			contextListString.add("Hobbies");
		}if(contextTravelCheckbox.isSelected()) {
			contextList.add(Context.TRAVEL);
			contextListString.add("Travel");
		}
		String genderFilterString ="";
		String incomeFilterString ="";
		String ageFilterString ="";
		String contextFilterString ="";
		if(genderListString.size()==2) {
		} else if(genderListString.size()>0) {
			genderFilterString += "Gender: ";
			for(int i=0;i<genderListString.size(); i++) {
				genderFilterString += genderListString.get(i).toString();
			}
		}if(incomeListString.size() == 3) {
		}else if(incomeListString.size()>0) {
			incomeFilterString += "Income: ";
			for(int i=0;i<incomeListString.size(); i++) {
				incomeFilterString += (" "+incomeListString.get(i).toString());
			}
		}if(ageListString.size() == 5) {
		}else if(ageListString.size()>0) {
			ageFilterString += "Age: ";
			for(int i=0;i<ageListString.size(); i++) {
				ageFilterString += (" "+ageListString.get(i).toString());
			}
		}if(contextListString.size() == 6) {
		}else if(contextListString.size()>0) {
			contextFilterString += "Context: ";
			for(int i=0;i<contextListString.size(); i++) {
				contextFilterString += (" "+contextListString.get(i).toString());
			}
		}
		
		String filterText = genderFilterString+" | "+incomeFilterString+" | "+ageFilterString+" | "+contextFilterString;
		allFilterTexts.add(filterText);
		if(allFilterTexts.size()==1) {
			filterLabel1.setText(filterText);
		}else if(allFilterTexts.size()==2) {
			filterLabel2.setText(filterText);
		}else if(allFilterTexts.size()==3) {
			filterLabel3.setText(filterText);
		}else if(allFilterTexts.size()==4) {
			filterLabel4.setText(filterText);
		}else if(allFilterTexts.size()==5) {
			filterLabel5.setText(filterText);
		}else if(allFilterTexts.size()==6) {
			filterLabel6.setText(filterText);
		}else if(allFilterTexts.size()==7) {
			filterLabel7.setText(filterText);
		}else if(allFilterTexts.size()==8) {
			filterLabel8.setText(filterText);
		}else if(allFilterTexts.size()==9) {
			filterLabel9.setText(filterText);
		}else if(allFilterTexts.size()==10) {
			filterLabel10.setText(filterText);
		}
		getTotalCostOverTime(true, genderList, ageList, incomeList, contextList);
		getImpressionsOverTime(true, genderList, ageList, incomeList, contextList);
		getClicksOverTime(true, genderList, ageList, incomeList, contextList);
		getUniquesOverTime(true, genderList, ageList, incomeList, contextList);
		getConversionsOverTime(true, genderList, ageList, incomeList, contextList);
		getBouncesOverTime(true, genderList, ageList, incomeList, contextList);
		getBounceRatesOverTime(true, genderList, ageList, incomeList, contextList);
		getCPMsOverTime(true, genderList, ageList, incomeList, contextList);
		getCTRsOverTime(true, genderList, ageList, incomeList, contextList);
		getCPAsOverTime(true, genderList, ageList, incomeList, contextList);
		getCPCsOverTime(true, genderList, ageList, incomeList, contextList);
		getCPCHistogramsOverTime(true, genderList, ageList, incomeList, contextList);
		
	}
	
	public void removeGraphs(int index) {
		totalCostGraph.getData().remove(index);
		impressionGraph.getData().remove(index);
		clickGraph.getData().remove(index);
		uniqueGraph.getData().remove(index);
		conversionsGraph.getData().remove(index);
		bounceGraph.getData().remove(index);
		bounceRateGraph.getData().remove(index);
		CPMGraph.getData().remove(index);
		CTRGraph.getData().remove(index);
		CPAGraph.getData().remove(index);
		CPCGraph.getData().remove(index);
		CPCHistogram.getData().remove(index);
	}
	
	public void deleteFilter1Clicked() {
		filterHBox1.setVisible(false);
		allFilterTexts.remove(0);
		removeGraphs(0);
	}
	public void deleteFilter2Clicked() {
		filterHBox2.setVisible(false);
		allFilterTexts.remove(1);
		removeGraphs(1);
	}
	public void deleteFilter3Clicked() {
		filterHBox3.setVisible(false);
		allFilterTexts.remove(2);
		removeGraphs(2);
	}
	public void deleteFilter4Clicked() {
		filterHBox4.setVisible(false);
		allFilterTexts.remove(3);
		removeGraphs(3);
	}
	public void deleteFilter5Clicked() {
		filterHBox5.setVisible(false);
		allFilterTexts.remove(4);
		removeGraphs(4);
	}
	public void deleteFilter6Clicked() {
		filterHBox6.setVisible(false);
		allFilterTexts.remove(5);
		removeGraphs(5);
	}
	public void deleteFilter7Clicked() {
		filterHBox7.setVisible(false);
		allFilterTexts.remove(6);
		removeGraphs(6);
	}
	public void deleteFilter8Clicked() {
		filterHBox8.setVisible(false);
		allFilterTexts.remove(7);
		removeGraphs(7);
	}
	public void deleteFilter9Clicked() {
		filterHBox9.setVisible(false);
		allFilterTexts.remove(8);
		removeGraphs(8);
	}
	public void deleteFilter10Clicked() {
		filterHBox10.setVisible(false);
		allFilterTexts.remove(9);
		removeGraphs(9);
	}
	
	public void deleteAllFiltersClicked() {

		filterHBox1.setVisible(false);
		filterHBox2.setVisible(false);
		filterHBox3.setVisible(false);
		filterHBox4.setVisible(false);
		filterHBox5.setVisible(false);
		filterHBox6.setVisible(false);
		filterHBox7.setVisible(false);
		filterHBox8.setVisible(false);
		filterHBox9.setVisible(false);
		filterHBox10.setVisible(false);
		
		allFilterTexts.clear();
		
		totalCostGraph.getData().clear();
		impressionGraph.getData().clear();
		clickGraph.getData().clear();
		uniqueGraph.getData().clear();
		conversionsGraph.getData().clear();
		bounceGraph.getData().clear();
		bounceRateGraph.getData().clear();
		CPMGraph.getData().clear();
		CTRGraph.getData().clear();
		CPAGraph.getData().clear();
		CPCGraph.getData().clear();
		CPCHistogram.getData().clear();

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
	
	public void getImpressionsOverTime(boolean isFilter, List<Gender> gender, List<Age> age, List<Income> income, List<Context> context){
		if(!isFilter) {
			DataFilter df = new DataFilter(Metric.IMPRESSIONS,null,null,null,null);
			impressionSeries = dm.getSeries(df, Granularity.HOURLY);
			impressionGraph.setLegendVisible(false);
			impressionYAxis.setLabel("Impressions");
			impressionXAxis.setLabel("Time (Date)");
		}else {
			DataFilter df = new DataFilter(Metric.IMPRESSIONS,gender,age,income,context);
			impressionSeries = dm.getSeries(df, Granularity.HOURLY);
			impressionGraph.setLegendVisible(false);
			impressionYAxis.setLabel("Impressions");
			impressionXAxis.setLabel("Time (Date)");
		}
		
		impressionGraph.getData().add(impressionSeries);
	}
	
	public void getClicksOverTime(boolean isFilter, List<Gender> gender, List<Age> age, List<Income> income, List<Context> context){
		if(!isFilter) {
			DataFilter df = new DataFilter(Metric.CLICKS,null,null,null,null);
			clickSeries = dm.getSeries(df, Granularity.HOURLY);
			clickGraph.setLegendVisible(false);
			clickYAxis.setLabel("Clicks");
			clickXAxis.setLabel("Time (Date)");
		}else {
			DataFilter df = new DataFilter(Metric.CLICKS,gender,age,income,context);
			clickSeries = dm.getSeries(df, Granularity.HOURLY);
			clickGraph.setLegendVisible(false);
			clickYAxis.setLabel("Clicks");
			clickXAxis.setLabel("Time (Date)");
		}
		
		clickGraph.getData().add(clickSeries);
	}
	

	
	public void getCPMsOverTime(boolean isFilter, List<Gender> gender, List<Age> age, List<Income> income, List<Context> context){
		if(!isFilter) {
			DataFilter df = new DataFilter(Metric.CPM,null,null,null,null);
			CPMSeries = dm.getSeries(df, Granularity.HOURLY);
			CPMGraph.setLegendVisible(false);
			CPMYAxis.setLabel("CPM");
			CPMXAxis.setLabel("Time (Date)");
		}else {
			DataFilter df = new DataFilter(Metric.CPM,gender,age,income,context);
			CPMSeries = dm.getSeries(df, Granularity.HOURLY);
			CPMGraph.setLegendVisible(false);
			CPMYAxis.setLabel("CPM");
			CPMXAxis.setLabel("Time (Date)");
		}
		
		CPMYAxis.setAutoRanging(false);
		CPMYAxis.setLowerBound(0);
		CPMYAxis.setUpperBound(2.0);
		CPMYAxis.setTickUnit(0.05);
		CPMGraph.getData().add(CPMSeries);
	
	}
	
	public void getCPCsOverTime(boolean isFilter, List<Gender> gender, List<Age> age, List<Income> income, List<Context> context){
		if(!isFilter) {
			DataFilter df = new DataFilter(Metric.CPC,null,null,null,null);
			CPCSeries = dm.getSeries(df, Granularity.HOURLY);
			CPCGraph.setLegendVisible(false);
			CPCYAxis.setLabel("CPC");
			CPCXAxis.setLabel("Time (Date)");
		}else {
			DataFilter df = new DataFilter(Metric.CPC,gender,age,income,context);
			CPCSeries = dm.getSeries(df, Granularity.HOURLY);
			CPCGraph.setLegendVisible(false);
			CPCYAxis.setLabel("CPC");
			CPCXAxis.setLabel("Time (Date)");
		}
		
		CPCGraph.getData().add(CPCSeries);
	
	}
	
	public void getCTRsOverTime(boolean isFilter, List<Gender> gender, List<Age> age, List<Income> income, List<Context> context){
		if(!isFilter) {
			DataFilter df = new DataFilter(Metric.CTR,null,null,null,null);
			CTRSeries = dm.getSeries(df, Granularity.HOURLY);
			CTRGraph.setLegendVisible(false);
			CTRYAxis.setLabel("CTR");
			CTRXAxis.setLabel("Time (Date)");
		}else {
			DataFilter df = new DataFilter(Metric.CTR,gender,age,income,context);
			CTRSeries = dm.getSeries(df, Granularity.HOURLY);
			CTRGraph.setLegendVisible(false);
			CTRYAxis.setLabel("CTR");
			CTRXAxis.setLabel("Time (Date)");
		}
		
		CTRGraph.getData().add(CTRSeries);
	
	}
	
	public void getCPAsOverTime(boolean isFilter, List<Gender> gender, List<Age> age, List<Income> income, List<Context> context){
		if(!isFilter) {
			DataFilter df = new DataFilter(Metric.CPA,null,null,null,null);
			CPASeries = dm.getSeries(df, Granularity.HOURLY);
			CPAGraph.setLegendVisible(false);
			CPAYAxis.setLabel("CPA");
			CPAXAxis.setLabel("Time (Date)");
		}else {
			DataFilter df = new DataFilter(Metric.CPA,gender,age,income,context);
			CPASeries = dm.getSeries(df, Granularity.HOURLY);
			CPAGraph.setLegendVisible(false);
			CPAYAxis.setLabel("CPA");
			CPAXAxis.setLabel("Time (Date)");
		}
		CPAGraph.getData().add(CPASeries);
	
	}
	
	public void getConversionsOverTime(boolean isFilter, List<Gender> gender, List<Age> age, List<Income> income, List<Context> context){
	if(!isFilter) {
		DataFilter df = new DataFilter(Metric.CONVERSIONS,null,null,null,null);
		conversionsSeries = dm.getSeries(df, Granularity.HOURLY);
		conversionsGraph.setLegendVisible(false);
		conversionsYAxis.setLabel("Conversions");
		conversionsXAxis.setLabel("Time (Date)");
	}else {
		DataFilter df = new DataFilter(Metric.CONVERSIONS,gender,age,income,context);
		conversionsSeries = dm.getSeries(df, Granularity.HOURLY);
		conversionsGraph.setLegendVisible(false);
		conversionsYAxis.setLabel("Conversions");
		conversionsXAxis.setLabel("Time (Date)");
	}
	
	conversionsGraph.getData().add(conversionsSeries);
	
	}
	
	public void getTotalCostOverTime(boolean isFilter, List<Gender> gender, List<Age> age, List<Income> income, List<Context> context){
		if(!isFilter) {
			DataFilter df = new DataFilter(Metric.TOTAL_COST,null,null,null,null);
			totalCostSeries = dm.getSeries(df, Granularity.HOURLY);
			totalCostGraph.setLegendVisible(false);
			totalCostYAxis.setLabel("Total Cost");
			totalCostXAxis.setLabel("Time (Date)");
		}else {
			DataFilter df = new DataFilter(Metric.TOTAL_COST,gender,age,income,context);
			totalCostSeries = dm.getSeries(df, Granularity.HOURLY);
			totalCostGraph.setLegendVisible(false);
			totalCostYAxis.setLabel("Total Cost");
			totalCostXAxis.setLabel("Time (Date)");
		}
		
		totalCostGraph.getData().add(totalCostSeries);
	
	}
	
	public void getUniquesOverTime(boolean isFilter, List<Gender> gender, List<Age> age, List<Income> income, List<Context> context){
		if(!isFilter) {
			DataFilter df = new DataFilter(Metric.UNIQUES,null,null,null,null);
			uniqueSeries = dm.getSeries(df, Granularity.HOURLY);
			uniqueGraph.setLegendVisible(false);
			uniqueYAxis.setLabel("Uniques");
			uniqueXAxis.setLabel("Time (Date)");
		}else {
			DataFilter df = new DataFilter(Metric.UNIQUES,gender,age,income,context);
			uniqueSeries = dm.getSeries(df, Granularity.HOURLY);
			uniqueGraph.setLegendVisible(false);
			uniqueYAxis.setLabel("Uniques");
			uniqueXAxis.setLabel("Time (Date)");
		}
		uniqueGraph.getData().add(uniqueSeries);
	}
	
	public void getBouncesOverTime(boolean isFilter, List<Gender> gender, List<Age> age, List<Income> income, List<Context> context){
		if(!isFilter) {
			DataFilter df = new DataFilter(Metric.BOUNCES,null,null,null,null);
			bounceSeries = dm.getSeries(df, Granularity.HOURLY);
			bounceGraph.setLegendVisible(false);
			bounceYAxis.setLabel("Bounces");
			bounceXAxis.setLabel("Time (Date)");
		}else {
			DataFilter df = new DataFilter(Metric.BOUNCES,gender,age,income,context);
			bounceSeries = dm.getSeries(df, Granularity.HOURLY);
			bounceGraph.setLegendVisible(false);
			bounceYAxis.setLabel("Bounces");
			bounceXAxis.setLabel("Time (Date)");
		}
		bounceGraph.getData().add(bounceSeries);
		
	}
	
	public void getBounceRatesOverTime(boolean isFilter, List<Gender> gender, List<Age> age, List<Income> income, List<Context> context){
		if(!isFilter) {
			DataFilter df = new DataFilter(Metric.BOUNCE_RATE,null,null,null,null);
			bounceRateSeries = dm.getSeries(df, Granularity.HOURLY);
			bounceRateGraph.setLegendVisible(false);
			bounceRateYAxis.setLabel("Bounce Rate");
			bounceRateXAxis.setLabel("Time (Date)");
		}else {
			DataFilter df = new DataFilter(Metric.BOUNCE_RATE,gender,age,income,context);
			bounceRateSeries = dm.getSeries(df, Granularity.HOURLY);
			bounceRateGraph.setLegendVisible(false);
			bounceRateYAxis.setLabel("Bounce Rate");
			bounceRateXAxis.setLabel("Time (Date)");
		}
		bounceRateGraph.getData().add(bounceRateSeries);
	}
	
	public void getCPCHistogramsOverTime(boolean isFilter, List<Gender> gender, List<Age> age, List<Income> income, List<Context> context){
		if(!isFilter) {
			DataFilter df = new DataFilter(Metric.CPC,null,null,null,null);
			CPCSeries = dm.getSeries(df, Granularity.HOURLY);
			CPCHistogram.setLegendVisible(false);
			CPCHistogramYAxis.setLabel("Frequency Density");
			CPCHistogramXAxis.setLabel("Time (Date)");
		}else {
			DataFilter df = new DataFilter(Metric.CPC,gender,age,income,context);
			CPCSeries = dm.getSeries(df, Granularity.HOURLY);
			CPCHistogram.setLegendVisible(false);
			CPCHistogramYAxis.setLabel("Frequency Density");
			CPCHistogramXAxis.setLabel("Time (Date)");
		}
		CPCHistogram.getData().clear();
		CPCHistogram.getData().add(CPCSeries);
	
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
        
        
        WritableImage img1 = new WritableImage((int)toolbar.getWidth(),(int)toolbar.getHeight());
        toolbar.snapshot(null, img1);
        
        WritableImage img2 = new WritableImage((int)mainStackPane.getWidth(),(int)mainStackPane.getHeight());
        mainStackPane.snapshot(null,img2);
        
        WritableImage img3 = new WritableImage((int)filterPane.getWidth(),(int)filterPane.getHeight());
        filterPane.snapshot(null, img3);
        
        int totalHeight = (int) (img1.getHeight() + img2.getHeight() + img3.getHeight());
        int totalWidth = (int) img1.getWidth();
        
        BufferedImage fullImage = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = fullImage.createGraphics();
        
        g2d.drawImage(SwingFXUtils.fromFXImage(img1, null), 0, 0, null);
        g2d.drawImage(SwingFXUtils.fromFXImage(img2, null), 0, (int)img1.getHeight(), null);
        g2d.drawImage(SwingFXUtils.fromFXImage(img3, null), 0, (int)(img1.getHeight()+img2.getHeight()), null);
        g2d.dispose();
        
        FileChooser saveDialog = new FileChooser();
        FileChooser.ExtensionFilter extension = new FileChooser.ExtensionFilter("PNG image", "*.png");
        saveDialog.getExtensionFilters().add(extension);
        saveDialog.setTitle("Save image");
        File file = saveDialog.showSaveDialog(stage);
        
        try {
            ImageIO.write(fullImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
            
    }
	

}
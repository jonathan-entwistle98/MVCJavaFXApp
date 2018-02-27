import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;



public class Controller {
	
//	@FXML
//	private HBox headerDashboard;
//	@FXML
//	private VBox leftAreaDashboard;
//	@FXML
//	private TextFlow metricsText;
//	@FXML
//	private VBox bodyDashboard;
//	@FXML
//	private HBox footerDashboard;
//	@FXML
//	private Label sidebarLabel1;
//	@FXML
//	private Label sidebarLabel2;	
//	@FXML
//	private Label sidebarLabel3;	
//	@FXML
//	private Label sidebarLabel4;	
//	@FXML
//	private Label sidebarLabel5;	
//	@FXML
//	private Label sidebarLabel6;	
//	@FXML
//	private Label sidebarLabel7;	
//	@FXML
//	private Label sidebarLabel8;	
//	@FXML
//	private Label sidebarLabel9;	
//	@FXML
//	private Label sidebarLabel10;
//	@FXML
//	private Label sidebarLabel11;
	
	private Model model;
	
	private ArrayList<Object> allMetrics = new ArrayList<Object>();
	
	//Not sure if initialise method is needed
	public void initialize(){	
	}
	
	public void setUp(Model model){
		this.model = model;
		allMetrics.add(calculateNumUniqueUserClicks());
		allMetrics.add(calculateNumClicksConverted());
		allMetrics.add(calculateCampaignCost());
		allMetrics.add(calculateCTR());
		allMetrics.add(calculateCPA());
		allMetrics.add(calculateCPC());
		allMetrics.add(calculateCPM());
		allMetrics.add(calculateNumberOfBounces());
		allMetrics.add(calculateBounceRate());
	}

//	/**
//	 * Uses data from model class to set all required metrics to be added to the sidebar in the view
//	 * @param model
//	 */
//	public void initModel(Model model) {
//		this.model = model;
//
//		sidebarLabel1.setText("Number of Clicks: " + (model.clickLogList.size()-1));
//		sidebarLabel2.setText("Number of Impressions: " + (model.impressionLogList.size()-1));
//		sidebarLabel3.setText("Number of unique user clicks: " + calculateNumUniqueUserClicks());
//		sidebarLabel4.setText("Number of conversions: " + calculateNumClicksConverted());
//		sidebarLabel5.setText("Total cost of campaign: " + calculateCampaignCost());
//		sidebarLabel6.setText("Click-through-rate (CTR): " + calculateCTR());
//		sidebarLabel7.setText("Cost-per-aquisition (CPA): " + calculateCPA());
//		sidebarLabel8.setText("Cost-per-click (CPC): " + calculateCPC());
//		sidebarLabel9.setText("Cost per-thousand-impressions (CPM): " + calculateCPM());
//		sidebarLabel10.setText("Number of bounces: " + calculateNumberOfBounces());
//		sidebarLabel11.setText("Bounce rate: " + calculateBounceRate());
//	}
	/**
	 * @return number of unique user clicks
	 */
	public int calculateNumUniqueUserClicks(){
		int totalClicks = model.clickLogList.size();
		
		ArrayList<String> usersIDList = new ArrayList<String>();
		//for loop starts at id=1 so that the header text "ID" is not added as an actual id
		for(int i=1; i<totalClicks; i++){
			String[] splitValues = model.clickLogList.get(i).split(",");
		
			if(splitValues[1] != null){
				usersIDList.add(splitValues[1]);	
			}
		}
		
		HashSet hashset = new HashSet(usersIDList);
		usersIDList.clear();
		usersIDList.addAll(hashset);
		
		return usersIDList.size();
	}
	
	/**
	 * @return number of clicks converted
	 */
	public int calculateNumClicksConverted(){
		int totalClicks = model.serverLogList.size();
		int clicksWithConversions = 0;
		for(int i=0; i<totalClicks; i++){
				String[] splitValues = model.serverLogList.get(i).split(",");
			
			if(splitValues[4].equals("Yes")){
				String checkValue = splitValues[4];
				clicksWithConversions++;
			}
		}
		return clicksWithConversions;
	}
	
	//Not sure about this - I assume the total cost is the sum of impression costs and click costs
	/**
	 * @return total ad campaign cost
	 */
	public Float calculateCampaignCost(){
		int totalClicks = model.clickLogList.size();
		Float sumClickCost = 0.0f;
		ArrayList<Float> clickCosts = new ArrayList<Float>();
		
		for(int i=0; i<totalClicks; i++){
			String[] splitValues = model.clickLogList.get(i).split(",");
		
			if(splitValues[2] != null && !splitValues[2].equals("Click Cost")){
				clickCosts.add(Float.parseFloat(splitValues[2]));	
			}
		}
		
		for(int k=0; k<clickCosts.size(); k++){
			sumClickCost += clickCosts.get(k);
		}
		
		
		return sumClickCost;
	}
	
	/**
	 * @return click through rate (CTR)
	 */
	public double calculateCTR(){
		
		double numImpressions = model.impressionLogList.size()-1;
		double numClicks = model.clickLogList.size()-1;
		
		return numClicks/numImpressions;
	}
	
	/**
	 * @return cost per acquisition (CPA)
	 */
	public double calculateCPA(){
		
		int numClicksConverted = calculateNumClicksConverted();
		Float campaignCost = calculateCampaignCost();
		
		return campaignCost/numClicksConverted;
	}
	
	/**
	 * @return cost per click (CPC)
	 */
	public double calculateCPC(){
		
		int totalClicks = model.clickLogList.size()-1;
		Float campaignCost = calculateCampaignCost();
		
		return campaignCost/totalClicks;
	}
	
	/**
	 * @return cost per thousand clicks (CPM)
	 */
	public double calculateCPM(){
		
		int totalImpressions = model.impressionLogList.size()-1;
		Float campaignCost = calculateCampaignCost();
		double costPerImpression = campaignCost/totalImpressions;
		return costPerImpression*1000;
	}	
	
	//I define a bounce as a user who spends less than 2 minutes on the site
	//More parameters (e.g. the number of pages visited) could be added to this method later
	//If exit date is N/A I assign a date 2015-01-31 (in the far future compared to other dates), 
	//so a bounce is not registered in these N/A cases
	/**
	 * Calculates the number of bounces, where a bounce as a user who spends less than 2 minutes on the site
	 * @return number of bounces
	 */
	public int calculateNumberOfBounces(){
//		int numberOfBounces = 0;
		int bouncesCount = 0;
		int totalClicks = model.serverLogList.size();
//		ArrayList<String> entryDates = new ArrayList<String>();
//		ArrayList<String> exitDates = new ArrayList<String>();
//		ArrayList<Integer> pagesViewed = new ArrayList<Integer>();
		
		for(int i=1; i<totalClicks; i++){
			String[] splitValues = model.serverLogList.get(i).split(",");
		    
			if (Integer.parseInt(splitValues[3]) == 1) {
				bouncesCount++;
				
			} else {
					if(splitValues[0] != null && splitValues[2] != null){
	
						String entry = splitValues[0];	
						String exit_ = splitValues[2];
						String[] split = entry.split(" ");
						String entryDate = split[0];
						String entryTime = split[1];
						String[] entryDatesplit = entryDate.split("-");
						String[] entryTimesplit = entryTime.split(":");
					    int entryYear = Integer.parseInt(entryDatesplit[0]);
					    int entryMonth = Integer.parseInt(entryDatesplit[1]);
					    int entryDay = Integer.parseInt(entryDatesplit[2]);
					    int entryHour = Integer.parseInt(entryTimesplit[0]);
					    int entryMinute = Integer.parseInt(entryTimesplit[1]);
					    int entrySecond = Integer.parseInt(entryTimesplit[2]);
					    @SuppressWarnings("deprecation")
						int entryDateInt = (int) new Date(entryYear, entryMonth, entryDay, entryHour,entryMinute, entrySecond).getTime();
					    String[] split_ = exit_.split(" ");
						String exitDate = split_[0];
						String exitTime = split_[1];
						String[] exitDatesplit = exitDate.split("-");
						String[] exitTimesplit = exitTime.split(":");
					    int exitYear = Integer.parseInt(exitDatesplit[0]);
					    int exitMonth = Integer.parseInt(exitDatesplit[1]);
					    int exitDay = Integer.parseInt(exitDatesplit[2]);
					    int exitHour = Integer.parseInt(exitTimesplit[0]);
					    int exitMinute = Integer.parseInt(exitTimesplit[1]);
					    int exitSecond = Integer.parseInt(exitTimesplit[2]);
					    @SuppressWarnings("deprecation")
						int exitDateInt = (int) new Date(exitYear, exitMonth, exitDay, exitHour,exitMinute, exitSecond).getTime();
					   
					    if ((exitDateInt-entryDateInt)<12000) {
					    	bouncesCount++;
					    }
					    
					}
			
			}
			
		
			
		}
		
		return bouncesCount;
	}
	
	public double calculateBounceRate() {
		
		int totalClicks = model.clickLogList.size()-1;
		int totalBounces = calculateNumberOfBounces();
		
		return totalBounces*1.0/totalClicks;
	}
	
	public ArrayList<Object> getAllMetrics(){
		return allMetrics;
	}
	

}

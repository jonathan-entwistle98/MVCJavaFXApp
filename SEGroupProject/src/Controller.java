import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

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
	private Label sidebarLabel1;
	@FXML
	private Label sidebarLabel2;	
	@FXML
	private Label sidebarLabel3;	
	@FXML
	private Label sidebarLabel4;	
	@FXML
	private Label sidebarLabel5;	
	@FXML
	private Label sidebarLabel6;	
	@FXML
	private Label sidebarLabel7;	
	@FXML
	private Label sidebarLabel8;	
	@FXML
	private Label sidebarLabel9;	
	@FXML
	private Label sidebarLabel10;
	
	private Model model;
	
	//Not sure if initialise method is needed
	public void initialize(){
	}

	public void initModel(Model model) {
		this.model = model;

		sidebarLabel1.setText("Number of Clicks: " + (model.clickLogList.size()-1));
		sidebarLabel2.setText("Number of Impressions: " + (model.impressionLogList.size()-1));
		sidebarLabel3.setText("Number of unique user clicks: " + calculateNumUniqueUserClicks());
		sidebarLabel4.setText("Number of conversions: " + calculateNumClicksConverted());
		sidebarLabel5.setText("Total cost of campaign: " + calculateCampaignCost());
		sidebarLabel6.setText("Click-through-rate (CTR): " + calculateCTR());
		sidebarLabel7.setText("Cost-per-aquisition (CPA): " + calculateCPA());
		sidebarLabel8.setText("Cost-per-click (CPC): " + calculateCPC());
		sidebarLabel9.setText("Cost per-thousand-impressions (CPM): " + calculateCPM());
		sidebarLabel10.setText("Number of bounces: " + calculateNumberOfBounces());
	}
	
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
	
	public double calculateCTR(){
		
		double numImpressions = model.impressionLogList.size()-1;
		double numClicks = model.clickLogList.size()-1;
		
		return numClicks/numImpressions;
	}
	
	public double calculateCPA(){
		
		int numClicksConverted = calculateNumClicksConverted();
		Float campaignCost = calculateCampaignCost();
		
		return campaignCost/numClicksConverted;
	}
	
	public double calculateCPC(){
		
		int totalClicks = model.clickLogList.size()-1;
		Float campaignCost = calculateCampaignCost();
		
		return campaignCost/totalClicks;
	}
	
	public double calculateCPM(){
		
		int totalImpressions = model.impressionLogList.size()-1;
		Float campaignCost = calculateCampaignCost();
		double costPerImpression = campaignCost/totalImpressions;
		return costPerImpression*1000;
	}
	
	//I define a bounce as a user who spends less than 2 minutes on the site while viewing more than 1 page,
	//or less than 5 minutes while only viewing 1 page
	public int calculateNumberOfBounces(){
		int numberOfBounces = 0;
		int totalClicks = model.serverLogList.size();
		ArrayList<String> entryDates = new ArrayList<String>();
		ArrayList<String> exitDates = new ArrayList<String>();
		ArrayList<Integer> pagesViewed = new ArrayList<Integer>();
		
		for(int i=1; i<totalClicks; i++){
			String[] splitValues = model.serverLogList.get(i).split(",");
		
			if(splitValues[0] != null){
				entryDates.add(splitValues[0]);	
			}
			if(splitValues[2] != null){
				exitDates.add(splitValues[2]);
			}
			if(splitValues[3] != null){
				pagesViewed.add(Integer.parseInt(splitValues[3]));
			}
		}
		
		
		
		
		ArrayList<String> entryDatesDate = new ArrayList<String>();
		ArrayList<String> entryDatesTime = new ArrayList<String>();
		
		for(String dateEntry : entryDates){
			String[] dateSplit = dateEntry.split(" ");
			entryDatesDate.add(dateSplit[0]);
			entryDatesTime.add(dateSplit[1]);
		}
		
		ArrayList<String> entryDatesDay = new ArrayList<String>();
		ArrayList<String> entryDatesMonth = new ArrayList<String>();
		ArrayList<String> entryDatesYear = new ArrayList<String>();
		
		for(String pieceOfDate : entryDatesDate){
			String[] dateSplit = pieceOfDate.split("-");
			entryDatesDay.add(dateSplit[0]);
			entryDatesMonth.add(dateSplit[1]);
			entryDatesYear.add(dateSplit[2]);
		}
		
		ArrayList<String> entryDatesMinute = new ArrayList<String>();
		ArrayList<String> entryDatesHour = new ArrayList<String>();
		
		for(String timeEntry : entryDatesTime){
			String[] dateSplit = timeEntry.split(":");
			entryDatesHour.add(dateSplit[0]);
			entryDatesMinute.add(dateSplit[1]);
		}
		
//		ArrayList<Calendar> entryCalendarDates = new ArrayList<Calendar>();
		ArrayList<ArrayList<Integer>> entryCalendarDates = new ArrayList<ArrayList<Integer>>();
		
		for(int i=0; i<entryDatesYear.size(); i++){
			
			Calendar calendar = Calendar.getInstance();
			ArrayList<Integer> datesAndTimes = new ArrayList<Integer>();
			calendar.clear();
			TimeZone tz = TimeZone.getTimeZone("Europe/London") ;
			calendar.setTimeZone(tz);
//			calendar.set(Calendar.ERA, GregorianCalendar.AD);
			
//			calendar.set(0, Integer.parseInt(entryDatesYear.get(i)));
//			calendar.set(1, Integer.parseInt(entryDatesMonth.get(i)));
//			calendar.set(2, Integer.parseInt(entryDatesDay.get(i)));
//			calendar.set(3, Integer.parseInt(entryDatesHour.get(i)));
//			calendar.set(4, Integer.parseInt(entryDatesMinute.get(i)));
			
			datesAndTimes.add(Integer.parseInt(entryDatesYear.get(i)));
			datesAndTimes.add(Integer.parseInt(entryDatesMonth.get(i)));
			datesAndTimes.add(Integer.parseInt(entryDatesDay.get(i)));
			datesAndTimes.add(Integer.parseInt(entryDatesHour.get(i)));
			datesAndTimes.add(Integer.parseInt(entryDatesMinute.get(i)));
			
			
			
//			entryCalendarDates.add(calendar);
			
			entryCalendarDates.add(datesAndTimes);
		}
		
		
		
		
		
		ArrayList<String> exitDatesDate = new ArrayList<String>();
		ArrayList<String> exitDatesTime = new ArrayList<String>();
		
		for(String dateExit : exitDates){
			
			String defaultExitDate = "2015-31-01";
			String defaultExitTime = "23:00";
			
			if(dateExit.equals("n/a") || dateExit.equals("n-a")){
				exitDatesDate.add(defaultExitDate);
				exitDatesTime.add(defaultExitTime);
			} else{
				String[] dateSplit = dateExit.split(" ");
				exitDatesDate.add(dateSplit[0]);
				exitDatesTime.add(dateSplit[1]);
			}
			
			
		}
		
		ArrayList<String> exitDatesDay = new ArrayList<String>();
		ArrayList<String> exitDatesMonth = new ArrayList<String>();
		ArrayList<String> exitDatesYear = new ArrayList<String>();
		
		for(String pieceOfDate : exitDatesDate){
			String[] dateSplit = pieceOfDate.split("-");
			exitDatesDay.add(dateSplit[0]);
			exitDatesMonth.add(dateSplit[1]);
			exitDatesYear.add(dateSplit[2]);
		}
		
		ArrayList<String> exitDatesMinute = new ArrayList<String>();
		ArrayList<String> exitDatesHour = new ArrayList<String>();
		
		for(String timeExit : exitDatesTime){
			String[] dateSplit = timeExit.split(":");
			exitDatesHour.add(dateSplit[0]);
			exitDatesMinute.add(dateSplit[1]);
		}
		
//		ArrayList<Calendar> exitCalendarDates = new ArrayList<Calendar>();
		ArrayList<ArrayList<Integer>> exitCalendarDates = new ArrayList<ArrayList<Integer>>();
		
		for(int i=0; i<exitDatesYear.size(); i++){
			
			Calendar calendar = Calendar.getInstance();
			ArrayList<Integer> datesAndTimes = new ArrayList<Integer>();
			calendar.clear();
			TimeZone tz = TimeZone.getTimeZone("Europe/London") ;
			calendar.setTimeZone(tz);
//			calendar.set(Calendar.ERA, GregorianCalendar.AD);
			
//			calendar.set(0, Integer.parseInt(exitDatesYear.get(i)));
//			calendar.set(1, Integer.parseInt(exitDatesMonth.get(i)));
//			calendar.set(2, Integer.parseInt(exitDatesDay.get(i)));
//			calendar.set(3, Integer.parseInt(exitDatesHour.get(i)));
//			calendar.set(4, Integer.parseInt(exitDatesMinute.get(i)));
			
			datesAndTimes.add(Integer.parseInt(exitDatesYear.get(i)));
			datesAndTimes.add(Integer.parseInt(exitDatesMonth.get(i)));
			datesAndTimes.add(Integer.parseInt(exitDatesDay.get(i)));
			datesAndTimes.add(Integer.parseInt(exitDatesHour.get(i)));
			datesAndTimes.add(Integer.parseInt(exitDatesMinute.get(i)));
			
			
//			exitCalendarDates.add(calendar);
			
			exitCalendarDates.add(datesAndTimes);
		}
		
		int bouncesCount = 0;
		
		for(int i=0; i<entryCalendarDates.size(); i++){
			System.out.println("CP1");
			
			int test = entryCalendarDates.get(2).get(0);
			//int minutes = (int) ChronoUnit.MINUTES.between(entryCalendarDates.get(2).getTime(), exitCalendarDates.get(2).getTime());
			
			//Date testDate = entryCalendarDates.get(2).getTime();
			
			//long minutes = getDateDiff(entryCalendarDates.get(2).getTime(),exitCalendarDates.get(2).getTime(),TimeUnit.MINUTES);
	
			LocalDateTime ldtEntry = LocalDateTime.of(entryCalendarDates.get(i).get(2), entryCalendarDates.get(i).get(0), entryCalendarDates.get(i).get(1), entryCalendarDates.get(i).get(3), entryCalendarDates.get(i).get(4), 00);			
			LocalDateTime ldtExit = LocalDateTime.of(exitCalendarDates.get(i).get(2), exitCalendarDates.get(i).get(0), exitCalendarDates.get(i).get(1), exitCalendarDates.get(i).get(3), exitCalendarDates.get(i).get(4), 00);

			
			System.out.println("CP2");
			
			long minutes = ChronoUnit.MINUTES.between(ldtEntry, ldtExit);
			
			
		//	long seconds = (entryCalendarDates.get(2).getTimeInMillis() - exitCalendarDates.get(2).getTimeInMillis()) / 1000;
		//	int minutes = (int) (seconds / 60);
			
			System.out.println("CP3");
			if(minutes<2){
				bouncesCount++;
			}
		}
		
		
		return bouncesCount;
	}
	
	/**
	 * Get a diff between two dates
	 * @param date1 the oldest date
	 * @param date2 the newest date
	 * @param timeUnit the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	

}

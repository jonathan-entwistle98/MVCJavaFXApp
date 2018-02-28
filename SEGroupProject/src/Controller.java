

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class Controller {

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

	@FXML
	void setGraphVisible(ActionEvent event) {
		if(Histogram.isVisible()){
			Histogram.setVisible(false);
			Graph.setVisible(true);
		}
		Graph.setVisible(true);
	}

	@FXML
	void setHistogramVisible(ActionEvent event) {
		if(Graph.isVisible()){
			Graph.setVisible(false);
			Histogram.setVisible(true);
		}
		Histogram.setVisible(true);
	}

	public Model model;

	public void initialize() {

	}

	public void initModel(Model model) {
		this.model = model;

		sidebarLabel1.setText("No. of clicks: " + String.valueOf(model.clickLogList.size() - 1));
		sidebarLabel2.setText("No. of impressions: " + String.valueOf(model.impressionLogList.size() - 1));
		sidebarLabel3.setText("No. of unique clicks: " + String.valueOf(calculateNumUniqueUserClicks()));
		sidebarLabel4.setText("No. of clicks converted: " + String.valueOf(calculateNumClicksConverted()));
		sidebarLabel5.setText("Total campaign cost: " + String.valueOf(calculateCampaignCost()));
		sidebarLabel6.setText("Click-through-rate (CTR): " + String.valueOf(calculateCTR()));
		sidebarLabel7.setText("Cost-per-aquisition (CPA): " + String.valueOf(calculateCPA()));
		sidebarLabel8.setText("Cost-per-click (CPC): " + String.valueOf(calculateCPC()));
		sidebarLabel9.setText("Cost-per-thousand-clicks (CPM): " + String.valueOf(calculateCPM()));
		sidebarLabel10.setText("Number of bounces: " + String.valueOf(calculateNumberOfBounces()));
		sidebarLabel11.setText("Bounce rate: " + String.valueOf(calculateBounceRate()));
	}

	public int calculateNumUniqueUserClicks() {
		int totalClicks = model.clickLogList.size();

		ArrayList<String> usersIDList = new ArrayList<String>();
		// for loop starts at id=1 so that the header text "ID" is not added as
		// an actual id
		for (int i = 1; i < totalClicks; i++) {
			String[] splitValues = model.clickLogList.get(i).split(",");

			if (splitValues[1] != null) {
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
	public int calculateNumClicksConverted() {
		int totalClicks = model.serverLogList.size();
		int clicksWithConversions = 0;
		for (int i = 0; i < totalClicks; i++) {
			String[] splitValues = model.serverLogList.get(i).split(",");

			if (splitValues[4].equals("Yes")) {
				String checkValue = splitValues[4];
				clicksWithConversions++;
			}
		}
		return clicksWithConversions;
	}

	// Not sure about this - I assume the total cost is the sum of impression
	// costs and click costs
	/**
	 * @return total ad campaign cost
	 */
	public Float calculateCampaignCost() {
		int totalClicks = model.clickLogList.size();
		Float sumClickCost = 0.0f;
		ArrayList<Float> clickCosts = new ArrayList<Float>();

		for (int i = 0; i < totalClicks; i++) {
			String[] splitValues = model.clickLogList.get(i).split(",");

			if (splitValues[2] != null && !splitValues[2].equals("Click Cost")) {
				clickCosts.add(Float.parseFloat(splitValues[2]));
			}
		}

		for (int k = 0; k < clickCosts.size(); k++) {
			sumClickCost += clickCosts.get(k);
		}

		return sumClickCost;
	}

	/**
	 * @return click through rate (CTR)
	 */
	public double calculateCTR() {

		double numImpressions = model.impressionLogList.size() - 1;
		double numClicks = model.clickLogList.size() - 1;

		return numClicks / numImpressions;
	}

	/**
	 * @return cost per acquisition (CPA)
	 */
	public double calculateCPA() {

		int numClicksConverted = calculateNumClicksConverted();
		Float campaignCost = calculateCampaignCost();

		return campaignCost / numClicksConverted;
	}

	/**
	 * @return cost per click (CPC)
	 */
	public double calculateCPC() {

		int totalClicks = model.clickLogList.size() - 1;
		Float campaignCost = calculateCampaignCost();

		return campaignCost / totalClicks;
	}

	/**
	 * @return cost per thousand clicks (CPM)
	 */
	public double calculateCPM() {

		int totalImpressions = model.impressionLogList.size() - 1;
		Float campaignCost = calculateCampaignCost();
		double costPerImpression = campaignCost / totalImpressions;
		return costPerImpression * 1000;
	}

	// I define a bounce as a user who spends less than 2 minutes on the site
	// More parameters (e.g. the number of pages visited) could be added to this
	// method later
	// If exit date is N/A I assign a date 2015-01-31 (in the far future
	// compared to other dates),
	// so a bounce is not registered in these N/A cases
	/**
	 * Calculates the number of bounces, where a bounce as a user who spends
	 * less than 2 minutes on the site
	 * 
	 * @return number of bounces
	 */
	public int calculateNumberOfBounces() {
		int numberOfBounces = 0;
		int totalClicks = model.serverLogList.size();
		ArrayList<String> entryDates = new ArrayList<String>();
		ArrayList<String> exitDates = new ArrayList<String>();
		ArrayList<Integer> pagesViewed = new ArrayList<Integer>();

		for (int i = 1; i < totalClicks; i++) {
			String[] splitValues = model.serverLogList.get(i).split(",");

			if (splitValues[0] != null) {
				entryDates.add(splitValues[0]);
			}
			if (splitValues[2] != null) {
				exitDates.add(splitValues[2]);
			}
			if (splitValues[3] != null) {
				pagesViewed.add(Integer.parseInt(splitValues[3]));
			}
		}

		ArrayList<String> entryDatesDate = new ArrayList<String>();
		ArrayList<String> entryDatesTime = new ArrayList<String>();

		for (String dateEntry : entryDates) {
			String[] dateSplit = dateEntry.split(" ");
			entryDatesDate.add(dateSplit[0]);
			entryDatesTime.add(dateSplit[1]);
		}

		ArrayList<String> entryDatesDay = new ArrayList<String>();
		ArrayList<String> entryDatesMonth = new ArrayList<String>();
		ArrayList<String> entryDatesYear = new ArrayList<String>();

		for (String pieceOfDate : entryDatesDate) {
			String[] dateSplit = pieceOfDate.split("-");
			entryDatesDay.add(dateSplit[0]);
			entryDatesMonth.add(dateSplit[1]);
			entryDatesYear.add(dateSplit[2]);
		}

		ArrayList<String> entryDatesSecond = new ArrayList<String>();
		ArrayList<String> entryDatesMinute = new ArrayList<String>();
		ArrayList<String> entryDatesHour = new ArrayList<String>();

		for (String timeEntry : entryDatesTime) {
			String[] dateSplit = timeEntry.split(":");
			entryDatesHour.add(dateSplit[0]);
			entryDatesMinute.add(dateSplit[1]);
			entryDatesSecond.add(dateSplit[2]);
		}

		ArrayList<ArrayList<Integer>> entryCalendarDates = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < entryDatesYear.size(); i++) {

			ArrayList<Integer> datesAndTimes = new ArrayList<Integer>();

			datesAndTimes.add(Integer.parseInt(entryDatesYear.get(i)));
			datesAndTimes.add(Integer.parseInt(entryDatesMonth.get(i)));
			datesAndTimes.add(Integer.parseInt(entryDatesDay.get(i)));
			datesAndTimes.add(Integer.parseInt(entryDatesHour.get(i)));
			datesAndTimes.add(Integer.parseInt(entryDatesMinute.get(i)));
			datesAndTimes.add(Integer.parseInt(entryDatesSecond.get(i)));

			entryCalendarDates.add(datesAndTimes);
		}

		ArrayList<String> exitDatesDate = new ArrayList<String>();
		ArrayList<String> exitDatesTime = new ArrayList<String>();

		for (String dateExit : exitDates) {

			String defaultExitDate = "2015-01-31";
			String defaultExitTime = "23:00:00";

			if (dateExit.equals("n/a") || dateExit.equals("n-a")) {
				exitDatesDate.add(defaultExitDate);
				exitDatesTime.add(defaultExitTime);
			} else {
				String[] dateSplit = dateExit.split(" ");
				exitDatesDate.add(dateSplit[0]);
				exitDatesTime.add(dateSplit[1]);
			}

		}

		ArrayList<String> exitDatesDay = new ArrayList<String>();
		ArrayList<String> exitDatesMonth = new ArrayList<String>();
		ArrayList<String> exitDatesYear = new ArrayList<String>();

		for (String pieceOfDate : exitDatesDate) {
			String[] dateSplit = pieceOfDate.split("-");
			exitDatesDay.add(dateSplit[0]);
			exitDatesMonth.add(dateSplit[1]);
			exitDatesYear.add(dateSplit[2]);
		}
		ArrayList<String> exitDatesSecond = new ArrayList<String>();
		ArrayList<String> exitDatesMinute = new ArrayList<String>();
		ArrayList<String> exitDatesHour = new ArrayList<String>();

		for (String timeExit : exitDatesTime) {
			String[] dateSplit = timeExit.split(":");
			exitDatesHour.add(dateSplit[0]);
			exitDatesMinute.add(dateSplit[1]);
			exitDatesSecond.add(dateSplit[2]);
		}

		ArrayList<ArrayList<Integer>> exitCalendarDates = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < exitDatesYear.size(); i++) {

			ArrayList<Integer> datesAndTimes = new ArrayList<Integer>();

			datesAndTimes.add(Integer.parseInt(exitDatesYear.get(i)));
			datesAndTimes.add(Integer.parseInt(exitDatesMonth.get(i)));
			datesAndTimes.add(Integer.parseInt(exitDatesDay.get(i)));
			datesAndTimes.add(Integer.parseInt(exitDatesHour.get(i)));
			datesAndTimes.add(Integer.parseInt(exitDatesMinute.get(i)));
			datesAndTimes.add(Integer.parseInt(exitDatesSecond.get(i)));

			exitCalendarDates.add(datesAndTimes);
		}

		int bouncesCount = 0;

		for (int i = 0; i < entryCalendarDates.size(); i++) {

			int test = entryCalendarDates.get(2).get(0);

			LocalDateTime ldtEntry = LocalDateTime.of(entryCalendarDates.get(i).get(2),
					entryCalendarDates.get(i).get(1), entryCalendarDates.get(i).get(0),
					entryCalendarDates.get(i).get(3), entryCalendarDates.get(i).get(4),
					entryCalendarDates.get(i).get(5));
			LocalDateTime ldtExit = LocalDateTime.of(exitCalendarDates.get(i).get(2), exitCalendarDates.get(i).get(1),
					exitCalendarDates.get(i).get(0), exitCalendarDates.get(i).get(3), exitCalendarDates.get(i).get(4),
					exitCalendarDates.get(i).get(5));

			long minutes = ChronoUnit.MINUTES.between(ldtEntry, ldtExit);

			if (minutes < 2) {
				bouncesCount++;
			}
		}

		return bouncesCount;
	}

	public double calculateBounceRate() {

		int totalClicks = model.clickLogList.size() - 1;
		int totalBounces = calculateNumberOfBounces();

		return totalBounces * 1.0 / totalClicks;
	}

}

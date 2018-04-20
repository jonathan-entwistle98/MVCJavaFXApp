import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest{

//	File testFileImpressions = new File("testImpressions.csv");
//	List<Log> impressionLog = CSVLoader.loadCSVData(testFileImpressions, FileType.IMPRESSION_LOG);
//	
//	File testFileClicks = new File("testClicks.csv");
//	List<Log> clickLog = CSVLoader.loadCSVData(testFileClicks, FileType.CLICK_LOG);
//	
//	File testFileServer = new File("testServer.csv");
//	List<Log> serverLog = CSVLoader.loadCSVData(testFileServer, FileType.SERVER_LOG);
	ArrayList<ImpressionEntry> imps;
	ArrayList<ClickEntry> clicks;
	MetricStorage metricStorage;
	
	@Before
	public void createMetricStorage() {
		metricStorage = new MetricStorage();
	}
	@Before
	public void createImpressions(){
		imps = new ArrayList<ImpressionEntry>();
		ImpressionEntry imp1 = new ImpressionEntry(0, 11111, Gender.MALE, Age.A25TO34,Income.MEDIUM, Context.HOBBIES, (float) 0.0005);
		ImpressionEntry imp2 = new ImpressionEntry(1, 11112, Gender.FEMALE, Age.A35TO44,Income.HIGH, Context.SHOPPING, (float) 0.000625);
		ImpressionEntry imp3 = new ImpressionEntry(2, 11113, Gender.FEMALE, Age.A25TO34,Income.LOW, Context.BLOG, (float) 0.000235);
		imps.add(imp1);
		imps.add(imp2);
		imps.add(imp3);
	}
	@Before
	public void createClicks(){
		clicks = new ArrayList<ClickEntry>();
		ClickEntry cl1 = new ClickEntry(0,22221,(float) 0.0004,Gender.FEMALE,Age.A25TO34,Income.LOW,10,1,false);
		ClickEntry cl2 = new ClickEntry(1,22222,(float) 0.0003,Gender.MALE,Age.L25,Income.MEDIUM,300,3,true);
		ClickEntry cl3 = new ClickEntry(2,22223,(float) 0.0007,Gender.MALE,Age.M54,Income.HIGH,25,2,false);
		clicks.add(cl1);
		clicks.add(cl2);
		clicks.add(cl3);
	}

	
	@Test
	public void testcalcImpressionsOverTime() {

		Calculator testingCalculator = new Calculator(metricStorage, imps, clicks);
		testingCalculator.calcMetrics(3);

		int[] expectedImpressionDateArray = {1,1,1};

		assertArrayEquals(expectedImpressionDateArray, metricStorage.getImpressions());
	}
	
//	@Test
//	public void testcalcImpressions() {
//		assertEquals(calcImpressions(impressionLog), 4);
//	}
//
//	@Test
//	public void testCalcClicks() {		
//		assertEquals(calcClicks(clickLog), 2);
//	}
//
//	@Test
//	public void testcalcUniques() {
//		assertEquals(calcUniques(clickLog), 1);
//	}
//
//	@Test
//	public void testcalcClickCost() {
//		assertEquals(calcClickCost(clickLog), 23.5, 0.1);
//	}
//	
//	@Test
//	public void testcalcImpressionCost() {
//		assertEquals(calcImprCost(impressionLog), 0.006, 0.001);
//	}
//	
//	@Test
//	public void testcalcCTR() {
//		assertEquals(calcCTR(calcClicks(clickLog), calcImpressions(impressionLog)), 0.5, 0.1);
//	}
//	
//	@Test
//	public void testcalcCPA() {
//		assertEquals(calcCPA(calcImprCost(impressionLog) + calcClickCost(clickLog), calcConversions(serverLog)), 23.5, 0.1);
//	}
//	
//	@Test
//	public void testcalcCPC() {
//		assertEquals(calcCPC(calcClickCost(clickLog), calcClicks(clickLog)), 11.8, 0.1);
//	}
//
//	@Test
//	public void testcalcCPM() {
//		assertEquals(calcCPM(calcImprCost(impressionLog), calcImpressions(impressionLog)), 1.5, 0.1);
//	}
//	
//	@Test
//	public void testcalcBounces() {
//		assertEquals(calcBounces(serverLog, 1, 120), 1);
//	}
//	
//	@Test
//	public void testcalcBounceRate() {
//		assertEquals(calcBounceRate(calcBounces(serverLog, 1, 120), calcClicks(clickLog)), 0.5, 0.1);
//	}
//	
}
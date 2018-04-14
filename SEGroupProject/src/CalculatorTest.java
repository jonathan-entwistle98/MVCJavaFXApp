//import static org.junit.Assert.*;
//
//import java.io.File;
//import java.util.List;
//
//import org.junit.Test;
//
//public class CalculatorTest extends Calculator{
//
//	File testFileImpressions = new File("testImpressions.csv");
//	List<Log> impressionLog = CSVLoader.loadCSVData(testFileImpressions, FileType.IMPRESSION_LOG);
//	
//	File testFileClicks = new File("testClicks.csv");
//	List<Log> clickLog = CSVLoader.loadCSVData(testFileClicks, FileType.CLICK_LOG);
//	
//	File testFileServer = new File("testServer.csv");
//	List<Log> serverLog = CSVLoader.loadCSVData(testFileServer, FileType.SERVER_LOG);
//	
//	
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
//}
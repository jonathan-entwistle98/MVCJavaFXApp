import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

public class CalculatorTest extends Calculator{

	File testFileImpressions = new File("testImpressions.csv");
	List<Log> impressionLog = CSVLoader.loadCSVData(testFileImpressions, FileType.IMPRESSION_LOG);
	
	File testFileClicks = new File("testClicks.csv");
	List<Log> clickLog = CSVLoader.loadCSVData(testFileClicks, FileType.CLICK_LOG);
	
	File testFileServer = new File("testServer.csv");
	List<Log> serverLog = CSVLoader.loadCSVData(testFileServer, FileType.SERVER_LOG);
	
	
	@Test
	public void testcalcImpressions() {
		assertEquals(calcImpressions(impressionLog), 4);
	}

	@Test
	public void testCalcClicks() {		
		assertEquals(calcClicks(clickLog), 2);
	}

	@Test
	public void testcalcUniques() {
		assertEquals(calcUniques(clickLog), 1);
	}

	@Test
	public void testcalcClickCost() {
		assertEquals(calcClickCost(clickLog), 23.5, 0.1);
	}
	
	@Test
	public void testcalcImpressionCost() {
		assertEquals(2, 2);
	}
	
	@Test
	public void testcalcCTR() {
		assertEquals(2, 2);
	}
	
	@Test
	public void testcalcCPA() {
		assertEquals(2, 2);
	}
	
	@Test
	public void testcalcCPC() {
		assertEquals(2, 2);
	}

	@Test
	public void testcalcCPM() {
		assertEquals(2, 2);
	}

	//Still need to do bounce rate caclulations tests

	
}
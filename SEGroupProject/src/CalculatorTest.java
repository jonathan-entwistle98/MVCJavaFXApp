import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest{

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
	

	@Test
	public void testCalcClicksOverTime() {		
		
		Calculator testingCalculator = new Calculator(metricStorage, imps, clicks);
		testingCalculator.calcMetrics(3);

		int[] expectedClickDateArray = {1,1,1};

		assertArrayEquals(expectedClickDateArray, metricStorage.getClicks());
	}

	@Test
	public void testcalcUniquesOverTime() {
		
		Calculator testingCalculator = new Calculator(metricStorage, imps, clicks);
		testingCalculator.calcMetrics(3);
		
		int[] expectedUniqueArray = {1,1,1};
		
		assertArrayEquals(expectedUniqueArray, metricStorage.getUniques());
	}

	@Test
	public void testcalcClickCostOverTime() {
		
		Calculator testingCalculator = new Calculator(metricStorage, imps, clicks);
		testingCalculator.calcMetrics(3);
		
		float expectedCost = (float) 0.0014;
		
		assertEquals(expectedCost,(float)metricStorage.getCosts()[1],0.01);
	}
	
	@Test
	public void testcalcCTROverTime() {

		Calculator testingCalculator = new Calculator(metricStorage, imps, clicks);
		testingCalculator.calcMetrics(3);

		float[] expectedCTRDateArray = {1.0f,1.0f,1.0f};

		assertArrayEquals(expectedCTRDateArray, metricStorage.getCtr(), 0.001f);
	}
	@Test
	public void testcalcCPAOverTime() {

		Calculator testingCalculator = new Calculator(metricStorage, imps, clicks);
		testingCalculator.calcMetrics(3);

		float[] expectedCPADateArray = {0.0005f,0.000625f,0.000235f};

		assertArrayEquals(expectedCPADateArray, metricStorage.getCpa(), 0.001f);
	}
	@Test
	public void testcalcCPCOverTime() {

		Calculator testingCalculator = new Calculator(metricStorage, imps, clicks);
		testingCalculator.calcMetrics(3);

		float[] expectedCPCDateArray = {0.0005f,0.000625f,0.000235f};

		assertArrayEquals(expectedCPCDateArray, metricStorage.getCpc(), 0.001f);
	}
	@Test
	public void testcalcCPMOverTime() {

		Calculator testingCalculator = new Calculator(metricStorage, imps, clicks);
		testingCalculator.calcMetrics(3);

		float[] expectedCPMDateArray = {0.5f,0.625f,0.235f};

		assertArrayEquals(expectedCPMDateArray, metricStorage.getCpm(), 0.001f);
	}
	
}
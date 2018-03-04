import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {
	
	List<Log> impressionLog;
	List<Log> clicksLog;
	List<Log> serverLog;
	
	int minPages;
	int minSeconds;
	
	public void loadCSVs(File impressions, File clicks, File server) {
		// TODO add error checking.
		
		impressionLog = CSVLoader.loadCSVData(impressions, FileType.IMPRESSION_LOG);
		clicksLog = CSVLoader.loadCSVData(clicks, FileType.CLICK_LOG);
		serverLog = CSVLoader.loadCSVData(server, FileType.SERVER_LOG);
		
		System.out.println((((ServerLog)serverLog.get(11)).getExitDate() == null));
	}
	
	public OverviewItems getOverviewItems() {
		//TODO insert min variables.
		return Calculator.getOverview(impressionLog, clicksLog, serverLog, 2, 0);
	}
	
}

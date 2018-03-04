import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {
	
	List<Log> impressionLog;
	List<Log> clicksLog;
	List<Log> serverLog;
	
	public void loadCSVs(File impressions, File clicks, File server) {
		// TODO add error checking.
		
		impressionLog = CSVLoader.loadCSVData(impressions, FileType.IMPRESSION_LOG);
		clicksLog = CSVLoader.loadCSVData(clicks, FileType.CLICK_LOG);
		serverLog = CSVLoader.loadCSVData(server, FileType.SERVER_LOG);
	}
	
	public OverviewItems getOverviewItems() {
		return Calculator.getOverview(impressionLog, clicksLog, serverLog);
	}
	
}

import java.io.File;
import java.util.List;

public class Model {
	
	private List<Log> impressionLog;
	private List<Log> clicksLog;
	private List<Log> serverLog;
	
	private int minPages;
	private int minSeconds;
	
	/**
	 * 
	 * @param impressions CSV impression log file
	 * @param clicks CSV click log file
	 * @param server CSV server log file
	 */
	public void loadCSVs(File impressions, File clicks, File server) {
		// TODO add error checking.
		
		impressionLog = CSVLoader.loadCSVData(impressions, FileType.IMPRESSION_LOG);
		clicksLog = CSVLoader.loadCSVData(clicks, FileType.CLICK_LOG);
		serverLog = CSVLoader.loadCSVData(server, FileType.SERVER_LOG);
	}
	
	/**
	 * 
	 * @return object holding campaign overview data.
	 */
	public OverviewItems getOverviewItems() {
		//TODO insert min variables.
		return Calculator.getOverview(impressionLog, clicksLog, serverLog, minPages, minSeconds);
	}

	public int getMinPages() {
		return minPages;
	}

	/**
	 *  
	 * @param minPages less than that and bounce will be registered. Ignored if 0.
	 */
	public void setMinPages(int minPages) {
		this.minPages = minPages;
	}

	public int getMinSeconds() {
		return minSeconds;
	}

	/**
	 * 
	 * @param minSeconds less than that and bounce will be registered. Ignored if 0.
	 */
	public void setMinSeconds(int minSeconds) {
		this.minSeconds = minSeconds;
	}
	
}

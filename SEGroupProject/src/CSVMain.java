import java.io.File;
import java.util.ArrayList;

public class CSVMain {

	public static void main (String[] args) {
		File file = new File("impression_log.csv");
		ArrayList<Log> impressions;
		long time = System.currentTimeMillis();
		impressions = (ArrayList<Log>) CSVLoader.loadCSVData(file, FileType.IMPRESSION_LOG);
		System.out.println("Loaded in " + (System.currentTimeMillis() - time) + "ms");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

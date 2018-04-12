import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static void main (String[] args) {
		
		File file1 = new File("impression_log2.csv");
		File file2 = new File("click_log2.csv");
		File file3 = new File("server_log2.csv");
		
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		Date start = null;
		Date end = null;
		try {
			start = new SimpleDateFormat(dateFormat).parse("2015-01-01 12:00:00");
			end = new SimpleDateFormat(dateFormat).parse("2015-02-30 13:59:08");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DataModel dm = new DataModel();
		dm.init();
//		dm.exportCSVs(file1, file2, file3);
		dm.selectCampaign(1);
		long time = System.currentTimeMillis();
		dm.fetchData(start, end);
		System.out.printf("Database access time: %.2fs\n", ((System.currentTimeMillis() - time) / 1000.0f));
//		dm.getMetric(null);
	}

}

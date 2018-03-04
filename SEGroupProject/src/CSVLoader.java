import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

public abstract class CSVLoader {
	
	//TODO normal javadoc
	/*
	 * fileType:
	 *  1- ImpressionLog
	 *  2- ClickLog
	 *  3- ServerLog
	 */

	private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 
	 * @param file file to be loaded.
	 * @param fileType type of log file to be loaded.
	 * @return list of log objects which represent rows in a log file. 
	 */
	public static List<Log> loadCSVData (File file, FileType fileType){
		
		List<Log> log = new ArrayList<Log>();
		
		CsvParserSettings settings = new CsvParserSettings();
		//the file used in the example uses '\n' as the line separator sequence.
		//the line separator sequence is defined here to ensure systems such as MacOS and Windows
		//are able to process this file correctly (MacOS uses '\r'; and Windows uses '\r\n').
		settings.getFormat().setLineSeparator("\n");
		
		// creates a CSV parser
		CsvParser parser = new CsvParser(settings);
		
		// call beginParsing to read records one by one, iterator-style.
		parser.beginParsing(file);
		
		String[] row;
		row = parser.parseNext();
		
		switch(fileType) {
			// Impression CSV
			case IMPRESSION_LOG: {
				while ((row = parser.parseNext()) != null) {
					// parses row and creates log row object.
					log.add(CSVLoader.parseImpression(row));
				}
				break;
			}
			// Click CSV
			case CLICK_LOG: {
				while ((row = parser.parseNext()) != null) {
					// parses row and creates log row object.
					log.add(CSVLoader.parseClick(row));
					
				}
				break;
			}
			// Server CSV
			case SERVER_LOG: {
				while ((row = parser.parseNext()) != null) {
					// parses row and creates log row object.
					log.add(CSVLoader.parseServer(row));
				}
				break;
			}
		}
		parser.stopParsing();		
		return log;
	}
	
	/**
	 * Takes a row from impression log as a string and converts to a log object.
	 * 
	 * @param attributes
	 * @return object representing a row in impression log.
	 */
	public static ImpressionLog parseImpression (String[] attributes) {

		Date date = null;
		try {
			date = new SimpleDateFormat(dateFormat).parse(attributes[0]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		long id = Long.parseLong(attributes[1]);
		Gender gender;
		switch(attributes[2]){
		case "Male":
			gender = Gender.MALE;
			break;
		case "Female":
			gender = Gender.FEMALE;
			break;
		default:
			gender = null;
			break;
		}
		Age age;
		switch(attributes[3]){
		case "<25":
			age = Age.L25;
			break;
		case "25-34":
			age = Age.A25TO34;
			break;
		case "35-44":
			age = Age.A35TO44;
			break;
		case "45-54":
			age = Age.A45TO54;
			break;
		case ">54":
			age = Age.M54;
			break;
		default:
			age = null;
			break;
		}
		Income income;
		switch(attributes[4]){
		case "Low":
			income = Income.LOW;
			break;
		case "Medium":
			income = Income.MEDIUM;
			break;
		case "High":
			income = Income.HIGH;
			break;
		default:
			income = null;
			break;
		}
		Context context;
		switch(attributes[5]){
		case "News":
			context = Context.NEWS;
			break;
		case "Shopping":
			context = Context.SHOPPING;
			break;
		case "Social Media":
			context = Context.SOCIAL_MEDIA;
			break;
		case "Blog":
			context = Context.BLOG;
			break;
		case "Hobbies":
			context = Context.HOBBIES;
			break;
		case "Travel":
			context = Context.TRAVEL;
			break;
		default:
			context = null;
			break;
		}
		float cost = Float.parseFloat(attributes[6]);

		return new ImpressionLog(date, id, gender, age, income, context, cost);
	}
	
	/**
	 * Takes a row from click log as a string and converts to a log object.
	 * 
	 * @param attributes
	 * @return object representing a row in click log.
	 */
	public static ClickLog parseClick (String[] attributes) {
		
		Date date = null;
		try {
			date = new SimpleDateFormat(dateFormat).parse(attributes[0]);
		} catch (ParseException e) {
//			e.printStackTrace();
		}
		long id = Long.parseLong(attributes[1]);
		float cost = Float.parseFloat(attributes[2]);
		
		return new ClickLog(date, id, cost);
	}
	
	/**
	 * Takes a row from server log as a string and converts to a log object.
	 * 
	 * @param attributes
	 * @return object representing a row in server log.
	 */
	public static ServerLog parseServer (String[] attributes) {

		Date enDate = null;
		try {
			enDate = new SimpleDateFormat(dateFormat).parse(attributes[0]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		long id = Long.parseLong(attributes[1]);
		Date exDate = null;
		try {
			exDate = new SimpleDateFormat(dateFormat).parse(attributes[2]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		int pages = Integer.parseInt(attributes[3]);
		boolean conversion;
		switch(attributes[4]){
		case "Yes":
			conversion = true;
			break;
		case "No":
			conversion = false;
			break;
		default:
			//TODO it assumes the conversion was not made if the field is empty. Might want to change.
			conversion = false;
			break;
		}
		
		return new ServerLog(enDate, id, exDate, pages, conversion);
	}
}

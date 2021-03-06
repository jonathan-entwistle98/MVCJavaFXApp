import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
					// Expected output: Date, ID, Gender, Age, Income, Context, ImpressionCost.
					// Converts string attributes to objects.
					Object[] a = CSVLoader.parseImpression(row);
					// Generates log entry.
					log.add(new ImpressionLog((DateC)a[0], (long)a[1], (Gender)a[2], (Age)a[3],
							                  (Income)a[4], (Context)a[5], (float)a[6]));
				}
				break;
			}
			// Click CSV
			case CLICK_LOG: {
				while ((row = parser.parseNext()) != null) {
					// Expected output: Date, ID, Gender, Age, Income, Context, ImpressionCost.
					// Converts string attributes to objects.
					Object[] a = CSVLoader.parseImpression(row);
					// Generates log entry.
					log.add(new ClickLog((DateC)a[0], (long)a[1], (float)a[2]));
					
				}
				break;
			}
			// Server CSV
			case SERVER_LOG: {
				while ((row = parser.parseNext()) != null) {
					// Expected output: Date, ID, Gender, Age, Income, Context, ImpressionCost.
					// Converts string attributes to objects.
					Object[] a = CSVLoader.parseImpression(row);
					// Generates log entry.
					log.add(new ServerLog((DateC)a[0], (long)a[1], (DateC)a[2], (int)a[3], (boolean)a[4]));
				}
				break;
			}
		}
		parser.stopParsing();
		return log;
	}
	
	public static Object[] parseImpression (String[] attributes) {

		DateC date = new DateC(attributes[0]);
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

		Object[] a = {date, id, gender, age, income, context, cost};
		return a;
	}
	
	public static Object[] parseClick (String[] attributes) {
		
		DateC date = new DateC(attributes[0]);
		long id = Long.parseLong(attributes[1]);
		float cost = Float.parseFloat(attributes[2]);
		
		Object[] a = {date, id, cost};
		return a;
	}
	
	public static Object[] parseServer (String[] attributes) {

		DateC enDate = new DateC(attributes[0]);
		long id = Long.parseLong(attributes[1]);
		DateC exDate = new DateC(attributes[2]);
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
		
		Object[] a = {enDate, id, exDate, pages, conversion};
		return a;
	}
}

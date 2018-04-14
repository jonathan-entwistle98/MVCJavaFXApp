import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DataParser {

	private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * Takes a row from impression log as a string and converts to a log object.
	 * 
	 * @param attributes
	 * @return object representing a row in impression log.
	 */
	public static ImpressionEntry parseImpression (String[] attributes) {

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

		return null;
	}
	
	/**
	 * Takes a row from click log as a string and converts to a log object.
	 * 
	 * @param attributes
	 * @return object representing a row in click log.
	 */
	public static ClickEntry parseClick (String[] attributes) {
		
		Date date = null;
		try {
			date = new SimpleDateFormat(dateFormat).parse(attributes[0]);
		} catch (ParseException e) {
//			e.printStackTrace();
		}
		long id = Long.parseLong(attributes[1]);
		float cost = Float.parseFloat(attributes[2]);
		
		Gender gender;
		switch(attributes[3]){
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
		switch(attributes[4]){
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
		switch(attributes[5]){
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
		
		return null;
	}
	
	/**
	 * Takes a row from server log as a string and converts to a log object.
	 * 
	 * @param attributes
	 * @return object representing a row in server log.
	 */
	public static ServerEntry parseServer (String[] attributes) {

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
		
		return null;
	}
	
	/**
	 * Takes a row from impression log as an array of objects and converts to a log object.
	 * 
	 * @param attributes
	 * @return object representing a row in impression log.
	 */
	public static ImpressionEntry makeImpression (Object[] attributes) {

		long date = (long) attributes[0];
		long id = (long) attributes[1];
		Gender gender;
		switch((String)attributes[2]){
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
		switch((String)attributes[3]){
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
		switch((String)attributes[4]){
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
		switch((String)attributes[5]){
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
		float cost = (float)attributes[6];

		return new ImpressionEntry(date, id, gender, age, income, context, cost);
	}
	
	/**
	 * Takes a row from click log as a string and converts to a log object.
	 * 
	 * @param attributes
	 * @return object representing a row in click log.
	 */
	public static ClickEntry makeClick (Object[] attributes) {
		
		long date = (long) attributes[0];
		long id = (long) attributes[1];
		float cost = (float) attributes[2];
		
		Gender gender;
		switch((String)attributes[3]){
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
		switch((String)attributes[4]){
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
		switch((String)attributes[5]){
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
		int seconds = (int) attributes[6];
		int pages = (int) attributes[7];
		boolean conversion;
		switch((String)attributes[8]){
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
		
		return new ClickEntry(date, id, cost, gender, age, income, seconds, pages, conversion);
	}
	
	/**
	 * Takes a row from server log as a string and converts to a log object.
	 * 
	 * @param attributes
	 * @return object representing a row in server log.
	 */
	public static ServerEntry makeServer (Object[] attributes) {

		long date = (long) attributes[0];
		Date enDate = null;
		try {
			enDate = new SimpleDateFormat(dateFormat).parse((String)attributes[1]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		long id = (long) attributes[2];
		Date exDate = null;
		try {
			exDate = new SimpleDateFormat(dateFormat).parse((String)attributes[3]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		int pages = (int) attributes[4];
		boolean conversion;
		switch((String)attributes[5]){
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
		
		return new ServerEntry(date, enDate, id, exDate, pages, conversion);
	}
	
	/***
	 * 
	 * @param refDate date of reference (date before)
	 * @param date current date
	 * @return integer expressing number of hours since reference date.
	 */
	public static int relativeDate(long refDate, String date_s) {
		
		try {
			Date date = new SimpleDateFormat(dateFormat).parse(date_s);
			
			return (int)((date.getTime()-refDate) / 3600000.0f) ;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public static int relativeDate(long refDate, Date date) {
		
		return (int) ((date.getTime() - refDate) / 3600000.0f);
	}
	
	public static long roundToHours(String date_s) {
		
		try {
			Date date = new SimpleDateFormat(dateFormat).parse(date_s);
			return (long) (Long.divideUnsigned(date.getTime(), 3600000) * 3600000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
		
	}
	
}

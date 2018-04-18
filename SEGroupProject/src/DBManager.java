import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

public class DBManager {
	
	String dateFormat = "yyyy-MM-dd HH:mm:ss";
	
	public static String DBNAME = "jdbc:sqlite:test.db";
	
	public int selectedCampaign;
	public long refDate = 0;
	public long endDate = 0;
	private int rowID = 0;
	
	/***
	 * Returns data WITH relative time offset. (first r_date always 0)
	 * 
	 * @param startD
	 * @param endD
	 * @return
	 */
	public Object[] fetchData(Date startD, Date endD) {
		
		String condition = ";";
		String conditionClick = ";";
		
		// Set date range if campaign was selected.
		int start = 0;
		if(refDate != 0) {
			start = DataParser.relativeDate(refDate, startD);
			int end = DataParser.relativeDate(refDate, endD);
			
			condition = "AND R_DATE BETWEEN\n"
					+ start +" AND " + end + ";";
			
			conditionClick = "AND c.R_DATE BETWEEN\n"
					+ start +" AND " + end + ";";
		}
		ResultSet rs = null;
		
		try (
				Connection conn = DriverManager.getConnection(DBNAME);
				Statement stmt = conn.createStatement();
				) {
			conn.setAutoCommit(false);
			
			// Get Impression Log
			String sql = "SELECT * FROM IMPRESSION_LOG\n"
					+ "WHERE CAMPAIGN = " + selectedCampaign + "\n"
					+ condition;
			
			rs = stmt.executeQuery(sql);
			
			ArrayList<ImpressionEntry> impressions = new ArrayList<ImpressionEntry>();
			//TODO Use original types instead of string to improve performance.
			while ( rs.next() ) {
				
				Object[] attributes = {
						rs.getLong("R_DATE") - start, // Accounting for offset
						rs.getLong("ID"),
						rs.getString("GENDER"),
						rs.getString("AGE"),
						rs.getString("INCOME"),
						rs.getString("CONTEXT"),
						rs.getFloat("IMPRESSION_COST")};
				
				impressions.add(DataParser.makeImpression(attributes));
			}

			// Get Click Log
			sql = "SELECT DISTINCT c.ROWID, c.R_DATE, c.ID, c.CLICK_COST, c.CAMPAIGN,\n"
					+ "c.CAMPAIGN, strftime('%s', c.EXIT_DATE) - strftime('%s', c.ENTRY_DATE) AS SECONDS_VIEWED,\n" 
					+ "c.PAGES_VIEWED, c.CONVERSION,\n"
					+ "i.GENDER, i.AGE, i.INCOME, i.CONTEXT\n"
					+ "FROM CLICK_LOG c\n"
					+ "LEFT JOIN IMPRESSION_LOG i\n"
					+ "WHERE c.ID = i.ID\n"
					+ "AND c.CAMPAIGN = " + selectedCampaign + "\n"
					+ conditionClick;
			rs.close();
			rs = stmt.executeQuery(sql);
			
			ArrayList<ClickEntry> clicks = new ArrayList<ClickEntry>();
			//TODO Use original types instead of string to improve performance.
			//TODO Make new table to reduce query complexity.
			while ( rs.next() ) {
				
				Object[] attributes = {
						rs.getLong("R_DATE") - start, // Accounting for offset
						rs.getLong("ID"),
						rs.getFloat("CLICK_COST"),
						rs.getString("GENDER"),
						rs.getString("AGE"),
						rs.getString("INCOME"),
						rs.getInt("SECONDS_VIEWED"),
						rs.getInt("PAGES_VIEWED"),
						rs.getString("CONVERSION")
						};

				clicks.add(DataParser.makeClick(attributes));
			}
			
//			// Get Server Log
//			sql = "SELECT *\n"
//				+ "FROM SERVER_LOG\n"
//				+ "WHERE CAMPAIGN = " + selectedCampaign + "\n"
//				+ condition;
//	
//			rs.close();
//			rs = stmt.executeQuery(sql);
//			
//			ArrayList<ServerEntry> servers = new ArrayList<ServerEntry>();
//			//TODO Use original types instead of string to improve performance.
//			while ( rs.next() ) {
//				
//				Object[] attributes = {
//						rs.getLong("R_DATE") - start, // Accounting for offset
//						rs.getString("ENTRY_DATE"),
//						rs.getLong("ID"),
//						rs.getString("EXIT_DATE"),
//						rs.getInt("PAGES_VIEWED"),
//						rs.getString("CONVERSION")};
//
//				servers.add(DataParser.makeServer(attributes));
//			}
			
			return new Object[]{impressions, clicks};
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
	/***
	 * Exports CSV to database.
	 * 
	 * @param impressions
	 * @param clicks
	 * @param servers
	 * @param campaignName
	 * @return
	 */
	public void exportData(File impressions, File clicks, File servers, double[] progress , String campaignName) {
		
		getRowID();
		
		// Set filesize for progress bar.
		double totalLength = (double)impressions.length() + (double)clicks.length();
		progress[1] = (75000 / (double)impressions.length()) * ((double)impressions.length() / totalLength);
		progress[2] = (51000 / (double)clicks.length()) * ((double)clicks.length() / totalLength);
		// Parses CSV and updates database.
		exportImpression(impressions, progress);
		exportRichClick(clicks, servers, progress);
		
		//fetchAllData()
		
		//doCalculations()
		
		createCampaign("DefaultName");
		
	}
		
	private void exportImpression(File impressionFile, double[] progress) {

		// Create SQL statement
		String SQL = "INSERT INTO IMPRESSION_LOG (CAMPAIGN, R_DATE, ID, GENDER, "
				+ "AGE, INCOME, CONTEXT, IMPRESSION_COST) "
		        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (
				Connection conn = DriverManager.getConnection(DBNAME);
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				) {
			conn.setAutoCommit(false);
			
			CsvParser parser = parseCSV(impressionFile);
			
			int counter1 = 0;
			int counter2 = 0;
			boolean dateSet = false;
			
			String[] row;
			while ((row = parser.parseNext()) != null) {
				if(dateSet == false) {
					refDate = DataParser.roundToHours(row[0]);
					dateSet = true;
				}
//				// Set the variables
				pstmt.setInt(1, rowID);
				pstmt.setLong(2, DataParser.relativeDate(refDate, row[0]));
				pstmt.setLong(3, Long.parseLong(row[1]));
				pstmt.setString(4, row[2]);
				pstmt.setString(5, row[3]);
				pstmt.setString(6, row[4]);
				pstmt.setString(7, row[5]);
				pstmt.setFloat(8, Float.parseFloat(row[6]));
//				
//				// Add it to the batch
				pstmt.addBatch();
				
				counter1++;
				if(counter1 >= 100000) {
					pstmt.executeBatch();
					counter1 = 0;
				}
				
				counter2++;
				if(counter2 >= 1000) {
					pstmt.executeBatch();
					counter2 = 0;
					progress[0] += progress[1];
					System.out.println(progress[0]);
				}
			}
			pstmt.executeBatch();

			//Explicitly commit statements to apply changes
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
      
	}
	
	private void exportRichClick(File clickFile, File serverFile, double[] progress) {
		
		// Create SQL statement
		String SQL = "INSERT INTO CLICK_LOG (CAMPAIGN, R_DATE, ID, CLICK_COST, ENTRY_DATE, EXIT_DATE,\n"
				+ "PAGES_VIEWED, CONVERSION)\n"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (				
				Connection conn = DriverManager.getConnection(DBNAME);
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				) {
			conn.setAutoCommit(false);
			
			CsvParser clickParser = parseCSV(clickFile);
			CsvParser serverParser = parseCSV(serverFile);
			
			int counter1 = 0;
			int counter2 = 0;
			
			String[] cRow;
			String[] sRow;
			while ((cRow = clickParser.parseNext()) != null) {
				if((sRow = serverParser.parseNext()) != null) {
					// Check if IDs match
					if(cRow[1].equals(sRow[1])) {
						// Set the variables
						pstmt.setInt(1, rowID);
						pstmt.setLong(2, DataParser.relativeDate(refDate, cRow[0]));
						pstmt.setLong(3, Long.parseLong(cRow[1]));
						pstmt.setFloat(4, Float.parseFloat(cRow[2]));
						pstmt.setString(5, sRow[0]);
						pstmt.setString(6, sRow[2]);
						pstmt.setLong(7, Long.parseLong(sRow[3]));
						pstmt.setString(8, sRow[4]);

						// Add it to the batch
						pstmt.addBatch();
						
						counter1++;
						if(counter1 >= 100000) {
							pstmt.executeBatch();
							counter1 = 0;
						}
						
						counter2++;
						if(counter2 >= 1000) {
							pstmt.executeBatch();
							counter2 = 0;
							progress[0] += progress[2];
							System.out.println(progress[0]);
						}
						
					} else {
						System.err.println("Click and server log row mismatch. (User IDs differ on same rows) " + cRow[1] + " and " + sRow[1]);
					}
				} else {
					System.err.println("Server log is incomplete.");
				}
			}

			pstmt.executeBatch();

			//Explicitly commit statements to apply changes
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

//	private void exportServer(Connection conn, CsvParser parser) {
//		
//		// Create SQL statement
//		String SQL = "INSERT INTO SERVER_LOG (CAMPAIGN, R_DATE, ENTRY_DATE, ID, EXIT_DATE, "
//				+ "PAGES_VIEWED, CONVERSION) "
//		        + "VALUES(?, ?, ?, ?, ?, ?, ?)";
//		try (
//				PreparedStatement pstmt = conn.prepareStatement(SQL);
//				) {
//			
//			int counter = 0;
//			String[] row;
//			while ((row = parser.parseNext()) != null) {
//				// Set the variables
//				pstmt.setInt(1, rowID);
//				pstmt.setLong(2, DataParser.relativeDate(refDate, row[0]));
//				pstmt.setString(3, row[0]);
//				pstmt.setLong(4, Long.parseLong(row[1]));
//				pstmt.setString(5, row[2]);
//				pstmt.setLong(6, Long.parseLong(row[3]));
//				pstmt.setString(7, row[4]);
//
//				// Add it to the batch
//				pstmt.addBatch();
//				
//				counter++;
//				if(counter >= 250000) {
//					pstmt.executeBatch();
//					counter = 0;
//				}
//			}
//
//			pstmt.executeBatch();
//
//			//Explicitly commit statements to apply changes
//			conn.commit();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//	}
	
	private	CsvParser parseCSV(File file) {
		//TODO add header check based on file type.
		
		
		// The settings object provides many configuration options
		CsvParserSettings parserSettings = new CsvParserSettings();
		
		//You can configure the parser to automatically detect what line separator sequence is in the input
		parserSettings.setLineSeparatorDetectionEnabled(true);
		
		// Let's consider the first parsed row as the headers of each column in the file.
		parserSettings.setHeaderExtractionEnabled(true);
		
		// creates a parser instance with the given settings
		CsvParser parser = new CsvParser(parserSettings);
		
		// the 'parse' method will parse the file and delegate each parsed row to the RowProcessor you defined
		parser.beginParsing(file);

		return parser;
	}

	private void createCampaign(String name) {

		// Create SQL statement
		String SQL = "INSERT INTO CAMPAIGN (ROWID, NAME, START_DATE, END_DATE,\n"
				+ "IMPRESSIONS, CLICKS, UNIQUES, CONVERSIONS, TOTAL_COST,\n"
				+ "CTR, CPA, CPC, CPM)\n"
		        + "VALUES(?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		ResultSet rs = null;
		try (
				Connection conn = DriverManager.getConnection(DBNAME);
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				) {
			
			conn.setAutoCommit(false);
			int impressions = 0;
			int clicks = 0;
			int uniques = 0;
			int conversions = 0;
			float totalCost = 0;
			float CTR = 0;
			float CPA = 0;
			float CPC = 0;
			float CPM = 0;
			
			rs = stmt.executeQuery("SELECT COUNT(rowid) FROM IMPRESSION_LOG WHERE CAMPAIGN == " + rowID);
			if(rs.next()) {
				impressions = rs.getInt(1);
			}
			rs.close();
			rs = stmt.executeQuery("SELECT COUNT(rowid) FROM CLICK_LOG WHERE CAMPAIGN == " + rowID);
			if(rs.next()) {
				clicks = rs.getInt(1);
			}
			rs.close();
			rs = stmt.executeQuery("SELECT COUNT(id) FROM CLICK_LOG WHERE CAMPAIGN == " + rowID);
			if(rs.next()) {
				uniques = rs.getInt(1);
			}
			rs.close();
			rs = stmt.executeQuery("SELECT COUNT(rowid) FROM CLICK_LOG WHERE CONVERSION = 'Yes' AND CAMPAIGN == " + rowID);
			if(rs.next()) {
				conversions = rs.getInt(1);
			}
			rs.close();
			rs = stmt.executeQuery("SELECT SUM(IMPRESSION_COST) FROM IMPRESSION_LOG WHERE CAMPAIGN == " + rowID);
			if(rs.next()) {
				totalCost += rs.getInt(1);
				CPM = rs.getInt(1) / (impressions / 1000.0f); 
			}
			rs.close();
			rs = stmt.executeQuery("SELECT SUM(CLICK_COST) FROM CLICK_LOG WHERE CAMPAIGN == " + rowID);
			if(rs.next()) {
				totalCost += rs.getInt(1);
				CPC = rs.getInt(1) / (float)clicks;
			}
			CTR = clicks / (float)impressions;
			CPA = totalCost / (float)conversions;
			
			pstmt.setInt(1, rowID);
			pstmt.setString(2, name);
			pstmt.setLong(3, refDate);
			pstmt.setLong(4, endDate);
			pstmt.setInt(5, impressions);
			pstmt.setInt(6, clicks);
			pstmt.setInt(7, uniques);
			pstmt.setInt(8, conversions);
			pstmt.setFloat(9, totalCost);
			pstmt.setFloat(10, CTR);
			pstmt.setFloat(11, CPA);
			pstmt.setFloat(12, CPC);
			pstmt.setFloat(13, CPM);
			
			// Add it to the batch
			pstmt.addBatch();

			//Create an int[] to hold returned values
			pstmt.executeBatch();

			//Explicitly commit statements to apply changes
			conn.commit();
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
      
	}
	
	public OverviewItems selectCampaign(int ID) {
		
		String sql = "SELECT * FROM CAMPAIGN\n"
				+ "WHERE rowid = " + ID + ";";
		try (
				Connection conn = DriverManager.getConnection(DBNAME);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				) {

			//TODO Use original types instead of string to improve performance.
			if ( rs.next() ) {
				refDate = rs.getLong("START_DATE");
				endDate = rs.getLong("END_DATE");
				selectedCampaign = ID;
				return new OverviewItems(
						rs.getInt("IMPRESSIONS"),
						rs.getInt("CLICKS"),
						rs.getInt("UNIQUES"),
						rs.getInt("CONVERSIONS"),
						rs.getFloat("TOTAL_COST"),
						rs.getFloat("CTR"),
						rs.getFloat("CPA"),
						rs.getFloat("CPC"),
						rs.getFloat("CPM"));
			}
			return null;

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return null;
	}
	
	public ArrayList<Integer> getCampaigns() {
		
		String sql = "SELECT rowid FROM CAMPAIGN\n";
		ArrayList<Integer> campaigns = new ArrayList<Integer>();
				
		try (
				Connection conn = DriverManager.getConnection(DBNAME);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				) {

			while ( rs.next() ) {
				campaigns.add(rs.getInt(1));
			}
			return campaigns;

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return null;
		
	}
	
	public ArrayList<ArrayList<Object>> getCampaignNamesAndIds() {
		ArrayList<ArrayList<Object>> allIdAndNameLists = new ArrayList<ArrayList<Object>>();
		
		String sql = "SELECT rowid, name FROM CAMPAIGN\n";
				
		try (
				Connection conn = DriverManager.getConnection(DBNAME);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				) {

			while ( rs.next() ) {
				ArrayList<Object> idAndNameList = new ArrayList<Object>();
				idAndNameList.add(rs.getInt(1));
				idAndNameList.add(rs.getString(2));
				allIdAndNameLists.add(idAndNameList);
			}
			return allIdAndNameLists;

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return null;
		
	}
	
	private void getRowID() {
		String sql = "SELECT max(rowid) FROM CAMPAIGN\n";
				
		try (
				Connection conn = DriverManager.getConnection(DBNAME);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				) {

			if ( rs.next() ) {
				rowID = rs.getInt(1) + 1;
			}
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
	
	public void init() {
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			// Register driver.
			DriverManager.registerDriver(new org.sqlite.JDBC());
			
			// Establish connection and create db if does not exist.
			conn = DriverManager.getConnection(DBNAME);
			
			// Create tables if do not exist.			
			stmt = conn.createStatement();
	        
			String campaignT = "CREATE TABLE IF NOT EXISTS CAMPAIGN (\n"
	                + "	NAME TEXT,\n"
					+ " START_DATE INTEGER,\n"
	                + " END_DATE INTEGER,\n"
					+ " IMPRESSIONS INTEGER,\n"
					+ " CLICKS INTEGER,\n"
					+ " UNIQUES INTEGER,\n"
					+ " CONVERSIONS INTEGER,\n"
					+ " TOTAL_COST REAL,\n"
					+ " CTR REAL,\n"
					+ " CPA REAL,\n"
					+ " CPC REAL,\n"
					+ " CPM REAL\n"
	                + ");";
			
			String impressionT = "CREATE TABLE IF NOT EXISTS IMPRESSION_LOG (\n"
					+ "	CAMPAIGN INTEGER,\n"
	                + "	R_DATE INTEGER,\n"
	                + "	ID INTEGER,\n"
	                + "	GENDER TEXT,\n"
	                + "	AGE TEXT,\n"
	                + "	INCOME TEXT,\n"
	                + "	CONTEXT TEXT,\n"
	                + "	IMPRESSION_COST REAL\n"
	                + ");";
			
			String clickT = "CREATE TABLE IF NOT EXISTS CLICK_LOG (\n"
					+ "	CAMPAIGN INTEGER,\n"
					+ "	R_DATE INTEGER,\n"
	                + "	ID INTEGER,\n"
	                + "	CLICK_COST REAL,\n"
					+ "	ENTRY_DATE TEXT,\n"
					+ "	EXIT_DATE TEXT,\n"
					+ "	PAGES_VIEWED INTEGER,\n"
	                + "	CONVERSION TEXT\n"
	                + ");";
			
//			String serverT = "CREATE TABLE IF NOT EXISTS SERVER_LOG (\n"
//					+ "	CAMPAIGN INTEGER,\n"
//					+ " R_DATE INTEGER,\n"
//					+ "	ENTRY_DATE TEXT,\n"
//	                + "	ID INTEGER,\n"
//					+ "	EXIT_DATE TEXT,\n"
//					+ "	PAGES_VIEWED INTEGER,\n"
//	                + "	CONVERSION TEXT\n"
//	                + ");";
			
			String indexI = "CREATE INDEX IF NOT EXISTS ID_I ON IMPRESSION_LOG (ID)";
			
	        stmt.executeUpdate(campaignT);
	        stmt.executeUpdate(impressionT);
	        stmt.executeUpdate(clickT);
//	        stmt.executeUpdate(serverT);
	        stmt.executeUpdate(indexI);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	    	  try {
	    		if(stmt!=null)
				stmt.close();
	    	  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	    	  }
	    	  try {
	    		if(conn!=null)
				conn.close();
	    	  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	    	  }
	    }
	}
	
}

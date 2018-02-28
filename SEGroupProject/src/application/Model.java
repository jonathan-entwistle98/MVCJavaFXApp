package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Model {
	
	public ArrayList<String> clickLogList = new ArrayList<String>();
	public ArrayList<String> serverLogList = new ArrayList<String>();
	public ArrayList<String> impressionLogList = new ArrayList<String>();
	
	/**
	 * Reads in 3 csv files, splitting each csv file by line and adding these lines to 3 separate arrayLists
	 * (one arrayList per csv file)
	 */
	public void loadCSVData(){
		
		try {
			Scanner scannerClickLog = new Scanner(new File("click_log.csv"));
			Scanner scannerServerLog = new Scanner(new File("server_log.csv"));
			Scanner scannerImpressionLog = new Scanner(new File("impression_log.csv"));
			
			while(scannerClickLog.hasNextLine()){
			
				String clickLogLine = scannerClickLog.nextLine();
				String[] clickLogArray = clickLogLine.split("[\\r\\n]+");
				clickLogList.add(clickLogArray[0]);
			}
				
			while(scannerServerLog.hasNextLine()){
				String serverLogLine = scannerServerLog.nextLine();
				String[] serverLogArray = serverLogLine.split("[\\r\\n]+");
				serverLogList.add(serverLogArray[0]);
			}
			
			while(scannerImpressionLog.hasNextLine()){
				String impressionLogLine = scannerImpressionLog.nextLine();
				String[] impressionLogArray = impressionLogLine.split("[\\r\\n]+");
				impressionLogList.add(impressionLogArray[0]);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("click log +++++++++");
		System.out.println(clickLogList.get(0));
		System.out.println(clickLogList.get(1));
		System.out.println(clickLogList.get(2));
		System.out.println(clickLogList.size());
		System.out.println("click log ---------");
		
		System.out.println("server log +++++++++");
		System.out.println(serverLogList.get(0));
		System.out.println(serverLogList.get(1));
		System.out.println(serverLogList.get(2));
		System.out.println(serverLogList.size());
		System.out.println("server log ---------");
		
		System.out.println("impression log +++++++++");
		System.out.println(impressionLogList.get(0));
		System.out.println(impressionLogList.get(1));
		System.out.println(impressionLogList.get(2));
		System.out.println(impressionLogList.size());
		
		System.out.println("impression log ---------");
	}
	


	
}


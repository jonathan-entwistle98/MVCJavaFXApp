import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AdDashboard{
	
	private Controller controller;
	
	public static void main(String[] args) {
		
		 AdDashboard main = new AdDashboard();
		 main.init(args);
		 
	//	launch(args);
	}


	
	/**
	 * Creates a new Scene, adds this scene to the stage and sets the css and fxml files to be used.
	 * Controller and Model classes instantiated, and reference to model passed to controller
	 */
	public void init(String[] args) {
		View view = new View();
		Application.launch(View.class, args);
		
//		View view = new View(primaryStage);
			
	//	FXMLLoader loader = new FXMLLoader(getClass().getResource("viewDashboard.fxml"));
		
		controller = new Controller();
		
//		FXMLLoader loader = view.getLoader();
		
		view.setController(controller);
		
		
		
		//view.setRoot(root);
		
		Model model = new Model();
		model.loadCSVData();
		
		controller.initModel(model);	

//		primaryStage.setTitle("Dashboard");
//		Scene scene = new Scene(root, 1000, 600);
//		scene.getStylesheets().add("style.css");
//		primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
//		primaryStage.setScene(scene);
//		primaryStage.show();
		
	}
	

}

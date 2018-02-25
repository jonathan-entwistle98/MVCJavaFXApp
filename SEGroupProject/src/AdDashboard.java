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

public class AdDashboard extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	
	/**
	 * Creates a new Scene, adds this scene to the stage and sets the css and fxml files to be used.
	 * Controller and Model classes instantiated, and reference to model passed to controller
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
			
		FXMLLoader loader = new FXMLLoader(getClass().getResource("viewDashboard.fxml"));
		
		Controller controller = new Controller();
		
		loader.setController(controller);
		
		Parent root = loader.load();
		
		Model model = new Model();
		model.loadCSVData();
		
		controller.initModel(model);	

		primaryStage.setTitle("Dashboard");
		Scene scene = new Scene(root, 1000, 600);
		scene.getStylesheets().add("style.css");
		primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	

}

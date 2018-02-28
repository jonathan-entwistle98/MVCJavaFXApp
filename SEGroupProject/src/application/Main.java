package application;
	
import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		
	
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("NewXml.fxml")); 
			Controller controller = new Controller();
			loader.setController(controller);
			Parent root = loader.load();
			Model model = new Model();
			model.loadCSVData();
			controller.initModel(model);
			
			Scene scene = new Scene(root,1200,800);
			
		
			
			primaryStage.setTitle("Dashboard");
			
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Model extends Application {
	
	private boolean isLoggedIn = false;

	public static void main(String[] args) {
		launch(args);
	}
	
	/* 
	 * Creates a new Scene, adds this scene to the stage and sets the css and fxml files to be used.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("viewDashboard.fxml"));
		primaryStage.setTitle("Dashboard");
		Scene scene = new Scene(root, 1200, 600);
		scene.getStylesheets().add("style.css");
		primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
		primaryStage.setScene(scene);
		primaryStage.show();
	}		
	
}

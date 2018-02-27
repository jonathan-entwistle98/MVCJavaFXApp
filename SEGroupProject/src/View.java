import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class View extends Application{
	
	Stage stage;
	private FXMLLoader loader;
	private Controller controller;
	
	
//	public View(Stage stage) {
//		this.stage = stage;
//	}
	
	public void start(Stage primaryStage) throws Exception {
	
		
		loader = new FXMLLoader(getClass().getResource("viewDashboard.fxml"));
		
		Parent root = loader.load();
//		/////////////////////////////////////////
//		
//		Controller controller = new Controller();
//		
//		loader.setController(controller);
//		
//		Parent root = loader.load();
//		
//		Model model = new Model();
//		model.loadCSVData();
//		
//		controller.initModel(model);	
		/////////////////////////////////////////
		
		primaryStage.setTitle("Dashboard");
		Scene scene = new Scene(root, 1000, 600);
		scene.getStylesheets().add("style.css");
		primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public FXMLLoader getLoader() {
		return this.loader;
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	
}

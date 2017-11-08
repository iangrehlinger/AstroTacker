package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception{

		Parent root = FXMLLoader.load(getClass().getResource("/MainGUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Astrotracker");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
		
	}

}


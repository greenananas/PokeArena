package Controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXRouter.bind(this, primaryStage);

			FXRouter.when("start", "View/StartMenu.fxml");
			FXRouter.when("solo", "View/MenuSolo.fxml");
			FXRouter.when("multi", "View/MenuMulti.fxml");
			FXRouter.when("fight", "View/Fight.fxml");
			
			FXRouter.startFrom("start");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

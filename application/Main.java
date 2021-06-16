package application;

import Controller.MenuMultiController;
import javafx.application.Application;
import javafx.stage.Stage;
//
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {

			FXRouter.bind(this, primaryStage);

			FXRouter.when("start", "View/StartMenu.fxml");
			FXRouter.when("solo", "View/MenuSolo.fxml");
			FXRouter.when("multi", "View/MenuMulti.fxml");
			FXRouter.when("fight", "View/Fight.fxml");
			FXRouter.when("lobby", "View/Lobby.fxml");

			FXRouter.startFrom("start");

		} catch (Exception e) {
			System.out.println("balec");
		}
	}

	@Override
	public void stop() {
		MenuMultiController.end();
		System.out.println("bye");

	}

	public static void main(String[] args) {
		launch(args);
	}
}

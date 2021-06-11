package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.FXRouter;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
//

public class StartMenuController implements Initializable {

	@FXML	private BorderPane mainPane;
	@FXML	private HBox hbox;
	@FXML	private VBox vbox;
	@FXML	private Button soloButton;
	@FXML	private Button startButton;
	@FXML	private Button multiButton;
	@FXML	private Button returnButton;
	@FXML	private Button teamButton;
	@FXML	private MediaView mediaView;
	@FXML	private ImageView imageLogo;
	@FXML	private AnchorPane menuSolo;
	@FXML	private TextField namePlayer;
	@FXML	private ChoiceBox<String> teamList;
	@FXML	private ChoiceBox<?> modeList;
	@FXML	private Label errLabel;

	private MediaPlayer mediaPlayer;
	private Media media;
	
	// Event Listener on Button[#startButton].onAction
	@FXML
	public void handleStartButton(ActionEvent event) {


		if (namePlayer.getText().isEmpty()) {
			errLabel.setVisible(true);

		} else {

			System.out.println("start");
			//recup name 

			hbox.getChildren().remove(vbox);

			soloButton.setVisible(true);
			multiButton.setVisible(true);
			returnButton.setVisible(true);
			teamButton.setVisible(true);
		}
	}

	// Event Listener on Button[#soloButton].onAction
	@FXML
	public void handleSoloButton(ActionEvent event) {


		hbox.getChildren().remove(soloButton);
		hbox.getChildren().remove(multiButton);
		teamButton.setVisible(false);
		returnButton.setVisible(true);

		Parent root;
		URL url = application.Main.class.getResource("/View/MenuSolo.fxml");
		FXMLLoader loader = new FXMLLoader(url);

		try {
			root = loader.load();
			mainPane.setCenter(root);

		} catch (IOException e) {
			System.out.println("Erreur chargement menu solo.");;
		}

	}

	// Event Listener on Button[#multiButton].onAction
	@FXML
	public void handleMultiButton(ActionEvent event) {
//		System.out.println("multi");
//		hbox.getChildren().remove(soloButton);
//		hbox.getChildren().remove(multiButton);
//		teamButton.setVisible(false);
//		returnButton.setVisible(true);
		
		Parent root;
		URL url = application.Main.class.getResource("/application/View/MenuMulti.fxml");
		FXMLLoader loader = new FXMLLoader(url);

		try {
			root = loader.load();
			mainPane.setCenter(root);

		} catch (IOException e) {
			System.out.println("Erreur chargement menu solo.");;
		}
	}

	// Event Listener on Button[#teamButton].onAction
	@FXML
	public void handleTeamButton(ActionEvent event) {
		System.out.println("team");
		
		
	}
		
	// Event Listener on Button[#returnButton].onAction
	@FXML
	public void handleReturnButton(ActionEvent event) {
		System.out.println("back");
		
		try {
			FXRouter.goTo("start");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		String path = new File("Resources/homeScreen.mp4").getAbsolutePath();
		media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);

		mediaView.setMediaPlayer(mediaPlayer);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.setAutoPlay(true);

		soloButton.setVisible(false);
		multiButton.setVisible(false);
		returnButton.setVisible(false);
		errLabel.setVisible(false);
		teamButton.setVisible(false);

	}
}

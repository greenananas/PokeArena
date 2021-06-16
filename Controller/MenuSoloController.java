package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Team;
import application.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;

public class MenuSoloController implements Initializable {
	@FXML
	private AnchorPane menuSolo;
	@FXML
	private TextField nameField;
	@FXML
	private ChoiceBox<Team> teamList;
	@FXML
	private ChoiceBox<String> modeList;
	@FXML
	private Label labelChooseTeam;
	@FXML
	private Button startButton;

	// Event Listener on Button[#startButton].onAction
	@FXML
	public void handleStartButton(ActionEvent event) {

		try {
			FXRouter.goTo("fight");

		} catch (IOException e) {			
			System.out.println("Erreur chargement menu solo.");
			e.printStackTrace();;
		}	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		labelChooseTeam.setText("Choisit ton equipe "); // + getNom

		// TODO
		// ajouter les choix de teamList

		modeList.getItems().clear();
		modeList.getItems().addAll("3v3", "6v6");
		modeList.getSelectionModel().select("3v3");
	}

}

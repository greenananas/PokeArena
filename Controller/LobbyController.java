package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Team;
import PokeArenaNetwork.Client.PokeArenaClient;
import PokeArenaNetwork.Client.PokeArenaClientState;
import PokeArenaNetwork.Server.PokeArenaServer;
import PokeArenaNetwork.Server.PokeArenaServerState;
import application.FXRouter;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

public class LobbyController implements Initializable {
	@FXML
	private ImageView j1Connected;
	@FXML
	private ImageView j2Connected;
	@FXML
	private Button startButton;
	@FXML
	private Label statusLabel;
	@FXML
	private ImageView imageLogo;
	@FXML
	private Button quitButton;

	private PokeArenaClient cli;
	private PokeArenaServer srv;
	private Team team;

	// Event Listener on Button[#startButton].onAction
	@FXML
	public void handleStartButton(ActionEvent event) {
				
		if (this.srv != null) {
			// In the hosting client (client 1)
			if (this.srv.getState() == PokeArenaServerState.WAITING_FOR_START) {
				this.srv.startBattle();
				try {
					FXRouter.goTo("fight", cli, srv);
				} catch (IOException e) {
					System.out.println("Erreur go to Fight *** "+srv.getState().toString());
					e.printStackTrace();
				}
			}

		} else {
			// In the joining client (client 2)
			if ((this.cli.getState() == PokeArenaClientState.WAITING_FOR_START)
					|| (this.cli.getState() == PokeArenaClientState.NEED_TO_SEND_ACTION)) {
				try {
					FXRouter.goTo("fight", cli, null);
				} catch (IOException e) {
					System.out.println("Erreur go to Fight *** "+cli.getState().toString());
					e.printStackTrace();
				}
			}

		}

	}

	// Event Listener on Label[#statusLabel].onMouseClicked
	@FXML
	public void updateStatus(MouseEvent event) {

		if (this.srv != null) {
			// In the hosting client (client 1)
			if (this.srv.getState()==PokeArenaServerState.WAITING_FOR_CLIENT_1_TEAM) {
				j2Connected.setImage(new Image("Resources/Buttons/Others/connectedRed.png"));
				this.cli.sendTeam(team);
				System.out.println("team sent pour j1");

				statusLabel.setText("STATUS : JOUEUR 2 CONNECTE");
			}
			
			if (this.srv.getState()==PokeArenaServerState.WAITING_FOR_START) {
				statusLabel.setText("STATUS : CLIQUER SUR COMMENCER");
				startButton.setStyle("-fx-background-color: #8f0500; \n	-fx-text-fill: #fff;");
			}

		} else {
			// In the joining client (client 2)
			this.cli.sendTeam(team);
			
			if ((this.cli.getState() == PokeArenaClientState.WAITING_FOR_START)
					|| (this.cli.getState() == PokeArenaClientState.NEED_TO_SEND_ACTION)) {
				statusLabel.setText("STATUS : CLIQUER SUR COMMENCER");
				startButton.setStyle("-fx-background-color: #8f0500; \n	-fx-text-fill: #fff;");
			}
		}

	}

	// Event Listener on Button[#quitButton].onAction
	@FXML
	public void handleQuitButton(ActionEvent event) {

		try {
			MenuMultiController.end();
			FXRouter.goTo("start");

		} catch (IOException e) {
			System.out.println("Erreur quit");
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.cli = (PokeArenaClient) FXRouter.getData1();
		this.srv = MenuMultiController.server;
		this.team = (Team) FXRouter.getData2();

		if (this.srv != null) {
			// In the hosting client (client 1)
			if (this.srv.getState() == PokeArenaServerState.WAITING_FOR_CLIENT2_TO_JOIN) {
				statusLabel.setText("STATUS : EN ATTENTE DE JOUEUR 2");

			} else {
				System.out.println("OHOH ERR INATENDUE :"+this.srv.getState().toString());
			}

		} else {
			// In the joining client (client 2)
			j2Connected.setImage(new Image("Resources/Buttons/Others/connectedRed.png"));
			statusLabel.setText("STATUS : EN ATTENTE D'UNE ACTION DE JOUEUR 1");
		}

	}
}

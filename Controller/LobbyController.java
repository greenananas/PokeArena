package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import Model.Team;
import PokeArenaNetwork.Client.PokeArenaClient;
import PokeArenaNetwork.Client.PokeArenaClientState;
import PokeArenaNetwork.Server.PokeArenaServer;
import PokeArenaNetwork.Server.PokeArenaServerState;
import application.FXRouter;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

public class LobbyController implements Initializable {
	@FXML
	private ImageView j2Gray;
	@FXML
	private Button startButton;
	@FXML
	private Label statusLabel;
	@FXML
	private ImageView imageLogo;
	@FXML
	private Button quitButton;
	@FXML
	private Label j2Connected;

	private PokeArenaClient cli;
	private PokeArenaServer srv;
	private Team team;

	// Event Listener on Button[#startButton].onAction
	@FXML
	public void handleStartButton(ActionEvent event) {
		
		System.out.println("--------CLI-----start---");
		System.out.println(this.cli.getState().toString());
		System.out.println("----------------");
		
		if (this.srv != null) {
			
			System.out.println("--------SRV----start----");
			System.out.println(this.srv.getState().toString());
			System.out.println("----------------");

			if (this.srv.getState() == PokeArenaServerState.WAITING_FOR_START) {
				this.srv.startBattle();
				try {
					FXRouter.goTo("fight");
				} catch (IOException e) {
					System.out.println("Erreur go to Fight");
				}
			}

		} else {
			if ((this.cli.getState() == PokeArenaClientState.WAITING_FOR_START)
					|| (this.cli.getState() == PokeArenaClientState.NEED_TO_SEND_ACTION)) {
				try {
					FXRouter.goTo("fight");
				} catch (IOException e) {
					System.out.println("Erreur go to Fight");
				}
			}

		}

	}

	// Event Listener on Label[#statusLabel].onMouseClicked
	@FXML
	public void updateStatus(MouseEvent event) {
		
		System.out.println("--------CLI--updt------");
		System.out.println(this.cli.getState().toString());
		System.out.println("----------------");

		if (this.srv != null) {
			
			System.out.println("--------SRV-----updt---");
			System.out.println(this.srv.getState().toString());
			System.out.println("----------------");

			switch (this.srv.getState()) {
			case WAITING_FOR_CLIENT2_TO_JOIN:
				statusLabel.setText("STATUS : EN ATTENTE DE JOUEUR 2");

			case WAITING_FOR_CLIENT_1_TEAM:
				statusLabel.setText("STATUS : EN ATTENTE DES EQUIPES");
				j2Gray.setVisible(false);
				this.cli.sendTeam(team);
				break;

			case WAITING_FOR_START:
				statusLabel.setText("STATUS : CLIQUER SUR COMMENCER");
				startButton.setStyle("-fx-background-color: #8f0500; \n	-fx-text-fill: #fff;");
				break;

			default:
				statusLabel.setText(this.cli.getState().toString());
				break;
			}

		} else {
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
		
		System.out.println("-------CLI---init------");
		System.out.println(this.cli.getState().toString());
		System.out.println("----------------");

		if (this.srv != null) {
			
			System.out.println("--------SRV---init-----");
			System.out.println(this.srv.getState().toString());
			System.out.println("----------------");

			if (this.srv.getState() == PokeArenaServerState.WAITING_FOR_CLIENT2_TO_JOIN) {
				statusLabel.setText("STATUS : EN ATTENTE DE JOUEUR 2");

			} else {
				System.out.println(this.srv.getState().toString());
			}

		} else {
			statusLabel.setText("STATUS : EN ATTENTE D'UNE ACTION DE JOUEUR 1");
			cli.sendTeam(team);
		}

	}
}

package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Model.*;
import PokeArenaNetwork.Client.PokeArenaClient;
import PokeArenaNetwork.Server.PokeArenaServer;
import application.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;

import javafx.scene.control.ChoiceBox;

//

public class MenuMultiController implements Initializable{
	@FXML
	private AnchorPane menuSolo;
	@FXML
	private Label labelChooseTeam;
	@FXML
	private ChoiceBox<String> modeList;
	@FXML
	private ChoiceBox<String> teamList;
	@FXML
	private Button hostButton;
	@FXML
	private TextField ipField;
	@FXML
	private Button joinButton;
	
	public static PokeArenaServer server;

	// Event Listener on Button[#hostButton].onAction
	@FXML
	public void handleHostButton(ActionEvent event) {
		// TODO

		server = new PokeArenaServer("localhost", 8887);
		server.start();

		PokeArenaClient client;
		Team team;
		
		if(modeList.getValue()=="3v3") {
			team = TeamBuilder.getTeamByName(teamList.getValue(),TeamBuilder.allTeams3);
		
		} else {
			team = TeamBuilder.getTeamByName(teamList.getValue(),TeamBuilder.allTeams6);

		}
		

		try {
			client = new PokeArenaClient("localhost", 8887);
			client.connect();

			
			while(!client.isOpen()) {}
				
			FXRouter.goTo("lobby", client, team); 

		} catch (URISyntaxException e) {

			System.out.println("Erreur connexion");
			
		} catch (IOException e) {
			System.out.println("Erreur chargement Lobby.fxml");
			
		} 


	}

	// Event Listener on Button[#joinButton].onAction
	@FXML
	public void handleJoinButton(ActionEvent event) {
		// TODO
		// check ipField -> affiche msg si erreur

		PokeArenaClient client;
		Team team;
		
		
		if(modeList.getValue()=="3v3") {
			team = TeamBuilder.getTeamByName(teamList.getValue(),TeamBuilder.allTeams3);
		
		} else {
			team = TeamBuilder.getTeamByName(teamList.getValue(),TeamBuilder.allTeams6);

		}
		

		try {
			client = new PokeArenaClient(ipField.getText(), 8887);
			client.connect();

			FXRouter.goTo("lobby", client, team);

		} catch (URISyntaxException e) {

			System.out.println("Erreur connexion");
			
		} catch (IOException e) {
			System.out.println("Erreur chargement Lobby.fxml");
			
		} 
	}
	
	
	public static void end() {
		if(server!=null) {
			try {
				server.stop();
			} catch (InterruptedException e) {
				System.out.println("pg");
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		TeamBuilder.load_teams();
		
		modeList.getItems().clear();
		modeList.getItems().addAll("3v3", "6v6");
		modeList.getSelectionModel().select("3v3");
		
		List<Team> listT = TeamBuilder.allTeams3;
		teamList.getItems().clear();
		for (Team team : listT) {
			teamList.getItems().addAll(team.getName());
		}
		modeList.getSelectionModel().select(listT.get(1).getName());
		for (Team team : listT) {
			System.out.println(team.getName());
		}
		
		modeList.getSelectionModel().selectedIndexProperty().addListener((ChangeListener<Object>) (observable, oldValue, newValue) -> {
			List<Team> tl;
			if(modeList.getValue() == "3v3") {
				// 6 teams
				tl = TeamBuilder.allTeams6;

			} else {
				// 3 teams
				tl = TeamBuilder.allTeams3;
			}

			teamList.getItems().clear();
			for (Team team : tl) {
				teamList.getItems().addAll(team.getName());
			}
			modeList.getSelectionModel().select(tl.get(1).getName());
		});
		
	}
}

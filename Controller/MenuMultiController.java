package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import Model.*;
import PokeArenaNetwork.Client.PokeArenaClient;
import PokeArenaNetwork.Server.PokeArenaServer;
import application.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
	@FXML
	private Label errLabel;
	
	public static PokeArenaServer server;

	// Event Listener on Button[#hostButton].onAction
	@FXML
	public void handleHostButton(ActionEvent event) {

		server = new PokeArenaServer("localhost", 8888);
		server.start();

		PokeArenaClient client;
		Team team = TeamBuilder.allTeams3.get(0);
		
		if(modeList.getValue()=="3v3") {
			team = TeamBuilder.getTeamByName(teamList.getValue(), TeamBuilder.allTeams3);
		
		} else {				
			
			team = TeamBuilder.getTeamByName(teamList.getValue(), TeamBuilder.allTeams6);
			
		}

		try {
			client = new PokeArenaClient(ipField.getText(), 8888);
			client.connect();

			
			while(!client.isOpen()) {}
				
			FXRouter.goTo("lobby", client, team); 

		}  catch (IOException e) {
			
			System.out.println("Erreur chargement Lobby.fxml");
			e.printStackTrace();
			
		}catch (Exception e) {

			System.out.println("Erreur connexion");
			errLabel.setVisible(true);
			
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
			client = new PokeArenaClient(ipField.getText(), 8888);
			client.connect();

			FXRouter.goTo("lobby", client, team);

		} catch (IOException e) {
			System.out.println("Erreur chargement Lobby.fxml");
			e.printStackTrace();
			
		} catch (Exception e) {
			System.out.println("Erreur connexion");
			errLabel.setVisible(true);

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
		String name = "";
		try {
			File myObj = new File("Resources/name.txt");
			Scanner myReader = new Scanner(myObj);
			name= myReader.nextLine();
			myReader.close();

		} catch (IOException e) {
			System.out.println("Erreur file name.txt missing");
			e.printStackTrace();
		}
		labelChooseTeam.setText("Choisit ton equipe " + name);

		modeList.getItems().clear();
		modeList.getItems().addAll("3v3", "6v6");
		modeList.getSelectionModel().select("3v3");

		List<Team> listT = TeamBuilder.allTeams3;
		teamList.getItems().clear();
		for (Team team : listT) {
			teamList.getItems().addAll(team.getName());
		}
		teamList.getSelectionModel().select(0);

		modeList.getSelectionModel().selectedIndexProperty().addListener((ChangeListener<Object>) (observable, oldValue, newValue) -> {
			List<Team> tl = new ArrayList<Team>();

			if(modeList.getValue() == "3v3") {
				// 6 teams
				tl = TeamBuilder.allTeams6;

			} else {
				// 3 teams
				tl = TeamBuilder.allTeams3;
			}
			
			ArrayList<String> names = new ArrayList<>();
			
			teamList.getItems().clear();
			for (Team team : tl) {
				names.add(team.getName());
				//teamList.getItems().addAll(team.getName());
			}
			teamList.setItems(FXCollections.observableArrayList(names));
			
			teamList.getSelectionModel().select(tl.get(1).getName());
		});
		
	}
}

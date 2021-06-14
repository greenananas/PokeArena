package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.*;
import PokeArenaNetwork.Client.PokeArenaClient;
import PokeArenaNetwork.Server.PokeArenaServer;
import application.FXRouter;

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
	private ChoiceBox<Team> teamList;
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

		// TEMPORAIRE A SUPPRIMER --- Initialisation d'objets pour l'exemple
		Move dracocharge = new Move("Dracocharge", PokeTypes.type.DRAGON, true, 100, 70, 16, 0, 0);
		Move dracogriffe = new Move("Dracogriffe", PokeTypes.type.DRAGON, true, 80, 100, 24, 0, 0);
		Move seisme = new Move("Séisme", PokeTypes.type.GROUND, true, 100, 100, 16, 0, 0);
		Move lanceflammes = new Move("Lance-Flammes", PokeTypes.type.FIRE, false, 90, 100, 24, 0, 0);
		Move poingmeteor = new Move("Poing Météore", PokeTypes.type.STEEL, true, 90, 90, 16, 0, 0);
		Move poingglace = new Move("Poing Glace", PokeTypes.type.ICE, true, 75, 100, 24, 0, 0);
		Move poingeclair = new Move("Poing Éclair", PokeTypes.type.ELECTRIC, true, 75, 100, 24, 0, 0);
		Move surf = new Move("Surf", PokeTypes.type.WATER, false, 95, 100, 24, 0, 0);
		Move hydrocanon = new Move("Hydrocanon", PokeTypes.type.WATER, false, 120, 80, 8, 0, 0);
		Move luminocanon = new Move("Luminocanon", PokeTypes.type.STEEL, false, 80, 100, 16, 0, 0);
		Move laserglace = new Move("Laser Glace", PokeTypes.type.ICE, false, 95, 100, 16, 0, 0);

		Pokemon carchacrok = new Pokemon("Carchacrok", PokeTypes.type.DRAGON, PokeTypes.type.GROUND, 50, 108, 130, 95, 80, 85, 102, 0, 0, 0, 0, 0, 0, "HARDY", dracocharge, dracogriffe, seisme, lanceflammes);
		Pokemon metalosse = new Pokemon("Metalosse", PokeTypes.type.STEEL, PokeTypes.type.PSYCHIC, 50, 80, 135, 130, 95, 90, 70, 0, 0, 0, 0, 0, 0, "HARDY", poingmeteor, seisme, poingglace, poingeclair);
		Pokemon pingoleon = new Pokemon("Pingoléon", PokeTypes.type.WATER, PokeTypes.type.STEEL, 50, 84, 86, 88, 111, 101, 60, 0, 0, 0, 0, 0, 0, "HARDY", surf, hydrocanon, luminocanon, laserglace);

		Team team = new Team();
		team.addPokemon(carchacrok);
		team.addPokemon(metalosse);
		team.addPokemon(pingoleon);
		/////////////////////////////////////////////////////
		
		// A DECOMMENTER :
		//Team team = teamList.getValue();

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
		
		// TEMPORAIRE A MODIFIER --- Initialisation d'objets pour l'exemple		
		Move lamederock = new Move("Lame de Roc", PokeTypes.type.ROCK, true, 100, 80, 8, 0, 0);
		Move poingfeu = new Move("Poing Feu", PokeTypes.type.FIRE, true, 75, 100, 24, 0, 0);
		Move machouille = new Move("Machouille", PokeTypes.type.DARK, true, 80, 100, 24, 0, 0);
		Move lamedair = new Move("Lame d'Air", PokeTypes.type.FLYING, false, 75, 95, 24, 0, 0);
		Move eclatmagique = new Move("Éclat Magique", PokeTypes.type.FAIRY, false, 80, 100, 16, 0, 0);
		Move ballombre = new Move("Ball'Ombre", PokeTypes.type.GHOST, false, 80, 100, 24, 0, 0);		
		Move seisme = new Move("Séisme", PokeTypes.type.GROUND, true, 100, 100, 16, 0, 0);
		Move lanceflammes = new Move("Lance-Flammes", PokeTypes.type.FIRE, false, 90, 100, 24, 0, 0);
		Move poingglace = new Move("Poing Glace", PokeTypes.type.ICE, true, 75, 100, 24, 0, 0);
		Move poingeclair = new Move("Poing Éclair", PokeTypes.type.ELECTRIC, true, 75, 100, 24, 0, 0);
		
		Pokemon tyranocif = new Pokemon("Tyranocif", PokeTypes.type.ROCK, PokeTypes.type.DARK, 50, 100, 134, 110, 95, 100, 61, 0, 0, 0, 0, 0, 0, "HARDY", lamederock, poingfeu, seisme, machouille);
		Pokemon togekiss = new Pokemon("Togekiss", PokeTypes.type.FAIRY, PokeTypes.type.FLYING, 50, 85, 50, 95, 120, 115, 80, 0, 0, 0, 0, 0, 0, "HARDY", lamedair, eclatmagique, ballombre, lanceflammes);
		Pokemon elekable = new Pokemon("Elekable", PokeTypes.type.ELECTRIC, null, 50, 75, 123, 67, 95, 85, 95, 0, 0, 0, 0, 0, 0, "HARDY", poingeclair, poingfeu, poingglace, seisme);
		
		Team team = new Team();
		team.addPokemon(tyranocif);
		team.addPokemon(togekiss);
		team.addPokemon(elekable);
		//////////////////////////////////////////////

		// A DECOMMENTER :
		//Team team = teamList.getValue();

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
		// TODO 
		// remplir choices box

		
	}
}

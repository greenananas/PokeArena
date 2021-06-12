package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Pokemon;
import Model.Trainer;
import PokeArenaNetwork.Client.PokeArenaClient;
import PokeArenaNetwork.Server.PokeArenaServer;
import PokeArenaNetwork.Server.PokeArenaServerState;
import application.FXRouter;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
//

public class FightController implements Initializable {
	@FXML 	private Button returnButton;
	@FXML 	private Button quitButton;
	@FXML	private Button darkModeButton;

	@FXML	private ImageView imageLogo;
	@FXML	private ImageView attackButton;
	@FXML	private ImageView pokemonButton;
	@FXML	private ImageView battleGround;
	
	@FXML	private GridPane gridChoice;
	@FXML	private AnchorPane backgroundFight;
	
	@FXML	private HBox attackHbox;
	@FXML	private HBox pokemonHbox;
	
	// Event Listener on Button[#darkModeButton].onAction
	@FXML
	public void handleDarkModeButton(ActionEvent event) {
		if (backgroundFight.getStyle().equals("-fx-background-color: linear-gradient(to top, black, #4D2320)")) {
			backgroundFight.setStyle("-fx-background-color: linear-gradient(to top, #8EE66C ,#7AEBF0)");
		} else {
			backgroundFight.setStyle("-fx-background-color: linear-gradient(to top, black, #4D2320)");

		}
	}
	
	// Event Listener on Button[#returnButton].onAction
	@FXML
	public void handleReturnButton(ActionEvent event) {
		attackHbox.setVisible(false);
		pokemonHbox.setVisible(false);
		gridChoice.setVisible(true);
		returnButton.setVisible(false);

	}
	
	// Event Listener on Button[#returnButton].onAction
	@FXML
	public void handleQuitButton(ActionEvent event) {
		try {
			MenuMultiController.end();
			FXRouter.goTo("start");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("hyhy");
		}
	}
	
	// Event Listener on ImageView[#attackButton].onMouseClicked
	@FXML
	public void handleAttackButton(MouseEvent event) {
		attackHbox.setVisible(true);
		gridChoice.setVisible(false);
		returnButton.setVisible(true);
	}
	
	// Event Listener on ImageView[#pokemonButton].onMouseClicked
	@FXML
	public void handlePokemonButton(MouseEvent event) {
		pokemonHbox.setVisible(true);
		gridChoice.setVisible(false);
		returnButton.setVisible(true);

	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO
		/* set battleground
		 * set the 2 pokemon
		 * set the title with 2 names 
		 */
		
		PokeArenaClient client = (PokeArenaClient) FXRouter.getData1();
		PokeArenaServer serveur = (PokeArenaServer) FXRouter.getData2();

		Boolean waiting = true;
		
//		while(waiting) {
//			
//		}
		
//		switch(serveur.getState()) {
//		case WAITING_FOR_START :
//			System.out.println("Waiting for start");
//			break;
//		case WAITING_FOR_CLIENT2_TO_JOIN :
//			System.out.println("Waiting client 2");
//			break;
//			
//		default :
//			System.out.println("wut");
//			
//		}
     
//		if (serveur == null) {
//			// join
//			
//			
//		} else {
//			//host
//			
//		}

		
	}

}

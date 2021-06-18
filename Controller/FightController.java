package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import Model.ChangePkmn;
import Model.PokeStatus;
import Model.PokeStatus.Status;
import Model.Pokemon;
import PokeArenaNetwork.Client.PokeArenaClient;
import PokeArenaNetwork.Client.PokeArenaClientState;
import PokeArenaNetwork.Server.PokeArenaServer;
import application.FXRouter;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.GridPane;

public class FightController implements Initializable  {
	@FXML	private Label statusFight;
	
	@FXML 	private Button returnButton;
	@FXML 	private Button quitButton;
	@FXML	private Button darkModeButton;

	@FXML	private ImageView imageLogo;
	@FXML	private ImageView attackButton;
	@FXML	private ImageView pokemonButton;
	@FXML	private ImageView battleGround;

	@FXML	private ImageView imageCurrPkm;
	@FXML	private Label nameCurrPkm;
	@FXML	private ProgressBar pvCurrPkm;
	@FXML	private Label pvLabelCurrPkm;
	@FXML	private ImageView statusCurrPkm;
	
	@FXML	private ImageView imageOppPkm;
	@FXML	private Label nameOppPkm;
	@FXML	private ProgressBar pvOppPkm;
	@FXML	private Label pvLabelOppPkm;
	@FXML	private ImageView statusOppPkm;
	
	@FXML	private StackPane sp6;
	@FXML	private StackPane sp5;
	@FXML	private StackPane sp4;
	
//	@FXML	private ImageView p1;
//	@FXML	private Label nameP1;
	@FXML	private ImageView p2;
	@FXML	private Label nameP2;
	@FXML	private ImageView p3;
	@FXML	private Label nameP3;
	@FXML	private ImageView p4;
	@FXML	private Label nameP4;
	@FXML	private ImageView p5;
	@FXML	private Label nameP5;
	@FXML	private ImageView p6;
	@FXML	private Label nameP6;
	
	@FXML	private ImageView a1;
	@FXML	private Label nameA1;
	@FXML	private ImageView a2;
	@FXML	private Label nameA2;
	@FXML	private ImageView a3;
	@FXML	private Label nameA3;
	@FXML	private ImageView a4;
	@FXML	private Label nameA4;
	
	@FXML	private GridPane gridChoice;
	@FXML	private GridPane gridFight;
	@FXML	private AnchorPane backgroundFight;
	
	@FXML	private HBox attackHbox;
	@FXML	private HBox pokemonHbox;
	
	private PokeArenaClient cli;
	private PokeArenaServer srv;
	private float oldHP;
	
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
			System.out.println("Erreur quit");
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
	

	// Event Listener on ImageView[#a1].onMouseClicked
	@FXML
	public void handleA1(MouseEvent event) {
		
		if (cli.getState()==PokeArenaClientState.NEED_TO_SEND_ACTION) {
			cli.sendMove(cli.getTrainer().getLeadingPkmn().getMove1());	
		}
		updateAll();
		
	}
	// Event Listener on ImageView[#a2].onMouseClicked
	@FXML
	public void handleA2(MouseEvent event) {
		
		if (cli.getState()==PokeArenaClientState.NEED_TO_SEND_ACTION) {
			cli.sendMove(cli.getTrainer().getLeadingPkmn().getMove2());	
		}
		updateAll();
	}
	// Event Listener on ImageView[#a3].onMouseClicked
	@FXML
	public void handleA3(MouseEvent event) {

		if (cli.getState()==PokeArenaClientState.NEED_TO_SEND_ACTION) {
			cli.sendMove(cli.getTrainer().getLeadingPkmn().getMove3());	
		}
		updateAll();
	}
	// Event Listener on ImageView[#a4].onMouseClicked
	@FXML
	public void handleA4(MouseEvent event) {
		
		if (cli.getState()==PokeArenaClientState.NEED_TO_SEND_ACTION) {
			cli.sendMove(cli.getTrainer().getLeadingPkmn().getMove4());	
		}
		updateAll();
	}
	
	// Event Listener on ImageView[#p2].onMouseClicked
	@FXML
	public void handleP2(MouseEvent event) {

		System.out.println("p2 en action");
		if ((cli.getState() == PokeArenaClientState.NEED_TO_SEND_ACTION
				|| cli.getState() == PokeArenaClientState.NEED_TO_SEND_CHANGEPKMN)
				&& cli.getTrainer().getPokemonTeam().getPokemons().get(1).getHP()>0) {
			cli.sendChangePkmn(new ChangePkmn(1));
		}
		updateAll();
	}
	
	// Event Listener on ImageView[#p3].onMouseClicked
	@FXML
	public void handleP3(MouseEvent event) {
		
		if ((cli.getState() == PokeArenaClientState.NEED_TO_SEND_ACTION
				|| cli.getState() == PokeArenaClientState.NEED_TO_SEND_CHANGEPKMN)
				&& cli.getTrainer().getPokemonTeam().getPokemons().get(2).getHP()>0) {
			cli.sendChangePkmn(new ChangePkmn(2));
		}
		updateAll();
	}
	
	// Event Listener on ImageView[#p4].onMouseClicked
	@FXML
	public void handleP4(MouseEvent event) {
		
		if ((cli.getState() == PokeArenaClientState.NEED_TO_SEND_ACTION
				|| cli.getState() == PokeArenaClientState.NEED_TO_SEND_CHANGEPKMN)
				&& cli.getTrainer().getPokemonTeam().getPokemons().get(3).getHP()>0)  {
			cli.sendChangePkmn(new ChangePkmn(3));
		}
		updateAll();
	}
	
	// Event Listener on ImageView[#p5].onMouseClicked
	@FXML
	public void handleP5(MouseEvent event) {
		
		if ((cli.getState() == PokeArenaClientState.NEED_TO_SEND_ACTION
				|| cli.getState() == PokeArenaClientState.NEED_TO_SEND_CHANGEPKMN)
				&& cli.getTrainer().getPokemonTeam().getPokemons().get(4).getHP()>0)   {
			cli.sendChangePkmn(new ChangePkmn(4));
		}
		updateAll();
	}
	
	// Event Listener on ImageView[#p6].onMouseClicked
	@FXML
	public void handleP6(MouseEvent event) {
		
		if ((cli.getState() == PokeArenaClientState.NEED_TO_SEND_ACTION
				|| cli.getState() == PokeArenaClientState.NEED_TO_SEND_CHANGEPKMN)
				&& cli.getTrainer().getPokemonTeam().getPokemons().get(5).getHP()>0)   {
			cli.sendChangePkmn(new ChangePkmn(5));			
		}
		updateAll();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.cli = (PokeArenaClient) FXRouter.getData1();
		this.srv = (PokeArenaServer) FXRouter.getData2();
		
		FightController fightCtrlr = this;
		
		Platform.runLater(new Runnable() {
			 @Override public void run() {
			     cli.setCtrl(fightCtrlr);
			 }
			});
		
		ArrayList<Pokemon> listPkms = cli.getTrainer().getPokemonTeam().getPokemons();
		if(listPkms.size()<4) {	
			sp4.getChildren().remove(nameP4);
			sp5.getChildren().remove(nameP5);
			sp6.getChildren().remove(nameP6);
			
			sp4.getChildren().remove(p4);
			sp5.getChildren().remove(p5);
			sp6.getChildren().remove(p6);
		}

		this.oldHP = cli.getTrainer().getLeadingPkmn().getMaxHP();
		updateAll();	

	}
	
	@FXML
	public void updateAll() {
		
	
		switch (cli.getState()) {
		case ACTION_SENT:
			statusFight.setText("EN ATTENTE DE VOTRE ADVERSAIRE");
			break;
			
		case NEED_TO_SEND_CHANGEPKMN:
			statusFight.setText("CHOISIR UN AUTRE POKEMON");
			break;
			
		case NEED_TO_SEND_ACTION:
			statusFight.setText("QUE VOULEZ VOUS FAIRE ?");
			break;
		
		case BATTLE_LOST:
			statusFight.setText("PERDU, DOMMAGE.... ");
			break;
			
		case BATTLE_WON:
			statusFight.setText("GAGNE! FELICITATION");
			break;
		
		default:
			statusFight.setText(cli.getState().toString());
			break;
		}
		
		updateAttacks();
		updatePokemons();
	}
	
	
	private void updateAttacks() {
		
		String type1 = cli.getTrainer().getLeadingPkmn().getMove1().getMoveType().toString().toLowerCase();
		String type2 = cli.getTrainer().getLeadingPkmn().getMove2().getMoveType().toString().toLowerCase();
		String type3 = cli.getTrainer().getLeadingPkmn().getMove3().getMoveType().toString().toLowerCase();
		String type4 = cli.getTrainer().getLeadingPkmn().getMove4().getMoveType().toString().toLowerCase();

		String name1 = cli.getTrainer().getLeadingPkmn().getMove1().getName();
		String name2 = cli.getTrainer().getLeadingPkmn().getMove2().getName();
		String name3 = cli.getTrainer().getLeadingPkmn().getMove3().getName();
		String name4 = cli.getTrainer().getLeadingPkmn().getMove4().getName();
		
		try {
			a1.setImage(new Image("Resources/Buttons/Types/" + type1 + ".png"));
			a2.setImage(new Image("Resources/Buttons/Types/" + type2 + ".png"));
			a3.setImage(new Image("Resources/Buttons/Types/" + type3 + ".png"));
			a4.setImage(new Image("Resources/Buttons/Types/" + type4 + ".png"));
		} catch(Exception e) {
			System.out.println(type1 +"*"+ type2 +"*"+ type3 +"*"+ type4);
		}
		nameA1.setText(name1);
		nameA2.setText(name2);
		nameA3.setText(name3);
		nameA4.setText(name4);		
		
	}
	
	private void updatePokemons() {
		
		ArrayList<Pokemon> listPkms = cli.getTrainer().getPokemonTeam().getPokemons();
		
		String name2 = listPkms.get(1).getName();
		String name3 = listPkms.get(2).getName();
		
		nameP2.setText(name2);
		nameP3.setText(name3);
		
		if(listPkms.size()>3) {
				
			String name4 = listPkms.get(3).getName();
			String name5 = listPkms.get(4).getName();
			String name6 = listPkms.get(5).getName();

			nameP4.setText(name4);
			nameP5.setText(name5);		
			nameP6.setText(name6);
			
			if(listPkms.get(3).getHP()<=0) {
				p4.setImage(new Image("/Resources/Buttons/Others/pokeballIconDark.png"));
				nameP4.setStyle("	-fx-background-color: #a3a3a3;	\n"
						+ "	-fx-text-fill: #d7d7d7;");
			}		
			if(listPkms.get(4).getHP()<=0) {
				p5.setImage(new Image("/Resources/Buttons/Others/pokeballIconDark.png"));
				nameP5.setStyle("	-fx-background-color: #a3a3a3;	\n"
						+ "	-fx-text-fill: #d7d7d7;");
			}
			if(listPkms.get(5).getHP()<=0) {
				p6.setImage(new Image("/Resources/Buttons/Others/pokeballIconDark.png"));
				nameP6.setStyle("	-fx-background-color: #a3a3a3;	\n"
						+ "	-fx-text-fill: #d7d7d7;");			}
			
		}
			
		if(listPkms.get(1).getHP()<=0) {
			p2.setImage(new Image("Resources/Buttons/Others/pokeballIconDark.png"));
			nameP2.setStyle("	-fx-background-color: #a3a3a3;	\n"
					+ "	-fx-text-fill: #d7d7d7;");
		}
		if(listPkms.get(2).getHP()<=0) {
			p3.setImage(new Image("Resources/Buttons/Others/pokeballIconDark.png"));
			nameP3.setStyle("	-fx-background-color: #a3a3a3;	\n"
					+ "	-fx-text-fill: #d7d7d7;");
		}

		
		String path = "Resources/Sprites/back/"+ cli.getTrainer().getLeadingPkmn().getId() + ".png";
		imageCurrPkm.setImage(new Image(path));
		
		float maxHP = cli.getTrainer().getLeadingPkmn().getMaxHP();
		float currHP = cli.getTrainer().getLeadingPkmn().getHP();
		
		pvCurrPkm.setProgress(currHP/maxHP);
		pvLabelCurrPkm.setText((int)currHP + "/" + (int)maxHP);
		nameCurrPkm.setText(cli.getTrainer().getLeadingPkmn().getName());
		
		Status status = cli.getTrainer().getLeadingPkmn().getStatus().status;
		String statStr = "poisoned";
		
		switch(status) {
		case NORMAL:
			statusCurrPkm.setVisible(false);
			break;
		
		case BADLY_POISONED:
			statStr = "poisoned";
			break;
			
		default:
			statStr = status.toString().toLowerCase();
			System.out.println("--------------------------" + statStr + "---------------------------");
			break;
		}
		statusCurrPkm.setImage(new Image("Resources/Buttons/Status/"+ statStr +".png"));
		
		path = "Resources/Sprites/frontFrame2/"+ cli.getOpponentPokemon().getId() + ".png";
		imageOppPkm.setImage(new Image(path));
		
		maxHP = cli.getOpponentPokemon().getMaxHP();
		currHP = cli.getOpponentPokemon().getHP();
		
		pvOppPkm.setProgress(currHP/maxHP);
		pvLabelOppPkm.setText((int)currHP + "/" +  (int)maxHP);		
		nameOppPkm.setText(cli.getOpponentPokemon().getName());
		
		status = cli.getOpponentPokemon().getStatus().status;
		statStr = "poisoned";
		
		switch(status) {
		case NORMAL:
			statusOppPkm.setVisible(false);
			break;
		
		case BADLY_POISONED:
			statStr = "poisoned";
			break;
			
		default:
			statStr = status.toString().toLowerCase();
			break;
		}
		statusOppPkm.setImage(new Image("Resources/Buttons/Status/"+ statStr +".png"));
		
//		
//		if(this.oldHP >= currHP) {
//			imageCurrPkm.setTranslateX(-2);
//			try {
//				TimeUnit.SECONDS.sleep((long) 0.5);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			imageCurrPkm.setTranslateX(2);
//			
//		}
//
//		oldHP = currHP;
		
	}
}

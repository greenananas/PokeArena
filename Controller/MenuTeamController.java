package Controller;

import Model.*;
import Model.Utils.Pair;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MenuTeamController implements Initializable {
	@FXML
	private AnchorPane menuSet;
	@FXML
	private RadioButton team3;
	@FXML
	private RadioButton team6;

	/*
	 * Faire ceci sur la déclaration fxml pour tous les pokémons.
	 *
	 * p4.setEditable(true); p4.setMouseTransparent(false);
	 * p4.setFocusTraversable(true);
	 */
//    @FXML    private PokemonMenuItem p1;
//    @FXML    private PokemonMenuItem p2;
//    @FXML    private PokemonMenuItem p3;
//    @FXML    private PokemonMenuItem p4;
//    @FXML    private PokemonMenuItem p5;
//    @FXML    private PokemonMenuItem p6;
//    private ArrayList<PokemonMenuItem> pkmnCurrentTeam;
	@FXML
	private TextField p1;
	@FXML
	private TextField p2;
	@FXML
	private TextField p3;
	@FXML
	private TextField p4;
	@FXML
	private TextField p5;
	@FXML
	private TextField p6;
	private ArrayList<TextField> pkmnCurrentTeam;

	@FXML
	private Button addButton;
	@FXML
	private TextField teamNameField;
	@FXML
	private TextField errorField;
	@FXML
	private Button suppButton;
	@FXML
	private Button saveButton;
	@FXML
	private FilteredComboBox pkmnList;
	@FXML
	private FilteredComboBox teamList;
	@FXML
	private HBox hboxtest;

	// Event Listener on Button[#addButton].onAction
	@FXML
	public void handleAddButton(ActionEvent event) {
		int i = 0;
		while (i < 6 && !pkmnCurrentTeam.get(i).getText().equals("")) {
			i++;
		}
		if (i < 6) {
			pkmnCurrentTeam.get(i).setText(pkmnList.cb.getValue());
			pkmnCurrentTeam.get(i).setVisible(true);
		}
	}

	// Event Listener on Button[#saveButton].onAction
	@FXML
	public void handleSaveButton(ActionEvent event) {
		TeamBuilder nt = new TeamBuilder();
		ArrayList<String> team = new ArrayList<>();
		for (TextField tf : pkmnCurrentTeam) {
			team.add(tf.getText());
		}
		try {
			nt.create(team, teamNameField.getText());
		} catch (UnknownPokemonException e) {
			errorField.setText("Un des Pokémons séléctionnés n'existe pas !");
		} catch (MultipleSamePokemonException e) {
			errorField.setText("Vous ne pouvez pas avoir plusieurs fois le même Pokémon !");
		} catch (TeamNameAlreadyExistsException e) {
			errorField.setText("Vous ne pouvez pas avoir deux équipes avec le même nom !");
		}
	}

	// Event Listener on FilteredComboBox[#teamList].onAction
	@FXML
	public void handleTeamList(ActionEvent event) {
		ArrayList<Pokemon> poke = TeamBuilder.getTeamByName(teamList.cb.getValue(),
				(team3.isSelected() ? TeamBuilder.allTeams3 : TeamBuilder.allTeams6)).getPokemons();
		for (int p = 0; p < poke.size(); p++) {
			pkmnCurrentTeam.get(p).setText(poke.get(p).getName());
			pkmnCurrentTeam.get(p).setVisible(true);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		hboxtest = new FilteredComboBox();



		
		
		Pair<List<Integer>, List<String>> pkmnListItems = TeamBuilder.getAvailablePokemons();
		ArrayList<String> pkmnListData = new ArrayList<>();

		for (int i = 0; i < pkmnListItems.getLeft().size(); i++) {
			pkmnListData.add(pkmnListItems.getLeft().get(i) + " - " + pkmnListItems.getRight().get(i));
		}
		((FilteredComboBox) hboxtest).setData(pkmnListData);
		
		
//		
//		
//        ComboBox<String> cb = new ComboBox<String>();
//        cb.setEditable(true);
//
//        // Create a list with some dummy values.
//        ObservableList<String> items = FXCollections.observableArrayList("One", "Two", "Three", "Four", "Five", "Six",
//                "Seven", "Eight", "Nine", "Ten", "One", "Two", "Three", "Four", "Five", "Six",
//                "Seven", "Eight", "Nine", "Ten");
//
//        // Create a FilteredList wrapping the ObservableList.
//        FilteredList<String> filteredItems = new FilteredList<String>(items, p -> true);
//
//        // Add a listener to the textProperty of the combobox editor. The
//        // listener will simply filter the list every time the input is changed
//        // as long as the user hasn't selected an item in the list.
//        cb.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
//            final TextField editor = cb.getEditor();
//            final String selected = cb.getSelectionModel().getSelectedItem();
//
//            // This needs run on the GUI thread to avoid the error described
//            // here: https://bugs.openjdk.java.net/browse/JDK-8081700.
//            Platform.runLater(() -> {
//                // If the no item in the list is selected or the selected item
//                // isn't equal to the current input, we refilter the list.
//                if (selected == null || !selected.equals(editor.getText())) {
//                    filteredItems.setPredicate(item -> {
//                        // We return true for any items that starts with the
//                        // same letters as the input. We use toUpperCase to
//                        // avoid case sensitivity.
//                        if (item.toUpperCase().startsWith(newValue.toUpperCase())) {
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    });
//                }
//            });
//        });
//
//        cb.setItems(filteredItems);
		
//		ToggleGroup group = new ToggleGroup();
//		team3.getStyleClass().remove("radio-button");
//		team3.getStyleClass().add("toggle-button");
//		team6.getStyleClass().remove("radio-button");
//		team6.getStyleClass().add("toggle-button");
//		team3.setToggleGroup(group);
//		team6.setToggleGroup(group);

//		pkmnCurrentTeam.add(p1);
//		pkmnCurrentTeam.add(p2);
//		pkmnCurrentTeam.add(p3);
//		pkmnCurrentTeam.add(p4);
//		pkmnCurrentTeam.add(p5);
//		pkmnCurrentTeam.add(p6);
//
//		ArrayList<String> team3list = new ArrayList<>();
//		ArrayList<String> team6list = new ArrayList<>();
//		for (Team t : TeamBuilder.allTeams3) {
//			team3list.add(t.getName());
//		}
//		for (Team t : TeamBuilder.allTeams6) {
//			team6list.add(t.getName());
//		}
//
//		group.selectedToggleProperty().addListener((ob, o, n) -> {
//			RadioButton rb = (RadioButton) group.getSelectedToggle();
//			if (rb != null) {
//				if (rb.getId().equals("team3")) {
//					p4.setVisible(false);
//					p5.setVisible(false);
//					p6.setVisible(false);
//					teamList.clear();
//					teamList.setData(team3list);
//					for (TextField pmi : pkmnCurrentTeam) {
//						pmi.setText("");
//						pmi.setVisible(false); //bouton associé
//					}
//				} else {
//					p4.setVisible(true);
//					p5.setVisible(true);
//					p6.setVisible(true);
//					teamList.clear();
//					teamList.setData(team6list);
//					for (TextField pmi : pkmnCurrentTeam) {
//						pmi.setText("");
//						pmi.setVisible(false);
//					}
//				}
//			}
//		});
//		team3.setSelected(true);
//
//		// Initializing the list of all Pokemons.
//		Pair<List<Integer>, List<String>> pkmnListItems = TeamBuilder.getAvailablePokemons();
//		ArrayList<String> pkmnListData = new ArrayList<>();
//
//		for (int i = 0; i < pkmnListItems.getLeft().size(); i++) {
//			pkmnListData.add(pkmnListItems.getLeft().get(i) + " - " + pkmnListItems.getRight().get(i));
//		}
//		pkmnList.setData(pkmnListData);
	}
}

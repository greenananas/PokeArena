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

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;

public class MenuTeamController implements Initializable {
    @FXML
    private AnchorPane menuSet;
    @FXML
    private RadioButton team3;
    @FXML
    private RadioButton team6;
    @FXML
    /* Faire ceci sur la déclaration fxml pour tous les pokémons.
     *
     * p4.setEditable(true);
     * p4.setMouseTransparent(false);
     * p4.setFocusTraversable(true);
     */
    private PokemonMenuItem p1;
    @FXML
    private PokemonMenuItem p2;
    @FXML
    private PokemonMenuItem p3;
    @FXML
    private PokemonMenuItem p4;
    @FXML
    private PokemonMenuItem p5;
    @FXML
    private PokemonMenuItem p6;
    private ArrayList<PokemonMenuItem> pkmnCurrentTeam;
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

    // Event Listener on Button[#addButton].onAction
    @FXML
    public void handleAddButton(ActionEvent event) {
        int i = 0;
        while (i < 6 && !pkmnCurrentTeam.get(i).getText().equals("")) {
            i++;
        }
        if (i < 6) {
            pkmnCurrentTeam.get(i).setText(pkmnList.cb.getValue());
            pkmnCurrentTeam.get(i).display(true);
        }
    }

    // Event Listener on Button[#saveButton].onAction
    @FXML
    public void handleSaveButton(ActionEvent event) {
        TeamBuilder nt = new TeamBuilder();
        ArrayList<String> team = new ArrayList<>();
        for (PokemonMenuItem pmi : pkmnCurrentTeam) {
            team.add(pmi.getText());
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
        ArrayList<Pokemon> poke = TeamBuilder.getTeamByName(teamList.cb.getValue(), (team3.isSelected() ? TeamBuilder.allTeams3 : TeamBuilder.allTeams6)).getPokemons();
        for (int p = 0; p < poke.size(); p++) {
            pkmnCurrentTeam.get(p).setText(poke.get(p).getName());
            pkmnCurrentTeam.get(p).display(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup group = new ToggleGroup();
        team3.getStyleClass().remove("radio-button");
        team3.getStyleClass().add("toggle-button");
        team6.getStyleClass().remove("radio-button");
        team6.getStyleClass().add("toggle-button");
        team3.setToggleGroup(group);
        team6.setToggleGroup(group);

        pkmnCurrentTeam.add(p1);
        pkmnCurrentTeam.add(p2);
        pkmnCurrentTeam.add(p3);
        pkmnCurrentTeam.add(p4);
        pkmnCurrentTeam.add(p5);
        pkmnCurrentTeam.add(p6);

        ArrayList<String> team3list = new ArrayList<>();
        ArrayList<String> team6list = new ArrayList<>();
        for (Team t : TeamBuilder.allTeams3) {
            team3list.add(t.getName());
        }
        for (Team t : TeamBuilder.allTeams6) {
            team6list.add(t.getName());
        }

        group.selectedToggleProperty().addListener((ob, o, n) -> {
            RadioButton rb = (RadioButton) group.getSelectedToggle();
            if (rb != null) {
                if (rb.getId().equals("team3")) {
                    p4.setVisible(false);
                    p5.setVisible(false);
                    p6.setVisible(false);
                    teamList.clear();
                    teamList.setData(team3list);
                    for (PokemonMenuItem pmi : pkmnCurrentTeam) {
                        pmi.setText("");
                        pmi.display(false);
                    }
                } else {
                    p4.setVisible(true);
                    p5.setVisible(true);
                    p6.setVisible(true);
                    teamList.clear();
                    teamList.setData(team6list);
                    for (PokemonMenuItem pmi : pkmnCurrentTeam) {
                        pmi.setText("");
                        pmi.display(false);
                    }
                }
            }
        });
        team3.setSelected(true);

        //Initializing the list of all Pokemons.
        Pair<List<Integer>, List<String>> pkmnListItems = TeamBuilder.getAvailablePokemons();
        ArrayList<String> pkmnListData = new ArrayList<>();
        for (int i = 0; i < pkmnListItems.getLeft().size(); i++) {
            pkmnListData.add(pkmnListItems.getLeft().get(i) + " - " + pkmnListItems.getRight().get(i));
        }
        pkmnList.setData(pkmnListData);
    }
}

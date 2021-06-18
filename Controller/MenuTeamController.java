package Controller;

import Model.*;
import Model.Utils.Pair;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private Label p1;
    @FXML
    private Label p2;
    @FXML
    private Label p3;
    @FXML
    private Label p4;
    @FXML
    private Label p5;
    @FXML
    private Label p6;
    private ArrayList<Label> pkmnCurrentTeam;

    @FXML
    private Button addButton;
    @FXML
    private ChoiceBox<String> teamField;
    @FXML
    private TextField teamNameField;
    @FXML
    private Button suppButton;
    @FXML
    private Button saveButton;
    @FXML
    private ComboBox<String> pkmnList;
    @FXML
    private Label msgLabel;

    ArrayList<String> team3list;
    ArrayList<String> team6list;

    // Event Listener on Button[#addButton].onAction
    @FXML
    public void handleAddButton(ActionEvent event) {
        int i = 0;
        while (i < 6 && !pkmnCurrentTeam.get(i).getText().equals("---")) {
            i++;
        }
        if (i < 6) {
            pkmnCurrentTeam.get(i).setText(pkmnList.getValue().split(" - ")[1]);
            ImageView img = new ImageView(new Image("Resources/Sprites/frontFrame2/" + pkmnList.getValue().split(" - ")[0] + ".png"));
            img.setFitHeight(40);
            img.setPreserveRatio(true);
            pkmnCurrentTeam.get(i).setGraphic(img);
        }
    }

    @FXML
    public void handleDelButton(MouseEvent event) {
        Label item = (Label) event.getSource();
        int i = 0;
        while (i < 5 && pkmnCurrentTeam.get(i) != item) {
            i++;
        }
        int k = i;
        while (k < 5 && !pkmnCurrentTeam.get(k).getText().equals("---")) {
            k++;
        }
        for (int j = i; j < k; j++) {
            pkmnCurrentTeam.get(j).setText(pkmnCurrentTeam.get(j + 1).getText());
            pkmnCurrentTeam.get(j + 1).setText("---");
            pkmnCurrentTeam.get(j).setGraphic(pkmnCurrentTeam.get(j + 1).getGraphic());
            pkmnCurrentTeam.get(j + 1).setGraphic(null);
        }
    }

    // Event Listener on Button[#saveButton].onAction
    @FXML
    public void handleSaveButton(ActionEvent event) {
        TeamBuilder nt = new TeamBuilder();
        ArrayList<String> team = new ArrayList<>();
        for (Label lbl : pkmnCurrentTeam) {
            if (!lbl.getText().equals("---")) team.add(lbl.getText());
        }
        try {
            if (teamField.getValue().equals("--Nouvelle équipe--")) {
                nt.create(team, teamNameField.getText());
                msgLabel.setText("L'équipe a bien été créée.");
                teamField.getItems().add(teamNameField.getText());
            }
            else {
                TeamBuilder.remove_team(teamField.getValue(), (team3.isSelected() ? 3 : 6));
                nt.create(team, teamNameField.getText());
                msgLabel.setText("L'équipe a bien été modifiée.");
                teamField.getItems().add(teamNameField.getText());
            }

        } catch (UnknownPokemonException e) {
            msgLabel.setText("Un des Pokémons séléctionnés n'existe pas !");
        } catch (MultipleSamePokemonException e) {
            msgLabel.setText("Vous ne pouvez pas avoir plusieurs fois le même Pokémon !");
        } catch (TeamNameAlreadyExistsException e) {
            msgLabel.setText("Vous ne pouvez pas avoir deux équipes avec le même nom !");
        } catch (UnknownTeamException e) {
            msgLabel.setText("Vous ne pouvez pas modifier cette équipe.");
        } finally {
            reloadLists();
        }
    }

    // Event Listener on Button[#saveButton].onAction
    @FXML
    public void handleSuppButton(ActionEvent event) {
        try {
            TeamBuilder.remove_team(teamField.getValue(), (team3.isSelected() ? 3 : 6));
        } catch (UnknownTeamException e) {
            e.printStackTrace();
        }
        msgLabel.setText("L'équipe a bien été supprimée.");
        for (Label lbl : pkmnCurrentTeam) {
            lbl.setText("---");
            lbl.setGraphic(null);
        }
        reloadLists();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        teamField.getSelectionModel().selectedIndexProperty().addListener((ChangeListener<Object>) (observable, oldValue, newValue) -> {
            if (!newValue.equals(-1) && !newValue.equals(0)){
                ArrayList<Pokemon> poke = TeamBuilder.getTeamByName(teamField.getItems().get((int)newValue),
                        (team3.isSelected() ? TeamBuilder.allTeams3 : TeamBuilder.allTeams6)).getPokemons();
                for (int p = 0; p < poke.size(); p++) {
                    pkmnCurrentTeam.get(p).setText(poke.get(p).getName());
                    ImageView img = new ImageView(new Image("Resources/Sprites/frontFrame2/" + poke.get(p).getId() + ".png"));
                    img.setFitHeight(40);
                    img.setPreserveRatio(true);
                    pkmnCurrentTeam.get(p).setGraphic(img);
                }
            }
        });
        Pair<List<Integer>, List<String>> pkmnListItems = TeamBuilder.getAvailablePokemons();
        ArrayList<String> pkmnListData = new ArrayList<>();

        for (int i = 0; i < pkmnListItems.getLeft().size(); i++) {
            pkmnListData.add(pkmnListItems.getLeft().get(i) + " - " + pkmnListItems.getRight().get(i));
        }
        pkmnList.setItems(FXCollections.observableArrayList(pkmnListData));

        ToggleGroup group = new ToggleGroup();
        team3.setToggleGroup(group);
        team6.setToggleGroup(group);

        pkmnCurrentTeam = new ArrayList<>();
        pkmnCurrentTeam.add(p1);
        pkmnCurrentTeam.add(p2);
        pkmnCurrentTeam.add(p3);
        pkmnCurrentTeam.add(p4);
        pkmnCurrentTeam.add(p5);
        pkmnCurrentTeam.add(p6);

        reloadLists();

        group.selectedToggleProperty().addListener((ob, o, n) -> {
            RadioButton rb = (RadioButton) group.getSelectedToggle();
            if (rb != null) {
                teamField.getItems().clear();
                teamField.getItems().add("--Nouvelle équipe--");
                teamField.getSelectionModel().select(0);
                if (rb.getId().equals("team3")) {
                    p4.setVisible(false);
                    p5.setVisible(false);
                    p6.setVisible(false);
                    teamField.getItems().addAll(team3list);
                    for (Label lbl : pkmnCurrentTeam) {
                        lbl.setText("---");
                        lbl.setGraphic(null);
                    }
                } else {
                    p4.setVisible(true);
                    p5.setVisible(true);
                    p6.setVisible(true);
                    teamField.getItems().addAll(team6list);
                    for (Label lbl : pkmnCurrentTeam) {
                        lbl.setText("---");
                        lbl.setGraphic(null);
                    }
                }
            }
        });
        team6.setSelected(true);
    }

    public void reloadLists() {
        teamField.getItems().clear();
        teamField.getItems().add("--Nouvelle équipe--");
        teamField.getSelectionModel().select(0);
        team3list = new ArrayList<>();
        team6list = new ArrayList<>();
        for (Team t : TeamBuilder.allTeams3) {
            team3list.add(t.getName());
        }
        for (Team t : TeamBuilder.allTeams6) {
            team6list.add(t.getName());
        }
        teamField.getItems().addAll((team3.isSelected() ? team3list : team6list));
    }
}

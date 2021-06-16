package Controller;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class PokemonMenuItem extends HBox {
    private TextField pkmnLabel;
    private Button deleteButton;

    public PokemonMenuItem() {
        pkmnLabel = new TextField();
        deleteButton = new Button("Supprimer");
        deleteButton.setVisible(false);

        deleteButton.setOnAction(event -> {
            pkmnLabel.setText("");
            deleteButton.setVisible(false);
        });
    }

    public void setUp(TextField label) {
        pkmnLabel = label;
    }

    public void setText(String txt) {
        pkmnLabel.setText(txt);
    }

    public String getText() {
        return pkmnLabel.getText();
    }

    public void display(boolean t) {
        deleteButton.setVisible(t);
    }

}

package Controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class FilteredComboBox extends HBox {
    // Data list.
    ObservableList<String> items;

    ComboBox<String> cb;

    public FilteredComboBox() {

    	cb = new ComboBox<>();
        cb.setEditable(true);

        // Create a FilteredList wrapping the ObservableList.
        FilteredList<String> filteredItems = new FilteredList<String>(items, p -> true);

        // Add a listener to the textProperty of the combobox editor. The
        // listener will simply filter the list every time the input is changed
        // as long as the user hasn't selected an item in the list.
        cb.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            cb.show();
            final TextField editor = cb.getEditor();
            final String selected = cb.getSelectionModel().getSelectedItem();

            // This needs to run on the GUI thread to avoid the error described
            // here: https://bugs.openjdk.java.net/browse/JDK-8081700.
            Platform.runLater(() -> {
                // If there is no item in the list selected or the selected item
                // isn't equal to the current input, we refilter the list.
                if (selected == null || !selected.equals(editor.getText())) {
                    filteredItems.setPredicate(item -> {
                        // We return true for any items that contains the
                        // same letters as the input. We use toUpperCase to
                        // avoid case sensitivity.
                        if (item.toUpperCase().contains(newValue.toUpperCase())) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
            });
        });

        cb.setItems(filteredItems);

        this.getChildren().add(cb);
    }

    public void clear() {
        cb.getEditor().clear();
    }

    public void setData(ArrayList<String> data) {
        this.items = FXCollections.observableArrayList(data);
    }
}
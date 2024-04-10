package ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Allergy;

import java.util.Optional;

import dao.AllergyDAO;

public class AllergyListScreen extends Application {

    private TableView<Allergy> allergiesTable = new TableView<>();
    private ObservableList<Allergy> allergiesData = FXCollections.observableArrayList();
    private AllergyDAO allergyDAO = new AllergyDAO();
    private int patientId = 1; // Example patient ID, replace with actual ID as needed

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Allergies for Patient: " + patientId);

        // Define columns
        TableColumn<Allergy, String> allergenColumn = new TableColumn<>("Allergen");
        allergenColumn.setCellValueFactory(new PropertyValueFactory<>("allergen"));

        TableColumn<Allergy, String> reactionColumn = new TableColumn<>("Reaction");
        reactionColumn.setCellValueFactory(new PropertyValueFactory<>("reaction"));

        allergiesTable.getColumns().addAll(allergenColumn, reactionColumn);
        allergiesTable.setItems(allergiesData);

        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");

        addButton.setOnAction(e -> showAllergyDialog(null)); // Null indicates adding a new record

        editButton.setOnAction(e -> {
            Allergy selected = allergiesTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                showAllergyDialog(selected); // Pass selected for editing
            }
        });

        deleteButton.setOnAction(e -> deleteSelectedAllergy()); // New code for deleting

        editButton.setDisable(true);
        deleteButton.setDisable(true);
        allergiesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean isItemSelected = newSelection != null;
            editButton.setDisable(!isItemSelected);
            deleteButton.setDisable(!isItemSelected);
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(allergiesTable, addButton, editButton, deleteButton);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();

        loadAllergies();
    }

    private void loadAllergies() {
        allergiesData.clear();
        allergiesData.addAll(allergyDAO.getAll());
    }

    private void showAllergyDialog(Allergy allergy) {
        Dialog<Allergy> dialog = new Dialog<>();
        dialog.setTitle((allergy == null) ? "Add New Allergy" : "Edit Allergy");
        dialog.setHeaderText((allergy == null) ? "Enter details for the new allergy" : "Edit details of the allergy");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        TextField allergenField = new TextField();
        TextField reactionField = new TextField();

        if (allergy != null) {
            allergenField.setText(allergy.getAllergen());
            reactionField.setText(allergy.getReaction());
        }

        grid.add(new Label("Allergen:"), 0, 0);
        grid.add(allergenField, 1, 0);
        grid.add(new Label("Reaction:"), 0, 1);
        grid.add(reactionField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new Allergy(
                        allergy != null ? allergy.getAllergyID() : 0, // Use existing ID if editing
                        patientId,
                        allergenField.getText(),
                        reactionField.getText()
                );
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            if (allergy == null) {
                Allergy newAllergy = allergyDAO.save(result);
                allergiesData.add(newAllergy);
            } else {
                allergy.setAllergen(allergenField.getText());
                allergy.setReaction(reactionField.getText());
                allergyDAO.update(allergy);
                loadAllergies();
            }
        });
    }

    private void deleteSelectedAllergy() {
        Allergy selected = allergiesTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this allergy?", ButtonType.YES, ButtonType.NO);
            confirmAlert.setHeaderText("Confirm Deletion");
            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                allergyDAO.delete(selected.getAllergyID());
                allergiesData.remove(selected);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

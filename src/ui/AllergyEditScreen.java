package ui;

import dao.AllergyDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Allergy;

public class AllergyEditScreen {

    private AllergyDAO allergyDAO = new AllergyDAO();
    private Allergy allergy; // This can be null if adding a new allergy

    public AllergyEditScreen(Allergy allergy) {
        this.allergy = allergy; // If allergy is null, we're adding a new record
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle((allergy == null) ? "Add New Allergy" : "Edit Allergy");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Allergen Field
        Label allergenLabel = new Label("Allergen:");
        GridPane.setConstraints(allergenLabel, 0, 0);
        TextField allergenField = new TextField();
        if (allergy != null) allergenField.setText(allergy.getAllergen());
        GridPane.setConstraints(allergenField, 1, 0);

        // Reaction Field
        Label reactionLabel = new Label("Reaction:");
        GridPane.setConstraints(reactionLabel, 0, 1);
        TextField reactionField = new TextField();
        if (allergy != null) reactionField.setText(allergy.getReaction());
        GridPane.setConstraints(reactionField, 1, 1);

        // Save Button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            saveAllergy(allergenField.getText(), reactionField.getText());
            primaryStage.close(); // Close the stage after saving
        });
        GridPane.setConstraints(saveButton, 1, 2);

        grid.getChildren().addAll(allergenLabel, allergenField, reactionLabel, reactionField, saveButton);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveAllergy(String allergen, String reaction) {
        if (allergy == null) {
            // Adding a new Allergy
            allergy = new Allergy(); // Assuming default constructor is available
        }
        // Set or update properties
        allergy.setAllergen(allergen);
        allergy.setReaction(reaction);

        // Save or update in the database
        if (allergy.getAllergyID() == 0) {
            allergyDAO.save(allergy); // Add new record
        } else {
            allergyDAO.update(allergy); // Update existing record
        }
    }
}

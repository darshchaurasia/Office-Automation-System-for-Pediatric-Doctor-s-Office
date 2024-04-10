package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Patient;
import dao.PatientDAO;

public class PatientDetailScreen {

    private final PatientDAO patientDAO = new PatientDAO();

    public void start(Stage primaryStage, int patientId) {
        primaryStage.setTitle("Patient Details");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));

        // Fetch the patient details from the database
        Patient patient = patientDAO.getPatientById(patientId);
        if (patient == null) {
            layout.getChildren().add(new Label("Patient details not found."));
        } else {
            // Display patient details
            Label nameLabel = new Label("Name: " + patient.getFirstName() + " " + patient.getLastName());
            Label contactInfoLabel = new Label("Contact: " + patient.getContactInfo());
            // Add more labels for additional patient details
            
            layout.getChildren().addAll(nameLabel, contactInfoLabel);

            // Optionally, add buttons or links for editing patient details, viewing health records, etc.
            Button editButton = new Button("Edit");
            editButton.setOnAction(e -> {
                // Open PatientEditScreen for editing
                new PatientEditScreen().start(primaryStage, patient);
            });

            layout.getChildren().add(editButton);
        }

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

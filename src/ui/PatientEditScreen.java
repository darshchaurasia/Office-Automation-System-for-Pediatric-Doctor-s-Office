package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Patient;
import dao.PatientDAO;

public class PatientEditScreen {

    private final PatientDAO patientDAO = new PatientDAO();

    public void start(Stage primaryStage, Patient patient) {
        primaryStage.setTitle(patient != null ? "Edit Patient" : "Add New Patient");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // First Name
        Label firstNameLabel = new Label("First Name:");
        GridPane.setConstraints(firstNameLabel, 0, 0);
        TextField firstNameField = new TextField();
        firstNameField.setText(patient != null ? patient.getFirstName() : "");
        GridPane.setConstraints(firstNameField, 1, 0);

        // Last Name
        Label lastNameLabel = new Label("Last Name:");
        GridPane.setConstraints(lastNameLabel, 0, 1);
        TextField lastNameField = new TextField();
        lastNameField.setText(patient != null ? patient.getLastName() : "");
        GridPane.setConstraints(lastNameField, 1, 1);

        // Contact Info
        Label contactInfoLabel = new Label("Contact Info:");
        GridPane.setConstraints(contactInfoLabel, 0, 2);
        TextField contactInfoField = new TextField();
        contactInfoField.setText(patient != null ? patient.getContactInfo() : "");
        GridPane.setConstraints(contactInfoField, 1, 2);

        // Birthday, InsuranceInfo, PharmacyInfo etc. would be added in a similar fashion

        Button saveButton = new Button("Save");
        GridPane.setConstraints(saveButton, 1, 3);
        saveButton.setOnAction(e -> savePatient(primaryStage, patient, firstNameField.getText(), lastNameField.getText(), contactInfoField.getText() /* other fields as parameters */));

        grid.getChildren().addAll(firstNameLabel, firstNameField, lastNameLabel, lastNameField, contactInfoLabel, contactInfoField, saveButton);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void savePatient(Stage stage, Patient patient, String firstName, String lastName, String contactInfo /* other parameters */) {
        // Validate input data
        if (firstName.isEmpty() || lastName.isEmpty() || contactInfo.isEmpty()) {
            showAlert("Validation Error", "Please fill in all fields.");
            return;
        }

        // Create a new Patient object or update the existing one
        if (patient == null) {
            patient = new Patient(); // Assuming the Patient class has a no-args constructor
        }
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setContactInfo(contactInfo);
        // Set other properties from the parameters

        // Save or update the patient in the database
        if (patient.getPatientID() == 0) {
            patientDAO.savePatient(patient);
        } else {
            patientDAO.updatePatient(patient);
        }

        // Close the current screen and possibly refresh the patient list view
        stage.close();
        // Here you might want to refresh the PatientListScreen or navigate back to it.
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

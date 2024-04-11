package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import dao.PatientDAO;
import dao.HealthIssueDAO;
import model.Patient;
import model.HealthIssue;

import java.time.LocalDate;
import java.util.List;

public class PatientRegistration extends Application {

    private PatientDAO patientDAO = new PatientDAO();
    private HealthIssueDAO healthIssueDAO = new HealthIssueDAO();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Patient Intake Form");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        TextField firstNameTextField = new TextField();
        TextField lastNameTextField = new TextField();
        TextField contactInfoTextField = new TextField();
        TextField healthIssuesTextField = new TextField();
        TextField insuranceInfoTextField = new TextField();

        grid.add(new Label("First Name:"), 0, 0);
        grid.add(firstNameTextField, 1, 0);
        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(lastNameTextField, 1, 1);
        grid.add(new Label("Contact Info:"), 0, 2);
        grid.add(contactInfoTextField, 1, 2);
        grid.add(new Label("Health Issues:"), 0, 3);
        grid.add(healthIssuesTextField, 1, 3);
        grid.add(new Label("Insurance Info:"), 0, 4);
        grid.add(insuranceInfoTextField, 1, 4);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> savePatientInfo(
                firstNameTextField.getText(),
                lastNameTextField.getText(),
                contactInfoTextField.getText(),
                healthIssuesTextField.getText(),
                insuranceInfoTextField.getText()
        ));

        grid.add(saveButton, 1, 5);

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void savePatientInfo(String firstName, String lastName, String contactInfo, String healthHistory, String insuranceInfo) {
        if (firstName.isEmpty() || lastName.isEmpty() || contactInfo.isEmpty() || healthHistory.isEmpty() || insuranceInfo.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled.");
            return;
        }

        // Construct and save the patient
        Patient patient = new Patient(0, firstName, lastName, "", contactInfo, insuranceInfo, "");
        patientDAO.savePatient(patient); // Assuming this method now properly sets and returns the patient's ID

        // Retrieve the latest patient assuming the last added patient is the one we just saved
        // This part needs to be replaced with your method of retrieving the saved patient's ID
        List<Patient> patients = patientDAO.getAllPatients();
        Patient latestPatient = patients.get(patients.size() - 1); // Assuming the last patient is the one we want

        // Assuming current date for the issueDate
        String issueDate = LocalDate.now().toString();
        HealthIssue newHealthIssue = new HealthIssue(0, latestPatient.getPatientID(), healthHistory, issueDate);
        healthIssueDAO.save(newHealthIssue);

        // Display an alert with the patient ID
        showAlert(Alert.AlertType.INFORMATION, "Patient Saved", "Patient information saved successfully.\nPatient ID: " + latestPatient.getPatientID());

        // Optionally, if you have a method that directly returns the saved patient with the ID, you can simplify the above steps.
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

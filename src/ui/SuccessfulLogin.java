package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.UIHelper;
import dao.PatientDAO;

public class SuccessfulLogin extends Application {

    private PatientDAO patientDAO = new PatientDAO();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Patient Login");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(25, 50, 25, 50));
        vbox.setAlignment(Pos.CENTER);

        Label patientIdLabel = new Label("Patient ID:");
        TextField patientIdField = new TextField();
        patientIdField.setPromptText("Enter your Patient ID");

        Button loginButton = UIHelper.createButton("Login"); // Assuming UIHelper is accessible here
        loginButton.setOnAction(e -> {
            if (validatePatientId(patientIdField.getText())) {
                showNavigationDialog(primaryStage, patientIdField.getText());
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid Patient ID.");
            }
        });

        vbox.getChildren().addAll(patientIdLabel, patientIdField, loginButton);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean validatePatientId(String patientIdText) {
        try {
            int patientId = Integer.parseInt(patientIdText);
            return patientDAO.existsPatientId(patientId);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showNavigationDialog(Stage parentStage, String patientId) {
        Stage dialog = new Stage();
        dialog.initOwner(parentStage);
        dialog.setTitle("Patient Portal");

        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.setPadding(new Insets(20, 40, 20, 40));

        Label welcomeLabel = new Label("Welcome, Patient #" + patientId);
        
        Button messagesButton = UIHelper.createButton("View Messages");
        Button editProfileButton = UIHelper.createButton("Edit Profile");
        Button prescriptionsButton = UIHelper.createButton("View Prescriptions");
        Button allergiesButton = UIHelper.createButton("Edit Allergies");
        Button healthIssuesButton = UIHelper.createButton("Edit Health Issues");

        // Use the openScreen method to navigate
        messagesButton.setOnAction(e -> openScreen(new MessagesScreen()));
        editProfileButton.setOnAction(e -> openScreen(new PatientListScreen()));
        prescriptionsButton.setOnAction(e -> openScreen(new PrescriptionListScreen()));
        allergiesButton.setOnAction(e -> openScreen(new AllergyListScreen()));
        healthIssuesButton.setOnAction(e -> openScreen(new HealthIssueListScreen()));

        dialogVbox.getChildren().addAll(welcomeLabel, messagesButton, editProfileButton, prescriptionsButton, allergiesButton, healthIssuesButton);
        
        Scene dialogScene = new Scene(dialogVbox, 300, 400);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void openScreen(Application screen) {
        try {
            Stage stage = new Stage();
            screen.start(stage);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Error opening screen: " + e.getMessage());
        }
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

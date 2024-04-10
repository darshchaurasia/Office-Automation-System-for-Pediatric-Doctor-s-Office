package ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.UIHelper;

public class DashboardScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Healthcare Management System");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        Button patientsButton = UIHelper.createButton("Patients List");
        patientsButton.setOnAction(e -> openScreen(new PatientListScreen()));

        Button allergiesButton = UIHelper.createButton("Allergies List");
        allergiesButton.setOnAction(e -> openScreen(new AllergyListScreen()));

        Button prescriptionsButton = UIHelper.createButton("Prescriptions List");
        prescriptionsButton.setOnAction(e -> openScreen(new PrescriptionListScreen()));

        Button visitsButton = UIHelper.createButton("Visits List");
        visitsButton.setOnAction(e -> openScreen(new VisitListScreen()));

        Button healthIssuesButton = UIHelper.createButton("Health Issues List");
        healthIssuesButton.setOnAction(e -> openScreen(new HealthIssueListScreen()));

        Button messagesButton = UIHelper.createButton("Messages");
        messagesButton.setOnAction(e -> openScreen(new MessagesScreen()));

        vbox.getChildren().addAll(patientsButton, allergiesButton, prescriptionsButton, visitsButton, healthIssuesButton, messagesButton);

        Scene scene = new Scene(vbox, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openScreen(Application screen) {
        try {
            Stage stage = new Stage();
            screen.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        // VBox as the root layout manager for simplicity
        VBox root = new VBox(10); // 10 is the spacing between elements
        root.setPadding(new javafx.geometry.Insets(15)); // Padding around the VBox

        // Buttons for navigation
        Button btnPatientManagement = new Button("Patient Management");
        Button btnHealthRecords = new Button("Health Records");
        Button btnCommunication = new Button("Messages");

        // Add buttons to the VBox
        root.getChildren().addAll(btnPatientManagement, btnHealthRecords, btnCommunication);

        // Set actions for buttons (placeholder actions for demonstration)
        btnPatientManagement.setOnAction(event -> System.out.println("Navigate to Patient Management"));
        btnHealthRecords.setOnAction(event -> System.out.println("Navigate to Health Records"));
        btnCommunication.setOnAction(event -> System.out.println("Navigate to Messages"));

        // Set up the primary stage
        Scene scene = new Scene(root, 300, 250); // Scene size is adjustable based on needs
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

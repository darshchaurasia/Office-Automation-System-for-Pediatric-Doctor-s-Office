package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.HealthIssue;
import dao.HealthIssueDAO;

import java.time.LocalDate;

public class HealthIssueEditScreen extends Application {
    private HealthIssueDAO healthIssueDAO = new HealthIssueDAO();
    private HealthIssue currentIssue; // This would be set when editing an issue

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle((currentIssue == null) ? "Add Health Issue" : "Edit Health Issue");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Issue Description
        Label issueDescriptionLabel = new Label("Description:");
        GridPane.setConstraints(issueDescriptionLabel, 0, 0);
        TextField issueDescriptionField = new TextField();
        if (currentIssue != null) issueDescriptionField.setText(currentIssue.getIssueDescription());
        GridPane.setConstraints(issueDescriptionField, 1, 0);

        // Issue Date
        Label issueDateLabel = new Label("Date:");
        GridPane.setConstraints(issueDateLabel, 0, 1);
        DatePicker issueDatePicker = new DatePicker();
        if (currentIssue != null) issueDatePicker.setValue(LocalDate.parse(currentIssue.getIssueDate()));
        GridPane.setConstraints(issueDatePicker, 1, 1);

        // Save Button
        Button saveButton = new Button("Save");
        GridPane.setConstraints(saveButton, 1, 2);
        saveButton.setOnAction(e -> {
            saveHealthIssue(issueDescriptionField.getText(), issueDatePicker.getValue(), currentIssue);
            primaryStage.close();
        });

        // Cancel Button
        Button cancelButton = new Button("Cancel");
        GridPane.setConstraints(cancelButton, 1, 3);
        cancelButton.setOnAction(e -> primaryStage.close());

        grid.getChildren().addAll(issueDescriptionLabel, issueDescriptionField, issueDateLabel, issueDatePicker, saveButton, cancelButton);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveHealthIssue(String description, LocalDate date, HealthIssue issue) {
        if (issue == null) {
            issue = new HealthIssue(); // Assuming default constructor is available
            issue.setPatientID(1); // Set this to the actual patient ID
        }
        issue.setIssueDescription(description);
        issue.setIssueDate(date.toString()); // Converting LocalDate to String
        if (issue.getIssueID() == 0) {
            healthIssueDAO.save(issue);
        } else {
            healthIssueDAO.update(issue);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Optionally, you could have a method to set the current issue if this screen is for editing
    public void setCurrentIssue(HealthIssue issue) {
        this.currentIssue = issue;
    }
}

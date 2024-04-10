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
import model.HealthIssue;

import java.time.LocalDate;
import java.util.Optional;

import dao.HealthIssueDAO;

public class HealthIssueListScreen extends Application {

    private TableView<HealthIssue> table = new TableView<>();
    private ObservableList<HealthIssue> data = FXCollections.observableArrayList();
    private HealthIssueDAO healthIssueDAO = new HealthIssueDAO();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Health Issues");

        TableColumn<HealthIssue, String> descriptionCol = new TableColumn<>("Issue Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("issueDescription"));

        TableColumn<HealthIssue, String> dateCol = new TableColumn<>("Issue Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("issueDate"));

        table.getColumns().addAll(descriptionCol, dateCol);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addEditHealthIssue(null)); // null indicates a new health issue

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteSelectedHealthIssue());
        
        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> {
            HealthIssue selectedIssue = table.getSelectionModel().getSelectedItem();
            if (selectedIssue != null) {
                addEditHealthIssue(selectedIssue); // edit selected health issue
            }
            
            
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(table, addButton, editButton, deleteButton);

        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);

        loadHealthIssues();
        primaryStage.show();
    }

    private void loadHealthIssues() {
        data.clear();
        data.addAll(healthIssueDAO.getAll());
        table.setItems(data);
    }

    private void deleteSelectedHealthIssue() {
        HealthIssue selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            healthIssueDAO.delete(selected.getIssueID());
            data.remove(selected);
        }
    }
    private void addEditHealthIssue(HealthIssue issue) {
        Dialog<HealthIssue> dialog = new Dialog<>();
        dialog.setTitle((issue == null) ? "Add New Health Issue" : "Edit Health Issue");

        // Set the button types.
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField issueDescriptionField = new TextField();
        issueDescriptionField.setPromptText("Issue Description");
        DatePicker issueDateField = new DatePicker();
        issueDateField.setPromptText("Issue Date");

        if (issue != null) {
            issueDescriptionField.setText(issue.getIssueDescription());
            // Assuming the issueDate is a String. You might need to convert it to a LocalDate.
            issueDateField.setValue(LocalDate.parse(issue.getIssueDate()));
        }

        grid.add(new Label("Description:"), 0, 0);
        grid.add(issueDescriptionField, 1, 0);
        grid.add(new Label("Date:"), 0, 1);
        grid.add(issueDateField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a HealthIssue when the save button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                if (issue == null) {
                    // Assuming a new ID is auto-generated. Set other necessary fields as needed.
                    return new HealthIssue(0, // You might not need to set this depending on your setup
                            0, // Set patientID appropriately
                            issueDescriptionField.getText(),
                            issueDateField.getValue().toString()); // Convert LocalDate to String
                } else {
                    issue.setIssueDescription(issueDescriptionField.getText());
                    issue.setIssueDate(issueDateField.getValue().toString());
                    return issue;
                }
            }
            return null;
        });

        
        Optional<HealthIssue> result = dialog.showAndWait();
        result.ifPresent(newIssue -> {
            if (issue == null) {
                healthIssueDAO.save(newIssue);
            } else {
                healthIssueDAO.update(newIssue);
            }
            loadHealthIssues(); // Refresh the list to show the new or updated issue.
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

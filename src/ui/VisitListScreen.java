package ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Visit;
import dao.VisitDAO;

public class VisitListScreen extends Application {

    private TableView<Visit> visitsTable = new TableView<>();
    private ObservableList<Visit> visitsData = FXCollections.observableArrayList();
    private VisitDAO visitDAO = new VisitDAO();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Visits");

        // Define columns
        TableColumn<Visit, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("visitDate"));

        TableColumn<Visit, String> nurseNotesColumn = new TableColumn<>("Nurse Notes");
        nurseNotesColumn.setCellValueFactory(new PropertyValueFactory<>("nurseNotes"));

        TableColumn<Visit, String> doctorNotesColumn = new TableColumn<>("Doctor Notes");
        doctorNotesColumn.setCellValueFactory(new PropertyValueFactory<>("doctorNotes"));

        visitsTable.getColumns().addAll(dateColumn, nurseNotesColumn, doctorNotesColumn);
        visitsTable.setItems(visitsData);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> showEditDialog(null)); // Null indicates adding a new visit

        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> {
            Visit selectedVisit = visitsTable.getSelectionModel().getSelectedItem();
            if (selectedVisit != null) {
                showEditDialog(selectedVisit);
            }
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Visit selectedVisit = visitsTable.getSelectionModel().getSelectedItem();
            if (selectedVisit != null) {
                visitDAO.deleteVisit(selectedVisit.getVisitID());
                loadVisits();
            }
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(visitsTable, addButton, editButton, deleteButton);

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        loadVisits();
    }

    private void loadVisits() {
        visitsData.clear();
        visitsData.addAll(visitDAO.getAllVisits());
    }

    private void showEditDialog(Visit visit) {
        VisitEditScreen visitEditScreen = new VisitEditScreen(visitDAO);
        visitEditScreen.showVisitDialog(visit).ifPresent(result -> {
            if (visit == null) {
                visitDAO.saveVisit(result);
            } else {
                visitDAO.updateVisit(result);
            }
            loadVisits();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

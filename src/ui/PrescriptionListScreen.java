package ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Prescription;

import java.util.Optional;

import dao.PrescriptionDAO;

public class PrescriptionListScreen extends Application {

    private TableView<Prescription> prescriptionTable = new TableView<>();
    private ObservableList<Prescription> prescriptionData = FXCollections.observableArrayList();
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
    private int patientId = 1; // Assuming this is dynamically set based on the selected patient

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Prescriptions for Patient ID: " + patientId);

        // Define table columns
        TableColumn<Prescription, String> medicationColumn = new TableColumn<>("Medication");
        medicationColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));

        TableColumn<Prescription, String> dosageColumn = new TableColumn<>("Dosage");
        dosageColumn.setCellValueFactory(new PropertyValueFactory<>("dosage"));

        TableColumn<Prescription, String> instructionsColumn = new TableColumn<>("Instructions");
        instructionsColumn.setCellValueFactory(new PropertyValueFactory<>("instructions"));

        prescriptionTable.getColumns().addAll(medicationColumn, dosageColumn, instructionsColumn);
        prescriptionTable.setItems(prescriptionData);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addOrEditPrescription(null));

        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> {
            Prescription selected = prescriptionTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                addOrEditPrescription(selected);
            }
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteSelectedPrescription());

        VBox vbox = new VBox();
        vbox.getChildren().addAll(prescriptionTable, addButton, editButton, deleteButton);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();

        loadPrescriptions();
    }

    private void loadPrescriptions() {
        prescriptionData.clear();
        prescriptionData.addAll(prescriptionDAO.getAllPrescriptions()); // You might need to modify this to filter by patientId
    }

    private void addOrEditPrescription(Prescription prescription) {
        PrescriptionEditScreen editScreen = new PrescriptionEditScreen(prescriptionDAO, prescription);
        editScreen.showAndWait().ifPresent(result -> {
            if (prescription == null) {
                prescriptionDAO.savePrescription(result);
            } else {
                prescriptionDAO.updatePrescription(result);
            }
            loadPrescriptions();
        });
    }


    private void deleteSelectedPrescription() {
        Prescription selected = prescriptionTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this prescription?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                prescriptionDAO.deletePrescription(selected.getPrescriptionID());
                prescriptionData.remove(selected);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package ui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Prescription;

import java.util.Optional;

import dao.PrescriptionDAO;

public class PrescriptionEditScreen {

    private Dialog<Prescription> dialog;
    private PrescriptionDAO prescriptionDAO;
    private TextField medicationField;
    private TextField dosageField;
    private TextField instructionsField;
    private Prescription existingPrescription; // null if adding

    public PrescriptionEditScreen(PrescriptionDAO dao, Prescription prescription) {
        this.prescriptionDAO = dao;
        this.existingPrescription = prescription;
        createUI();
    }

    private void createUI() {
        dialog = new Dialog<>();
        dialog.setTitle((existingPrescription == null) ? "Add Prescription" : "Edit Prescription");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        medicationField = new TextField();
        dosageField = new TextField();
        instructionsField = new TextField();

        if (existingPrescription != null) {
            // Pre-populate fields if editing
            medicationField.setText(existingPrescription.getMedication());
            dosageField.setText(existingPrescription.getDosage());
            instructionsField.setText(existingPrescription.getInstructions());
        }

        grid.add(new Label("Medication:"), 0, 0);
        grid.add(medicationField, 1, 0);
        grid.add(new Label("Dosage:"), 0, 1);
        grid.add(dosageField, 1, 1);
        grid.add(new Label("Instructions:"), 0, 2);
        grid.add(instructionsField, 1, 2);

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(medicationField::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                if (existingPrescription == null) {
                    // Adding a new prescription
                    return new Prescription(0, 0, medicationField.getText(), dosageField.getText(), instructionsField.getText()); // Adjust IDs as necessary
                } else {
                    // Updating an existing prescription
                    existingPrescription.setMedication(medicationField.getText());
                    existingPrescription.setDosage(dosageField.getText());
                    existingPrescription.setInstructions(instructionsField.getText());
                    return existingPrescription;
                }
            }
            return null;
        });
    }

    public Optional<Prescription> showAndWait() {
        return dialog.showAndWait();
    }
}

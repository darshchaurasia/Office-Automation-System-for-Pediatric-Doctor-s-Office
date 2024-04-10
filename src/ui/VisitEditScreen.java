package ui;

import javafx.scene.control.Dialog;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import model.Visit;
import dao.VisitDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class VisitEditScreen {

    private final VisitDAO visitDAO;

    public VisitEditScreen(VisitDAO visitDAO) {
        this.visitDAO = visitDAO;
    }

    public Optional<Visit> showVisitDialog(Visit existingVisit) {
        Dialog<Visit> dialog = new Dialog<>();
        dialog.setTitle(existingVisit == null ? "Add New Visit" : "Edit Visit");

        // Add button types.
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Create and configure input fields and labels.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        DatePicker visitDatePicker = new DatePicker();
        TextField nurseNotesField = new TextField();
        TextField doctorNotesField = new TextField();

        if (existingVisit != null) {
            // Initialize fields with existing data.
            visitDatePicker.setValue(existingVisit.getVisitDate() == null ? null : LocalDate.parse(existingVisit.getVisitDate()));
            nurseNotesField.setText(existingVisit.getNurseNotes());
            doctorNotesField.setText(existingVisit.getDoctorNotes());
        }

        grid.add(new Label("Visit Date:"), 0, 0);
        grid.add(visitDatePicker, 1, 0);
        grid.add(new Label("Nurse Notes:"), 0, 1);
        grid.add(nurseNotesField, 1, 1);
        grid.add(new Label("Doctor Notes:"), 0, 2);
        grid.add(doctorNotesField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a Visit when the OK button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                Visit visit = existingVisit == null ? new Visit() : existingVisit;
                visit.setVisitDate(visitDatePicker.getValue() != null ? visitDatePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE) : null);
                visit.setNurseNotes(nurseNotesField.getText());
                visit.setDoctorNotes(doctorNotesField.getText());
                return visit;
            }
            return null;
        });

        // Showing dialog and returning the result.
        return dialog.showAndWait();
    }
}

package dao;

import db.DatabaseConnector;
import model.Prescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {

    public Prescription getPrescriptionById(int prescriptionID) {
        String sql = "SELECT * FROM Prescriptions WHERE PrescriptionID = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, prescriptionID);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Prescription(
                    rs.getInt("PrescriptionID"),
                    rs.getInt("VisitID"),
                    rs.getString("Medication"),
                    rs.getString("Dosage"),
                    rs.getString("Instructions")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Prescription> getAllPrescriptions() {
        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT * FROM Prescriptions";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                prescriptions.add(new Prescription(
                    rs.getInt("PrescriptionID"),
                    rs.getInt("VisitID"),
                    rs.getString("Medication"),
                    rs.getString("Dosage"),
                    rs.getString("Instructions")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return prescriptions;
    }

    public void savePrescription(Prescription prescription) {
        String sql = "INSERT INTO Prescriptions (VisitID, Medication, Dosage, Instructions) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, prescription.getVisitID());
            pstmt.setString(2, prescription.getMedication());
            pstmt.setString(3, prescription.getDosage());
            pstmt.setString(4, prescription.getInstructions());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updatePrescription(Prescription prescription) {
        String sql = "UPDATE Prescriptions SET VisitID = ?, Medication = ?, Dosage = ?, Instructions = ? WHERE PrescriptionID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, prescription.getVisitID());
            pstmt.setString(2, prescription.getMedication());
            pstmt.setString(3, prescription.getDosage());
            pstmt.setString(4, prescription.getInstructions());
            pstmt.setInt(5, prescription.getPrescriptionID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePrescription(int prescriptionID) {
        String sql = "DELETE FROM Prescriptions WHERE PrescriptionID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, prescriptionID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

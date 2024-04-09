package dao;

import db.DatabaseConnector;
import model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public Patient getPatientById(int patientID) {
        String sql = "SELECT * FROM Patients WHERE PatientID = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, patientID);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Patient(
                    rs.getInt("PatientID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Birthday"), // Consider converting to LocalDate if using LocalDate in the model
                    rs.getString("ContactInfo"),
                    rs.getString("InsuranceInfo"),
                    rs.getString("PharmacyInfo")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM Patients";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                patients.add(new Patient(
                    rs.getInt("PatientID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Birthday"), // Consider converting to LocalDate if using LocalDate in the model
                    rs.getString("ContactInfo"),
                    rs.getString("InsuranceInfo"),
                    rs.getString("PharmacyInfo")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return patients;
    }

    public void savePatient(Patient patient) {
        String sql = "INSERT INTO Patients (FirstName, LastName, Birthday, ContactInfo, InsuranceInfo, PharmacyInfo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, patient.getFirstName());
            pstmt.setString(2, patient.getLastName());
            pstmt.setString(3, patient.getBirthday()); // Adjust for LocalDate if necessary
            pstmt.setString(4, patient.getContactInfo());
            pstmt.setString(5, patient.getInsuranceInfo());
            pstmt.setString(6, patient.getPharmacyInfo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updatePatient(Patient patient) {
        String sql = "UPDATE Patients SET FirstName = ?, LastName = ?, Birthday = ?, ContactInfo = ?, InsuranceInfo = ?, PharmacyInfo = ? WHERE PatientID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, patient.getFirstName());
            pstmt.setString(2, patient.getLastName());
            pstmt.setString(3, patient.getBirthday()); // Adjust for LocalDate if necessary
            pstmt.setString(4, patient.getContactInfo());
            pstmt.setString(5, patient.getInsuranceInfo());
            pstmt.setString(6, patient.getPharmacyInfo());
            pstmt.setInt(7, patient.getPatientID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePatient(int patientID) {
        String sql = "DELETE FROM Patients WHERE PatientID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, patientID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

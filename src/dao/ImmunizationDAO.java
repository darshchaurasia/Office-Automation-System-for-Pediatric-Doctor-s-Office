package dao;

import db.DatabaseConnector;
import model.Immunization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImmunizationDAO {

    public Immunization getImmunizationById(int immunizationID) {
        String sql = "SELECT * FROM Immunizations WHERE ImmunizationID = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, immunizationID);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Immunization(
                    rs.getInt("ImmunizationID"),
                    rs.getInt("PatientID"),
                    rs.getString("Vaccine"),
                    rs.getString("ImmunizationDate") // Adjust for LocalDate if necessary
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Immunization> getAllImmunizations() {
        List<Immunization> immunizations = new ArrayList<>();
        String sql = "SELECT * FROM Immunizations";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                immunizations.add(new Immunization(
                    rs.getInt("ImmunizationID"),
                    rs.getInt("PatientID"),
                    rs.getString("Vaccine"),
                    rs.getString("ImmunizationDate") // Adjust for LocalDate if necessary
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return immunizations;
    }

    public void saveImmunization(Immunization immunization) {
        String sql = "INSERT INTO Immunizations (PatientID, Vaccine, ImmunizationDate) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, immunization.getPatientID());
            pstmt.setString(2, immunization.getVaccine());
            pstmt.setString(3, immunization.getImmunizationDate()); // Adjust for LocalDate if necessary
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateImmunization(Immunization immunization) {
        String sql = "UPDATE Immunizations SET PatientID = ?, Vaccine = ?, ImmunizationDate = ? WHERE ImmunizationID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, immunization.getPatientID());
            pstmt.setString(2, immunization.getVaccine());
            pstmt.setString(3, immunization.getImmunizationDate()); // Adjust for LocalDate if necessary
            pstmt.setInt(4, immunization.getImmunizationID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteImmunization(int immunizationID) {
        String sql = "DELETE FROM Immunizations WHERE ImmunizationID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, immunizationID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

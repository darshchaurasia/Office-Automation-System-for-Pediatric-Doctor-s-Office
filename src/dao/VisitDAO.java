package dao;

import db.DatabaseConnector;
import model.Visit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisitDAO {

    public Visit getVisitById(int visitID) {
        String sql = "SELECT * FROM Visits WHERE VisitID = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, visitID);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Visit(
                    rs.getInt("VisitID"),
                    rs.getInt("PatientID"),
                    rs.getString("VisitDate"), // Adjust for LocalDate if necessary
                    rs.getString("NurseNotes"),
                    rs.getString("DoctorNotes")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Visit> getAllVisits() {
        List<Visit> visits = new ArrayList<>();
        String sql = "SELECT * FROM Visits";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                visits.add(new Visit(
                    rs.getInt("VisitID"),
                    rs.getInt("PatientID"),
                    rs.getString("VisitDate"), // Adjust for LocalDate if necessary
                    rs.getString("NurseNotes"),
                    rs.getString("DoctorNotes")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return visits;
    }

    public void saveVisit(Visit visit) {
        String sql = "INSERT INTO Visits (PatientID, VisitDate, NurseNotes, DoctorNotes) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, visit.getPatientID());
            pstmt.setString(2, visit.getVisitDate()); // Adjust for LocalDate if necessary
            pstmt.setString(3, visit.getNurseNotes());
            pstmt.setString(4, visit.getDoctorNotes());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateVisit(Visit visit) {
        String sql = "UPDATE Visits SET PatientID = ?, VisitDate = ?, NurseNotes = ?, DoctorNotes = ? WHERE VisitID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, visit.getPatientID());
            pstmt.setString(2, visit.getVisitDate()); // Adjust for LocalDate if necessary
            pstmt.setString(3, visit.getNurseNotes());
            pstmt.setString(4, visit.getDoctorNotes());
            pstmt.setInt(5, visit.getVisitID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteVisit(int visitID) {
        String sql = "DELETE FROM Visits WHERE VisitID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, visitID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

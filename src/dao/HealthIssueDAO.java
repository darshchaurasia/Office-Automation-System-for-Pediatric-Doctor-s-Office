package dao;

import db.DatabaseConnector;
import model.HealthIssue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HealthIssueDAO {

    public HealthIssue getById(int issueID) {
        String sql = "SELECT * FROM HealthIssues WHERE IssueID = ?";
        HealthIssue healthIssue = null;
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, issueID);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                healthIssue = new HealthIssue(
                    rs.getInt("IssueID"),
                    rs.getInt("PatientID"),
                    rs.getString("IssueDescription"),
                    rs.getString("IssueDate") // Consider converting to LocalDate if using LocalDate in the model
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return healthIssue;
    }

    public List<HealthIssue> getAll() {
        List<HealthIssue> issues = new ArrayList<>();
        String sql = "SELECT * FROM HealthIssues";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                HealthIssue healthIssue = new HealthIssue(
                    rs.getInt("IssueID"),
                    rs.getInt("PatientID"),
                    rs.getString("IssueDescription"),
                    rs.getString("IssueDate") // Consider converting to LocalDate if using LocalDate in the model
                );
                issues.add(healthIssue);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return issues;
    }

    public void save(HealthIssue healthIssue) {
        String sql = "INSERT INTO HealthIssues (PatientID, IssueDescription, IssueDate) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, healthIssue.getPatientID());
            pstmt.setString(2, healthIssue.getIssueDescription());
            pstmt.setString(3, healthIssue.getIssueDate()); // Adjust for LocalDate if necessary
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(HealthIssue healthIssue) {
        String sql = "UPDATE HealthIssues SET PatientID = ?, IssueDescription = ?, IssueDate = ? WHERE IssueID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, healthIssue.getPatientID());
            pstmt.setString(2, healthIssue.getIssueDescription());
            pstmt.setString(3, healthIssue.getIssueDate()); // Adjust for LocalDate if necessary
            pstmt.setInt(4, healthIssue.getIssueID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int issueID) {
        String sql = "DELETE FROM HealthIssues WHERE IssueID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, issueID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

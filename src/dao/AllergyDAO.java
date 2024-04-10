package dao;

import db.DatabaseConnector;
import model.Allergy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AllergyDAO {

    public Allergy getById(int id) {
        String sql = "SELECT * FROM Allergies WHERE AllergyID = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Allergy(
                    rs.getInt("AllergyID"),
                    rs.getInt("PatientID"),
                    rs.getString("Allergen"),
                    rs.getString("Reaction")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Allergy> getAll() {
        List<Allergy> allergies = new ArrayList<>();
        String sql = "SELECT * FROM Allergies";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                allergies.add(new Allergy(
                    rs.getInt("AllergyID"),
                    rs.getInt("PatientID"),
                    rs.getString("Allergen"),
                    rs.getString("Reaction")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allergies;
    }
    public Allergy save(Allergy allergy) {
        String sql = "INSERT INTO Allergies (PatientID, Allergen, Reaction) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, allergy.getPatientID());
            pstmt.setString(2, allergy.getAllergen());
            pstmt.setString(3, allergy.getReaction());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating allergy failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    allergy.setAllergyID(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating allergy failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return allergy;
    }


    public void update(Allergy allergy) {
        String sql = "UPDATE Allergies SET PatientID = ?, Allergen = ?, Reaction = ? WHERE AllergyID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, allergy.getPatientID());
            pstmt.setString(2, allergy.getAllergen());
            pstmt.setString(3, allergy.getReaction());
            pstmt.setInt(4, allergy.getAllergyID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Allergies WHERE AllergyID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

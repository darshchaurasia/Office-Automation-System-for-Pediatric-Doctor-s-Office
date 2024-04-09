package dao;

import db.DatabaseConnector;
import model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    public Message getMessageById(int messageID) {
        String sql = "SELECT * FROM Messages WHERE MessageID = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, messageID);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Message(
                    rs.getInt("MessageID"),
                    rs.getInt("PatientID"),
                    rs.getString("MessageContent"),
                    rs.getString("ReplyContent"),
                    rs.getString("Timestamp") // Adjust for LocalDateTime if necessary
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM Messages";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                messages.add(new Message(
                    rs.getInt("MessageID"),
                    rs.getInt("PatientID"),
                    rs.getString("MessageContent"),
                    rs.getString("ReplyContent"),
                    rs.getString("Timestamp") // Adjust for LocalDateTime if necessary
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public void saveMessage(Message message) {
        String sql = "INSERT INTO Messages (PatientID, MessageContent, ReplyContent, Timestamp) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, message.getPatientID());
            pstmt.setString(2, message.getMessageContent());
            pstmt.setString(3, message.getReplyContent());
            pstmt.setString(4, message.getTimestamp()); // Adjust for LocalDateTime if necessary
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateMessage(Message message) {
        String sql = "UPDATE Messages SET PatientID = ?, MessageContent = ?, ReplyContent = ?, Timestamp = ? WHERE MessageID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, message.getPatientID());
            pstmt.setString(2, message.getMessageContent());
            pstmt.setString(3, message.getReplyContent());
            pstmt.setString(4, message.getTimestamp()); // Adjust for LocalDateTime if necessary
            pstmt.setInt(5, message.getMessageID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteMessage(int messageID) {
        String sql = "DELETE FROM Messages WHERE MessageID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, messageID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

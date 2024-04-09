package model;

public class Message {
    private int messageID;
    private int patientID;
    private String messageContent;
    private String replyContent;
    private String timestamp; // Consider using LocalDateTime for date and time handling

    // Constructor with all fields
    public Message(int messageID, int patientID, String messageContent, String replyContent, String timestamp) {
        this.messageID = messageID;
        this.patientID = patientID;
        this.messageContent = messageContent;
        this.replyContent = replyContent;
        this.timestamp = timestamp;
    }

    // Default constructor
    public Message() {}

    // Getters and setters for each field
    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
               "messageID=" + messageID +
               ", patientID=" + patientID +
               ", messageContent='" + messageContent + '\'' +
               ", replyContent='" + replyContent + '\'' +
               ", timestamp='" + timestamp + '\'' +
               '}';
    }
}

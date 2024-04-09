package model;

public class HealthIssue {
    private int issueID;
    private int patientID;
    private String issueDescription;
    private String issueDate; // This can be changed to LocalDate for better date handling

    // Constructor with all fields
    public HealthIssue(int issueID, int patientID, String issueDescription, String issueDate) {
        this.issueID = issueID;
        this.patientID = patientID;
        this.issueDescription = issueDescription;
        this.issueDate = issueDate;
    }

    // Default constructor
    public HealthIssue() {}

    // Getters and setters for each field
    public int getIssueID() {
        return issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "HealthIssue{" +
               "issueID=" + issueID +
               ", patientID=" + patientID +
               ", issueDescription='" + issueDescription + '\'' +
               ", issueDate='" + issueDate + '\'' +
               '}';
    }
}

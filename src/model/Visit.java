package model;

public class Visit {
    private int visitID;
    private int patientID;
    private String visitDate; // Consider using LocalDate for date handling
    private String nurseNotes;
    private String doctorNotes;

    // Constructor with all fields
    public Visit(int visitID, int patientID, String visitDate, String nurseNotes, String doctorNotes) {
        this.visitID = visitID;
        this.patientID = patientID;
        this.visitDate = visitDate;
        this.nurseNotes = nurseNotes;
        this.doctorNotes = doctorNotes;
    }

    // Default constructor
    public Visit() {}

    // Getters and setters for each field
    public int getVisitID() {
        return visitID;
    }

    public void setVisitID(int visitID) {
        this.visitID = visitID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getNurseNotes() {
        return nurseNotes;
    }

    public void setNurseNotes(String nurseNotes) {
        this.nurseNotes = nurseNotes;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }

    @Override
    public String toString() {
        return "Visit{" +
               "visitID=" + visitID +
               ", patientID=" + patientID +
               ", visitDate='" + visitDate + '\'' +
               ", nurseNotes='" + nurseNotes + '\'' +
               ", doctorNotes='" + doctorNotes + '\'' +
               '}';
    }
}

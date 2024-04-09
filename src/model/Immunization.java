package model;

public class Immunization {
    private int immunizationID;
    private int patientID;
    private String vaccine;
    private String immunizationDate; // Consider changing to LocalDate for date handling

    // Constructor with all fields
    public Immunization(int immunizationID, int patientID, String vaccine, String immunizationDate) {
        this.immunizationID = immunizationID;
        this.patientID = patientID;
        this.vaccine = vaccine;
        this.immunizationDate = immunizationDate;
    }

    // Default constructor
    public Immunization() {}

    // Getters and setters for each field
    public int getImmunizationID() {
        return immunizationID;
    }

    public void setImmunizationID(int immunizationID) {
        this.immunizationID = immunizationID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getImmunizationDate() {
        return immunizationDate;
    }

    public void setImmunizationDate(String immunizationDate) {
        this.immunizationDate = immunizationDate;
    }

    @Override
    public String toString() {
        return "Immunization{" +
               "immunizationID=" + immunizationID +
               ", patientID=" + patientID +
               ", vaccine='" + vaccine + '\'' +
               ", immunizationDate='" + immunizationDate + '\'' +
               '}';
    }
}

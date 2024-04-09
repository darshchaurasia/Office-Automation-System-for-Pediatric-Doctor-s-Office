package model;

public class Patient {
    private int patientID;
    private String firstName;
    private String lastName;
    private String birthday; // Consider using LocalDate for better date handling
    private String contactInfo;
    private String insuranceInfo;
    private String pharmacyInfo;

    // Constructor with all fields
    public Patient(int patientID, String firstName, String lastName, String birthday, String contactInfo, String insuranceInfo, String pharmacyInfo) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.contactInfo = contactInfo;
        this.insuranceInfo = insuranceInfo;
        this.pharmacyInfo = pharmacyInfo;
    }

    // Default constructor
    public Patient() {}

    // Getters and setters for each field
    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getInsuranceInfo() {
        return insuranceInfo;
    }

    public void setInsuranceInfo(String insuranceInfo) {
        this.insuranceInfo = insuranceInfo;
    }

    public String getPharmacyInfo() {
        return pharmacyInfo;
    }

    public void setPharmacyInfo(String pharmacyInfo) {
        this.pharmacyInfo = pharmacyInfo;
    }

    @Override
    public String toString() {
        return "Patient{" +
               "patientID=" + patientID +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", birthday='" + birthday + '\'' +
               ", contactInfo='" + contactInfo + '\'' +
               ", insuranceInfo='" + insuranceInfo + '\'' +
               ", pharmacyInfo='" + pharmacyInfo + '\'' +
               '}';
    }
}

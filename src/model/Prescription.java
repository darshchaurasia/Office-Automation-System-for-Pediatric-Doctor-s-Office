package model;

public class Prescription {
    private int prescriptionID;
    private int visitID;
    private String medication;
    private String dosage;
    private String instructions;

    // Constructor with all fields
    public Prescription(int prescriptionID, int visitID, String medication, String dosage, String instructions) {
        this.prescriptionID = prescriptionID;
        this.visitID = visitID;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
    }

    // Default constructor
    public Prescription() {}

    // Getters and setters for each field
    public int getPrescriptionID() {
        return prescriptionID;
    }

    public void setPrescriptionID(int prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public int getVisitID() {
        return visitID;
    }

    public void setVisitID(int visitID) {
        this.visitID = visitID;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Prescription{" +
               "prescriptionID=" + prescriptionID +
               ", visitID=" + visitID +
               ", medication='" + medication + '\'' +
               ", dosage='" + dosage + '\'' +
               ", instructions='" + instructions + '\'' +
               '}';
    }
}

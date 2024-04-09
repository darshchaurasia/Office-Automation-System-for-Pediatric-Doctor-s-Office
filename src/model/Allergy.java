package model;

public class Allergy {
    private int allergyID;
    private int patientID;
    private String allergen;
    private String reaction;

    // Constructor with all fields
    public Allergy(int allergyID, int patientID, String allergen, String reaction) {
        this.allergyID = allergyID;
        this.patientID = patientID;
        this.allergen = allergen;
        this.reaction = reaction;
    }

    // Default constructor
    public Allergy() {}

    // Getters and setters for each field
    public int getAllergyID() {
        return allergyID;
    }

    public void setAllergyID(int allergyID) {
        this.allergyID = allergyID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getAllergen() {
        return allergen;
    }

    public void setAllergen(String allergen) {
        this.allergen = allergen;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    @Override
    public String toString() {
        return "Allergy{" +
               "allergyID=" + allergyID +
               ", patientID=" + patientID +
               ", allergen='" + allergen + '\'' +
               ", reaction='" + reaction + '\'' +
               '}';
    }
}

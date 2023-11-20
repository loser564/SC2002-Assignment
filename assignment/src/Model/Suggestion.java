package Model;

public class Suggestion {

    private int suggestionID;       // The ID of the suggestion
    private String suggestionText; // The text of the suggestion
    private String studentID;       // The ID of the student who made the suggestion
    private String staffID;         // The ID of the staff who accepted the suggestion
    private boolean status;    // Indicates whether the suggestion has been accepted
    private Camp camp;

    public Suggestion(String studentID,String suggestionText, boolean isAccepted) {
        this.studentID = studentID; // Initialize the suggestion ID to 0
        this.suggestionText = suggestionText;
        this.status = false; // Initialize the suggestion as not accepted
    }

    // Getter and setter methods for suggestionID
    public int getSuggestionID() {
        return suggestionID;
    }

    public void setSuggestionID(int suggestionID) {
        this.suggestionID = suggestionID;
    }

    // Getter and setter methods for suggestionText
    public String getSuggestionText() {
        return suggestionText;
    }

    public void setSuggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    // Getter and setter methods for isAccepted
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean accepted) {
        status = accepted;
    }

    @Override
    public String toString() {
        // Customize the toString method to provide a formatted representation of the suggestion
        return "Suggestion: " + suggestionText + "\nAccepted: " + (status ? "Yes" : "No");
    }

    public String getStudentID() { return studentID; }

    public void setStudentID(String studentID) { this.studentID = studentID; }

    public String getStaffID() { return staffID; }

    public void setStaffID(String staffID) { this.staffID = staffID; }

    public Camp getCamp() { return camp; }

    public void setCamp(Camp camp) { this.camp = camp; }

    
}

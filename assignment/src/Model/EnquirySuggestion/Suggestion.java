package Model.EnquirySuggestion;

import Model.Camp.Camp;

/**
 * The {@code Suggestion} class represents a suggestion made by a student.
 * It contains information such as the suggestion text, the student who made the suggestion,
 * and whether the suggestion has been accepted.
 * 
 * @author Alicia
 * @version 8.0
 * @since 2023-11-19
 */
public class Suggestion {

    private int suggestionID;       // The ID of the suggestion
    private String suggestionText; // The text of the suggestion
    private String studentID;       // The ID of the student who made the suggestion
    private String staffID;         // The ID of the staff who accepted the suggestion
    private boolean status;    // Indicates whether the suggestion has been accepted
    private Camp camp;          // The camp associated with the suggestion

    /**
     * Constructs a new {@code Suggestion} object with the specified student ID, suggestion text,
     * and initial acceptance status.
     *
     * @param studentID The ID of the student who made the suggestion.
     * @param suggestionText The text of the suggestion.
     * @param isAccepted The initial acceptance status of the suggestion.
     */
    public Suggestion(String studentID, String suggestionText, boolean isAccepted) {
        this.studentID = studentID;
        this.suggestionText = suggestionText;
        this.status = isAccepted;
    }

    /**
     * Gets the ID of the suggestion.
     *
     * @return The ID of the suggestion.
     */
    public int getSuggestionID() {
        return suggestionID;
    }

    /**
     * Sets the ID of the suggestion.
     *
     * @param suggestionID The ID to set.
     */
    public void setSuggestionID(int suggestionID) {
        this.suggestionID = suggestionID;
    }

    /**
     * Gets the text of the suggestion.
     *
     * @return The text of the suggestion.
     */
    public String getSuggestionText() {
        return suggestionText;
    }

    /**
     * Sets the text of the suggestion.
     *
     * @param suggestionText The text to set.
     */
    public void setSuggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    /**
     * Gets the acceptance status of the suggestion.
     *
     * @return {@code true} if the suggestion is accepted, {@code false} otherwise.
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Sets the acceptance status of the suggestion.
     *
     * @param accepted {@code true} to indicate that the suggestion is accepted, {@code false} otherwise.
     */
    public void setStatus(boolean accepted) {
        status = accepted;
    }

    /**
     * Gets the student ID of the student who made the suggestion.
     *
     * @return The student ID.
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Sets the student ID of the student who made the suggestion.
     *
     * @param studentID The student ID to set.
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Gets the staff ID of the staff who accepted the suggestion.
     *
     * @return The staff ID.
     */
    public String getStaffID() {
        return staffID;
    }

    /**
     * Sets the staff ID of the staff who accepted the suggestion.
     *
     * @param staffID The staff ID to set.
     */
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    /**
     * Gets the camp associated with the suggestion.
     *
     * @return The associated camp.
     */
    public Camp getCamp() {
        return camp;
    }

    /**
     * Sets the camp associated with the suggestion.
     *
     * @param camp The camp to set.
     */
    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    /**
     * Returns a formatted representation of the suggestion.
     *
     * @return A formatted string containing the suggestion text and acceptance status.
     */
    @Override
    public String toString() {
        return "Suggestion: " + suggestionText + "\nAccepted: " + (status ? "Yes" : "No");
    }
}


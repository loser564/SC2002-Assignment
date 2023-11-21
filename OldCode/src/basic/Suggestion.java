package src.basic;
public class Suggestion {
    private String suggestionText; // The text of the suggestion
    private boolean isAccepted;    // Indicates whether the suggestion has been accepted

    public Suggestion(String suggestionText) {
        this.suggestionText = suggestionText;
        this.isAccepted = false; // Initialize the suggestion as not accepted
    }

    public Camp getCamp() {
        // Return the camp that this suggestion is for
        return null;
    }

    // Getter and setter methods for suggestionText
    public String getSuggestionText() {
        return suggestionText;
    }

    public void setSuggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    // Getter and setter methods for isAccepted
    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    @Override
    public String toString() {
        // Customize the toString method to provide a formatted representation of the suggestion
        return "Suggestion: " + suggestionText + "\nAccepted: " + (isAccepted ? "Yes" : "No");
    }
}

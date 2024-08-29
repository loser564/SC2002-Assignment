package Model.Staff;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import Model.EnquirySuggestion.Suggestion;

/**
 * The {@code SuggestionInterface} interface defines methods for managing camp suggestions.
 * Classes implementing this interface are responsible for viewing, printing details of,
 * and approving suggestions related to specific camps.
 */
public interface SuggestionInterface {
    
    /**
     * Retrieves and returns a list of suggestions associated with a staff member's camps.
     *
     * @return An {@code ArrayList} of {@link Suggestion} objects representing camp suggestions.
     */
    ArrayList<Suggestion> viewMyCampsSuggestions();
    
    /**
     * Prints details of a camp suggestion, including its ID, student ID, associated camp name,
     * suggestion text, and status.
     *
     * @param suggestion The {@link Suggestion} object for which details should be printed.
     */
    void printSuggestionDetails(Suggestion suggestion);
    
    /**
     * Approves a camp suggestion based on its suggestion ID. The approval status of the
     * suggestion is updated.
     *
     * @param suggestionID The ID of the suggestion to be approved.
     * @throws IOException If an I/O error occurs while performing the approval.
     */
    void approveSuggestion(int suggestionID) throws IOException;
}

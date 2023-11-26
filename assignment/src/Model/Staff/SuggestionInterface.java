package Model.Staff;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import Model.EnquirySuggestion.Suggestion;

public interface SuggestionInterface {
    ArrayList<Suggestion> viewMyCampsSuggestions();
    void printSuggestionDetails(Suggestion suggestion);
    void approveSuggestion(int suggestionID) throws IOException;
}
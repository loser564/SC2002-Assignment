package View;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;


import Model.Camp.*;
import Model.CampComm.*;
import Model.EnquirySuggestion.*;

/**
 * The CampCommFunctions class consists of functions available to a Camp Committee Member
 * It provides methods for viewing camp details, making suggestions, managing suggestions,
 * replying to enquiries, and generating reports.
 * This class serves as a part of the View layer in the CAMs application.
 *
 * @author Alicia
 * @version 1.0
 * @since 2023-11-19

 */

public class CampCommFunctions {
    /**
     * Default constructor for the CampCommFunctions class.
     */
    public CampCommFunctions(){}
    
    /**
     * Displays details of a specific camp using the provided CampCommitee and Camp instances.
     *
     * @param campComm The CampCommitee instance handling the camp details.
     * @param camp The Camp instance for which details are to be displayed.
     */

    public void viewDetails(CampCommitee campComm, Camp camp){
        campComm.printCampDetails(camp);
    }

    /**
     * Allows a Camp Committee member to make suggestions related to the camp.
     *
     * @param campComm The CampCommitee instance handling suggestions.
     * @throws IOException If an I/O error occurs while reading user input.
     */

    public void makeSuggestions(CampCommitee campComm) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your suggestion: ");
        String message = sc.nextLine();
        campComm.makeSuggestions(message);
        System.out.println("Suggestion sent successfully!");
        campComm.addPoints(1);
        
    }
    
    /**
     * Displays suggestions submitted by the Camp Committee member.
     *
     * @param campComm The CampCommitee instance handling suggestions.
     */

    public void viewMySuggestions(CampCommitee campComm){
        ArrayList<Suggestion> mySuggestions = campComm.viewMySuggestions();
        for(Suggestion s: mySuggestions){
            System.out.println("Debug: suggestion printing");
            campComm.printSuggestionDetails(s); 
        }
    }
    
    /**
     * Allows a Camp Committee member to edit their own suggestions if they have not been approved.
     *
     * @param campComm The CampCommitee instance handling suggestions.
     * @throws IOException If an I/O error occurs while reading user input.
     */

    public void editMySuggestions(CampCommitee campComm) throws IOException{
        viewMySuggestions(campComm);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the suggestion ID to edit: ");
        int suggestionID = sc.nextInt();
        sc.nextLine();
        ArrayList<Suggestion> mySuggestions = campComm.viewMySuggestions();
        for(Suggestion s: mySuggestions){
            if(s.getSuggestionID() == suggestionID){
                if (s.getStatus() == true){
                    System.out.println("Suggestion has already been approved!");
                    break;
                }
                else{
                    System.out.println("Enter new suggestion: ");
                    String newSuggestion = sc.nextLine();
                    s.setSuggestionText(newSuggestion);
                    campComm.editSuggestion(s);
                    System.out.println("Suggestion edited successfully!");
                    break;
                }
               
            }
        }
        // sc.close();
    }

    /**
     * Deletes a suggestion submitted by the Camp Committee member if it has not been approved.
     *
     * @param campComm The CampCommitee instance handling suggestions.
     * @throws IOException If an I/O error occurs while reading user input.
     */

    public void deleteMySuggestions(CampCommitee campComm) throws IOException{
        viewMySuggestions(campComm);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the suggestion ID to delete: ");
        int suggestionID = sc.nextInt();
        sc.nextLine();
        ArrayList<Suggestion> mySuggestions = campComm.viewMySuggestions();
        for(Suggestion s: mySuggestions){
            if(s.getSuggestionID() == suggestionID){
                if (s.getStatus() == true){
                    System.out.println("Suggestion has already been approved!");
                    break;
                }
                else{
                    campComm.deleteSuggestion(s);
                    System.out.println("Suggestion deleted successfully!");
                    break;
                }
               
            }
        }
        // sc.close();
    }

    /**
     * Allows a Camp Committee member to reply to a camp-related enquiry.
     *
     * @param campComm The CampCommitee instance handling enquiries.
     * @throws IOException If an I/O error occurs while reading user input.
     */

    public void replyToEnquiry(CampCommitee campComm) throws IOException{
        Scanner sc = new Scanner(System.in);
        ArrayList<Enquiry> myEnquiries = campComm.viewMyCampsEnquiries();
        System.out.println("Enter the enquiry ID to reply: ");
        int enquiryID = sc.nextInt();
        sc.nextLine();
        for(Enquiry e: myEnquiries){
            if(e.getEnquiryID() == enquiryID){
                if(e.getStatus() == true){
                    System.out.println("Enquiry has already been replied!");
                    break;
                }
                else{
                    System.out.println("Enter your reply: ");
                    String reply = sc.nextLine();
                    campComm.replyEnquiry(enquiryID, reply);
                    System.out.println("Reply sent successfully!");
                    campComm.addPoints(1);
                    break;
                }
                
            }
        }
        // sc.close();
    }

    /**
     * Generates a report based on specified filters for a given camp.
     *
     * @param campComm The CampCommitee instance responsible for report generation.
     * @param camp The Camp instance for which the report is to be generated.
     * @param campName The name of the camp for which the report is generated.
     * @throws IOException If an I/O error occurs while reading user input.
     */

    public void generateReport(CampCommitee campComm, Camp camp, String campName) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your filters...");    
        System.out.println("If you do not wish to filter by a certain field, enter null");
        System.out.println("Facualty filter: ");
        String faculty = sc.nextLine();

        System.out.println("For Camp Committee?(true/false): ");
        Boolean role = sc.nextBoolean();

        campComm.generateReport(faculty, role, camp, campName);

        // sc.close();
    }


    
}

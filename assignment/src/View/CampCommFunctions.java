package View;
import java.util.Scanner;
import Model.*;
import java.io.IOException;
import java.util.ArrayList;


import Model.Camp.*;
import Model.CampComm.*;
import Model.EnquirySuggestion.*;
import Model.Student.*;
import Model.User.*;



public class CampCommFunctions {
    public CampCommFunctions(){}


    public void viewDetails(CampCommitee campComm, Camp camp){
        campComm.printCampDetails(camp);
    }

    public void makeSuggestions(CampCommitee campComm) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your suggestion: ");
        String message = sc.nextLine();
        campComm.makeSuggestions(message);
        System.out.println("Suggestion sent successfully!");
        campComm.addPoints(1);
        sc.close();
    }

    public void viewMySuggestions(CampCommitee campComm){
        ArrayList<Suggestion> mySuggestions = campComm.viewMySuggestions();
        for(Suggestion s: mySuggestions){
            if (s.getStudentID().equals(campComm.getCampCommID())){
                campComm.printSuggestionDetails(s);
            }
        }
    }

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
        sc.close();
    }

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
        sc.close();
    }

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
        sc.close();
    }

    public void generateReport(CampCommitee campComm, Camp camp, String campName){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your filters...");    
        System.out.println("If you do not wish to filter by a certain field, enter null");
        System.out.println("Facualty filter: ");
        String faculty = sc.nextLine();

        System.out.println("Role filter: ");
        Boolean role = sc.nextBoolean();

        campComm.generateReport(faculty, role, camp, campName);

        sc.close();
    }


    
}

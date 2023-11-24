package Model.CampComm;

import java.io.IOException;
import java.util.ArrayList;

import Model.EnquirySuggestion.Enquiry;
import Model.EnquirySuggestion.EnquiryManager;
import Model.EnquirySuggestion.Suggestion;
import Model.EnquirySuggestion.SuggestionManager;
import Model.Camp.Camp;
import Model.Camp.CampManager;
import Model.Student.Student;
import Model.Student.StudentReport;
import Model.User.UserManager;
import Model.User.UserRole;

public class CampCommitee extends Student{
    private String campCommID; // same as student ID
    private int points;

    public CampCommitee(){}



    public CampCommitee(String userID, String password, String faculty, UserRole role){
        this.campCommID = userID;
        this.setPassword(password);
        this.setFaculty(faculty);
        this.points = 0;
        setRole(role); // set role to STUDENT
    }

////////////////////////////////////// CAMP FUNCTIONS //////////////////////////////////////

    public Camp getCamp(String campName){
        ArrayList<Camp> camps = CampManager.readCamps();
        for(Camp c: camps){
            if(c.getCampName().equals(campName)){
                return c;
            }
        }
        return null;
    }


    

    public void printCampDetails(Camp camp){
        System.out.println("Camp Name: " + camp.getCampName());
        System.out.println("Camp Start Date: " + camp.getStartDate());
        System.out.println("Camp End Date: " + camp.getEndDate());
        System.out.println("Camp Registration Deadline: " + camp.getRegistrationDeadline());
        System.out.println("Camp Location: " + camp.getLocation());
        System.out.println("Camp Max Capacity: " + camp.getMaxCapacity());
        System.out.println("Camp Committee Slots: " + camp.getCampCommSlots());
        System.out.println("Camp Description: " + camp.getCampDescription());
        System.out.println("Camp Staff ID: " + camp.getStaffID());
    }


    public String getCampCommID() { return campCommID; }

    public void setCampCommID(String campCommID) { this.campCommID = campCommID; }


////////////////////////////////////// SUGGESTION FUNCTIONS //////////////////////////////////////
    public void makeSuggestions(String message) throws IOException{
        Suggestion suggestion = new Suggestion(this.campCommID, message, false);
        SuggestionManager.writeSuggestion(suggestion);
        
    }

    public ArrayList<Suggestion> viewMySuggestions(){
        ArrayList<Suggestion> suggestions = SuggestionManager.readSuggestions();
        ArrayList<Suggestion> mySuggestions = new ArrayList<>();
        for(Suggestion s: suggestions){
            if(s.getStudentID().equals(this.campCommID)){
                System.out.println(s);
            }
        }

        return mySuggestions;
    }

    public void editSuggestion(Suggestion suggestion) throws IOException{
        SuggestionManager.editSuggestion(suggestion);
    }

    public void deleteSuggestion(Suggestion suggestion) throws IOException{
        SuggestionManager.deleteSuggestion(suggestion);
    }

    public void printSuggestionDetails(Suggestion suggestion){
        System.out.println("Suggestion ID: " + suggestion.getSuggestionID());
        System.out.println("Suggestion: " + suggestion.getSuggestionText());
        System.out.println("Accepted: " + (suggestion.getStatus() ? "Yes" : "No"));
    }

    //////////////////////////////////////// ENQUIRY FUNCTIONS //////////////////////////////////////

    public ArrayList<Enquiry> viewMyCampsEnquiries(){
        ArrayList<Enquiry> enquiries = EnquiryManager.readEnquiries();
        ArrayList<Enquiry> myEnquiries = new ArrayList<>();
        for(Enquiry e: enquiries){
            Camp camp  = e.getCamp();
            if(camp.getCampCommittee().contains(this)){
                myEnquiries.add(e);
            }
        
        }
        return myEnquiries;
    }
    public void replyEnquiry(int enquiryID, String reply) throws IOException{
        ArrayList<Enquiry> enquiries = EnquiryManager.readEnquiries();
        for(Enquiry e: enquiries){
            if(e.getEnquiryID() == enquiryID){
                if(e.getStatus() == true){
                    System.out.println("Enquiry has already been replied!");
                    break;
                }
                else{ 
                    e.setReply(reply);
                    EnquiryManager.editEnquiry(e);
                    System.out.println("Reply sent successfully!");
                    break;
                }
                
            }
        }

    }

    public void printEnquiryDetails(Enquiry enquiry) throws IOException{
        System.out.println("Enquiry ID: " + enquiry.getEnquiryID());
        System.out.println("Enquiry Student ID: " + enquiry.getStudentID());
        System.out.println("Enquiry Title: " + enquiry.getTitle());
        System.out.println("Enquiry Message: " + enquiry.getMessage());
        System.out.println("Enquiry Status: " + enquiry.getStatus());
        System.out.println("Enquiry Reply: " + enquiry.getReply());

    }
///////////////////////////////// POINTS FUNCTIONS /////////////////////////////////
    public int getPoints(){
        return points;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public void addPoints(int points){
        this.points += points;
    }


///////////////////////////////// REPORT FUNCTIONS //////////////////////////////////////

    public void generateReport(String facultyFilter, boolean isCampCommFilter, Camp camp, String campName)
    {
        ArrayList<Student> students = UserManager.readStudents();
        StudentReport report = new StudentReport(students);

        ArrayList<Student> filteredStudents = (ArrayList<Student>) report.filter(facultyFilter, isCampCommFilter, camp, campName);

        try{
            report.generateReport(filteredStudents);
            System.out.println("Report generated successfully!");
        } catch (IOException e){
            System.out.println("Error generating report: " + e.getMessage());
        }
        
    }



    

    
}

package Model.Staff;
import java.util.ArrayList;
import java.util.Date;


import Model.EnquirySuggestion.Enquiry;
import Model.EnquirySuggestion.EnquiryManager;
import Model.EnquirySuggestion.Suggestion;
import Model.EnquirySuggestion.SuggestionManager;
import Model.Camp.Camp;
import Model.Camp.CampManager;
import Model.Camp.CampStatus;
import Model.CampComm.CampCommitee;
import Model.CampComm.CampCommiteeManager;
import Model.Student.Student;
import Model.Student.StudentManager;
import Model.Student.StudentReport;
import Model.User.User;
import Model.User.UserGroup;
import Model.User.UserManager;
import Model.User.UserRole;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Staff extends User implements SuggestionInterface, EnquiryInterfaceStaff {
    private String staffID;
    private String name;

    public Staff(){}

    public Staff(String name, String userID, String password, String faculty, UserRole role){
        this.name = name;
        this.staffID = userID;
        this.setPassword(password);
        this.setFaculty(faculty);
        setRole(role); // set role to STAFF
    }

    // getters and setters
    public String getStaffID() { return staffID; }
    
    public void setStaffID(String staffID) { this.staffID = staffID; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public UserRole getUserRole() { return super.getUserRole(); }

    public Camp getCamp(String campName){
        ArrayList<Camp> camps = CampManager.readCamps();
        for(Camp c: camps){
            if(c.getCampName().equals(campName)){
                return c;
            }
        }
        return null;
    }


    public void createCamp(String campName, Date startDate, Date endDate, Date registrationDeadline,
    String userGroup, String location, int maxCapacity, int campCommitteeSlots,
    String description) throws IOException{
        Camp camp = new Camp(campName, startDate, endDate, registrationDeadline, 
        userGroup, location, maxCapacity, campCommitteeSlots,description , getStaffID());
        camp.setCampName(campName);
        camp.setStartDate(startDate);
        camp.setEndDate(endDate);
        camp.setRegistrationDeadline(registrationDeadline);
        camp.setUserGroup(UserGroup.valueOf(userGroup));
        camp.setLocation(location);
        camp.setMaxCapacity(maxCapacity);
        camp.setCampCommSlots(campCommitteeSlots);
        camp.setCampDescription(description);
        camp.setStaffID(getUserID());

        CampManager.writeNewCamps(camp);
        System.out.println("Camp created successfully!");
        
    }

    public void editStartDate(Camp camp, Date newStartDate) throws IOException{
        ArrayList<Camp> camps = CampManager.readCamps();
        for (Camp c: camps){
            if(c.getCampName().equals(camp.getCampName())){
                c.setStartDate(newStartDate);
                CampManager.editCamps(c);
                System.out.println("Start date changed successfully!");
                break;
            }
        }
        if (camp.getStartDate().equals(newStartDate)){
            System.out.println("Start date is the same as before!");
        }

        else if (camp.getStartDate().after(newStartDate)){
            System.out.println("Start date cannot be before the current date!");
        }
    }

    public void editEndDate(Camp camp, Date newEndDate) throws IOException{
        ArrayList<Camp> camps = CampManager.readCamps();
        for (Camp c: camps){
            if(c.getCampName().equals(camp.getCampName())){
                c.setEndDate(newEndDate);
                CampManager.editCamps(c);
                System.out.println("End date changed successfully!");
                break;
            }
        }

        if (camp.getEndDate().equals(newEndDate)){
            System.out.println("End date is the same as before!");
        }

        else if (camp.getEndDate().before(newEndDate)){
            System.out.println("End date cannot be before the current date!");
        }

       
    }


    public void editRegistrationDeadline(Camp camp, Date newRegistrationDeadline) throws IOException{
        ArrayList<Camp> camps = CampManager.readCamps();
        for (Camp c: camps){
            if(c.getCampName().equals(camp.getCampName())){
                c.setRegistrationDeadline(newRegistrationDeadline);
                CampManager.editCamps(c);
                System.out.println("Registration deadline changed successfully!");
                break;
            }
        }

        if (camp.getRegistrationDeadline().equals(newRegistrationDeadline)){
            System.out.println("Registration deadline is the same as before!");
        }

        else if (camp.getRegistrationDeadline().before(newRegistrationDeadline)){
            System.out.println("Registration deadline cannot be before the current date!");
        }

        
    }

    public void editUserGroup(Camp camp, String newUserGroup) throws IOException{
        ArrayList<Camp> camps = CampManager.readCamps();
        for (Camp c: camps){
            if(c.getCampName().equals(camp.getCampName())){
                c.setUserGroup(UserGroup.valueOf(newUserGroup));
                CampManager.editCamps(c);
                System.out.println("User group changed successfully!");
                break;
            }
        }

        if (camp.getUserGroup().equals(newUserGroup)){
            System.out.println("User group is the same as before!");
        }

        
    }

    public void editLocation(Camp camp, String newLocation) throws IOException{
        ArrayList<Camp> camps = CampManager.readCamps();
        for (Camp c: camps){
            if(c.getCampName().equals(camp.getCampName())){
                c.setLocation(newLocation);
                CampManager.editCamps(c);
                System.out.println("Location changed successfully!");
                break;
            }
        }

        if (camp.getLocation().equals(newLocation)){
            System.out.println("Location is the same as before!");
        }

        
    }

    public void editMaxCapacity(Camp camp, int newMaxCapacity) throws IOException{
        ArrayList<Camp> camps = CampManager.readCamps();
        for (Camp c: camps){
            if(c.getCampName().equals(camp.getCampName())){
                c.setMaxCapacity(newMaxCapacity);
                CampManager.editCamps(c);
                System.out.println("Max capacity changed successfully!");
                break;
            }
        }

        if (camp.getMaxCapacity() == newMaxCapacity){
            System.out.println("Max capacity is the same as before!");
        }

       
    }

    public void editCampCommitteeSlots(Camp camp, int newCampCommitteeSlots) throws IOException{
        ArrayList<Camp> camps = CampManager.readCamps();
        for (Camp c: camps){
            if(c.getCampName().equals(camp.getCampName())){
                c.setCampCommSlots(newCampCommitteeSlots);
                CampManager.editCamps(c);
                System.out.println("Camp committee slots changed successfully!");
                break;
            }
        }

        if(camp.getCampCommSlots() > 10){
            System.out.println("Camp committee slots cannot be more than 10!");
        }

        if (camp.getCampCommSlots() == newCampCommitteeSlots){
            System.out.println("Camp committee slots is the same as before!");
        }

        

        
    }

    public void editCampDescription(Camp camp, String newDescription){
        ArrayList<Camp> camps = CampManager.readCamps();
        for (Camp c: camps){
            if(c.getCampName().equals(camp.getCampName())){
                c.setCampDescription(newDescription);
                System.out.println("Camp description changed successfully!");
                break;
            }
        }

        if (camp.getCampDescription().equals(newDescription)){
            System.out.println("Camp description is the same as before!");
        }

        
    }

    // view all camps
    public ArrayList<Camp> viewAllCamps(){
        ArrayList<Camp> camps = CampManager.readCamps();
        return camps;
    }

    // view camps made by Staff 
    public ArrayList<Camp> viewOwnCamps(){
        
        ArrayList<Camp> camps = new ArrayList<>();
        // System.out.println("Debug: viewCamps entered");
        
        ArrayList<Camp> allCamps = CampManager.readCamps();
        // System.out.println("Debug: after readCamp");
        for(Camp c: allCamps){
            if(c.getStaffID().equals(getUserID())){
                camps.add(c);
            }
        }
        return camps;
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


    public void deleteCamp(Camp camp) throws IOException{
        ArrayList<Camp> camps = CampManager.readCamps();
        if(camp.getStaffID().equals(getStaffID())){
            camps.remove(camp);
            CampManager.writeNewCamps(camp);
            System.out.println("Camp deleted successfully!");
        } else {
            System.out.println("Camp not deleted!");
        }
    }

    public void changeCampVisibility(Camp camp) throws IOException{
        // ArrayList<Camp> camps = CampManager.readCamps();
        if(camp.getStaffID().equals(getStaffID())){
            if(camp.getStatus() == CampStatus.VISIBLE){
                camp.setStatus(CampStatus.HIDDEN);
                CampManager.editCamps(camp);
                System.out.println("Camp visibility changed to CLOSED!");
            } else if(camp.getStatus() == CampStatus.VISIBLE){
                camp.setStatus(CampStatus.HIDDEN);
                CampManager.editCamps(camp);
                System.out.println("Camp visibility changed to OPEN!");
            }
        } else {
            System.out.println("Camp visibility not changed!");
        }
    }



    /////////////////// ENQUIRIES //////////////////////
    // view enquiries
    public ArrayList<Enquiry> viewMyCampsEnquiries(){
        ArrayList<Enquiry> enquiries = EnquiryManager.readEnquiries();
        ArrayList<Enquiry> myEnquiries = new ArrayList<>();
        for(Enquiry e: enquiries){
            Camp camp  = e.getCamp();
            if(camp.getStaffID().equals(this.staffID)){
                myEnquiries.add(e);
            }
        }
        return myEnquiries;
    }

    // reply enquiries
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


    public void printEnquiryDetails(Enquiry enquiry){
        System.out.println("Enquiry ID: " + enquiry.getEnquiryID());
        System.out.println("Enquiry Student ID: " + enquiry.getStudentID());
        System.out.println("Enquiry Title: " + enquiry.getTitle());
        System.out.println("Enquiry Message: " + enquiry.getMessage());
        System.out.println("Enquiry Status: " + enquiry.getStatus());
        System.out.println("Enquiry Reply: " + enquiry.getReply());

    }



    /////////////////////// Suggestions ///////////////////////

    public ArrayList<Suggestion> viewMyCampsSuggestions(){
        ArrayList<Suggestion> suggestions = SuggestionManager.readSuggestions();
        ArrayList<Suggestion> mySuggestions = new ArrayList<>();
        for(Suggestion s: suggestions){
            Camp camp  = s.getCamp();
            if(camp.getStaffID().equals(this.staffID)){
                mySuggestions.add(s);
            }
        }
        return mySuggestions;
    }

    // approve suggestions
    public void approveSuggestion(int suggestionID) throws IOException{
        ArrayList<Suggestion> suggestions = SuggestionManager.readSuggestions();
        for(Suggestion s: suggestions){
            if(s.getSuggestionID() == suggestionID){
                if(s.getStatus() == true){
                    System.out.println("Suggestion has already been approved!");
                    break;
                }
                else{ 
                    s.setStatus(true);
                    SuggestionManager.editSuggestion(s);
                    System.out.println("Suggestion approved successfully!");
                    break;
                }
                
            }
        }

    }
    


    public void printSuggestionDetails(Suggestion suggestion){
        System.out.println("Suggestion ID: " + suggestion.getSuggestionID());
        System.out.println("Suggestion Student ID: " + suggestion.getStudentID());
        System.out.println("Suggestion Camp Name: " + suggestion.getCamp().getCampName());
        System.out.println("Suggestion Text: " + suggestion.getSuggestionText());
        System.out.println("Suggestion Status: " + suggestion.getStatus());
    }



    ///////////////////////////////////////     REPORTS     ///////////////////////////////////////
    
    public void generateReport(String facultyFilter, boolean isCampCommFilter, Camp camp, String campName) throws IOException
    {
        ArrayList<Student> students = UserManager.readStudents();
        ArrayList<Student> registeredStudents = new ArrayList<Student>();
        for (Student s: students){
            String studentID = s.getStudentID();
            if (StudentManager.readStudentFile(campName, studentID)){
                registeredStudents.add(s);
            }
        }
        
        StudentReport.generateReport(registeredStudents, isCampCommFilter, facultyFilter);



    }

    public static void generatePerformanceReport(Staff staff, String campName) throws IOException {
        ArrayList<Student> students = UserManager.readStudents();
        ArrayList<CampCommitee> committeeMembers = new ArrayList<>();
    
        for (Student s : students) {
            String studentID = s.getStudentID();
            if (s instanceof CampCommitee && CampCommiteeManager.readCampCommFile(campName, studentID)) {
                System.out.println("Committee Member: " + studentID);
                committeeMembers.add((CampCommitee) s); // Safe cast because of the instanceof check
            }
        }
        System.out.println("Debug: report generation");
        PerformanceReport.generateReport(committeeMembers, campName);
    }
    
    
    

    

   
    
}

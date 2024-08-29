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

/**
 * The {@code Staff} class represents a staff member in the system. It extends the {@link User}
 * class and provides functionality related to camps, enquiries, suggestions, and generating reports.
 * Staff members have the ability to create, edit, and manage camps, respond to enquiries, approve
 * suggestions, and generate reports.
 * 
 * @author Jia Wei
 * @version 4.0
 * @since 2023-11-19
 */
public class Staff extends User implements SuggestionInterface, EnquiryInterfaceStaff {
    private String staffID;
    private String name;

    /**
     * Constructs a new {@code Staff} instance with default values.
     */

    public Staff(){}

     /**
     * Constructs a new {@code Staff} instance with the specified information.
     *
     * @param name     The name of the staff member.
     * @param userID   The user ID of the staff member.
     * @param password The password of the staff member.
     * @param faculty  The faculty to which the staff member belongs.
     * @param role     The role of the staff member.
     */

    public Staff(String name, String userID, String password, String faculty, UserRole role){
        this.name = name;
        this.staffID = userID;
        this.setPassword(password);
        this.setFaculty(faculty);
        setRole(role); // set role to STAFF
    }

    // getters and setters
    /**
     * Gets the staff ID of the staff member.
     * 
     * @return The staff ID of the staff member.
     */
    public String getStaffID() { return staffID; }
    /**
     * Sets the staff ID of the staff member.
     * 
     * @param staffID The staff ID to set.
     * 
     */
    public void setStaffID(String staffID) { this.staffID = staffID; }

    /**
     * Gets the staff member's name.
     *
     * @return The name of the staff member.
     */
    public String getName() { return name; }
     /**
     * Sets the staff member's name.
     *
     * @param name The new name for the staff member.
     */
    public void setName(String name) { this.name = name; }
    /**
     * Gets the user role of the staff member.
     *
     * @return The user role of the staff member.
     */
    public UserRole getUserRole() { return super.getUserRole(); }
    /**
     * Retrieves a camp by its name.
     *
     * @param campName The name of the camp to retrieve.
     * @return The {@link Camp} object associated with the camp name, or {@code null} if not found.
     */
    public Camp getCamp(String campName){
        ArrayList<Camp> camps = CampManager.readCamps();
        for(Camp c: camps){
            if(c.getCampName().equals(campName)){
                return c;
            }
        }
        return null;
    }

    /**
     * Creates a new camp with the provided details.
     *
     * @param campName           The name of the camp.
     * @param startDate          The start date of the camp.
     * @param endDate            The end date of the camp.
     * @param registrationDeadline The registration deadline for the camp.
     * @param userGroup          The user group associated with the camp.
     * @param location           The location of the camp.
     * @param maxCapacity        The maximum capacity of the camp.
     * @param campCommitteeSlots The number of camp committee slots available.
     * @param description        The description of the camp.
     * @throws IOException If an I/O error occurs while creating the camp.
     */
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
    /**
     * Edits an existing camp with the new start date
     * @param camp
     * @param newStartDate
     * @throws IOException
     */

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

    /**
     * Edits an existing camp with the new end date
     * @param camp
     * @param newEndDate
     * @throws IOException
     */
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

    /**
     * Edits an existing camp with the new registration deadline
     * @param camp
     * @param newRegistrationDeadline
     * @throws IOException
     */
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

    /**
     * Edits an existing camp with the new user group
     * @param camp
     * @param newUserGroup
     * @throws IOException
     */

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

    /**
     * Edits an existing camp with the new location
     * @param camp
     * @param newLocation
     * @throws IOException
     */

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

    /**
     * Edits an existing camp with the new max capacity
     * @param camp
     * @param newMaxCapacity
     * @throws IOException
     */

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

    /**
     * Edits an existing camp with the new camp committee slots
     * @param camp
     * @param newCampCommitteeSlots
     * @throws IOException
     */
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

    /**
     * Edits an existing camp with the new description
     * @param camp
     * @param newDescription
     */
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

    /**
     * Retrieves a list of all camps in the system.
     *
     * @return An {@link ArrayList} of {@link Camp} objects representing all camps in the system.
     */
    public ArrayList<Camp> viewAllCamps(){
        ArrayList<Camp> camps = CampManager.readCamps();
        return camps;
    }
    /**
     * Retrieves a list of camps created by the staff member.
     *
     * @return An {@link ArrayList} of {@link Camp} objects representing camps created by the staff member.
     */

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

    /**
     * Prints details of a camp to the console.
     *
     * @param camp The {@link Camp} object for which details should be printed.
     */

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

     /**
     * Deletes a camp if it was created by the staff member.
     *
     * @param camp The {@link Camp} object to be deleted.
     * @throws IOException If an I/O error occurs while deleting the camp.
     */
    public void deleteCamp(Camp camp) throws IOException{
        ArrayList<Camp> camps = CampManager.readCamps();
        // System.out.println("Staff ID: " + this.getUserID());
        // System.out.println("Camp Staff ID: " + camp.getStaffID());
        if(camp.getStaffID().equals(this.getUserID())){
            // System.out.println("Debug: camp Name: " + camp.getCampName());
            CampManager.deleteCamp(camp);
            System.out.println("Camp deleted successfully!");
        } else {
            System.out.println("Camp not deleted!");
        }
    }

    /**
     * Changes the visibility status of a camp.
     *
     * @param camp The {@link Camp} object for which visibility should be changed.
     * @throws IOException If an I/O error occurs while changing camp visibility.
     */

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
    /**
     * Retrieves a list of enquiries related to camps created by the staff member.
     *
     * @return An {@link ArrayList} of {@link Enquiry} objects representing enquiries related to the staff member's camps.
     */
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
    /**
     * Replies to an enquiry with a given ID.
     *
     * @param enquiryID The ID of the enquiry to be replied to.
     * @param reply The reply message to be sent.
     * @throws IOException If an I/O error occurs while replying to the enquiry.
     */
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

    /**
     * Prints details of an enquiry to the console.
     *
     * @param enquiry The {@link Enquiry} object for which details should be printed.
     */
    public void printEnquiryDetails(Enquiry enquiry){
        System.out.println("Enquiry ID: " + enquiry.getEnquiryID());
        System.out.println("Enquiry Student ID: " + enquiry.getStudentID());
        System.out.println("Enquiry Title: " + enquiry.getTitle());
        System.out.println("Enquiry Message: " + enquiry.getMessage());
        System.out.println("Enquiry Status: " + enquiry.getStatus());
        System.out.println("Enquiry Reply: " + enquiry.getReply());

    }



    /////////////////////// Suggestions ///////////////////////
    /**
     * Retrieves a list of suggestions related to camps created by the staff member.
     *
     * @return An {@link ArrayList} of {@link Suggestion} objects representing suggestions related to the staff member's camps.
     */
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
    /**
     * Approves a suggestion with a given ID.
     *
     * @param suggestionID The ID of the suggestion to be approved.
     * @throws IOException If an I/O error occurs while approving the suggestion.
     */
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
    
    /**
     * Prints details of a suggestion to the console.
     *
     * @param suggestion The {@link Suggestion} object for which details should be printed.
     */

    public void printSuggestionDetails(Suggestion suggestion){
        System.out.println("Suggestion ID: " + suggestion.getSuggestionID());
        System.out.println("Suggestion Student ID: " + suggestion.getStudentID());
        System.out.println("Suggestion Camp Name: " + suggestion.getCamp().getCampName());
        System.out.println("Suggestion Text: " + suggestion.getSuggestionText());
        System.out.println("Suggestion Status: " + suggestion.getStatus());
    }



    ///////////////////////////////////////     REPORTS     ///////////////////////////////////////
    
    /**
     * Generates a report based on specified filters and camp information.
     *
     * @param facultyFilter   A filter to include only students from a specific faculty in the report.
     * @param isCampCommFilter A boolean indicating whether to include camp committee members in the report.
     * @param camp            The {@link Camp} object for which the report should be generated.
     * @param campName        The name of the camp for which the report should be generated.
     * @throws IOException If an I/O error occurs while generating the report.
     */
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
    /**
     * Generates a performance report for camp committee members of a specific camp.
     *
     * @param staff    The {@link Staff} member responsible for generating the report.
     * @param campName The name of the camp for which the performance report should be generated.
     * @throws IOException If an I/O error occurs while generating the performance report.
     */

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

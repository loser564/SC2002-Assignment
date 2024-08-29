package Model.Student;

import java.io.*;
import java.util.ArrayList;

import Model.EnquirySuggestion.Enquiry;
import Model.EnquirySuggestion.EnquiryManager;
import Model.Camp.Camp;
import Model.Camp.CampManager;
import Model.CampComm.CampCommitee;
import Model.CampComm.CampCommiteeManager;
import Model.User.User;
import Model.User.UserGroup;
import Model.User.UserRole;

/**
* Represents a student user in the system, inheriting from the User class.
* Manages student-specific operations such as camp registration, camp committee applications,
* and handling of enquiries.
* @author SCEX Group 3
*/

public class Student extends User implements CampInterface, EnquiryInterfaceStudent, CampCommInterface{
    private String studentID;
    private boolean isCampCommittee;
    private String name;
    private int remainderCampCommSlots;

    /**
    * Default constructor for the Student class.
    */
    public Student(){}

    /**
    * Constructor for creating a new Student with specified attributes.
    *
    * @param name     The name of the student.
    * @param userID   The unique user ID of the student.
    * @param password The password of the student.
    * @param faculty  The faculty to which the student belongs.
    * @param role     The role of the user (in this case, STUDENT).
    */
    public Student(String name, String userID, String password, String faculty, UserRole role){
        // System.out.println("Constructing student - userID: " + userID); 
        this.name = name;
        this.studentID = userID;
        this.setPassword(password);
        this.setFaculty(faculty);
        setRole(role); // set role to STUDENT
    }


    ////////////////////// GETTERS AND SETTERS  //////////////////////

    /**
    * Gets the name of the student.
    *
    * @return The name of the student.
    */
    public String getName() { return name; }

    /**
    * Sets the name of the student.
    *
    * @param name The new name of the student.
    */
    public void setName(String name) { this.name = name; }

    /**
    * Gets the student ID.
    *
    * @return The student ID.
    */
    public String getStudentID() { return studentID; }

    /**
    * Sets the student ID.
    *
    * @param studentID The new student ID.
    */
    public void setStudentID(String studentID) { this.studentID = studentID; }
    


    

    

    //////////////////// CAMP COMMITTEE FUNCTIONS ////////////////////

    /**
    * Gets the remaining camp committee slots for the student.
    *
    * @return The remaining camp committee slots.
    */
    public int getRemainderCampCommSlots() { return remainderCampCommSlots; }

    /**
    * Sets whether the student is a camp committee member or not.
    *
    * @param isCampCommittee True if the student is a camp committee member, false otherwise.
    */
    public void setCampCommittee(boolean isCampCommittee) { this.isCampCommittee = isCampCommittee; }

    /**
    * Checks if the student is a member of any camp committee.
    *
    * @return True if the student is a camp committee member, false otherwise.
    */
    public boolean getCampCommittee() {
        ArrayList<Camp> camps = CampManager.readCamps();
        for(Camp c: camps){
            if(CampCommiteeManager.readCampCommFile(c.getCampName(), getUserID())){
                return true;
            }
        }
        return false;
    }

    /**
    * Applies for a camp committee position in a specified camp.
    *
    * @param camp The camp for which the student is applying for a committee position.
    * @throws IOException If an I/O error occurs.
    */
    public void applyCampCommittee(Camp camp) throws IOException{
        ArrayList<Camp> camps = CampManager.readCamps();
        System.out.println("Debug: applyCampCommittee entered");
        System.out.println("Debug: isCampCommittee: " + isCampCommittee);
        if(!isCampCommittee){
            System.out.println("Debug: You are not a camp committee!");
            if(camp.getCampCommSlots() > 0){
                System.out.println("Debug: Camp committee slots available!");
                camp.setCampCommSlots(camp.getCampCommSlots() -1);
                // camps.add(camp);
                // CampManager.writeCamps(camp);
                CampCommitee commiteeMember = new CampCommitee(this.getStudentID(), this.getPassword(), this.getFaculty(), name, UserRole.CAMP_COMMITTEE);
                // // add ID to camp committee list
                camp.getCampCommittee().add(commiteeMember);
                CampCommiteeManager.writeCampCommitee(camps, camp.getCampName(), this.getUserID());
                isCampCommittee = true;

            } else {
                System.out.println("Camp committee slots are full!");
                
            }
        } else {
            System.out.println("You are already a camp committee!");
        }
    }

    //////////////////// CAMP FUNCTIONS ////////////////////

    /**
    * Retrieves a list of camps available to the student based on their faculty or open to all.
    *
    * @return A list of camps.
    */
    public ArrayList<Camp> viewCamps(){
        
        ArrayList<Camp> camps = new ArrayList<>();
        // System.out.println("Debug: viewCamps entered");
        
        ArrayList<Camp> allCamps = CampManager.readCamps();
        // System.out.println("Debug: after readCamp");
        for(Camp c: allCamps){
            // if camp is for student's faculty or open to all
            if (c.getUserGroup().equals(this.getFaculty()) || c.getUserGroup() == UserGroup.ALL){
                camps.add(c);
            }
        }
        return camps;
    }

    /**
    * Retrieves a specific camp based on its name.
    *
    * @param campName The name of the camp to retrieve.
    * @return The camp object, or null if not found.
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
    * Registers the student for a specified camp.
    *
    * @param camp The camp for which the student is registering.
    * @param studentID The ID of the student registering.
    * @throws IOException If an I/O error occurs.
    */
    public void registerCamp(Camp camp, String studentID) throws IOException{
        ArrayList<Camp> camps;
        camps = CampManager.readCamps();
        // System.out.println("Debug: registerCamp entered");

        // check if this is legal
        // check if student is blacklisted
        if (camp.getBlackListedStudents().contains(this)){
            System.out.println("You have quit from this camp!");
            return;
        }
        // check if student  is registered
        else if (camp.getRegisteredStudents().contains(this)){
            System.out.println("You are already registered for this camp!");
            return;
        }

        // check if student is from the same faculty
        else if (!camp.getUserGroup().equals(this.getFaculty())){
            // System.out.println("Debug: checkk usergroup");
            if (camp.getUserGroup() != UserGroup.ALL){
                System.out.println("You are not from the same faculty as this camp!");
                return;
            }
        
        // check if camp is open for registration
        } else if (!camp.isOpen()){
            System.out.println("Camp is not open for registration!");
            return;
        }
        // check if camp is full
        else if(camp.getRegisteredStudents().size() == camp.getMaxCapacity()){
            System.out.println("Camp is full!");
            return;
        }
        // alls good, register

            camp.getRegisteredStudents().add(this);
            // System.out.println("Debug: c.getRegisteredStudents(): " + camp.getRegisteredStudents());
            System.out.println("Camp registered successfully!");
            int registerations = camp.getNumberOfRegisteredStudents();
            camp.setNumberOfRegisteredStudents(registerations + 1);
            System.out.println("Student ID: " + studentID);
            StudentManager.writeStudentFile(camps, camp.getCampName(), this.getUserID());

    
        
    }

    /**
    * Retrieves a list of camps for which the student is registered.
    *
    * @return A list of registered camps.
    * @throws IOException If an I/O error occurs.
    */
    public ArrayList<Camp> viewRegisteredCamps() throws IOException{
        
        ArrayList<Camp> registeredCamps = new ArrayList<>();
        ArrayList<Camp> allCamps = CampManager.readCamps();
        
        for(Camp c: allCamps){
            boolean inCamp = StudentManager.readStudentFile(c.getCampName(), this.getUserID());
            System.out.println(inCamp);
            if (inCamp){
                
                registeredCamps.add(c);
            }

        }
        return registeredCamps;
    }

    /**
    * Prints details of a specified camp.
    *
    * @param camp The camp for which details will be printed.
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
        System.out.println("-------------------------------------");
    }

    /**
    * Removes the student from a specified camp.
    *
    * @param camp The camp from which the student is quitting.
    * @throws IOException If an I/O error occurs.
    */
    // blacklist student from camp after
    public void quitCamp(Camp camp) throws IOException{
        String campName = camp.getCampName();
        if (StudentManager.readStudentFile(campName, this.getUserID())){
            ArrayList<Camp> camps = CampManager.readCamps();
            StudentManager.removeStudent(camps, campName, campName);;
        } else {
            System.out.println("You are not registered for this camp!");
        }
        
    }

    //////////////////// ENQUIRY FUNCTIONS ////////////////////
    /**
    * Submits an enquiry for a specified camp with a title and message.
    *
    * @param camp The camp for which the enquiry is submitted.
    * @param title The title of the enquiry.
    * @param message The message content of the enquiry.
    * @throws IOException If an I/O error occurs.
    */
    public void submitEnquiry(String camp,String title, String message) throws IOException{
        Enquiry enquiry = new Enquiry(this.getUserID(), camp, title, message, false);
        EnquiryManager.writeEnquiries(enquiry);
    }

    /**
    * Retrieves a list of all enquiries in the system.
    *
    * @return A list of all enquiries.
    */
    public ArrayList<Enquiry> viewAllEnquiries(){
        return EnquiryManager.readEnquiries();
    }

    /**
    * Retrieves a list of enquiries submitted by the student.
    *
    * @return A list of enquiries submitted by the student.
    */
    public ArrayList<Enquiry> viewMyEnquiries(){
        ArrayList<Enquiry> enquiries = EnquiryManager.readEnquiries();
        ArrayList<Enquiry> myEnquiries = new ArrayList<>();
        for(Enquiry e: enquiries){
            if(e.getStudentID().equals(this.getUserID())){
                myEnquiries.add(e);
            }
        }
        return myEnquiries;
    }


    /**
    * Prints details of a specified enquiry.
    *
    * @param enquiry The enquiry for which details will be printed.
    */
    public void printEnquiryDetails(Enquiry enquiry){
        System.out.println("Enquiry ID: " + enquiry.getEnquiryID());
        System.out.println("Enquiry Student ID: " + enquiry.getStudentID());
        System.out.println("Enquiry Title: " + enquiry.getTitle());
        System.out.println("Enquiry Message: " + enquiry.getMessage());
        System.out.println("Enquiry Status: " + enquiry.getStatus());
        System.out.println("Enquiry Reply: " + enquiry.getReply());

    }

    /**
    * Edits the content of a specified enquiry.
    *
    * @param enquiry The enquiry to be edited.
    * @throws IOException If an I/O error occurs.
    */
    public void editEnquiry(Enquiry enquiry) throws IOException{
        EnquiryManager.editEnquiry(enquiry);
    }

    /**
    * Deletes a specified enquiry.
    *
    * @param enquiry The enquiry to be deleted.
    * @throws IOException If an I/O error occurs.
    */
    public void deleteEnquiry(Enquiry enquiry) throws IOException{
        EnquiryManager.deleteEnquiry(enquiry);
    }

    @Override
    public void submitEnquiry(String title, String message) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'submitEnquiry'");
    }

    


    
}

package Model;

import java.io.*;
import java.util.ArrayList;

public class Student extends User{
    private String studentID;
    private boolean isCampCommittee;
    private String name;

    public Student(){}

    public Student(String name, String userID, String password, String faculty, UserRole role){
        this.name = name;
        this.studentID = userID;
        this.setPassword(password);
        this.setFaculty(faculty);
        setRole(role); // set role to STUDENT
    }


    ////////////////////// GETTERS AND SETTERS  //////////////////////
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
    
    public String getStudentID() { return this.studentID; }

    public void setStudentID(String studentID) { this.studentID = studentID; }

    public ArrayList<Camp> viewCamps(){
        ArrayList<Camp> camps = new ArrayList<>();
        ArrayList<Camp> allCamps = CampManager.readCamps();
        for(Camp c: allCamps){
            // if camp is for student's faculty or open to all
            if (c.getUserGroup().equals(this.getFaculty()) || c.getUserGroup() == UserGroup.ALL){
                camps.add(c);
            }
        }
        return camps;
    }

    

    //////////////////// CAMP COMMITTEE FUNCTIONS ////////////////////

    public void setCampCommittee(boolean isCampCommittee) { this.isCampCommittee = isCampCommittee; }

    public boolean getCampCommittee() { return isCampCommittee; }

    public void applyCampCommittee(Camp camp) throws IOException{
        ArrayList<Camp> camps = CampManager.readCamps();
        if(!isCampCommittee){
            if(camp.getCampCommSlots() > 0){
                camp.getCampCommSlots();
                camps.add(camp);
                CampManager.writeCamps(camp);
                isCampCommittee = true;
                CampCommitee commiteeMember = new CampCommitee(this.getStudentID(), this.getPassword(), this.getFaculty(), UserRole.CAMP_COMMITTEE);
                // add ID to camp committee list
                camp.getCampCommittee().add(commiteeMember);

            } else {
                System.out.println("Camp committee slots are full!");
            }
        } else {
            System.out.println("You are already a camp committee!");
        }
    }

    //////////////////// CAMP FUNCTIONS ////////////////////

    public Camp getCamp(String campName){
        ArrayList<Camp> camps = CampManager.readCamps();
        for(Camp c: camps){
            if(c.getCampName().equals(campName)){
                return c;
            }
        }
        return null;
    }

    public void registerCamp(Camp camp) throws IOException{
        ArrayList<Camp> camps = CampManager.readCamps();

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
        else{
            camp.getRegisteredStudents().add(this);
            camps.add(camp);
            CampManager.writeCamps(camp);
            System.out.println("Camp registered successfully!");
        }


        
    
        
    }

    public ArrayList<Camp> viewRegisteredCamps(){
        ArrayList<Camp> registeredCamps = new ArrayList<>();
        ArrayList<Camp> allCamps = CampManager.readCamps();
        for(Camp c: allCamps){
            if(c.getRegisteredStudents().contains(this)){
                registeredCamps.add(c);
            }
        }
        return registeredCamps;
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

    // blacklist student from camp after
    public void quitCamp(Camp camp) throws IOException{
        ArrayList<Camp> camps = CampManager.readCamps();
        if (camp.getRegisteredStudents().contains(this)){
            camp.getRegisteredStudents().remove(this);
            camp.getBlackListedStudents().add(this);
            camps.add(camp);
            CampManager.writeCamps(camp);
            System.out.println("Camp quit successfully!");
        } else {
            System.out.println("You are not registered for this camp!");
        }
        
    }

    //////////////////// ENQUIRY FUNCTIONS ////////////////////
    public void submitEnquiry(String title, String message) throws IOException{
        Enquiry enquiry = new Enquiry(this.getStudentID(), title, message, false);
        EnquiryManager.writeEnquiries(enquiry);
    }

    public ArrayList<Enquiry> viewAllEnquiries(){
        ArrayList<Enquiry> enquiries = EnquiryManager.readEnquiries();
        return enquiries;
    }

    public ArrayList<Enquiry> viewMyEnquiries(){
        ArrayList<Enquiry> enquiries = EnquiryManager.readEnquiries();
        ArrayList<Enquiry> myEnquiries = new ArrayList<>();
        for(Enquiry e: enquiries){
            if(e.getStudentID().equals(this.getStudentID())){
                myEnquiries.add(e);
            }
        }
        return myEnquiries;
    }

    public void printEnquiryDetails(Enquiry enquiry) throws IOException{
        System.out.println("Enquiry ID: " + enquiry.getEnquiryID());
        System.out.println("Enquiry Student ID: " + enquiry.getStudentID());
        System.out.println("Enquiry Title: " + enquiry.getTitle());
        System.out.println("Enquiry Message: " + enquiry.getMessage());
        System.out.println("Enquiry Status: " + enquiry.getStatus());
        System.out.println("Enquiry Reply: " + enquiry.getReply());

    }

    public void editEnquiry(Enquiry enquiry) throws IOException{
        EnquiryManager.editEnquiry(enquiry);
    }

    public void deleteEnquiry(Enquiry enquiry) throws IOException{
        EnquiryManager.deleteEnquiry(enquiry);
    }

    


    
}

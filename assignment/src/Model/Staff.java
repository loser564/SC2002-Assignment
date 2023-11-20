package Model;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Staff extends User {
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
    public String getStaffID() { return this.staffID; }
    
    public void setStaffID(String staffID) { this.staffID = staffID; }

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
        camp.setStaffID(getStaffID());

        CampManager.writeCamps(camp);
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
        ArrayList<Camp> ownCamps = new ArrayList<>();
        ArrayList<Camp> camps = CampManager.readCamps();
        System.out.println("Staff ID: " + getStaffID());
        for(Camp c: camps){
            System.out.println("Camp Staff ID: " + c.getStaffID());
            if(c.getStaffID().equals(getStaffID())){
                ownCamps.add(c);
            }
        }
        return ownCamps;
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
            CampManager.writeCamps(camp);
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


    public void printEnquiryDetails(Enquiry enquiry) throws IOException{
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
    


    public void printSuggestionDetails(Suggestion suggestion) throws IOException{
        System.out.println("Suggestion ID: " + suggestion.getSuggestionID());
        System.out.println("Suggestion Student ID: " + suggestion.getStudentID());
        System.out.println("Suggestion Camp Name: " + suggestion.getCamp().getCampName());
        System.out.println("Suggestion Text: " + suggestion.getSuggestionText());
        System.out.println("Suggestion Status: " + suggestion.getStatus());
    }



    ///////////////////////////////////////     REPORTS     ///////////////////////////////////////
    
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

    public static void generatePerformanceReport(Staff staff, Camp camp, String filePath) {
        ArrayList<CampCommitee> committeeMembers = camp.getCampCommittee();

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            writer.println("Camp Name: " + camp.getCampName());
            writer.println("Staff ID: " + staff.getStaffID());
            writer.println("Points Report:");

            for (CampCommitee committeeMember : committeeMembers) {
                writer.println("Committee Member: " + committeeMember.getCampCommID());
                writer.println("Points: " + committeeMember.getPoints());
                writer.println();
            }

            System.out.println("Performance report has been written to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}

package src.basic;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Camp {
    private String campName;
    private Date startDate;
    private Date endDate;
    private Date registrationClosingDate;
    private String userGroup;  // Own school or whole NTU
    private String location;
    private int totalSlots;
    private int campCommitteeSlots; // Max 10
    private String description;
    private String userID;  // Automatically tied to the id of the staff who created it
    private boolean isVisible;  // Whether the camp is visible to students

    private List<Student> attendees;  // List of students registered for the camp as attendees
    private List<Student> committeeMembers;  // List of students registered for the camp as committee
    
    private List<Camp> listOfCamps = new ArrayList<>();
 // List of camps created by staffs    
 Staff staffInCharge = new Staff("someUserID", "someFaculty");  // Assuming you have a Staff constructor

 
    public Camp(String campName, Date startDate, Date endDate, Date registrationClosingDate,
    String userGroup, String location, int totalSlots, int campCommitteeSlots,
    String description, Staff staffInCharge) {
        this.campName = campName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationClosingDate = registrationClosingDate;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;
        this.isVisible = true;

        this.attendees = new ArrayList<>();
        this.committeeMembers = new ArrayList<>();
    }

    // editing existig camps
    public void setCampDetails(String campName, String startDate, String endDate, String registrationClosingDate,
                                String userGroup, String location, int totalSlots, int campCommitteeSlots,
                                String description) throws ParseException {
        this.campName = campName;
        this.startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        this.endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        this.registrationClosingDate = new SimpleDateFormat("yyyy-MM-dd").parse(registrationClosingDate);
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
    }


    // Functions for other classes; can separate into other files later on
    public int isRegistrationOpen(Camp camp){
        // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate closingDate = LocalDate.parse("getRegistrationClosingDate(camp)", formatter);

        return closingDate.isAfter(currentDate) ? 1 : 0;
    }

    public boolean isCommitteeMember(Student student){
        for(Student committeeMember : committeeMembers)
            if(committeeMember.equals(student)) return true;
        
        return false;
    }

    // public void updateRemainingSlots(int registered){

    // }

    // Getters and setters for the camp attributes
    public String getName(Camp camp){
        return camp.campName;
    }
    public void setName(String campName){
        this.campName = campName;
    }

    public Date getStartDate(Camp camp){
        return camp.startDate;
    }

    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }

    public Date getEndDate(Camp camp){
        return camp.endDate;
    }

    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }
    
    public String getDescription(Camp camp){
        return camp.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void addCamp(Camp camp) {
    	listOfCamps.add(camp);
    }

    public void addAttendee(Student student) {
        attendees.add(student);
    }

    public void removeAttendee(Student student){
        attendees.remove(student);
    }

    public void addCommitteeMember(Student student) {
        committeeMembers.add(student);
    }

    public void getLocation(String location){
        this.location = location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getUserGroup(String userGroup){
        return userGroup;
    }

    public void setUserGroup(String userGroup){
        this.userGroup = userGroup;
    }


    public int getTotalSlots(Camp camp) {
        return totalSlots;
    }
    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }
    public int getRemainingSlots(Camp camp) {
        int remaining = totalSlots - attendees.size();
        return remaining;
    }

    public int getCampCommitteeSlots(Camp camp){
        return camp.campCommitteeSlots;
    }
    public void setCampCommitteeSlots(int campCommitteeSlots){
        this.campCommitteeSlots = campCommitteeSlots;
    }

    public int getRemainingCommitteeSlots(Camp camp){
        int remaining = campCommitteeSlots - committeeMembers.size();
        return remaining;
    }
    
    public boolean hasAvailableSlots(Camp camp){
        return getRemainingSlots(camp) > 0;
    }

    public List<Camp> getListOfCamps(){
    	return listOfCamps;
    }

    public void viewCampDetails(User user){
        if(user instanceof Staff || user instanceof CampCommittee){
            System.out.println("Camp Name: " + campName);
            System.out.println("Start Date: " + startDate);
            System.out.println("End Date: " + endDate);
            System.out.println("Registration Closing Date: " + registrationClosingDate);
            System.out.println("User Group: " + userGroup);
            System.out.println("Location: " + location);
            System.out.println("Total Slots: " + totalSlots);
            System.out.println("Camp Committee Slots: " + campCommitteeSlots);
            System.out.println("Description: " + description);
            System.out.println("Staff in Charge: " + staffInCharge.getName());
            System.out.println("Attendees: " + attendees);
            System.out.println("Committee Members: " + committeeMembers);

        }
        else{
            System.out.println("You do not have access to this information.")
        }
    }       
    
    public List<Student> getAttendees() {
        return attendees;
    }

    public List<Student> getCommitteeMembers() {
        return committeeMembers;
    }

    public Date getRegistrationClosingDate(Camp camp){
        return camp.registrationClosingDate;
    }

    public void setRegistrationClosingDate(Date registrationClosingDate){
        this.registrationClosingDate = registrationClosingDate;
    }

    public String getLocation(Camp camp){
        return camp.location;
    }

    public String getUserGroup(Camp camp){
        return camp.userGroup;
    }
    
    public Staff getStaffInCharge(Camp camp){
        return camp.staffInCharge;
    }

    public boolean isVisible(Camp camp){
        return isVisible;
    }

    public void toggleVisibility() {
        isVisible = !isVisible;  // Toggle the visibility status
        System.out.println("Camp visibility toggled. New status: " + (isVisible ? "Visible" : "Hidden"));
    }

    

}

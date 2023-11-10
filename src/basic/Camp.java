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
    private String startDate;
    private String endDate;
    private String registrationClosingDate;
    private String userGroup;  // Own school or whole NTU
    private String location;
    private int totalSlots;
    private int campCommitteeSlots; // Max 10
    private String description;
    private Staff staffInCharge;  // Automatically tied to the staff who created it


    private List<Student> attendees;  // List of students registered for the camp as attendees
    private List<Student> committeeMembers;  // List of students registered for the camp as committee
    
    private List<Camp> listOfCamps = new ArrayList<>();
; // List of camps created by staffs

    public Camp(String campName, String startDate, String endDate, String registrationClosingDate, String userGroup, String location,
                int totalSlots, int campCommitteeSlots, String description, Staff staffInCharge) {
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

        this.attendees = new ArrayList<>();
        this.committeeMembers = new ArrayList<>();
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

    public String getStartDate(Camp camp){
        return camp.startDate;
    }

    public String getEndDate(Camp camp){
        return camp.endDate;
    }
    
    public String getDescription(Camp camp){
        return camp.description;
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

    public int getTotalSlots(Camp camp) {
        return totalSlots;
    }
    
    public int getRemainingSlots(Camp camp) {
        int remaining = totalSlots - attendees.size();
        return remaining;
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
    
    public List<Student> getAttendees() {
        return attendees;
    }

    public List<Student> getCommitteeMembers() {
        return committeeMembers;
    }

    public String getRegistrationClosingDate(Camp camp){
        return camp.registrationClosingDate;
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
}


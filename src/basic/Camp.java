import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Camp {
    private String campName;
    private String dates;
    private String registrationClosingDate;
    private String userGroup;  // Own school or whole NTU
    private String location;
    private int totalSlots;
    private int campCommitteeSlots; // Max 10
    private String description;
    private Staff staffInCharge;  // Automatically tied to the staff who created it


    private List<Student> attendees;  // List of students registered for the camp as attendees
    private List<CampCommittee> committeeMembers;  // List of students registered for the camp as committee
    
    private List<Camp> listOfCamps = new ArrayList<>();
; // List of camps created by staffs

    public Camp(String campName, String dates, String registrationClosingDate, String userGroup, String location,
                int totalSlots, int campCommitteeSlots, String description, Staff staffInCharge) {
        this.campName = campName;
        this.dates = dates;
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

    // Getters and setters for the camp attributes
    
    public void addCamp(Camp camp) {
    	listOfCamps.add(camp);
    }

    public void addAttendee(Student student) {
        attendees.add(student);
    }

    public void addCommitteeMember(CampCommittee committeeMember) {
        committeeMembers.add(committeeMember);
    }

    public int getTotalSlots(Camp camp) {
        return totalSlots;
    }
    
    public int getRemainingSlots(Camp camp) {
        int remaining = totalSlots - attendees.size();
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

    public List<CampCommittee> getCommitteeMembers() {
        return committeeMembers;
    }

    
}


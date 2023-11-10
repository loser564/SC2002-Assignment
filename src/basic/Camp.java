import java.util.ArrayList;
import java.util.List;

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
    private List<CampCommitteeMember> committeeMembers;  // List of students registered for the camp as committee
    
    private List<Camp> listOfCamps; // List of camps created by staffs

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

    public void addCommitteeMember(CampCommitteeMember committeeMember) {
        committeeMembers.add(committeeMember);
    }

    public List<Camp> getListOfCamps(){
    	return listOfCamps;
    }
    
    public List<Student> getAttendees() {
        return attendees;
    }

    public List<CampCommitteeMember> getCommitteeMembers() {
        return committeeMembers;
    }
}


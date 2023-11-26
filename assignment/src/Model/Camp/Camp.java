package Model.Camp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import Model.CampComm.CampCommitee;
import Model.Student.Student;
import Model.User.UserGroup;


public class Camp {
    private String campName;
    private Date startDate;
    private Date endDate;
    private Date registrationDeadline;
    private UserGroup userGroup; // which faculty can register
    private String location;
    private int maxCapacity;
    private int campCommSlots; // number of committee slots
    private String campDescription;
    private String staffID;
    private CampStatus status;

    private ArrayList<Student> registeredStudents = new ArrayList<Student>();
    private ArrayList<Student> blackListedStudents = new ArrayList<Student>();
    private ArrayList<CampCommitee> campCommittee = new ArrayList<CampCommitee>();

    // camp constructor
    public Camp(String campName, Date startDate, Date endDate, Date registrationDeadline,
    String userGroup, String location, int maxCapacity, int campCommitteeSlots,
    String description, String staffID){
        this.campName = campName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationDeadline = registrationDeadline;
        this.userGroup = UserGroup.valueOf(userGroup);
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.campCommSlots = campCommitteeSlots;
        this.campDescription = description;
        this.staffID = staffID;

    }

    // getters and setters

    public CampStatus getStatus() { return status; }

    public void setStatus(CampStatus status) { this.status = status; }


    public String getCampName() { return campName; }

    public void setCampName(String campName) { this.campName = campName; }

    public Date getStartDate() { return startDate; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public Date getRegistrationDeadline() { return registrationDeadline; }
    
    public void setRegistrationDeadline(Date registrationDeadline) { this.registrationDeadline = registrationDeadline; }

    public UserGroup getUserGroup() { return userGroup; }

    public void setUserGroup(UserGroup userGroup) { this.userGroup = userGroup; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public int getMaxCapacity() { return maxCapacity; }


    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }

    public int getCampCommSlots() { return campCommSlots; }

    public void setCampCommSlots(int campCommSlots) 
    {   if (campCommSlots > 10){
            System.out.println("Camp committee slots cannot be more than 10!");
            return;
        }
        else{
            this.campCommSlots = campCommSlots;
        }
         
    }

    public String getCampDescription() { return campDescription; }

    public void setCampDescription(String campDescription) { this.campDescription = campDescription; }

    public String getStaffID() { return staffID; }

    public void setStaffID(String staffID) { this.staffID = staffID; }

    // check if camp is open for registration
    public boolean isOpen(){
        Date today = new Date();
        if(today.after(registrationDeadline)){
            return false;
        } else {
            return true;
        }
    }

    public int getNumberOfRegisteredStudents(){
        return registeredStudents.size();
    }

    public void setNumberOfRegisteredStudents(int numberOfRegisteredStudents){
        numberOfRegisteredStudents = registeredStudents.size();
    }

    

    public ArrayList<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public ArrayList<Student> getBlackListedStudents() {
        return blackListedStudents;
    }

    public ArrayList<CampCommitee> getCampCommittee() {
        return campCommittee;
    }

    
     
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Camp camp = (Camp) obj;
    return Objects.equals(this.getCampName(), camp.getCampName());
    // Add more comparison based on other unique identifiers of Camp if needed
}


   
    

    







    
    
}

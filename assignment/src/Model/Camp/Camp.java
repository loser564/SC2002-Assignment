package Model.Camp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import Model.CampComm.CampCommitee;
import Model.Student.Student;
import Model.User.UserGroup;


/**
 * The {@code Camp} class represents a camp with various attributes and functionality.
 * It stores information about the camp's name, dates, registration details, location,
 * capacity, committee slots, description, staff ID, and more. Additionally, it manages
 * the list of registered students, blacklisted students, and camp committee members.
 * 
 * <p>This class allows you to create and manage camp instances, check if the camp is open
 * for registration, retrieve information about registered students, blacklisted students,
 * and committee members, set the camp's status, and perform equality comparisons between
 * camp objects.
 * 
 * <p>Use the provided getters and setters to access and modify camp properties.
 * 
 * @author James Lim
 * @version 1.0
 * @since 2023-11-19
 */
public class Camp {
    // Fields

    /**
     * The name of the camp.
     */
    private String campName;

    /**
     * The start date of the camp.
     */
    private Date startDate;

    /**
     * The end date of the camp.
     */
    private Date endDate;

    /**
     * The registration deadline for the camp.
     */
    private Date registrationDeadline;

    /**
     * The user group representing which faculty can register for the camp.
     */
    private UserGroup userGroup;

    /**
     * The location of the camp.
     */
    private String location;

    /**
     * The maximum capacity of participants for the camp.
     */
    private int maxCapacity;

    /**
     * The number of committee slots available for the camp.
     */
    private int campCommSlots;

    /**
     * The description of the camp.
     */
    private String campDescription;

    /**
     * The staff ID associated with the camp.
     */
    private String staffID;

    /**
     * The status of the camp (e.g., active, canceled, completed, etc.).
     */
    private CampStatus status;

    /**
     * ArrayList containing instances of the {@code Student} class who have registered for the camp.
     * This list stores information about students who have successfully signed up for the camp.
     */
    private ArrayList<Student> registeredStudents = new ArrayList<Student>();

    /**
     * ArrayList containing instances of the {@code Student} class who have been blacklisted from the camp.
     * This list stores information about students who are not allowed to participate in the camp for certain reasons.
     */
    private ArrayList<Student> blackListedStudents = new ArrayList<Student>();

    /**
     * ArrayList containing instances of the {@code CampCommitee} class, representing members of the camp committee.
     * These members may have specific roles or responsibilities within the camp organization.
     */
    private ArrayList<CampCommitee> campCommittee = new ArrayList<CampCommitee>();

    // Constructors

    /**
     * Constructs a new camp with the specified attributes.
     *
     * @param campName             The name of the camp.
     * @param startDate            The start date of the camp.
     * @param endDate              The end date of the camp.
     * @param registrationDeadline The registration deadline for the camp.
     * @param userGroup            The user group representing which faculty can register for the camp.
     * @param location             The location of the camp.
     * @param maxCapacity          The maximum capacity of participants for the camp.
     * @param campCommitteeSlots   The number of committee slots available for the camp.
     * @param description          The description of the camp.
     * @param staffID              The staff ID associated with the camp.
     */
    public Camp(String campName, Date startDate, Date endDate, Date registrationDeadline,
                String userGroup, String location, int maxCapacity, int campCommitteeSlots,
                String description, String staffID) {
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

    // Getter and Setter Methods

    /**
     * Gets the current status of the camp.
     *
     * @return The status of the camp.
     */
    public CampStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the camp.
     *
     * @param status The new status to set.
     */
    public void setStatus(CampStatus status) {
        this.status = status;
    }

    /**
     * Gets the name of the camp.
     *
     * @return The name of the camp.
     */
    public String getCampName() {
        return campName;
    }

    /**
     * Sets the name of the camp.
     *
     * @param campName The new name to set.
     */
    public void setCampName(String campName) {
        this.campName = campName;
    }

    /**
     * Gets the start date of the camp.
     *
     * @return The start date of the camp.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the camp.
     *
     * @param startDate The new start date to set.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date of the camp.
     *
     * @return The end date of the camp.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the camp.
     *
     * @param endDate The new end date to set.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the registration deadline for the camp.
     *
     * @return The registration deadline for the camp.
     */
    public Date getRegistrationDeadline() {
        return registrationDeadline;
    }

    /**
     * Sets the registration deadline for the camp.
     *
     * @param registrationDeadline The new registration deadline to set.
     */
    public void setRegistrationDeadline(Date registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    /**
     * Gets the user group representing which faculty can register for the camp.
     *
     * @return The user group for the camp.
     */
    public UserGroup getUserGroup() {
        return userGroup;
    }

    /**
     * Sets the user group representing which faculty can register for the camp.
     *
     * @param userGroup The new user group to set.
     */
    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    /**
     * Gets the location of the camp.
     *
     * @return The location of the camp.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the camp.
     *
     * @param location The new location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the maximum capacity of participants for the camp.
     *
     * @return The maximum capacity of the camp.
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Sets the maximum capacity of participants for the camp.
     *
     * @param maxCapacity The new maximum capacity to set.
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * Gets the number of committee slots available for the camp.
     *
     * @return The number of committee slots for the camp.
     */
    public int getCampCommSlots() {
        return campCommSlots;
    }

    /**
     * Sets the number of committee slots available for the camp, with a maximum limit of 10.
     *
     * @param campCommSlots The new number of committee slots to set.
     */
    public void setCampCommSlots(int campCommSlots) {
        if (campCommSlots > 10) {
            System.out.println("Camp committee slots cannot be more than 10!");
            return;
        } else {
            this.campCommSlots = campCommSlots;
        }
    }

    /**
     * Gets the description of the camp.
     *
     * @return The description of the camp.
     */
    public String getCampDescription() {
        return campDescription;
    }

    /**
     * Sets the description of the camp.
     *
     * @param campDescription The new description to set.
     */
    public void setCampDescription(String campDescription) {
        this.campDescription = campDescription;
    }

    /**
     * Gets the staff ID associated with the camp.
     *
     * @return The staff ID associated with the camp.
     */
    public String getStaffID() {
        return staffID;
    }

    /**
     * Sets the staff ID associated with the camp.
     *
     * @param staffID The new staff ID to set.
     */
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    /**
     * Checks if the camp is open for registration.
     *
     * @return {@code true} if the current date is before the registration deadline, {@code false} otherwise.
     */
    public boolean isOpen() {
        Date today = new Date();
        if (today.after(registrationDeadline)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Gets the number of registered students for the camp.
     *
     * @return The number of registered students.
     */
    public int getNumberOfRegisteredStudents() {
        return registeredStudents.size();
    }

    /**
     * Sets the number of registered students for the camp.
     *
     * @param numberOfRegisteredStudents The new number of registered students to set.
     */
    public void setNumberOfRegisteredStudents(int numberOfRegisteredStudents) {
        // This method appears to be incomplete or unnecessary.
        // It's setting the provided parameter to the size of the registeredStudents list,
        // but it doesn't seem to serve a clear purpose.
    }

    /**
     * Gets the list of registered students for the camp.
     *
     * @return An ArrayList containing the registered students.
     */
    public ArrayList<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    /**
     * Gets the list of blacklisted students for the camp.
     *
     * @return An ArrayList containing the blacklisted students.
     */
    public ArrayList<Student> getBlackListedStudents() {
        return blackListedStudents;
    }

    /**
     * Gets the list of camp committee members.
     *
     * @return An ArrayList containing the camp committee members.
     */
    public ArrayList<CampCommitee> getCampCommittee() {
        return campCommittee;
    }

    /**
     * Checks if two Camp objects are equal based on their camp names.
     *
     * @param obj The object to compare for equality.
     * @return {@code true} if the objects are equal based on the camp name, {@code false} otherwise.
     *         You may need to add more comparisons based on other unique identifiers of Camp if needed.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Camp camp = (Camp) obj;
        return Objects.equals(this.getCampName(), camp.getCampName());
        // Add more comparison based on other unique identifiers of Camp if needed
    }
}


   
    

    







    
    


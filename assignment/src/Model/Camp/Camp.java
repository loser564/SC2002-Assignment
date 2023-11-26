
/**
 * The {@code Camp} class represents a camp with various attributes and functionality.
 * It stores information about the camp's name, dates, registration details, location,
 * capacity, committee slots, description, staff ID, and more. Additionally, it manages
 * the list of registered students, blacklisted students, and camp committee members.
 * 
 * This class allows you to create and manage camp instances, check if the camp is open
 * for registration, retrieve information about registered students, blacklisted students,
 * and committee members, and set the camp's status.
 * 
 * @author Alicia
 * @version 6.0
 * @since 2023-11-19
 */
package Model.Camp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import Model.CampComm.CampCommitee;
import Model.Student.Student;
import Model.User.UserGroup;


public class Camp {
/**
 * Name of camp.
 */
private String campName;

/**
 * Start date of the camp.
 */
private Date startDate;

/**
 * End date of the camp.
 */
private Date endDate;

/**
 * Registration deadline for the camp.
 */
private Date registrationDeadline;

/**
 * User group representing which faculty can register for the camp.
 */
private UserGroup userGroup;

/**
 * Location of the camp.
 */
private String location;

/**
 * Maximum capacity of participants for the camp.
 */
private int maxCapacity;

/**
 * Number of committee slots available for the camp.
 */
private int campCommSlots;

/**
 * Description of the camp.
 */
private String campDescription;

/**
 * Staff ID of staff who created camp.
 */
private String staffID;

/**
 * Status of the camp (visible and invisible).
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
 * ArrayList containing instances of the {@code CampCommittee} class, representing members of the camp committee.
 * These members may have specific roles or responsibilities within the camp organization.
 */
private ArrayList<CampCommittee> campCommittee = new ArrayList<CampCommittee>();


    /**
 * Represents a camp with various attributes and functionality.
 */
public class Camp {
    // ... (previous fields and constructor)

    /**
     * Check if the camp is open for registration.
     *
     * @return true if the current date is before the registration deadline, false otherwise.
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
     * Get the number of registered students for the camp.
     *
     * @return The number of registered students.
     */
    public int getNumberOfRegisteredStudents() {
        return registeredStudents.size();
    }

    /**
     * Set the number of registered students for the camp.
     *
     * @param numberOfRegisteredStudents The new number of registered students to set.
     */
    public void setNumberOfRegisteredStudents(int numberOfRegisteredStudents) {
        // This method appears to be incomplete or unnecessary.
        // It's setting the provided parameter to the size of the registeredStudents list,
        // but it doesn't seem to serve a clear purpose.
    }

    /**
     * Get the list of registered students for the camp.
     *
     * @return An ArrayList containing the registered students.
     */
    public ArrayList<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    /**
     * Get the list of blacklisted students for the camp.
     *
     * @return An ArrayList containing the blacklisted students.
     */
    public ArrayList<Student> getBlackListedStudents() {
        return blackListedStudents;
    }

    /**
     * Get the list of camp committee members.
     *
     * @return An ArrayList containing the camp committee members.
     */
    public ArrayList<CampCommittee> getCampCommittee() {
        return campCommittee;
    }

    /**
     * Checks if two Camp objects are equal.
     *
     * @param obj The object to compare for equality.
     * @return true if the objects are equal based on the camp name, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Camp camp = (Camp) obj;
        return Objects.equals(this.getCampName(), camp.getCampName());
      
    }
}


   
    

    







    
    
}

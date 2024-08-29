package Model.Student;

import java.io.IOException;
import java.util.ArrayList;
import Model.Camp.Camp;

/**
* Interface for handling camp committee-related operations for students.
* @author SCEX Group 3
*/

public interface CampCommInterface {
    
    /**
    * Gets the remaining camp committee slots for the student.
    *
    * @return The remaining camp committee slots.
    */
    int getRemainderCampCommSlots();

    /**
    * Sets whether the student is part of the camp committee.
    *
    * @param isCampCommittee True if the student is part of the camp committee, false otherwise.
    */
    void setCampCommittee(boolean isCampCommittee);

    /**
    * Checks if the student is part of the camp committee for any camp.
    *
    * @return True if the student is part of the camp committee, false otherwise.
    */
    boolean getCampCommittee();

    /**
    * Applies for the camp committee of a specified camp.
    *
    * @param camp The camp for which the student is applying for the committee.
    * @throws IOException If an I/O error occurs.
    */
    void applyCampCommittee(Camp camp) throws IOException;

}

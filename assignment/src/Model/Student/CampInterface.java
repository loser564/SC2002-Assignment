package Model.Student;

import java.io.IOException;
import java.util.ArrayList;
import Model.Camp.Camp;


/**
* Interface for handling camp-related operations for students.
* @authors SCEX Group 3
*/
public interface CampInterface {
    /**
    * Retrieves a list of available camps based on student's faculty or open to all.
    *
    * @return A list of available camps.
    * @throws IOException If an I/O error occurs.
    */
    ArrayList<Camp> viewCamps() throws IOException;

    /**
    * Registers a student for a specified camp.
    *
    * @param camp      The camp to be registered for.
    * @param studentID The ID of the student registering for the camp.
    * @throws IOException If an I/O error occurs.
    */
    void registerCamp(Camp camp, String studentID) throws IOException;

    /**
    * Retrieves a list of camps that the student is registered for.
    *
    * @return A list of registered camps.
    * @throws IOException If an I/O error occurs.
    */
    ArrayList<Camp> viewRegisteredCamps() throws IOException;

    /**
    * Prints details of a specified camp.
    *
    * @param camp The camp for which details will be printed.
    */
    void printCampDetails(Camp camp);

    /**
    * Quits a specified camp by removing the student from the registered list.
    *
    * @param camp The camp to quit.
    * @throws IOException If an I/O error occurs.
    */
    void quitCamp(Camp camp) throws IOException;
}
   


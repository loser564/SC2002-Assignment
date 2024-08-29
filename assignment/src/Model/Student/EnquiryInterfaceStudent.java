package Model.Student;

import java.io.IOException;
import java.util.ArrayList;
import Model.EnquirySuggestion.Enquiry;
import Model.EnquirySuggestion.EnquiryInterface;

/**
* Interface for handling enquiries related to students.
* @author SCEX Group 3
*/

    /**
    * Submits an enquiry with a specified title and message.
    *
    * @param title   The title of the enquiry.
    * @param message The message content of the enquiry.
    * @throws IOException If an I/O error occurs.
    */
public interface EnquiryInterfaceStudent extends EnquiryInterface {
    
    /**
    * Submits an enquiry with a specified title and message.
    *
    * @param title   The title of the enquiry.
    * @param message The message content of the enquiry.
    * @throws IOException If an I/O error occurs.
    */
    void submitEnquiry(String title, String message) throws IOException;
    
    /**
    * Retrieves a list of all enquiries in the system.
    *
    * @return A list of all enquiries.
    */
    ArrayList<Enquiry> viewAllEnquiries();
    
    /**
    * Retrieves a list of enquiries submitted by the student.
    *
    * @return A list of enquiries submitted by the student.
    */
    ArrayList<Enquiry> viewMyEnquiries();
    
    /**
    * Prints details of a specified enquiry.
    *
    * @param enquiry The enquiry for which details will be printed.
    */
    void printEnquiryDetails(Enquiry enquiry);
    
    /**
    * Edits the content of a specified enquiry.
    *
    * @param enquiry The enquiry to be edited.
    * @throws IOException If an I/O error occurs.
    */
    void editEnquiry(Enquiry enquiry) throws IOException;
    
    /**
    * Deletes a specified enquiry.
    *
    * @param enquiry The enquiry to be deleted.
    * @throws IOException If an I/O error occurs.
    */
    void deleteEnquiry(Enquiry enquiry) throws IOException;
    
    
}

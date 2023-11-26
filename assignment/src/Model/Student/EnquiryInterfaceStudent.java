package Model.Student;

import java.io.IOException;
import java.util.ArrayList;


import Model.EnquirySuggestion.Enquiry;
import Model.EnquirySuggestion.EnquriyInterface;

public interface EnquiryInterfaceStudent extends EnquriyInterface {
    void submitEnquiry(String title, String message) throws IOException;
    ArrayList<Enquiry> viewAllEnquiries();
    ArrayList<Enquiry> viewMyEnquiries();
    void printEnquiryDetails(Enquiry enquiry);
    void editEnquiry(Enquiry enquiry) throws IOException;
    void deleteEnquiry(Enquiry enquiry) throws IOException;
    
    
}

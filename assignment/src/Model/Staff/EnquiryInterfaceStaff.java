package Model.Staff;

import java.io.IOException;
import java.util.ArrayList;
import Model.EnquirySuggestion.Enquiry;
import Model.EnquirySuggestion.EnquriyInterface;



public interface EnquiryInterfaceStaff extends EnquriyInterface {
    ArrayList<Enquiry> viewMyCampsEnquiries();
    void printEnquiryDetails(Enquiry enquiry);
    void replyEnquiry(int enquiryID, String reply) throws IOException;
}

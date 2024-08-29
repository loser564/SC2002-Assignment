package Model.Staff;

import java.io.IOException;
import java.util.ArrayList;
import Model.EnquirySuggestion.Enquiry;
import Model.EnquirySuggestion.EnquiryInterface;

/**
 * The {@code EnquiryInterfaceStaff} interface defines methods that staff members can use
 * to manage and respond to enquiries related to camps.
 * It extends the {@link EnquiryInterface} interface to inherit common enquiry-related methods.
 * 
 * @author Alicia
 * @version 3.0
 * @since 2023-11-19
 */

public interface EnquiryInterfaceStaff extends EnquiryInterface {
    /**
     * Retrieves a list of enquiries related to camps that are associated with the staff member.
     *
     * @return An {@code ArrayList} of {@link Enquiry} objects representing camp-related enquiries.
     */
    ArrayList<Enquiry> viewMyCampsEnquiries();
    /**
     * Prints the details of a specific enquiry related to camps.
     *
     * @param enquiry The {@link Enquiry} object for which details need to be printed.
     */
    void printEnquiryDetails(Enquiry enquiry);
    /**
     * Replies to a specific camp-related enquiry identified by its unique ID.
     *
     * @param enquiryID The unique ID of the camp-related enquiry to which a reply is being sent.
     * @param reply The reply message to be sent as a response to the enquiry.
     * @throws IOException If an I/O error occurs while sending the reply.
     */
    void replyEnquiry(int enquiryID, String reply) throws IOException;
}

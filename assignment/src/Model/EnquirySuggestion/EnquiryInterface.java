package Model.EnquirySuggestion;

/**
 * The {@code EnquiryInterface} interface defines a contract for classes that can print
 * details of an {@link Enquiry} object.
 * 
 * @author Alicia
 * @version 2.0
 * @since 2023-11-19
 */
public interface EnquiryInterface {
    
    /**
     * Prints the details of an {@code Enquiry} object to the console.
     *
     * @param enquiry The {@code Enquiry} object whose details are to be printed.
     */
    void printEnquiryDetails(Enquiry enquiry);
}

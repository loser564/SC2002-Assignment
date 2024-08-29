package Model.EnquirySuggestion;

import Model.Camp.Camp;

/**
 * The {@code Enquiry} class represents an enquiry or suggestion made by a student regarding a camp.
 * It contains information such as the student's ID, camp name, title, message, status, reply, and an enquiry ID.
 * 
 * @author Alicia
 * @version 3.0
 * @since 2023-11-19
 */
public class Enquiry {
    private int enquiryID;
    private Camp camp;
    private String studentID;
    private String campName;
    private String message;
    private boolean status;
    private String reply;
    private String title;

    /**
     * Creates a new enquiry instance with the given parameters.
     *
     * @param studentID The ID of the student making the enquiry.
     * @param campName The name of the camp related to the enquiry.
     * @param title The title of the enquiry.
     * @param message The message content of the enquiry.
     * @param status The status of the enquiry (true if replied, false if pending).
     */
    public Enquiry(String studentID, String campName, String title, String message, boolean status) {
        this.studentID = studentID;
        this.campName = campName;
        this.title = title;
        this.message = message;
        this.status = status;
    }

    /**
     * Gets the enquiry ID.
     *
     * @return The enquiry ID.
     */
    public int getEnquiryID() {
        return enquiryID;
    }

    /**
     * Sets the enquiry ID.
     *
     * @param enquiryID The enquiry ID to set.
     */
    public void setEnquiryID(int enquiryID) {
        this.enquiryID = enquiryID;
    }

    /**
     * Gets the student ID associated with the enquiry.
     *
     * @return The student ID.
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Sets the student ID associated with the enquiry.
     *
     * @param studentID The student ID to set.
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Gets the title of the enquiry.
     *
     * @return The title of the enquiry.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the enquiry.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the message content of the enquiry.
     *
     * @return The message content of the enquiry.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message content of the enquiry.
     *
     * @param message The message content to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the status of the enquiry.
     *
     * @return The status of the enquiry (true if replied, false if pending).
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Sets the status of the enquiry.
     *
     * @param status The status to set (true if replied, false if pending).
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Gets the reply to the enquiry.
     *
     * @return The reply to the enquiry.
     */
    public String getReply() {
        return reply;
    }

    /**
     * Sets the reply to the enquiry.
     *
     * @param reply The reply to set.
     */
    public void setReply(String reply) {
        this.reply = reply;
    }

    /**
     * Gets the name of the camp related to the enquiry.
     *
     * @return The name of the camp.
     */
    public String getCampName() {
        return campName;
    }

    /**
     * Sets the name of the camp related to the enquiry.
     *
     * @param campName The name of the camp to set.
     */
    public void setCampName(String campName) {
        this.campName = campName;
    }

    /**
     * Sets the camp associated with the enquiry.
     *
     * @param camp The camp to set.
     */
    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    /**
     * Gets the camp associated with the enquiry.
     *
     * @return The camp associated with the enquiry.
     */
    public Camp getCamp() {
        return camp;
    }
}

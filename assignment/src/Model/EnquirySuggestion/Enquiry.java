package Model.EnquirySuggestion;

import Model.Camp.Camp;

public class Enquiry {
	private int enquiryID;
    private Camp camp;


    private String studentID;
    private String campName;
    private String message;
    private boolean status;
    private String reply;
    private String title;

    public Enquiry(String studentID, String campName, String title, String message, boolean status) {
        this.studentID = studentID;
        this.campName = campName;
        this.title = title;
        this.message = message;
        this.status = status;
    }

    // Getters and setters

    public int getEnquiryID(){
        return enquiryID;
    }

    public void setEnquiryID(int enquiryID){
        this.enquiryID = enquiryID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
    	this.studentID=studentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
    	this.title=title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean getStatus() {
    	return status;
    }
    
    public void setStatus(boolean status) {
    	this.status=status;
    }

    public String getReply() {
        return reply;
    }
    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String camp) {
    	this.campName=campName;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    public Camp getCamp() {
        return camp;
    }

    

}

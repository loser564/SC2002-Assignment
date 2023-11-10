package sc2002.shaojie;

public class Enquiry {
	private int id;
    private Camp camp;
    private String message;
    private boolean status;
    private String reply;

    public Enquiry(Camp camp, String message) {
        this.camp = camp;
        this.message = message;
    }

    // Getters and setters

    public Camp getCamp() {
        return camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
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
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
}
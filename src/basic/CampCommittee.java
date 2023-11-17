package src.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CampCommittee extends Student {
/** 
	

• A camp committee member is not allowed to directly edit the camp details.




• A camp committee member cannot quit from camp.
*/
	private int _points;
	private List<Camp> registeredCamps;
	private List<Camp> overseenCamps;
	private List<Enquiry> enquiries;
	private List<Suggestion> suggestions;
	private List<createdCamps> createdCamps;

	/**
	 * Constructor
	 */

	public CampCommittee(String aUserID, String aPassword, String aFaculty) {
		super(aUserID, aPassword, aFaculty);
		this._points = 0;
		this.registeredCamps = new ArrayList<Camp>();
		this.overseenCamps = new ArrayList<Camp>();
		this.enquiries = new ArrayList<Enquiry>();
		this.suggestions = new ArrayList<Suggestion>();	
	}
	//A camp committee member can get one point for each enquiry replied and each 
	// suggestion given. One extra point will be granted for each accepted suggestion. 
	/**
	 * Method to add points
	 */
	public void addPoints(int aNewPoints) {
		this._points += aNewPoints;
	}

	public int getPoints() {
		return this._points;
	}

	public void setPoints(int aNewPoints) {
		this._points = aNewPoints;
	}

	//A camp committee member can view and reply to enquiries from students to the 
	//camp they oversee. 
	/**
	 * method to read enquiries
	 */
	public void readEnquiries() {
		for (Enquiry e : this.enquiries) {
			System.out.println(e.toString());
		}
	}	

	/* method to reply enquiries */
	public void replyEnquiry(Enquiry aEnquiry, String aReply) {
		aEnquiry.setReply(aReply);
		this.addPoints(1);
	}

	//A camp committee member can view, edit, and delete the details of his/her 
	//suggestions before being processed. 
	/**
	 * method to read suggestions
	 */
	public void readSuggestions() {
		for (Suggestion s : this.suggestions) {
			System.out.println(s.toString());
		}
	}

	public void editSuggestion(Suggestion aSuggestion, String aNewSuggestion) {
		aSuggestion.setSuggestion(aNewSuggestion);
	}

	public void deleteSuggestion(Suggestion aSuggestion) {
		this.suggestions.remove(aSuggestion);
	}
	
	//A camp committee member can submit suggestions for changes to camp details to
	//staff.
	/**
	 * method to submit suggestions
	 */
	public void submitSuggestion(String aSuggestion) {
		Suggestion newSuggestion = new Suggestion(aSuggestion);
		this.suggestions.add(newSuggestion);
		this.addPoints(1);
	}


	// A camp committee member can generate a report of the list of students attending 
	// each camp that they have created. The list will include details of the camp as well as 
	// the roles of the participants. There should be filters for how the camp committee 
	//member would want to generate the list. (attendee, camp committee, etc.) (generate in 
	//either txt or csv format). 

	/**
	 * method to generate report
	 */

	public void generateReport(Camp aCamp, String filter, String format) {
    if (!isCampCreator(aCamp)) {
        System.out.println("You are not the creator of this camp!");
        return;
    }

    List<Participant> participants = aCamp.getParticipants();
    String reportFileName = aCamp.getCampName() + "_report." + format;

    try (FileWriter writer = new FileWriter(reportFileName)) {
        if (format.equals("txt")) {
            writer.write("Camp Name: " + aCamp.getCampName() + "\n");
            writer.write("Camp Date: " + aCamp.getCampDate() + "\n");
            writer.write("Participants:\n");

            for (Participant p : participants) {
                if (filter.equals("all") || (filter.equals("attendee") && p instanceof Student) || (filter.equals("camp committee") && p instanceof CampCommittee)) {
                    writeParticipantDetails(writer, format, aCamp, p.getName(), p.getRole());
                }
            }
        } else if (format.equals("csv")) {
            writer.write("Camp Name, Camp Date, Participant Name, Participant Role\n");
            for (Participant p : participants) {
                if (filter.equals("all") || (filter.equals("attendee") && p instanceof Student) || (filter.equals("camp committee") && p instanceof CampCommittee)) {
                    writeParticipantDetails(writer, format, aCamp, p.getName(), p.getRole());
                }
            }
        }
        System.out.println("Report generated successfully: " + reportFileName);
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
    }
}

	private void writeParticipantDetails(FileWriter writer, String format, Camp aCamp, String participantRole) throws IOException{
		if (format.equals("txt")){
			writer.write("Name: " + participantName + "\n");
			writer.write("Role: " + participantRole + "\n");
		}
		else if(format.equals("csv")){
			writer.write(aCamp.getCampName() + "," + aCamp.getCampDetails() + "," + participantName + "," + participantRole + "\n");
		}
	}

	private boolean	isCampCreator(Camp aCamp){
		return aCamp.getCreator().equals(this);
	}

	// A camp committee member can view the details of the camp that he/she has
	// registered for.
	/**
	 * method to view registered camps
	 */	
	public void viewRegisteredCamps() {
		for (Camp c : this.registeredCamps) {
			System.out.println(c.toString());
		}
	}
}
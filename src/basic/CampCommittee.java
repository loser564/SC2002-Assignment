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
	
	public CampCommittee(String UserId, String Password, String Name, String Faculty, boolean IsCampCommittee) {
		super(UserId, Password, Name, Faculty, IsCampCommittee);
		this._points = 0;
		this.registeredCamps = new ArrayList<>();
		this.overseenCamps = new ArrayList<>();
		this.enquiries = new ArrayList<>();
		this.suggestions = new ArrayList<>();
	}

	public void addPoints(int aPoints) {
		this._points += aPoints;
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
		for (Suggestion suggestion : suggestions) {
			String suggestionText = suggestion.getSuggestionText();
			// Do something with suggestionText
		}
	}
	

	public void editSuggestion(Suggestion aSuggestion, String aNewSuggestion) {
		if (aSuggestion != null) {
			aSuggestion.setSuggestionText(aNewSuggestion);
		}
	}
	
	public void deleteSuggestion(Suggestion aSuggestion) {
		if (aSuggestion != null) {
			this.suggestions.remove(aSuggestion);
		}
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

    List<Student> participants = aCamp.getAttendees();
    String reportFileName = aCamp.getName(aCamp) + "_report." + format;

    try (FileWriter writer = new FileWriter(reportFileName)) {
        if (format.equals("txt")) {
            writer.write("Camp Name: " + aCamp.getName(aCamp) + "\n");
            writer.write("Camp Start Date: " + aCamp.getStartDate(aCamp) + "\n");
            writer.write("Participants:\n");

            for (Student p : participants) {
                if (filter.equals("all") || (filter.equals("attendee") && p instanceof Student) || (filter.equals("camp committee") && p instanceof CampCommittee)) {
                    writeParticipantDetails(writer, format, aCamp, p.getName(), p.getRole());
                }
            }
        } else if (format.equals("csv")) {
            writer.write("Camp Name, Camp Date, Participant Name, Participant Role\n");
            for (Student p : participants) {
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

	private void writeParticipantDetails(FileWriter writer, String format, Camp aCamp, String participantName, String participantRole) throws IOException{
		if (format.equals("txt")){
			writer.write("Name: " + participantName + "\n");
			writer.write("Role: " + participantRole + "\n");
		}
		else if(format.equals("csv")){
			// defined details as attendess, date and location
			writer.write(aCamp.getName(aCamp) + "," + aCamp.getStartDate(aCamp) +
			"," + aCamp.getEndDate(aCamp) + "," + aCamp.getLocation(aCamp) +
			"," + aCamp.getUserGroup(aCamp) +
			 "," + participantName + "," + participantRole + "\n");
		}
	}

	private boolean	isCampCreator(Camp aCamp){
		return aCamp.getStaffInCharge(aCamp).equals(this);
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
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class Staff {
    // Staff attributes
    private String userId; // NTU network user ID
    private String password; // Staff's password
    private String faculty; // Faculty information
    private List<Camp> createdCamps; // Camps created by this staff

    // Constructor
    public Staff(String userId, String faculty) {
        this.userId = userId;
        this.password = "password"; // default password
        this.faculty = faculty;
        this.createdCamps = new ArrayList<>(); // initialize with no camps
    }

    // Accessor method for userId
    public String getUserId() {
        return this.userId;
    }

    // Accessor method for faculty
    public String getFaculty() {
        return this.faculty;
    }

    // Method to change the password
    public void changePassword(String newPassword) {
        // Validate the new password against some password policies if needed
        this.password = newPassword;
    }

    // Method to create a new camp
    public void createCamp(String campName, Date startDate, Date endDate, 
                           Date registrationCloseDate, String userGroup, 
                           String location, int totalSlots, String description) {
        // Create a new camp object with given details
        Camp newCamp = new Camp(campName, startDate, endDate, registrationCloseDate, userGroup, 
                                location, totalSlots, description, this.userId);
        // Add the new camp to the list of created camps
        this.createdCamps.add(newCamp);
    }

    // Method to edit an existing camp
    public void editCamp(Camp camp, String campName, Date startDate, Date endDate, 
                         Date registrationCloseDate, String userGroup, 
                         String location, int totalSlots, String description) {
        // Check if the camp exists and if the staff has permission to edit it
        if (this.createdCamps.contains(camp) && camp.getStaffInCharge().equals(this.userId)) {
            // Update the camp details using setters provided by the Camp class
            camp.setCampDetails(campName, startDate, endDate, registrationCloseDate, userGroup, 
                                location, totalSlots, description);
        }
    }

    // Method to delete a camp
    public void deleteCamp(Camp camp) {
        // Remove the camp from the list if the staff is in charge of it
        if (camp.getStaffInCharge().equals(this.userId)) {
            this.createdCamps.remove(camp);
        }
    }

    // Method to toggle the visibility of a camp
    public void toggleCampVisibility(Camp camp, boolean isVisible) {
        // Set the visibility of the camp if the staff is in charge of it
        if (camp.getStaffInCharge().equals(this.userId)) {
            camp.setVisibility(isVisible);
        }
    }

    // Method to view all camps created by this staff member
    public List<Camp> viewCreatedCamps() {
        // Return the list of camps created by this staff
        return this.createdCamps;
    }

    // Method to reply to enquiries
    public void replyToEnquiry(Enquiry enquiry, String replyMessage) {
        // Set the reply message to the enquiry if the staff is in charge of the camp in question
        if (this.createdCamps.contains(enquiry.getCamp()) && enquiry.getCamp().getStaffInCharge().equals(this.userId)) {
            enquiry.setReply(replyMessage);
        }
    }

    // Method to approve suggestions
    public void approveSuggestion(Suggestion suggestion) {
        // Approve the suggestion if it pertains to a camp managed by this staff
        if (this.createdCamps.contains(suggestion.getCamp()) && suggestion.getCamp().getStaffInCharge().equals(this.userId)) {
            suggestion.setApproved(true);
        }
    }

// Method to generate a report of the list of students attending camps created by the staff
    public void generateReport(Camp camp, String filterType, String reportFormat) {
        // Check if the staff member has created the camp
        if (this.createdCamps.contains(camp)) {
            // Generate the report based on the filter type (attendee, camp committee, etc.)
            List<Participant> filteredParticipants = camp.getParticipants().stream()
                .filter(p -> filterType.equals("all") || p.getRole().equals(filterType))
                .collect(Collectors.toList());

        // Decide on the report format
            switch (reportFormat.toLowerCase()) {
                case "txt":
                    generateTextReport(camp, filteredParticipants);
                    break;
                case "csv":
                    generateCSVReport(camp, filteredParticipants);
                    break;
                default:
                    System.out.println("Unsupported report format: " + reportFormat);
                    break;
        }
    }   else {
            System.out.println("Camp not found or not created by the staff: " + this.userId);
    }
}

    private void generateTextReport(Camp camp, List<Participant> participants) {
        String reportName = "Camp_Report_" + camp.getName() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportName))) {
            writer.write("Report for Camp: " + camp.getName() + "\n");
        for (Participant participant : participants) {
            writer.write(participant.toString() + "\n");
        }
    } catch (IOException e) {
        System.out.println("An error occurred while writing the text report.");
        e.printStackTrace();
    }
}

    private void generateCSVReport(Camp camp, List<Participant> participants) {
        String reportName = "Camp_Report_" + camp.getName() + ".csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportName))) {
            writer.write("Name,Role,Email,Status\n"); // CSV header
            for (Participant participant : participants) {
                writer.write(String.format("%s,%s,%s,%s\n", participant.getName(), participant.getRole(),
                    participant.getEmail(), participant.getStatus()));
        }
        }catch (IOException e) {
            System.out.println("An error occurred while writing the CSV report.");
            e.printStackTrace();
    }
}

    // Method to generate a performance report for camp committee members
    public void generatePerformanceReport(Camp camp) {
        // Check if the camp is managed by this staff and then generate report
        if (this.createdCamps.contains(camp)) {
            // Assuming we have a list of camp committee members and their performance metrics
            List<CommitteeMember> committeeMembers = camp.getCommitteeMembers();
            String reportName = "Camp_Committee_Performance_Report_" + camp.getName() + ".csv";
        
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportName))) {
                writer.write("Member Name,Points,Replies,Suggestions Accepted\n"); // CSV header
                for (CommitteeMember member : committeeMembers) {
                    // Calculating points as per your performance logic, here's a placeholder
                    int points = member.getRepliesCount() + member.getSuggestionsCount() + 
                             (member.getSuggestionsAccepted() * 2); // extra point for accepted suggestion
                    writer.write(String.format("%s,%d,%d,%d\n", member.getName(), points,
                        member.getRepliesCount(), member.getSuggestionsAccepted()));
            }
            } catch (IOException e) {
                System.out.println("An error occurred while writing the performance report.");
                e.printStackTrace();
        }
    }   else {
            System.out.println("Camp not managed by staff: " + this.userId);
    }
}

/

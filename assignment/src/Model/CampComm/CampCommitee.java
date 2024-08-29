package Model.CampComm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.EnquirySuggestion.Enquiry;
import Model.EnquirySuggestion.EnquiryManager;
import Model.EnquirySuggestion.Suggestion;
import Model.EnquirySuggestion.SuggestionManager;
import Model.Staff.EnquiryInterfaceStaff;
import Model.Camp.Camp;
import Model.Camp.CampManager;
import Model.Student.Student;
import Model.Student.StudentManager;
import Model.Student.StudentReport;
import Model.User.UserManager;
import Model.User.UserRole;

/**
 * The {@code CampCommitee} class represents camp committee members who are also students.
 * It extends the {@code Student} class and implements the {@code EnquiryInterfaceStaff} interface.
 * Camp committee members have various functions related to camps, suggestions, enquiries, points, and reports.
 * 
 * <p>These functions include accessing camp details, making and managing suggestions, viewing enquiries,
 * replying to enquiries, managing points, and generating reports.
 * 
 * @author Alicia
 * @version 5.0
 * @since 2023-11-19
 */
public class CampCommitee extends Student implements EnquiryInterfaceStaff {
    private int points;

    /**
     * Constructs a new {@code CampCommitee} instance with default values.
     */
    public CampCommitee() {}

    /**
     * Constructs a new {@code CampCommitee} instance with the specified attributes.
     *
     * @param name The name of the camp committee member.
     * @param userID The user ID of the camp committee member.
     * @param password The password of the camp committee member.
     * @param faculty The faculty to which the camp committee member belongs.
     * @param role The role of the camp committee member (e.g., Student, Committee).
     */
    public CampCommitee(String name, String userID, String password, String faculty, UserRole role) {
        super(name, userID, password, faculty, role);
        this.points = 0;
    }

    ////////////////////////////////////// CAMP FUNCTIONS //////////////////////////////////////
    
    /**
     * Retrieves the camp information for a given camp name.
     *
     * @param campName The name of the camp to retrieve.
     * @return The camp object if found, or {@code null} if not found.
     */
    public Camp getCamp(String campName) {
        ArrayList<Camp> camps = CampManager.readCamps();
        for (Camp c : camps) {
            if (c.getCampName().equals(campName)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Prints the details of a camp.
     *
     * @param camp The camp for which details should be printed.
     */
    public void printCampDetails(Camp camp) {
        System.out.println("Camp Name: " + camp.getCampName());
        System.out.println("Camp Start Date: " + camp.getStartDate());
        System.out.println("Camp End Date: " + camp.getEndDate());
        System.out.println("Camp Registration Deadline: " + camp.getRegistrationDeadline());
        System.out.println("Camp Location: " + camp.getLocation());
        System.out.println("Camp Max Capacity: " + camp.getMaxCapacity());
        System.out.println("Camp Committee Slots: " + camp.getCampCommSlots());
        System.out.println("Camp Description: " + camp.getCampDescription());
        System.out.println("Camp Staff ID: " + camp.getStaffID());
    }

    ////////////////////////////////////// SUGGESTION FUNCTIONS //////////////////////////////////////

    /**
     * Creates a new suggestion and writes it to the suggestions file.
     *
     * @param message The message of the suggestion.
     * @throws IOException If an I/O error occurs while writing the suggestion.
     */
    public void makeSuggestions(String message) throws IOException {
        if (this.getUserID() == null) {
            System.out.println("Camp Committee ID not found!");
            return;
        }
        Suggestion suggestion = new Suggestion(this.getUserID(), message, false);
        SuggestionManager.writeSuggestion(suggestion);
    }

    /**
     * Retrieves and returns suggestions made by the camp committee member.
     *
     * @return An ArrayList of suggestions made by the camp committee member.
     */
    public ArrayList<Suggestion> viewMySuggestions() {
        ArrayList<Suggestion> suggestions = SuggestionManager.readSuggestions();
        ArrayList<Suggestion> mySuggestions = new ArrayList<>();
        for (Suggestion s : suggestions) {
            if (s.getStudentID().equals(this.getUserID())) {
                System.out.println("Debug: suggestion added");
                mySuggestions.add(s);
            }
        }
        return mySuggestions;
    }

    /**
     * Edits an existing suggestion and updates it in the suggestions file.
     *
     * @param suggestion The modified suggestion to be saved.
     * @throws IOException If an I/O error occurs while editing the suggestion.
     */
    public void editSuggestion(Suggestion suggestion) throws IOException {
        SuggestionManager.editSuggestion(suggestion);
    }

    /**
     * Deletes an existing suggestion from the suggestions file.
     *
     * @param suggestion The suggestion to be deleted.
     * @throws IOException If an I/O error occurs while deleting the suggestion.
     */
    public void deleteSuggestion(Suggestion suggestion) throws IOException {
        SuggestionManager.deleteSuggestion(suggestion);
    }

    /**
     * Prints the details of a suggestion.
     *
     * @param suggestion The suggestion for which details should be printed.
     */
    public void printSuggestionDetails(Suggestion suggestion) {
        System.out.println("Suggestion ID: " + suggestion.getSuggestionID());
        System.out.println("Student ID: " + suggestion.getStudentID());
        System.out.println("Suggestion Text: " + suggestion.getSuggestionText());
        System.out.println("Accepted: " + (suggestion.getStatus() ? "Yes" : "No"));
    }

    ////////////////////////////////////// ENQUIRY FUNCTIONS //////////////////////////////////////

    /**
     * Retrieves and returns enquiries related to camps that involve the camp committee member.
     *
     * @return An ArrayList of enquiries related to camps involving the camp committee member.
     */
    public ArrayList<Enquiry> viewMyCampsEnquiries() {
        ArrayList<Enquiry> enquiries = EnquiryManager.readEnquiries();
        ArrayList<Enquiry> myEnquiries = new ArrayList<>();
        for (Enquiry e : enquiries) {
            Camp camp = e.getCamp();
            if (camp.getCampCommittee().contains(this)) {
                myEnquiries.add(e);
            }
        }
        return myEnquiries;
    }

    /**
     * Replies to an enquiry with the specified ID and updates its status.
     *
     * @param enquiryID The ID of the enquiry to reply to.
     * @param reply The reply message to the enquiry.
     * @throws IOException If an I/O error occurs while updating the enquiry.
     */
    public void replyEnquiry(int enquiryID, String reply) throws IOException {
        ArrayList<Enquiry> enquiries = EnquiryManager.readEnquiries();
        for (Enquiry e : enquiries) {
            if (e.getEnquiryID() == enquiryID) {
                if (e.getStatus()) {
                    System.out.println("Enquiry has already been replied!");
                    break;
                } else {
                    e.setReply(reply);
                    EnquiryManager.editEnquiry(e);
                    System.out.println("Reply sent successfully!");
                    break;
                }
            }
        }
    }

    /**
     * Prints the details of an enquiry.
     *
     * @param enquiry The enquiry for which details should be printed.
     */
    public void printEnquiryDetails(Enquiry enquiry) {
        System.out.println("Enquiry ID: " + enquiry.getEnquiryID());
        System.out.println("Enquiry Student ID: " + enquiry.getStudentID());
        System.out.println("Enquiry Title: " + enquiry.getTitle());
        System.out.println("Enquiry Message: " + enquiry.getMessage());
        System.out.println("Enquiry Status: " + enquiry.getStatus());
        System.out.println("Enquiry Reply: " + enquiry.getReply());
    }

    ////////////////////////////////////// POINTS FUNCTIONS //////////////////////////////////////
    
    /**
     * Retrieves the number of points accumulated by the camp committee member.
     *
     * @return The number of points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the number of points accumulated by the camp committee member.
     *
     * @param points The new number of points to set.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Adds points to the camp committee member's accumulated points.
     *
     * @param points The number of points to add.
     */
    public void addPoints(int points) {
        this.points += points;
    }

    ////////////////////////////////////// REPORT FUNCTIONS //////////////////////////////////////
    
    /**
     * Generates a report for registered students based on the specified filters and camp.
     *
     * @param facultyFilter The faculty filter for student selection.
     * @param isCampCommFilter The camp committee filter for student selection.
     * @param camp The camp for which the report is generated.
     * @param campName The name of the camp.
     * @throws IOException If an I/O error occurs while generating the report.
     */
    public void generateReport(String facultyFilter, boolean isCampCommFilter, Camp camp, String campName) throws IOException {
        ArrayList<Student> students = UserManager.readStudents();
        ArrayList<Student> registeredStudents = new ArrayList<Student>();
        for (Student s : students) {
            String studentID = s.getStudentID();
            if (StudentManager.readStudentFile(campName, studentID)) {
                registeredStudents.add(s);
            }
        }
        StudentReport.generateReport(registeredStudents, isCampCommFilter, facultyFilter);
    }
}

package src.basic;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class Student {
    private String userId;
    private String password;
    private String name;
    private String userGroup;
    private boolean isCampCommittee;
    private List<Enquiry> enquiries;
    private List<Camp> registeredCamps;

    public Student(String userId, String password, String name, String userGroup, boolean isCampCommittee) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.userGroup = userGroup;
        this.isCampCommittee = isCampCommittee;
        this.enquiries = new ArrayList<>();
        this.registeredCamps = new ArrayList<>();
    }

    // Getters and setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public boolean isCampCommittee() {
        return isCampCommittee;
    }

    public void setCampCommittee(boolean isCampCommittee) {
        this.isCampCommittee = isCampCommittee;
    }

    public List<Enquiry> getEnquiries() {
        return enquiries;
    }

    public void setEnquiries(List<Enquiry> enquiries) {
        this.enquiries = enquiries;
    }

    public List<Camp> getRegisteredCamps() {
        return registeredCamps;
    }

    public void setRegisteredCamps(List<Camp> registeredCamps) {
        this.registeredCamps = registeredCamps;
    }

    public void viewOpenCamps(List<Camp> openCamps) {
        for (Camp camp : openCamps) {
            System.out.println("Camp Name: " + camp.getName());
            System.out.println("Camp Description: " + camp.getDescription());
            System.out.println("Remaining Slots: " + camp.getRemainingSlots());
            System.out.println();
        }
    }

    

    public void registerForCamp(Camp camp, boolean isCommitteeMember) {
        if (registeredCamps.contains(camp)) {
            System.out.println("You are already registered for this camp.");
        } else if (!camp.hasAvailableSlots(camp)) {
            System.out.println("The camp is full. You cannot register for it.");
        } else if (!isCampDateAvailable(camp)) {
            System.out.println("There is a clash in the dates with another registered camp. You cannot register for this camp.");
        } else if (!camp.isRegistrationOpen(camp)) {
            System.out.println("The registration deadline for this camp has passed. You cannot register for it.");
        } else if (isCommitteeMember && isAlreadyCommitteeMember()) {
            System.out.println("You are already a committee member for another camp. You cannot register for this camp as a committee member.");
        } else {
            registeredCamps.add(camp);
            camp.updateRemainingSlots(-1);
            if (isCommitteeMember) {
                camp.addCommitteeMember(Student student);
                System.out.println("You have been added as a committee member for the camp: " + camp.getName());
            } else {
                System.out.println("You have successfully registered as an attendee for the camp: " + camp.getName());
            }
        }
    }

    private boolean isAlreadyCommitteeMember() {
        for (Camp camp : registeredCamps) {
            if (camp.isCommitteeMember(this)) {
                return true;
            }
        }
        return false;
    }
    
    public void withdrawFromCamp(Camp camp) {
        if (registeredCamps.remove(camp)) {
            camp.updateRemainingSlots(1);
            System.out.println("You have successfully withdrawn from the camp: " + camp.getName());
        } else {
            System.out.println("You are not registered for this camp.");
        }
    }
    
    
    private boolean isCampDateAvailable(Camp camp) {
        for (Camp registeredCamp : registeredCamps) {
            if (isCampDateClash(registeredCamp, camp)) {
                return false;
            }
        }
        return true;
    }

    private boolean isCampDateClash(Camp camp1, Camp camp2) {
    	LocalDate startDate1 = camp1.getStartDate();
    	LocalDate endDate1 = camp1.getEndDate();
    	LocalDate startDate2 = camp2.getStartDate();
    	LocalDate endDate2 = camp2.getEndDate();

    	return (startDate1.isBefore(endDate2) && endDate1.isAfter(startDate2));
    }
    

    public void submitEnquiry(Camp camp, String message) {

        Enquiry enquiry = new Enquiry(camp, message);
        enquiries.add(enquiry);
        System.out.println("Enquiry submitted: " + message);
    }

    public void viewEnquiries() {

        System.out.println("View enquiries feature");
        if (enquiries.isEmpty()) {
            System.out.println("You have no enquiries.");
        } else {
            for (Enquiry enquiry : enquiries) {
                System.out.println("Enquiry Camp: " + enquiry.getCamp().getName());
                System.out.println("Enquiry Message: " + enquiry.getMessage());
                System.out.println();
            }
        }
    }
    
    public void editEnquiry(int enquiryId) {
    	Scanner scanner = new Scanner(System.in);
        for (Enquiry enquiry : enquiries) {
            if (enquiry.getId() == enquiryId) {
                System.out.println("Enter the new enquiry message:");
                String newMessage = scanner.nextLine();
                enquiry.setMessage(newMessage);
                System.out.println("Enquiry updated successfully.");
                return;
            }
        }
        System.out.println("Enquiry not found.");
    }

    public void deleteEnquiry(int enquiryId) {
        for (Enquiry enquiry : enquiries) {
            if (enquiry.getId() == enquiryId) {
                enquiries.remove(enquiry);
                System.out.println("Enquiry deleted successfully.");
                return;
            }
        }
        System.out.println("Enquiry not found.");
    }
    
}
package src.Manager;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import src.model.Camp;
import src.model.enums.UserRole;


public class CampManager {
    public CampManager() {
    }

    // editing existig camps
    public void setCampDetails(String campName, String startDate, String endDate, String registrationClosingDate,
                                String userGroup, String location, int totalSlots, int campCommitteeSlots,
                                String description) throws ParseException {
        this.campName = campName;
        this.startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        this.endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        this.registrationClosingDate = new SimpleDateFormat("yyyy-MM-dd").parse(registrationClosingDate);
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
    }
}

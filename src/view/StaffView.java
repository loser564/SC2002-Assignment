package src.view;
import src.basic.Staff;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class StaffView {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Staff staff = new Staff(userID, faculty));  // Create an instance of the Staff class
		int choice = 0;
        switch (choice) {
            case 1:
                System.out.println("1. Create new camp");
                System.out.println("2. Edit camp");
                System.out.println("3. Delete camp");

                int choice2 = sc.nextInt();
                switch (choice2) {
                    case 1:
                        createNewCamp(staff);
                        break;
                }
                break;
        }
    }

    private static void createNewCamp(Staff staff) {
        int campCommitteeSlots = 0;
        System.out.println("Enter camp name: ");
        String campName = sc.next();
        System.out.println("Date format must be yyyy-MM-dd");

        Date startDate;
        do {
            System.out.println("Enter start date: ");
            String date1 = sc.next();
            startDate = stringToDate(date1);
        } while (startDate == null);

       Date endDate;
        do {
            System.out.println("Enter start date: ");
            String date2 = sc.next();
            endDate = stringToDate(date2);
        } while (endDate == null);
        Date registrationCloseDate;
		do {
			System.out.println("Enter registration close date: ");
			String date3 = sc.next();
			registrationCloseDate = stringToDate(date3);
		} while (registrationCloseDate == null);
        System.out.println("Enter group camp available to: ");
        String userGroup = sc.next();
        System.out.println("Enter location: ");
        String location = sc.next();
        System.out.println("Enter total slots: ");
        int totalSlots = sc.nextInt();

        if (campCommitteeSlots < 10) {
            System.out.println("Enter camp committee slots (max 10): ");
            campCommitteeSlots = sc.nextInt();
            if (campCommitteeSlots > 10) {
                System.out.println("Invalid input. Please enter a number less than 10");
                return;
            }
        }

        System.out.println("Enter description: ");
        String description = sc.next();
        System.out.println("Enter staff ID: ");
        String userID = sc.next();
        System.out.println("Enter faculty: ");
        String faculty = sc.next();

        // Now you can use the gathered information to create the camp using the staff instance
        staff.createCamp(campName, startDate, endDate, registrationCloseDate, userGroup,
                location, totalSlots, campCommitteeSlots, description, userID, faculty);
    }

    private static Date stringToDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the correct format.");
            return null;
        }
    }
}


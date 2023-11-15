package src.basic;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

// UserManager.java
public class UserManager {
    private HashMap<String, User> users = new HashMap<>();

    public UserManager() {
        // Constructor might be empty if you're loading users elsewhere or in the main method.
    }

    public void loadUsersFromCSV(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                // parts[0] is Name, parts[1] is Email, parts[2] is Faculty, parts[3] is Password if present, parts[4] is Role
                String userID = parts[1].substring(0, parts[1].indexOf('@'));
                String faculty = parts[2];
                UserRole role = UserRole.valueOf(parts[4].toUpperCase());
                User newUser = new User(userID, faculty); // Create a new user with the userID and faculty
                users.put(userID, newUser); // Add the user to the map
            }
        }
        reader.close();
    }

    public User getUser(String userID) {
        return users.get(userID);
    }

    public String authenticate(String userID, String password) {
        User user = getUser(userID);
    
        if (user != null && user.validatePassword(password)) {
            switch (user.getRole()) {
                case STUDENT:
                    return "Student";
                case CAMP_COMMITTEE:
                    return "CampCommitteeMember";
                case STAFF:
                    return "Staff";
                default:
                    return "Invalid";
            }
        } else {
            return "Invalid";
        }
    }
    

    public void changePassword(String userID, String newPassword) {
        User user = getUser(userID);
        if (user != null) {
            user.setPassword(newPassword);
        }
    }
}
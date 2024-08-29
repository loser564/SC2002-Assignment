package Model.User;

import java.io.IOException;

// every user has userID for logging in 
// every user has a deafult pw: "password"
// every user logs in through userID and pw
// ever user has a faculty
// every user can change their password 
// every user is either a student or staff

/**
* The base class representing a user in the system.
* Every user has a name, userID for logging in, default password ("password"),
* faculty, and a user role (either student or staff).
* Users can change their passwords.
* @author Ryan
* @version 1.0
* @since 2023-11-3
*/

public class User {
    private String name;
    private String userID;
    private String password;
    protected String faculty;
    private UserRole userRole;

    /**
    * Default constructor for the User class.
    */
    public User(){}

    /**
    * Get the name of the user.
    * @return The name of the user.
    */
    public String getName() { return name; }

    /**
    * Set the name of the user.
    * @param name The new name of the user.
    */
    public void setName(String name) { this.name = name; }

    /**
    * Get the userID of the user.
    * @return The userID of the user.
    */
    public String getUserID() {
        return userID;
    }

    /**
    * Set the userID of the user.
    * @param userID The new userID of the user.
    */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
    * Get the password of the user.
    * @return The password of the user.
    */
    public String getPassword() { return password; }

    /**
    * Set the password of the user.
    * @param password The new password of the user.
    */
    public void setPassword(String password) { this.password = password; }

    /**
    * Get the faculty of the user.
    * @return The faculty of the user.
    */
    public String getFaculty() { return faculty; }

    /**
    * Set the faculty of the user.
    * @param faculty The new faculty of the user.
    */
    public void setFaculty(String faculty) { this.faculty = faculty; }

    /**
    * Set the role of the user.
    * @param role The new role of the user.
    */
    public void setRole(UserRole role) { userRole = role; }

    /**
    * Get the role of the user.
    * @return The role of the user.
    */
    public UserRole getUserRole() { return userRole; }

    /**
    * Change the password of the user and update it in the corresponding file.
    * @param newPW The new password to set.
    * @return True if the password change is successful, false otherwise.
    * @throws IOException If an I/O error occurs during the password update.
    */
    public boolean changePassword(String newPW, String userID) throws IOException{
        setPassword(newPW);

        switch(userRole){
            case STUDENT:
                UserManager.writeStudentsPassword(userID, newPW);
                return true;
            case STAFF:
                UserManager.writeStaffPassword(userID, newPW);
                return true;
            default:
                return false;

        }
        
    }

}

package Model.User;

import java.io.IOException;

// every user has userID for logging in 
// every user has a deafult pw: "password"
// every user logs in through userID and pw
// ever user has a faculty
// every user can change their password 
// every user is either a student or staff
public class User {
    private String name;
    private String userID;
    private String password;
    protected String faculty;
    private UserRole userRole;

    public User(){}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getFaculty() { return faculty; }

    public void setFaculty(String faculty) { this.faculty = faculty; }


    public void setRole(UserRole role) { userRole = role; }

    public UserRole getUserRole() { return userRole; }

    public boolean changePassword(String newPW) throws IOException{
        setPassword(newPW);

        switch(userRole){
            case STUDENT:
                UserManager.writeStudentsPassword(this.getUserID(), newPW);
                return true;
            case STAFF:
                UserManager.writeStaffPassword(this.getUserID(), newPW);
                return true;
            default:
                return false;

        }
        
    }

}

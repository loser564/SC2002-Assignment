package src.basic;
public class User {
    private String userID;
    private String password;
    private String faculty;

    public User(String userID, String faculty) {
        this.userID = userID;
        this.password = "password"; // default password for all users
        this.faculty = faculty;
    }

    // Getters and setters as needed
    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFaculty() {
        return faculty;
    }

    // Method to check if the provided password matches the user's password
    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}
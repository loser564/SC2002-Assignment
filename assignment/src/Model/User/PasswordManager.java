package Model.User;

import java.io.IOException;

public class PasswordManager {
    public static void updatePassword(User user, String newPassword) throws IOException {
        boolean result = user.changePassword(newPassword);
        if (result) {
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("Password update failed.");
        }
}
}

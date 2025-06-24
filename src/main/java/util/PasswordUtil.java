package util;

import org.mindrot.jbcrypt.BCrypt;
public class PasswordUtil {

    // Password hashing with automatic salt
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(14));
    }

    // Password verification: does the entered password match the hash in the database
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}

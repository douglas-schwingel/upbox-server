package br.com.upboxserver.user.util;

import br.com.upboxserver.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class UserUtils {
    private static final Logger logger = LoggerFactory.getLogger(UserUtils.class);
    private static final Marker marker = MarkerFactory.getMarker("userUtils");

    public static boolean isValid(User user) {
        return verifyUserFields(user);
    }

    private static boolean verifyUserFields(User user) {
        if (user == null) return false;
        if (isEmailInvalid(user.getEmail())) return false;
        if (isNameInvalid(user.getName())) return false;
        if (isPasswordInvalid(user.getPassword())) return false;
        if (isUsernameInvalid(user.getUsername())) return false;
        return true;
    }


    private static boolean isUsernameInvalid(String username) {

        if (username.matches("\\W") || username.length() < 4) {
            logger.error(marker, "Invalid username: {}", username);
            return true;
        }
        return false;
    }

    private static boolean isEmailInvalid(String email) {
        if (email.length() < 1 || !email.contains("@")){
            logger.error(marker, "Invalid email: {}", email);
            return true;
        }
        return false;
    }

    private static boolean isNameInvalid(String name) {
        if (name == null || name.length() < 1){
            logger.error(marker, "Invalid name: {}", name);
            return true;
        }
        return false;
    }

    private static boolean isPasswordInvalid(String password) {
        if (password.length() < 8 || !password.contains("$")) {
            logger.error(marker, "Invalid passowrd: {}", password);
        }
        return false;
    }
}

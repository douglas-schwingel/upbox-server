package br.com.upboxserver.v1.user.util;

import br.com.upboxserver.v1.user.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserUtils {

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
            log.error("Invalid username: {}", username);
            return true;
        }
        return false;
    }

    private static boolean isEmailInvalid(String email) {
        if (email.length() < 1 || !email.contains("@")){
            log.error("Invalid email: {}", email);
            return true;
        }
        return false;
    }

    private static boolean isNameInvalid(String name) {
        if (name == null || name.length() < 1){
            log.error("Invalid name: {}", name);
            return true;
        }
        return false;
    }

    private static boolean isPasswordInvalid(String password) {
        if (password.length() < 8 || !password.contains("$")) {
            log.error("Invalid passowrd: {}", password);
        }
        return false;
    }
}

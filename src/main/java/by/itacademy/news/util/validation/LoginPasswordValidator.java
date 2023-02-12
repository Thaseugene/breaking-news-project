package by.itacademy.news.util.validation;

public class LoginPasswordValidator {
    private static LoginPasswordValidator instance;

    private LoginPasswordValidator() {}

    public static LoginPasswordValidator getInstance() {
        if (instance == null) {
            instance = new LoginPasswordValidator();
        }
        return instance;
    }

    public static boolean isValidLogin(String login) {
        return login != null && !login.trim().isEmpty();
    }

    public static boolean isValidPassword(String password) {
        return password != null && !password.trim().isEmpty();
    }
}

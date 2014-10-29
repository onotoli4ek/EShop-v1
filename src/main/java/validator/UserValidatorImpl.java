package validator;

import entity.User;

import java.util.HashMap;
import java.util.Map;

//todo: realize business logic!

public class UserValidatorImpl implements UserValidator{
    @Override
    public Map<String, String> validate(User user) {
        Map<String, String> errorMap = new HashMap<>();
        validateLogin(user.getLogin(), errorMap);
        validatePassword(user.getPassword(), errorMap);
        validateEmail(user.getEmail(), errorMap);
        return errorMap;
    }

    private void validateLogin (String login,Map<String, String> errorMap ) {
        if (login == null){
            errorMap.put("login", "login == null");
        } else if (login.length() < 3){
            errorMap.put("login", "login.length() < 3");
        }
    }
    private void validatePassword (String password,Map<String, String> errorMap ) {
        if (password == null){
            errorMap.put("password", "password == null");
        } else if (password.length() < 3){
            errorMap.put("password", "password.length() < 3");
        }
    }
    private void validateEmail (String email,Map<String, String> errorMap ) {
        if (email == null){
            errorMap.put("email", "email == null");
        } else if (email.length() < 3){
            errorMap.put("email", "email.length() < 3");
        }
    }
}

package validator;

import entity.User;

import java.util.Map;

public interface UserValidator extends Validator<User> {
    @Override
    public Map<String, String> validate(User user);
}

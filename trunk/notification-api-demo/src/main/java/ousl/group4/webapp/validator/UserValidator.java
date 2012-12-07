package ousl.group4.webapp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ousl.group4.model.User;

public class UserValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "username required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmedPassword", "username required.");

        User user = (User) o;
        if(!user.getPassword().equals(user.getConfirmedPassword())){
            errors.rejectValue("password", "Password and Conform password is not match.");
        }
    }
}

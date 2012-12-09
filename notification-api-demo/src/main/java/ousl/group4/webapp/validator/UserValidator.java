package ousl.group4.webapp.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ousl.group4.model.User;

@Component
public class UserValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "signup.err.username", "Field name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "signup.err.password", "Field name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmedPassword", "signup.err.confirmedPassword", "Field name is required");
        ValidationUtils.rejectIfEmpty(errors, "secretQuestion", "signup.err.secretquest", "Field is not selected");
        User user = (User) o;
        if(!user.getPassword().equals(user.getConfirmedPassword())){
            errors.rejectValue("password", "signup.err.passwordNotMatch");
        }
        if(user.getGender() == 0){
            errors.rejectValue("gender", "signup.err.gender");
        }
    }
}

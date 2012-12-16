package ousl.group4.webapp.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ousl.group4.model.User;

@Component
public class UserValidator implements Validator {

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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "signup.err.email", "Field name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "secretAnswer", "signup.err.secretanw", "Field name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "ignup.err.firstname", "First name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "signup.err.lastname", "Field name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "signup.err.dob", "Field is not selected");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressLine1", "signup.err.addrLine1", "Field name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressLine2", "signup.err.addrLine2", "Field name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressLine3", "signup.err.addrLine3", "Field name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber", "signup.err.mobileNo", "Field name is required");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors,"landNumber",);
        User user = (User) o;
        if (!user.getPassword().equals(user.getConfirmedPassword())) {
            errors.rejectValue("password", "signup.err.passwordNotMatch");
        }
        if (user.getGender() == 0) {
            errors.rejectValue("gender", "signup.err.gender");
        }
    }
}

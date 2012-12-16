package ousl.group4.webapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ousl.group4.model.User;
import ousl.group4.service.UserService;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        //regular expression for email
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        //regular expression for phone number
        String phoneNumberPattern = "^([0][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])$";

        Pattern emailAddressPattern = Pattern.compile(emailPattern);
        Pattern mobilePhoneNumberPattern = Pattern.compile(phoneNumberPattern);

        //verify filed empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "signup.err.username", "Field name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "signup.err.password", "Field name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmedPassword", "signup.err.confirmedPassword", "Field name is required");
        ValidationUtils.rejectIfEmpty(errors, "secretQuestion", "signup.err.secretquest", "Field is not selected");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "signup.err.email", "Field name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "secretAnswer", "signup.err.secretanw", "Field name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "signup.err.firstname", "First name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "signup.err.lastname", "Field name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "signup.err.dob", "Field name is selected");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressLine1", "signup.err.addrLine1", "Field name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressLine2", "signup.err.addrLine2", "Field name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressLine3", "signup.err.addrLine3", "Field name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber", "signup.err.mobileNo", "Field name is required");

        User user = (User) o;
        //verify username exist
        User isExistUser = userService.getUserByUsername(user.getUsername());
        if(isExistUser != null){
            errors.rejectValue("username", "signup.err.usernameexist");
        }
        //verify password and confirm password matching
        if (!user.getPassword().equals(user.getConfirmedPassword())) {
            errors.rejectValue("password", "signup.err.passwordNotMatch");
        }
        //verify gender is selected
        if (user.getGender() == 0) {
            errors.rejectValue("gender", "signup.err.gender");
        }
        //verify email address
        if(!user.getEmail().isEmpty()){
            if(!emailAddressPattern.matcher(user.getEmail()).matches()){
                errors.rejectValue("email", "signup.err.email.invalid");
            }
        }
        //verify mobile phone number
        if(!user.getMobileNumber().isEmpty()){
            if(!mobilePhoneNumberPattern.matcher(user.getMobileNumber()).matches()){
                errors.rejectValue("mobileNumber","signup.err.mobileNo.invalid");
            }
        }
        //verify land phone number if entered
        if(user.getLandNumber() != null){
            if(phoneNumberPattern.matches(user.getLandNumber())){
                errors.rejectValue("landNumber","signup.err.landno.invalid");
            }
        }
    }
}

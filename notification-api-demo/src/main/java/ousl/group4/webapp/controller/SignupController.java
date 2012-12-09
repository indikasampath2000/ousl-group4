package ousl.group4.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ousl.group4.model.Country;
import ousl.group4.model.User;
import ousl.group4.service.CountryService;
import ousl.group4.webapp.validator.UserValidator;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SessionAttributes(value = "user")
public class SignupController {

    @Autowired
    private CountryService countryService;
    @Autowired
    private UserValidator userValidator;

    /**
     * initialize form with user object
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/signup.html", method = RequestMethod.GET)
    public String initForm(ModelMap modelMap) {
        User user = new User();
        user.setAddressLine3("Sri Lanka");

        modelMap.addAttribute("user", user);
        return "signup";
    }

    /**
     * persist user and generate notification with instructions to enable account
     *
     * @return
     */
    @RequestMapping(value = "/signupComplete.html", method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("user")User user, BindingResult bindingResult, SessionStatus sessionStatus){

        userValidator.validate(user, bindingResult);

        if(bindingResult.hasErrors()){
            // validation fails
            return "signup";
        }else {
            sessionStatus.setComplete();
            // validation pass
            return "signup-success";
        }

    }

    /**
     * populate countries into select when form loading
     *
     * @return
     */
    @ModelAttribute("countries")
    public List<String> populateCountries() {
        List<Country> result = countryService.getAllCountries();

        List<String> countries = new ArrayList<String>();
        for (Object o : result) {
            Country c = (Country) o;
            countries.add(c.getName());
        }
        return countries;
    }

    /**
     * populate secret questions into select when form loading
     *
     * @return
     */
    @ModelAttribute("secretQuestions")
    public List<String> populateSecretQuestions() {
        List<String> quiz = new ArrayList<String>();
        quiz.add("What is your first school?");
        quiz.add("What is your mother's maiden name?");
        quiz.add("What is your first vehicle?");
        quiz.add("Who is your favorite author?");
        return quiz;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.setValidator(userValidator);
    }

}

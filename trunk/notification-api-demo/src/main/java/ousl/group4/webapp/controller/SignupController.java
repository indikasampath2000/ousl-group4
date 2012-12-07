package ousl.group4.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ousl.group4.model.Country;
import ousl.group4.model.User;
import ousl.group4.service.CountryManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/signup.html")
public class SignupController {

    @Autowired
    private CountryManager countryManager;

    /**
     * initialize form with user object
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap modelMap) {
        User user = new User();
        user.setGender('M');
        user.setAddressLine3("Sri Lanka");

        modelMap.addAttribute("user", user);
        return "signup";
    }

    /**
     * populate countries into select when form loading
     *
     * @return
     */
    @ModelAttribute("countries")
    public List<String> populateCountries() {
        List<Country> result = countryManager.getAllCountries();

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

    /**
     * populate gender into radio button when form loading
     *
     * @return
     */
    @ModelAttribute("gender")
    public Map<String, String> populateGender() {
        Map<String, String> gender = new HashMap<String, String>();
        gender.put("M", "Male");
        gender.put("F", "Female");
        return gender;
    }

}

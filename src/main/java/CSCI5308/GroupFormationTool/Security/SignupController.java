package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.AccessControl.AccessControlAbstractFactory;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicyContextListBuilder;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import CSCI5308.GroupFormationTool.PasswordPolicy.PasswordPolicyAbstractFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class SignupController
{
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String PASSWORD_CONFIRMATION = "passwordConfirmation";
    private final String FIRST_NAME = "firstName";
    private final String LAST_NAME = "lastName";
    private final String EMAIL = "email";
    
    private Logger log = LoggerFactory.getLogger(SignupController.class);

    @GetMapping("/signup")
    public String displaySignup(Model model)
    {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView processSignup
    (
        @RequestParam(name = USERNAME) String bannerID,
        @RequestParam(name = PASSWORD) String password,
        @RequestParam(name = PASSWORD_CONFIRMATION) String passwordConfirm,
        @RequestParam(name = FIRST_NAME) String firstName,
        @RequestParam(name = LAST_NAME) String lastName,
        @RequestParam(name = EMAIL) String email
    )
    {
    	log.info("Received request at processSignup with bannerID: " + bannerID + " fistName: " + firstName + " lastName: " + lastName);
        boolean success = false;
        IPasswordPolicyContextListBuilder activePasswordPolicyListBuilder = PasswordPolicyAbstractFactory.instance().getActivePasswordPolicyListBuilder();
        IUser user = AccessControlAbstractFactory.instance().getUser();
        user.setPassword(password);
        List<String> failedPasswordValidationList = user.failedPasswordValidationList(user,activePasswordPolicyListBuilder);
        if (user.isBannerIDValid(bannerID) &&
            user.isEmailValid(email) &&
            user.isFirstNameValid(firstName) &&
            user.isLastNameValid(lastName) &&
            (failedPasswordValidationList.size() == 0) &&
            password.equals(passwordConfirm))
        {
            IUser u = AccessControlAbstractFactory.instance().getUser();
            u.setBannerID(bannerID);
            u.setPassword(password);
            u.setFirstName(firstName);
            u.setLastName(lastName);
            u.setEmail(email);
            IUserPersistence userDB = AccessControlAbstractFactory.instance().getUserDB();
            IPasswordEncryption passwordEncryption = SecurityAbstractFactory.instance().getPasswordEncryption();
            success = u.createUser(userDB, passwordEncryption, null);
        }
        ModelAndView m;
        if (success)
        {
            m = new ModelAndView("login");
        }
        else
        {
        	log.warn("Failed to create new user with bannerID: " + bannerID + " fistName: " + firstName + " lastName: " + lastName);
            m = new ModelAndView("signup");
            m.addObject("passwordPolicyValidation",failedPasswordValidationList);
            m.addObject("errorMessage", "Invalid data, please check your values.");
        }
        return m;
    }
}
package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Courses.CoursesAbstractFactory;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicyContextListBuilder;
import CSCI5308.GroupFormationTool.PasswordPolicy.PasswordPolicyAbstractFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class UpdatePasswordController
{
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    
    private Logger log = LoggerFactory.getLogger(UpdatePasswordController.class);

    @PostMapping("/updatepassword")
    public ModelAndView updatePassword
    (
        @RequestParam(name = USERNAME) String bannerID,
        @RequestParam(name = PASSWORD) String password
    )
    {
    	log.info("Received request at updatePassword with bannerId: " + bannerID);
        boolean success = false;
        IPasswordPolicyContextListBuilder passwordPolicyContextListBuilder = PasswordPolicyAbstractFactory.instance().getActivePasswordPolicyListBuilder();
        IUser user = AccessControlAbstractFactory.instance().getUser();
        user.setPassword(password);
        user.setBannerID(bannerID);
        List<String> failedPasswordValidationList = user.failedPasswordValidationList(user,passwordPolicyContextListBuilder);
        IUpdatePassword updatePassword = AccessControlAbstractFactory.instance().getUpdatePassword();
        success = updatePassword.updatePassword(failedPasswordValidationList,user);
        ModelAndView m;

        if(success)
        {
            m = new ModelAndView("login");
        }
        else
        {
        	log.warn("Password updation failed for bannerId: " + bannerID);
            m = new ModelAndView("index");
            m.addObject("passwordPolicyValidation",failedPasswordValidationList);
            ICoursePersistence courseDB = CoursesAbstractFactory.instance().getCourseDB();
            List<ICourse> allCourses = courseDB.loadAllCourses();
            m.addObject("courses", allCourses);
        }
        return m;
    }
}

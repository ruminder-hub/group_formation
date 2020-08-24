package CSCI5308.GroupFormationTool.Courses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController
{
	private static final String ID = "id";
	
	private Logger log = LoggerFactory.getLogger(CourseController.class);
	
	@GetMapping("/course/course")
	public String course(Model model, @RequestParam(name = ID) long courseID)
	{
		log.info("Received request at CourseController.course with courseId: " + courseID);
		ICoursePersistence courseDB = CoursesAbstractFactory.instance().getCourseDB();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("instructorId", authentication.getName());
		model.addAttribute("isSurveyPublished", true);
		ICourse course = CoursesAbstractFactory.instance().getCourse();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);

		List<Role> userRoles = course.getAllRolesForCurrentUserInCourse();
		if (null == userRoles)
		{
			model.addAttribute("instructor", false);
			model.addAttribute("ta", false);
			model.addAttribute("student", false);
			model.addAttribute("guest", true);
		}
		else
		{
			model.addAttribute("instructor", userRoles.contains(Role.INSTRUCTOR));
			model.addAttribute("ta", userRoles.contains(Role.TA));
			model.addAttribute("student", userRoles.contains(Role.STUDENT));
			model.addAttribute("guest", userRoles.isEmpty());
		}
		return "course/course";
	}
}

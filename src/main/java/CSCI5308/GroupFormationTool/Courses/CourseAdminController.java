package CSCI5308.GroupFormationTool.Courses;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import CSCI5308.GroupFormationTool.AccessControl.AccessControlAbstractFactory;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseAdminController
{
	private static final String ID = "id";
	private static final String TITLE = "title";
	private static final String INSTRUCTOR = "instructor";
	private Logger log = LoggerFactory.getLogger(CourseAdminController.class);
	
	@GetMapping("/admin/course")
	public String course(Model model)
	{
		log.info("Recieved request at CourseAdminController.course");
		ICoursePersistence courseDB = CoursesAbstractFactory.instance().getCourseDB();
		List<ICourse> allCourses = courseDB.loadAllCourses();
		model.addAttribute("courses", allCourses);
		return "admin/course";
	}
	
	@GetMapping("/admin/assigninstructor")
	public String assignInstructor(Model model, @RequestParam(name = ID) long courseID)
	{
		log.info("Recieved request at assignInstructor for course: " + courseID);
		ICoursePersistence courseDB = CoursesAbstractFactory.instance().getCourseDB();
		ICourse course = CoursesAbstractFactory.instance().getCourse();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		ICourseUserRelationshipPersistence courseUserRelationshipDB = CoursesAbstractFactory.instance().getCourseUserRelationshipDB();
		List<IUser> allUsersNotCurrentlyInstructors = courseUserRelationshipDB.findAllUsersWithoutCourseRole(Role.INSTRUCTOR, courseID);
		model.addAttribute("users", allUsersNotCurrentlyInstructors);
		return "admin/assigninstructor";
	}
	
	@GetMapping("/admin/deletecourse")
	public ModelAndView deleteCourse(@RequestParam(name = ID) long courseID)
	{
		log.info("Recieved request at deleteCourse for course: " + courseID);
		ICoursePersistence courseDB = CoursesAbstractFactory.instance().getCourseDB();
		ICourse course = CoursesAbstractFactory.instance().getCourse();
		course.setId(courseID);
		course.delete(courseDB);
		ModelAndView mav = new ModelAndView("redirect:/admin/course");
		return mav;
	}

	@RequestMapping(value = "/admin/createcourse", method = RequestMethod.POST)
	public ModelAndView createCourse(@RequestParam(name = TITLE) String title)
	{
		log.info("Recieved request at createCourse with title: " + title);
		ICoursePersistence courseDB = CoursesAbstractFactory.instance().getCourseDB();
		ICourse course = CoursesAbstractFactory.instance().getCourse();
		course.setTitle(title);
		course.createCourse(courseDB);
		ModelAndView mav = new ModelAndView("redirect:/admin/course");
		return mav;
	}
	
	@RequestMapping(value = "/admin/assigninstructor", method = RequestMethod.POST) 
	public ModelAndView assignInstructorToCourse
	(
		@RequestParam(name = INSTRUCTOR) List<Integer> instructor,
		@RequestParam(name = ID) long courseID
	)
	{
		log.info("Recieved request at assignInstructorToCourse for course: " + courseID);
		ICourse course = CoursesAbstractFactory.instance().getCourse();
		course.setId(courseID);
		Iterator<Integer> iter = instructor.iterator();
		ICourseUserRelationshipPersistence courseUserRelationshipDB = CoursesAbstractFactory.instance().getCourseUserRelationshipDB();

		while (iter.hasNext())
		{
			IUser user = AccessControlAbstractFactory.instance().getUser();
			user.setId(iter.next().longValue());
			courseUserRelationshipDB.enrollUser(course, user, Role.INSTRUCTOR);
		}
		ModelAndView mav = new ModelAndView("redirect:/admin/course");
		return mav;
	}
}
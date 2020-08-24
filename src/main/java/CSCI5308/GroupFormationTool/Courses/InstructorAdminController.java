package CSCI5308.GroupFormationTool.Courses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class InstructorAdminController
{
	private static final String ID = "id";
	private static final String FILE = "file";
	private static final String SUCCESSFUL = "successful";
	private static final String FAILURES = "failures";
	private static final String DISPLAY_RESULTS = "displayresults";
	
	private Logger log = LoggerFactory.getLogger(InstructorAdminController.class);
	
	@GetMapping("/course/instructoradmin")
	public String instructorAdmin(Model model, @RequestParam(name = ID) long courseID)
	{
		log.info("Received request at instructor admin with course id: " + courseID);
		ICoursePersistence courseDB = CoursesAbstractFactory.instance().getCourseDB();
		ICourse course = CoursesAbstractFactory.instance().getCourse();
		courseDB.loadCourseByID(courseID, course);

		if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR))
		{
			model.addAttribute("isInstructor", true);
		}

		model.addAttribute("course", course);
		model.addAttribute("displayresults", false);

		if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR) || course.isCurrentUserEnrolledAsRoleInCourse(Role.TA))
		{
			log.info("Redirecting to page instructoradmin as user is either instructor or TA");
			return "course/instructoradmin";
		}
		else
		{
			return "logout";
		}
	}

	@GetMapping("/course/instructoradminresults")
	public String instructorAdmin
	(
		Model model,
		@RequestParam(name = ID) long courseID,
		@RequestParam(name = SUCCESSFUL, required = false) List<String> successful,
		@RequestParam(name = FAILURES, required = false) List<String> failures,
		@RequestParam(name = DISPLAY_RESULTS) boolean displayResults
	)
	{
		log.info("Received request at instructor admin /course/instructoradminresult with course id: " + courseID + " and displayResults " + displayResults);
		ICoursePersistence courseDB = CoursesAbstractFactory.instance().getCourseDB();
		ICourse course = CoursesAbstractFactory.instance().getCourse();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		model.addAttribute("displayresults", false);
		model.addAttribute(SUCCESSFUL, successful);
		model.addAttribute(FAILURES, failures);
		model.addAttribute(DISPLAY_RESULTS, displayResults);

		if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR) ||
			 course.isCurrentUserEnrolledAsRoleInCourse(Role.TA))
		{
			log.info("Redirecting to page instructoradmin as user is either instructor or TA");
			return "course/instructoradmin";
		}
		else
		{
			return "logout";
		}
	}

	@GetMapping("/course/enrollta")
	public String enrollTA(Model model, @RequestParam(name = ID) long courseID)
	{
		log.info("Received request to enrollTA for courseID: " + courseID);
		ICoursePersistence courseDB = CoursesAbstractFactory.instance().getCourseDB();
		ICourse course = CoursesAbstractFactory.instance().getCourse();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR) ||
			 course.isCurrentUserEnrolledAsRoleInCourse(Role.TA))
		{
			log.info("Redirecting to page enrollta as user is either instructor or TA");
			return "course/enrollta";
		}
		else
		{
			return "logout";
		}
	}

	@RequestMapping(value = "/course/uploadcsv", consumes = {"multipart/form-data"})
	public ModelAndView upload(@RequestParam(name = FILE) MultipartFile file, @RequestParam(name = ID) long courseID)
	{
		log.info("Received request at upload to upload CSV file for courseID: " + courseID);
		ICoursePersistence courseDB = CoursesAbstractFactory.instance().getCourseDB();
		ICourse course = CoursesAbstractFactory.instance().getCourse();
		courseDB.loadCourseByID(courseID, course);
	   	IStudentCSVParser parser = CoursesAbstractFactory.instance().getCSVParser(file);
	   	IStudentCSVImport importer = CoursesAbstractFactory.instance().getCSVImport(parser,course);

	   	ModelAndView mav = new ModelAndView("redirect:/course/instructoradminresults?id=" + Long.toString(courseID));
	   	mav.addObject("successful", importer.getSuccessResults());
	   	mav.addObject("failures", importer.getFailureResults());
	   	mav.addObject("displayresults", true);
	   	return mav;
	}
}

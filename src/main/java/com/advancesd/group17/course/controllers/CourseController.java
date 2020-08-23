package com.advancesd.group17.course.controllers;

import static com.advancesd.group17.utils.Constants.ADMIN_HOME_PAGE;
import static com.advancesd.group17.utils.Constants.REDIRECT;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.dao.CourseDaoImpl;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.services.CourseService;
import com.advancesd.group17.course.services.CourseServiceImpl;
import com.advancesd.group17.users.dao.UserDao;
import com.advancesd.group17.users.dao.UserDaoImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	private static final Logger log = LoggerFactory.getLogger(CourseController.class);
	
	
	@GetMapping("/delete/{id}")
	public String deleteCourse(@PathVariable Integer id) {
		log.info("Entered CourseController.deleteCourse");
		CourseService courseService = new CourseServiceImpl();
		courseService.deleteCourse(id);
		
		log.info("Exiting CourseController.deleteCourse");
		return REDIRECT + ADMIN_HOME_PAGE;
	}
	
	@GetMapping("/courselist")
	public List<Course> courseList() {
		log.info("Entered CourseController.courseList");
		CourseService courseService = new CourseServiceImpl();
		String bannerId = "admin";
		UserDao userDao = new UserDaoImpl();
		CourseDao courseDao = new CourseDaoImpl();
		return courseService.listOfCourses(bannerId, userDao, courseDao);
	}
	
	@PostMapping(path = "/add/{courseName}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String addCourse(@PathVariable(required = true) String courseName, @RequestParam HashMap<String, Object> inputMap) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		log.info("Entered CourseController.addCourse with model " + gson.toJson(inputMap));

		CourseService courseService = new CourseServiceImpl();
		courseService.addCourse(courseName, inputMap);
		return REDIRECT + ADMIN_HOME_PAGE;
	}
	
	@GetMapping("/details/{courseId}")
	public String viewCourseDetails(@PathVariable Integer courseId, Model model) {
		log.info("Entered CourseController.viewCourseDetails");
		CourseService courseService = new CourseServiceImpl();
		Course course = courseService.courseDetails(courseId);
		model.addAttribute("course", course);
		log.info("Exiting with course details {}", course);
		return "courseDetails";
		
	}

	@GetMapping("/home")
	public String homepage(@RequestParam("bannerid") String bannerid, Model model)
	{
		CourseDao courseDao = new CourseDaoImpl();
		CourseService courseService = new CourseServiceImpl();
		UserDao userDao = new UserDaoImpl();
		List<Course> listofcourses = courseService.listOfCourses(bannerid, userDao, courseDao);
		
		model.addAttribute("courses", listofcourses);
		model.addAttribute("bannerid", bannerid);
		
		return "Home";
	}
}

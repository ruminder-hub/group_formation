package com.advancesd.group17.course.services;

import static com.advancesd.group17.utils.Constants.COURSE_CREDITS_FIELD;
import static com.advancesd.group17.utils.Constants.COURSE_DESC_FIELD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.dao.CourseDaoImpl;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.users.dao.UserDao;

@Service
public class CourseServiceImpl implements CourseService{
	
	
	private static final Logger log=LoggerFactory.getLogger(CourseServiceImpl.class);

	@Override
	public Course addCourse(String courseName, HashMap<String, Object> courseParameters) {
		String courseDesc = (String) courseParameters.get(COURSE_DESC_FIELD);
		Integer courseCredits = Integer.parseInt(courseParameters.get(COURSE_CREDITS_FIELD).toString());
		
		Course course = new Course();
		course.setCourseCredits(courseCredits);
		course.setCourseDesc(courseDesc);
		course.setCourseName(courseName);
		CourseDao courseDao = new CourseDaoImpl();
		courseDao.addNewCourse(course);
		return course;
	}

	@Override
	public Boolean deleteCourse(Integer courseId) {
		CourseDao courseDao = new CourseDaoImpl();
		Boolean courseDeleted = courseDao.deleteCourse(courseId);
		log.info("Course Deleted" + courseDeleted);
		return courseDeleted;
	}

	@Override
	public List<Course> listOfCourses(String bannerId, UserDao userDao, CourseDao courseDao) {
		
		String userrole = userDao.getuserrolebybannerid(bannerId);
		
		List<Course> crs = new ArrayList<>();
		
		if("Guest".equals(userrole))
		{
			crs = courseDao.getAllCourses();
		}
		else
		{
			crs = courseDao.getcoursesbybannerid(bannerId);
		}
		
		return crs;
	}
	
	public List<Course> allCourses(CourseDao courseDao) {
		log.info("Entered CourseServiceImpl.allCourses");
		
		List<Course> courseList = new ArrayList<Course>();
		
		courseList = courseDao.getAllCourses();
		
		return courseList;
		
	}

	@Override
	public Course courseDetails(Integer courseId) {
		CourseDao courseDao = new CourseDaoImpl();
		Course course = courseDao.getCourseDetails(courseId);
		log.info("Course Details" + course);
		return course;
	}

}

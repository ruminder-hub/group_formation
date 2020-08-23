package com.advancesd.group17.course.services;

import java.util.HashMap;
import java.util.List;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.users.dao.UserDao;


public interface CourseService {
	
	public Course addCourse(String courseName, HashMap<String, Object> courseParameters);
	
	public Boolean deleteCourse(Integer id);
	
	public List<Course> listOfCourses(String bannerid, UserDao userDao, CourseDao courseDao);
	
	public List<Course> allCourses(CourseDao courseDao);
	
	public Course courseDetails(Integer id);

}

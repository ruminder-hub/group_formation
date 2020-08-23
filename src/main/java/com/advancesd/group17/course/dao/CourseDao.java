package com.advancesd.group17.course.dao;

import java.util.List;

import com.advancesd.group17.course.models.Course;


public interface CourseDao {
	
	List<Course> getAllCourses();
	
	Course addNewCourse(Course course);
	
	Boolean deleteCourse(Integer courseId);
	
	Course getCourseDetails(Integer courseId);
	
	List<Course> getcoursesbybannerid(String bannerid);
}



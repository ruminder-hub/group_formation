package com.advancesd.group17.course.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.services.CourseServiceImpl;
import com.advancesd.group17.users.model.User;



public class CourseTests {

	private String courseName = "CSCI5490";
	private String courseDesc = "Advance Software Development";
	private Integer courseCredits = 3;
	private String bannerId = "121";
	private User user = new User();
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Test
	public void setCourseNameTest() {
		LOGGER.info("Running setCourseNameTest");
		Course c = new Course();
		c.setCourseName(courseName);
		assertEquals(courseName,c.getCourseName());
	}
	
	@Test
	public void getCourseNameTest() {
		LOGGER.info("Running getCourseNameTest");
		Course c = new Course(1, courseName);
		assertEquals(courseName, c.getCourseName());
	}
	
	public void getIdTest() {
		LOGGER.info("Running getIdTest");
		Course c = new Course(1, courseName);
		assertEquals(courseName, c.getCourseId());
	}

	public void setIdTest(int id) {
		LOGGER.info("Running setIdTest");
		Course c = new Course();
		assertEquals(1, c.getCourseId());
	}
	
	public void getCourseCreditsTest() {
		LOGGER.info("Running getCourseCreditsTests");
		Course c = new Course(1, courseName, courseDesc, courseCredits, null);
		assertEquals(3, c.getCourseCredits());
	}
	
	public void setCourseCreditsTests(int credits) {
		LOGGER.info("Running setCourseCreditsTests");
		Course c = new Course();
		c.setCourseCredits(3);
		assertEquals(3, c.getCourseCredits());
	}
	
	public void getCourseInstructorTest() {
		LOGGER.info("Running getCourseInstructorTest");
		User user = new User();
		user.setBannerId(bannerId);
		Course course = new Course(1, courseName, courseDesc, courseCredits, user);
		assertEquals(course.getInstructor().getBannerId(), bannerId);
	}
	
	public void setCourseInstructorTest() {
		LOGGER.info("Running setCourseInstructorTest");
		User user = new User();
		user.setBannerId(bannerId);
		Course course = new Course();
		course.setInstructor(user);
		assertEquals(course.getInstructor().getBannerId(), bannerId);
	}
}

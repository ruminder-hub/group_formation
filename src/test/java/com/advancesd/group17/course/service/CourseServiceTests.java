package com.advancesd.group17.course.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.mock.MockCourseDaoImpl;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.services.CourseServiceImpl;

@SpringBootTest
public class CourseServiceTests {
	
	private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	private static CourseDao courseDao = null;
	
	private static String courseName = "CSCI5490";
	
	@BeforeAll
	public static void setUp() {
		log.info("Setting the environment");
		courseDao = new MockCourseDaoImpl();
	}
	
	@AfterEach
	public void tearDown() {
		log.info("Resetting the environment");
		courseDao = new MockCourseDaoImpl();
	}
	
	@Test
	public void addCourseTest() {
		log.info("Running addCourseTest");
		Course course = new Course();
		course.setCourseName("Advance Web");
		int intialListSize = courseDao.getAllCourses().size();
		Course addedCourse = courseDao.addNewCourse(course);
		assertEquals(intialListSize + 1, courseDao.getAllCourses().size());
	}
	
	@Test
	public void deleteCourseTest() {
		log.info("Running deleteCourseTest");
		int intialListSize = courseDao.getAllCourses().size();
		Boolean courseDeleted = courseDao.deleteCourse(1);
		assertTrue(courseDeleted);
	}
	
	@Test
	public void getCourseByBannerIdCase1Test() {
		log.info("Running getCourseByBannerIdCase1Test");
		int intialListSize = courseDao.getAllCourses().size();
		List<Course> courseList = courseDao.getcoursesbybannerid("B00836202");
		assertEquals(intialListSize, courseList.size());
	}
	
	@Test
	public void getCourseByBannerIdCase2Test() {
		log.info("Running getCourseByBannerIdCase2Test");
		List<Course> courseList = courseDao.getcoursesbybannerid("B00000000");
		assertEquals(null, courseList);
	}
	
	@Test
	public void courseDetailsTest() {
		log.info("Running getCourseByBannerIdCase2Test");
		Course course = courseDao.getCourseDetails(1);
		assertEquals(3, course.getCourseCredits());
	}
	
	@Test
	public void allCoursesTest() {
		assertEquals(2, courseDao.getAllCourses().size());
	}
}

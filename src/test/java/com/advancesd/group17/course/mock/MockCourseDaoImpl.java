package com.advancesd.group17.course.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.services.CourseServiceImpl;

public class MockCourseDaoImpl implements CourseDao {
	
	private static final Logger log = LoggerFactory.getLogger(MockCourseDaoImpl.class);
	List<Course> courseList;
	
	public MockCourseDaoImpl() {
		courseList = new ArrayList<Course>();
		
		Course course = new Course();
		course.setCourseName("Adv SDC");
		course.setCourseId(1);
		course.setCourseCredits(3);
		courseList.add(course);
		
		Course course2 = new Course();
		course2.setCourseName("Adv Web");
		course2.setCourseId(2);
		course2.setCourseCredits(3);
		courseList.add(course2);
	}
	
	@Override
	public List<Course> getAllCourses() 
	{
		return courseList; 	
	}

	@Override
	public List<Course> getcoursesbybannerid(String bannerid) {
		
		if(bannerid == "B00836202")
		{	
			return courseList;
		}
		
		if(bannerid == "B00000000")
		{	
			return null;
		}
		
		return courseList;		
	}

	@Override
	public Course addNewCourse(Course course) {
		courseList.add(course);
		return course;
	}

	@Override
	public Boolean deleteCourse(Integer courseId) {
		int initialCourseListSize = courseList.size();
		courseList = courseList.stream().filter(course -> course.getCourseId() != courseId).collect(Collectors.toList());
		
		return courseList.size() != initialCourseListSize ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public Course getCourseDetails(Integer courseId) {
		log.info("Course list size " + courseList.size() + "course Id" + courseId);
		List<Course> filteredCourseList = courseList.stream().filter(course -> course.getCourseId() == courseId).collect(Collectors.toList());
		
		if (CollectionUtils.isEmpty(filteredCourseList)) {
			log.info("Course List empty"  );
			return null;
		}
		return filteredCourseList.get(0);
	}

}

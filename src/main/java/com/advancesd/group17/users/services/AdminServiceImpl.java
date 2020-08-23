package com.advancesd.group17.users.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.dao.CourseDaoImpl;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.services.CourseService;
import com.advancesd.group17.users.dao.UserDao;
import com.advancesd.group17.users.dao.UserDaoImpl;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	CourseService courseService;
	
	private static Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Override
	public void createHomePage(Model model) {
		log.info("Entered AdminServiceImpl.createHomePage");
		CourseDao courseDao = new CourseDaoImpl();
		List<Course> courseList = courseService.allCourses(courseDao);
		model.addAttribute("courseList", courseList);
		log.info("Exiting AdminServiceImpl.createHomePage");
		return ;
		
	}

}

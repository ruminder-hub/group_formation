package com.advancesd.group17.users.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.users.services.AdminService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@GetMapping("/home")
	public String adminHomePage(Model model) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		log.info("Entered AdminController.adminHomePage with model " + gson.toJson(model));
		adminService.createHomePage(model);
		log.info("Exiting from AdminController.adminHomePage");
		return "adminHomePage";
		
	}
	
	@GetMapping("/addCourse")
	public String addCourseDetailsPage(@RequestParam(required = true) String courseName, Model model) {
		log.info("Entered AdminController.addCourseDetailsPage");
		model.addAttribute("courseName", courseName);
		model.addAttribute("course", new Course());
		
		log.info("Exiting from AdminController.addCourseDetailsPage");
		
		return "courseInstructor";
	}

}

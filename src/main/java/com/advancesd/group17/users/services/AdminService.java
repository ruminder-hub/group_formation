package com.advancesd.group17.users.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.advancesd.group17.course.models.Course;

@Service
public interface AdminService {
	
	public void createHomePage(Model model);

}

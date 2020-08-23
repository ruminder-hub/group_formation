package com.advancesd.group17.auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.advancesd.group17.auth.dao.AuthDao;
import com.advancesd.group17.auth.dao.AuthDaoImpl;
import com.advancesd.group17.auth.services.SignupServiceImpl;
import com.advancesd.group17.users.model.User;

@Controller
public class SignupController {

	@GetMapping("/signup")
	public String signup(Model model)
	{
		return "signup";
	}
	
	@PostMapping("/signup")
	public String submitsignup(@RequestParam("confirmpassowrd") String passwordConfirm, User user, Model model)
	{
		AuthDao dl = new AuthDaoImpl();
		SignupServiceImpl s = new SignupServiceImpl();
		
		if (user.getBannerId() == null || user.getBannerId().isEmpty() || "".equals(user.getBannerId()) ||
            user.getEmail() == null || user.getEmail().isEmpty() || "".equals(user.getEmail()) ||
            user.getFirstName() == null || user.getFirstName().isEmpty() || "".equals(user.getFirstName()) ||
            user.getLastName() == null || user.getLastName().isEmpty() || "".equals(user.getLastName()) ||
            user.getPassword() == null || user.getPassword().isEmpty() || "".equals(user.getPassword()) ||
            !user.getPassword().equals(passwordConfirm) 
           )
		{
			model.addAttribute("invalidData", true);
			return "signup";
        }
		else
		{
			if(s.IsAlreadyUser(user,dl))
			{
				model.addAttribute("alreadyuserexist",true);
				return "signup"; 
			}
			
			if(s.registeruser(user, dl))
			{
				model.addAttribute("registrationsuccess",true);
			}
			else
			{
				model.addAttribute("registrationfailed",true);
			}
			return "signup";
		}
	}
	
}

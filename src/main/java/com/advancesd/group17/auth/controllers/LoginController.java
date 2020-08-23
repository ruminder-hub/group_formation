package com.advancesd.group17.auth.controllers;

import static com.advancesd.group17.utils.Constants.REDIRECT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.advancesd.group17.auth.dao.AuthDao;
import com.advancesd.group17.auth.dao.AuthDaoImpl;
import com.advancesd.group17.auth.services.LoginServiceImpl;
import com.advancesd.group17.users.model.User;;


@Controller
public class LoginController {
	
	private Logger log = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/login")
	public String login(Model model)
	{
		log.info("Entered LoginController.login()");
		return "login";
	}
	
	@PostMapping("/login")
	public ModelAndView submitLogin(User usr, Model model)
	{
		log.info("Entered LoginController.submitLogin()");
		AuthDao dl = new AuthDaoImpl();
		LoginServiceImpl loginServiceImpl = new LoginServiceImpl();
		//s.userautentication(usr,dl, model);
				
		
		return loginServiceImpl.userAuthentication(usr,dl, model);
		
	}
	
}

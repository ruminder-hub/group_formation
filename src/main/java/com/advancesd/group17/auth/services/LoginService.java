package com.advancesd.group17.auth.services;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.advancesd.group17.auth.dao.AuthDao;
import com.advancesd.group17.users.model.User;

public interface LoginService {

	public ModelAndView userAuthentication(User usr, AuthDao dl, Model model);
	
}

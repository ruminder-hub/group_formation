package com.advancesd.group17.auth.services;

import static com.advancesd.group17.utils.Constants.ADMIN_HOME_PAGE;
import static com.advancesd.group17.utils.Constants.REDIRECT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.advancesd.group17.auth.dao.AuthDao;
import com.advancesd.group17.users.model.User;

public class LoginServiceImpl implements LoginService {

	private Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

	public boolean Isadmin(String bannerid, AuthDao dl) {
		if ("admin".equals(bannerid))
			return true;
		else
			return false;
	}

	@Override
	public ModelAndView userAuthentication(User usr, AuthDao dl, Model model) {
		log.info("Entered LoginServiceImpl.userAuthentication");
		if (usr.getBannerId() == null || usr.getBannerId().isEmpty() || "".equals(usr.getBannerId())
				|| usr.getPassword() == null || usr.getPassword().isEmpty() || "".equals(usr.getPassword())) {
			model.addAttribute("invalidData", true);
			return new ModelAndView("login");
		}
		Boolean authResult = dl.loginAuthentication(usr);
		
		if (authResult == Boolean.FALSE) {
			model.addAttribute("invalidcred", true);
			return new ModelAndView("login");
		}
		
		if(Isadmin(usr.getBannerId(), dl))
		{
			log.info("Admin User redirecting to Admin page" );
			return new ModelAndView(REDIRECT + ADMIN_HOME_PAGE);
		}
		else
		{
			return new ModelAndView("redirect:/course/home?bannerid="+usr.getBannerId());
		}
	}
}

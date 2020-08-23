package com.advancesd.group17.auth.services;

import com.advancesd.group17.auth.dao.AuthDao;
import com.advancesd.group17.email.MailConfig;
import com.advancesd.group17.users.model.User;

public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	@Override
	public boolean checkuserbybanner(User u, AuthDao ud) 
	{	
		return ud.isAlreadyUser(u);
	}
	
	@Override
	public boolean mailsent(String banner, AuthDao ud) 
	{	
		User u = ud.getUserCred(banner);
		
		try
		{
			MailConfig mc = new MailConfig();
			
			mc.sendmail(u.getEmail(), banner, u.getPassword());
        	return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;	
		}
	}
}

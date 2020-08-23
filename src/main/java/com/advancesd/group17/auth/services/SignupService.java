package com.advancesd.group17.auth.services;

import com.advancesd.group17.auth.dao.AuthDao;
import com.advancesd.group17.users.model.User;

public interface SignupService {

	public boolean IsAlreadyUser(User user, AuthDao dl);
	
	public boolean registeruser(User user, AuthDao dl);
	
}

package com.advancesd.group17.auth.services;

import com.advancesd.group17.auth.dao.AuthDao;
import com.advancesd.group17.users.model.User;

public interface ForgotPasswordService {

	public boolean checkuserbybanner(User u, AuthDao ud);
	public boolean mailsent(String banner, AuthDao ud);
}

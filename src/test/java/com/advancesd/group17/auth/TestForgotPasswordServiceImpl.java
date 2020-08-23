package com.advancesd.group17.auth;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.advancesd.group17.auth.dao.AuthDao;
import com.advancesd.group17.auth.services.ForgotPasswordService;
import com.advancesd.group17.auth.services.ForgotPasswordServiceImpl;
import com.advancesd.group17.users.model.User;

class TestForgotPasswordServiceImpl {

	AuthDao ud = new MockUserDaoImpl();
	ForgotPasswordService fps = new ForgotPasswordServiceImpl();
	
	@Test
	public void Testcheckuserbybanner() 
	{	
		User u = new User();
		u.setBannerId("admin");
		assertTrue(fps.checkuserbybanner(u, ud));
		
		u.setBannerId("B00000000");
		assertFalse(fps.checkuserbybanner(u, ud));
	}

}

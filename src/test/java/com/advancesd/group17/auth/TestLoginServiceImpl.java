package com.advancesd.group17.auth;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.advancesd.group17.auth.dao.AuthDao;
import com.advancesd.group17.auth.services.LoginServiceImpl;
import com.advancesd.group17.users.model.User;

class TestLoginServiceImpl {

	AuthDao ud = new MockUserDaoImpl();
	LoginServiceImpl ls = new LoginServiceImpl();
	
	@Test
	public void Testuserauthentication()
	{
		User u = new User();
		u.setBannerId("admin");
		u.setPassword("admin");
		
		u.setBannerId("poojan");
		u.setPassword("admin");
		assertFalse(ud.loginAuthentication(u));
	}
	
	@Test
	public void TestIsadmin()
	{
		assertTrue(ls.Isadmin("admin", ud));
		assertFalse(ls.Isadmin("poojan", ud));
	}
}

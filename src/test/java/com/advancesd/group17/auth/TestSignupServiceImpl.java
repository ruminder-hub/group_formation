package com.advancesd.group17.auth;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.advancesd.group17.auth.dao.AuthDao;
import com.advancesd.group17.users.model.User;

class TestSignupServiceImpl {

	User u = new User();
	AuthDao ud = new MockUserDaoImpl();
	
	@Test
	public void TestIsAlreadyUser()
	{
		u.setBannerId("admin");
		assertTrue(ud.isAlreadyUser(u));
		
		u.setBannerId("abcd");
		assertFalse(ud.isAlreadyUser(u));
	}
	
	@Test
	public void Testregisteruser()
	{
		u.setBannerId("B00000000");
		u.setEmail("abc.xyz@mail.com");
		u.setFirstName("abc");
		u.setLastName("xyz");
		u.setPassword("abcxyz");
		assertTrue(ud.registerUser(u));
		
		u.setBannerId("B00043050");
		u.setEmail("axyz@mail.com");
		u.setFirstName("ac");
		u.setLastName("xz");
		u.setPassword("ayz");
		assertFalse(ud.registerUser(u));
		
	}

}

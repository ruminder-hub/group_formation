package com.advancesd.group17.users.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.advancesd.group17.users.model.User;

class TestUser {
	
	User u = new User();
	
	@Test
	public void TestgetFname() 
	{	
		u.setFirstName("Downey");
		
		assertEquals("Downey", u.getFirstName());
	}

	@Test
	public void TestsetFname() 
	{
		u.setFirstName("Morgan");
		
		assertEquals("Morgan", u.getFirstName());
	}

	@Test
	public void TestgetLname() 
	{
		u.setLastName("Robert");
		
		assertEquals("Robert", u.getLastName());
	}

	@Test
	public void TestsetLname() 
	{
		u.setLastName("Katie");
		
		assertEquals("Katie", u.getLastName());
	}

	@Test
	public void TestgetEmaill() 
	{
		u.setEmail("abc@xyz.com");
		
		assertEquals("abc@xyz.com", u.getEmail());
	}
	
	@Test
	public void TestsetEmaill() 
	{
		u.setEmail("abcd@wxyz.com");
		
		assertEquals("abcd@wxyz.com", u.getEmail());
	}
	
	@Test
	public void TestgetBannerID()
	{
		u.setBannerId("1234");
		
		assertEquals("1234",u.getBannerId());
	}
	
	@Test
	public void TestsetBannerID()
	{
		u.setBannerId("1234");
		
		assertEquals("1234", u.getBannerId());
	}
	
	@Test
	public void TestgetPassword()
	{
		u.setPassword("aaaa");
		
		assertEquals("aaaa",u.getPassword());
	}
	
	@Test
	public void TestsetPassword()
	{
		u.setPassword("aaaa");
		
		assertEquals("aaaa",u.getPassword());
	}

}

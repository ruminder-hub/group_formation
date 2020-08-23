package com.advancesd.group17.users.mock;

import com.advancesd.group17.users.dao.UserDao;

public class MockUserDaoImpl implements UserDao{

	@Override
	public String getuserrolebybannerid(String bannerid) {
		
		if("B00836202".equals(bannerid))
		{
			return "TA";
		}
		
		if("B00000000".equals(bannerid))
		{
			return "Guest";
		}
		
		return null;
	}

}

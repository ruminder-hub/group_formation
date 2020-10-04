package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.*;

public class UserDBMock implements IUserPersistence
{
	public void loadUserByID(long id, IUser user)
	{
		user.setID(id);
		user.setBannerID("B00000000");
		user.setPassword("Pass@123");
		user.setFirstName("Rob");
		user.setLastName("Hawkey");
		user.setEmail("rhawkey123@dal.ca");
	}

	public void loadUserByBannerID(String bannerID, IUser user)
	{
		user.setID(1);
		user.setBannerID(bannerID);
		user.setPassword("Pass@123");
		user.setFirstName("Rob");
		user.setLastName("Hawkey");
		user.setEmail("rhawkey123@dal.ca");
	}
	
	public boolean createUser(IUser user)
	{
		user.setID(0);
		user.setBannerID("B00000000");
		user.setPassword("Pass@123");
		user.setFirstName("Rob");
		user.setLastName("Hawkey");
		user.setEmail("rhawkey123@dal.ca");
		return true;
	}

	@Override
	public boolean updatePassword(String bannerID, String password)
	{
		if(bannerID.equals("B00123456") && password.equals("aaaabbbb"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean isAlreadyUser(String bannerID)
	{
		boolean isExistingUser;
		if(bannerID.equals("B-444444"))
		{
			isExistingUser = true;
		}
		else{
			isExistingUser = false;
		}
		return isExistingUser;
	}
}

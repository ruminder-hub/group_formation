package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;

public class CurrentUserMock 
{
	public IUser getCurrentAuthenticatedUser()
	{
		IUserPersistence userDB = AccessControlAbstractFactoryMock.instance().getUserDBMock();
		String bannerID = "B00000000";
		IUser user = AccessControlAbstractFactoryMock.instance().getUser();
		userDB.loadUserByBannerID(bannerID, user);
		return user;
	}
}

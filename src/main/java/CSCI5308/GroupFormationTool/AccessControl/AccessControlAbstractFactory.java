package CSCI5308.GroupFormationTool.AccessControl;

public class AccessControlAbstractFactory implements IAccessControlAbstractFactory
{
	private static AccessControlAbstractFactory uniqueInstance = null;
	private IUserPersistence userDB;
	private IUserNotifications userNotifications;
	private IUpdatePassword updatePassword;

	private AccessControlAbstractFactory()
	{
		userDB = new UserDB();
		userNotifications = new UserNotification();
		updatePassword = new UpdatePassword();
	}

	public static AccessControlAbstractFactory instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new AccessControlAbstractFactory();
		}
		return uniqueInstance;
	}
	
	public IUserPersistence getUserDB()
	{
		return userDB;
	}

	public IUserNotifications getUserNotifications()
	{
		return userNotifications;
	}

	public IUpdatePassword getUpdatePassword()
	{
		return updatePassword;
	}

	public IUser getUser()
	{
		return new User();
	}
}

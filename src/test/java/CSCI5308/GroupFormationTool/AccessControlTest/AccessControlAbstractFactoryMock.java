package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicyValidation;

public class AccessControlAbstractFactoryMock
{
	private static AccessControlAbstractFactoryMock uniqueInstance = null;
	private IUserPersistence userDB;
	private IUserNotifications userNotifications;
	private IUpdatePassword updatePassword;

	private AccessControlAbstractFactoryMock()
	{
		userDB = new UserDBMock();
		userNotifications = new UserNotification();
		updatePassword = new UpdatePasswordMock();
	}

	public static AccessControlAbstractFactoryMock instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new AccessControlAbstractFactoryMock();
		}
		return uniqueInstance;
	}
	
	public IUserPersistence getUserDBMock()
	{
		return userDB;
	}

	public IUserNotifications getUserNotifications()
	{
		return userNotifications;
	}

	public IUpdatePassword getUpdatePasswordMock()
	{
		return updatePassword;
	}

	public IUser getUser()
	{
		return new User();
	}

	public IPasswordPolicyValidation getMaximumLengthPolicyMock()
	{
		return new MaximumLengthPolicyMock();
	}

	public IPasswordPolicyValidation getMinimumLengthPolicyMock()
	{
		return new MinimumLengthPolicyMock();
	}

	public IPasswordPolicyValidation getMinimumLowercasePolicyMock()
	{
		return new MinimumLowercasePolicyMock();
	}

	public IPasswordPolicyValidation getMinimumSymbolPolicyMock()
	{
		return new MinimumSymbolOrSpecialCharacterPolicyMock();
	}

	public IPasswordPolicyValidation getMinimumUppercasePolicyMock()
	{
		return new MinimumUppercasePolicyMock();
	}

	public IPasswordPolicyValidation getCharactersNotAllowedPolicyMock()
	{
		return new CharactersNotAllowedPolicyMock();
	}

	public IPasswordPolicyValidation getPasswordHistoryMock(IUser user)
	{
		return new PasswordHistoryPolicyMock(user);
	}
}

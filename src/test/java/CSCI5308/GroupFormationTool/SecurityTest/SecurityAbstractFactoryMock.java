package CSCI5308.GroupFormationTool.SecurityTest;

import CSCI5308.GroupFormationTool.Security.BCryptPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public class SecurityAbstractFactoryMock
{
	private static SecurityAbstractFactoryMock uniqueInstance = null;
	private IPasswordEncryption passwordEncryption;

	private SecurityAbstractFactoryMock()
	{
		passwordEncryption = new PasswordEncryptionMock();
	}

	public static SecurityAbstractFactoryMock instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new SecurityAbstractFactoryMock();
		}
		return uniqueInstance;
	}

	public IPasswordEncryption getPasswordEncryptionMock()
	{
		return passwordEncryption;
	}
}

package CSCI5308.GroupFormationTool.PasswordPolicy;

import CSCI5308.GroupFormationTool.AccessControl.IUser;

public class PasswordPolicyAbstractFactory implements IPasswordPolicyAbstractFactory
{
	private static PasswordPolicyAbstractFactory uniqueInstance = null;
	private IActivePasswordPolicyPersistence activePasswordPolicyDB;
	private IPasswordPolicyContextListBuilder activePasswordPolicyListBuilder;

	private PasswordPolicyAbstractFactory()
	{
		activePasswordPolicyDB = new ActivePasswordPolicyDB();
		activePasswordPolicyListBuilder = new PasswordPolicyContextListBuilder();
	}

	public static PasswordPolicyAbstractFactory instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new PasswordPolicyAbstractFactory();
		}
		return uniqueInstance;
	}

	public IPasswordPolicyContextListBuilder getActivePasswordPolicyListBuilder()
	{
		return activePasswordPolicyListBuilder;
	}

	public IActivePasswordPolicyPersistence getActivePasswordPolicyDB()
	{
		return activePasswordPolicyDB;
	}

	public IPasswordPolicyValidation getMaximumLengthPolicy()
	{
		return new MaximumLengthPolicy();
	}

	public IPasswordPolicyValidation getMinimumLengthPolicy()
	{
		return new MinimumLengthPolicy();
	}

	public IPasswordPolicyValidation getMinimumLowercasePolicy()
	{
		return new MinimumLowercasePolicy();
	}

	public IPasswordPolicyValidation getMinimumSymbolPolicy()
	{
		return new MinimumSymbolOrSpecialCharacterPolicy();
	}

	public IPasswordPolicyValidation getMinimumUppercasePolicy()
	{
		return new MinimumUppercasePolicy();
	}

	public IPasswordPolicyValidation getCharactersNotAllowedPolicy()
	{
		return new CharactersNotAllowedPolicy();
	}

	public IPasswordPolicyValidation getPasswordHistory(IUser user)
	{
		return new PasswordHistoryPolicy(user);
	}
}

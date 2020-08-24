package CSCI5308.GroupFormationTool.PasswordPolicy;

import CSCI5308.GroupFormationTool.AccessControl.IUser;

public interface IPasswordPolicyAbstractFactory
{
    public IPasswordPolicyContextListBuilder getActivePasswordPolicyListBuilder();

    public IActivePasswordPolicyPersistence getActivePasswordPolicyDB();

    public IPasswordPolicyValidation getMaximumLengthPolicy();

    public IPasswordPolicyValidation getMinimumLengthPolicy();

    public IPasswordPolicyValidation getMinimumLowercasePolicy();

    public IPasswordPolicyValidation getMinimumSymbolPolicy();

    public IPasswordPolicyValidation getMinimumUppercasePolicy();

    public IPasswordPolicyValidation getCharactersNotAllowedPolicy();

    public IPasswordPolicyValidation getPasswordHistory(IUser user);
}

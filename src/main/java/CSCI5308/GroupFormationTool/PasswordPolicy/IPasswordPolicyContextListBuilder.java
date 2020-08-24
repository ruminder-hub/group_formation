package CSCI5308.GroupFormationTool.PasswordPolicy;

import CSCI5308.GroupFormationTool.AccessControl.IUser;

import java.util.List;

public interface IPasswordPolicyContextListBuilder
{
    public List<Context> createAllPasswordPolicyList(IUser user);
}

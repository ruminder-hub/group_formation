package CSCI5308.GroupFormationTool.AccessControl;

import java.util.List;

public interface IUpdatePassword
{
    public boolean updatePassword(List<String> failedPasswordValidationList, IUser user);
}

package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.*;

import java.util.List;

public class UpdatePasswordMock implements IUpdatePassword {
    @Override
    public boolean updatePassword(List<String> failedPasswordValidationList, IUser user)
    {
        if (failedPasswordValidationList.size() == 0)
        {
            IUserPersistence userDB = AccessControlAbstractFactoryMock.instance().getUserDBMock();
            if(userDB.updatePassword("B00123456","aaaabbbb"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
}

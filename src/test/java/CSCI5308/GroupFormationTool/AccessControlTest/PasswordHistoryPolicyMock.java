package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicyValidation;
import CSCI5308.GroupFormationTool.AccessControl.User;

public class PasswordHistoryPolicyMock implements IPasswordPolicyValidation
{
    private static final String POLICY = "old x passwords not allowed";
    private String criteria = null;
    private String validatorCriteria = null;
    private String bannerID = null;

    public PasswordHistoryPolicyMock(IUser user)
    {
        this.bannerID = user.getBannerID();
    }

    @Override
    public boolean isPasswordValid(String password)
    {
        if(this.bannerID == "B00123456")
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String getValidationCriteria()
    {
        return "Last "+this.criteria+" passwords cannot be used";
    }
}

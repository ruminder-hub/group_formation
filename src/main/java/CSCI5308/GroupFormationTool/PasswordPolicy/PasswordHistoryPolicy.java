package CSCI5308.GroupFormationTool.PasswordPolicy;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.SecurityAbstractFactory;

import java.util.HashMap;
import java.util.List;

public class PasswordHistoryPolicy implements IPasswordPolicyValidation
{
    private static final String POLICY = "old x passwords not allowed";
    private String criteria = null;
    private String validatorCriteria = null;
    private String bannerID = null;

    public PasswordHistoryPolicy(IUser user)
    {
        this.bannerID = user.getBannerID();
    }

    @Override
    public boolean isPasswordValid(String password)
    {
        IActivePasswordPolicyPersistence activePasswordPolicyDB = PasswordPolicyAbstractFactory.instance().getActivePasswordPolicyDB();
        HashMap<String, String> activePasswordPolicyList = activePasswordPolicyDB.getActivePasswordPolicy();

        for (String policy : activePasswordPolicyList.keySet())
        {
            if (policy.equals(POLICY))
            {
                this.criteria = activePasswordPolicyList.get(policy);
                this.validatorCriteria = POLICY;

                List<String> passwordHistoryList = activePasswordPolicyDB.getPasswords(this.bannerID, Integer.parseInt(criteria));
                IPasswordEncryption passwordEncryption = SecurityAbstractFactory.instance().getPasswordEncryption();

                for (int i = 0; i < passwordHistoryList.size(); i++)
                {
                    if (passwordEncryption.matches(password, passwordHistoryList.get(i)))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String getValidationCriteria()
    {
        return "Last "+this.criteria+" passwords cannot be used";
    }

}

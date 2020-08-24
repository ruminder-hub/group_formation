package CSCI5308.GroupFormationTool.PasswordPolicy;

import java.util.HashMap;

public class MinimumLowercasePolicy implements IPasswordPolicyValidation
{
    private static final String POLICY = "min no of lowercase";
    private String criteria = null;
    private String validatorCriteria = null;

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

                Integer lowercase = 0;
                char[] charArray = password.toCharArray();

                for (int i = 0; i < charArray.length; i++)
                {
                    if (Character.isLowerCase(charArray[i]))
                    {
                        lowercase++;
                    }
                }

                if (lowercase >= Integer.parseInt(this.criteria))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String getValidationCriteria()
    {
        return this.validatorCriteria+" "+this.criteria;
    }
}

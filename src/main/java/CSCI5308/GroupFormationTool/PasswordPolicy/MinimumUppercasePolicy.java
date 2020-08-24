package CSCI5308.GroupFormationTool.PasswordPolicy;

import java.util.HashMap;

public class MinimumUppercasePolicy implements IPasswordPolicyValidation
{
    private static final String POLICY = "min no of uppercase";
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

                Integer uppercase = 0;
                char[] charArray = password.toCharArray();

                for (int i = 0; i < charArray.length; i++)
                {
                    if (Character.isUpperCase(charArray[i]))
                    {
                        uppercase++;
                    }
                }

                if (uppercase >= Integer.parseInt(this.criteria))
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

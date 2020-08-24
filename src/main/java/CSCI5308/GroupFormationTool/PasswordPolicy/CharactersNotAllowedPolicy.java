package CSCI5308.GroupFormationTool.PasswordPolicy;

import java.util.HashMap;

public class CharactersNotAllowedPolicy implements IPasswordPolicyValidation
{
    private static final String POLICY = "characters not allowed";
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

                char[] passwordCharArray = password.toCharArray();
                char[] criteriaCharArray = this.criteria.toCharArray();

                for (int i = 0; i < passwordCharArray.length; i++)
                {
                    for (int j = 0; j < criteriaCharArray.length; j++)
                    {
                        if (passwordCharArray[i] == criteriaCharArray[j])
                        {
                            return false;
                        }
                    }
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

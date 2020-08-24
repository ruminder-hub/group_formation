package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicyValidation;

public class MinimumLowercasePolicyMock implements IPasswordPolicyValidation
{
    private static final String POLICY = "min no of lowercase";
    private static final String CRITERIA = "1";
    private String criteria = null;
    private String validatorCriteria = null;

    @Override
    public boolean isPasswordValid(String password)
    {
        this.criteria = CRITERIA;
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

    @Override
    public String getValidationCriteria()
    {
        return this.validatorCriteria+" "+this.criteria;
    }
}

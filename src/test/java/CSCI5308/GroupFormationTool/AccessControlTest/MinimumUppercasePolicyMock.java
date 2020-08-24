package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicyValidation;

public class MinimumUppercasePolicyMock implements IPasswordPolicyValidation
{
    private static final String POLICY = "min no of uppercase";
    private static final String CRITERIA = "1";
    private String criteria = null;
    private String validatorCriteria = null;

    @Override
    public boolean isPasswordValid(String password)
    {
        this.criteria = CRITERIA;
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

    @Override
    public String getValidationCriteria()
    {
        return this.validatorCriteria+" "+this.criteria;
    }
}

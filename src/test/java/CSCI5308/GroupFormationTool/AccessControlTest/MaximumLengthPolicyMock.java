package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicyValidation;

public class MaximumLengthPolicyMock implements IPasswordPolicyValidation
{
    private static final String POLICY = "max length";
    private static final String CRITERIA = "10";
    private String criteria = null;
    private String validatorCriteria = null;

    @Override
    public boolean isPasswordValid(String password)
    {
        this.criteria = CRITERIA;
        this.validatorCriteria = POLICY;

        if(password.length() <= Integer.parseInt(this.criteria))
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

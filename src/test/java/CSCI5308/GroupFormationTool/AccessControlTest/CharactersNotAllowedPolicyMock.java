package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicyValidation;

public class CharactersNotAllowedPolicyMock implements IPasswordPolicyValidation
{
    private static final String POLICY = "characters not allowed";
    private static final String CRITERIA = "&()$";
    private String criteria = null;
    private String validatorCriteria = null;

    @Override
    public boolean isPasswordValid(String password)
    {
        this.criteria = CRITERIA;
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
        return true;
    }

    @Override
    public String getValidationCriteria()
    {
        return this.validatorCriteria+" "+this.criteria;
    }
}

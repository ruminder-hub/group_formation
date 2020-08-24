package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicyValidation;

public class MinimumSymbolOrSpecialCharacterPolicyMock implements IPasswordPolicyValidation
{
    private static final String POLICY = "min no of special character";
    private static final String CRITERIA = "1";
    private String criteria = null;
    private String validatorCriteria = null;

    @Override
    public boolean isPasswordValid(String password)
    {
        this.criteria = CRITERIA;
        this.validatorCriteria = POLICY;

        String passwordWithoutSpecialCharacters = password.replaceAll("[^a-zA-Z0-9]", "");
        Integer numberOfSpecialCharacters = password.length() - passwordWithoutSpecialCharacters.length();

        if (numberOfSpecialCharacters >= Integer.parseInt(this.criteria))
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

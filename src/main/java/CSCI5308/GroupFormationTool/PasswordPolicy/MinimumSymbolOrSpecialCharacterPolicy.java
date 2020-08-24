package CSCI5308.GroupFormationTool.PasswordPolicy;
import java.util.HashMap;

public class MinimumSymbolOrSpecialCharacterPolicy implements IPasswordPolicyValidation
{
    private static final String POLICY = "min no of special character";
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
        }
        return true;
    }

    @Override
    public String getValidationCriteria()
    {
        return this.validatorCriteria+" "+this.criteria;
    }
}

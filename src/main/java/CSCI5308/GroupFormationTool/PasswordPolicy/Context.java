package CSCI5308.GroupFormationTool.PasswordPolicy;

public class Context
{
    private IPasswordPolicyValidation passwordPolicyValidation;

    public void setStrategy(IPasswordPolicyValidation passwordPolicy)
    {
        this.passwordPolicyValidation = passwordPolicy;
    }

    public boolean executeStrategy(String password)
    {
        return passwordPolicyValidation.isPasswordValid(password);
    }

    public IPasswordPolicyValidation getStrategy()
    {
        return this.passwordPolicyValidation;
    }
}

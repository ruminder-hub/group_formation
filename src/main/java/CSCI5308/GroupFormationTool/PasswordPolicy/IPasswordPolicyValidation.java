package CSCI5308.GroupFormationTool.PasswordPolicy;

public interface IPasswordPolicyValidation
{
    public boolean isPasswordValid(String password);

    public String getValidationCriteria();
}

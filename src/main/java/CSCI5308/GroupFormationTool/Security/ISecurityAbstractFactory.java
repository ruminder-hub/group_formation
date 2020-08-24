package CSCI5308.GroupFormationTool.Security;

public interface ISecurityAbstractFactory
{
    public IPasswordEncryption getPasswordEncryption();
}

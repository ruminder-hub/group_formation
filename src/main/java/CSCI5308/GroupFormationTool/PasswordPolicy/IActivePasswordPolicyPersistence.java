package CSCI5308.GroupFormationTool.PasswordPolicy;

import java.util.HashMap;
import java.util.List;

public interface IActivePasswordPolicyPersistence
{
    public HashMap<String, String> getActivePasswordPolicy();

    public List<String> getPasswords(String bannerID, Integer criteria);
}

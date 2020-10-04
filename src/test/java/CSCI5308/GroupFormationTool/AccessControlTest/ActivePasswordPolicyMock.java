package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.PasswordPolicy.IActivePasswordPolicyPersistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivePasswordPolicyMock implements IActivePasswordPolicyPersistence
{
    @Override
    public HashMap<String, String> getActivePasswordPolicy()
    {
        HashMap<String,String> activePasswordPolicyList = new HashMap<>();

        activePasswordPolicyList.put("min length","8");
        activePasswordPolicyList.put("max length","10");
        activePasswordPolicyList.put("min no of uppercase","2");

        return activePasswordPolicyList;
    }

    @Override
    public List<String> getPasswords(String bannerID, Integer criteria)
    {
        List<String> passwordList = new ArrayList<>();

        if(criteria == 4)
        {
            passwordList.add("abcd");
            passwordList.add("abcdef");
            passwordList.add("abcdefgh");
            passwordList.add("abcdefghij");
        }
        if(criteria == 3)
        {
            passwordList.add("abcd");
            passwordList.add("abcdef");
            passwordList.add("abcdefgh");
        }
        return passwordList;
    }
}

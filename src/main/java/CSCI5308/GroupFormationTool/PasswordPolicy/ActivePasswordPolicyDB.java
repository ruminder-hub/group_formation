package CSCI5308.GroupFormationTool.PasswordPolicy;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivePasswordPolicyDB implements IActivePasswordPolicyPersistence
{
	private Logger log = LoggerFactory.getLogger(ActivePasswordPolicyDB.class);
	
    @Override
    public HashMap<String, String> getActivePasswordPolicy()
    {
    	log.debug("Getting ActivePassword Policy");
        HashMap<String, String> policyCriteriaMap = new HashMap<String, String>();
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spLoadActivePasswordPolicy()");
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    String policy = results.getString(1);
                    String criteria = results.getString(2);
                    policyCriteriaMap.put(policy,criteria);
                }
            }
        }
        catch (SQLException e)
        {
        	log.error("Error occured in getting active passwordpPolicy: " + e.getMessage());
			e.printStackTrace();
            return policyCriteriaMap;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return policyCriteriaMap;
    }

    @Override
    public List<String> getPasswords(String bannerID, Integer criteria)
    {
    	log.debug("Getting Password for user with bannerId: " + bannerID);
        List<String> passwordList = new ArrayList<>();
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spLoadPasswords(?,?)");
            proc.setParameter(1, bannerID);
            proc.setParameter(2,criteria);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    String password = results.getString(1);
                    passwordList.add(password);
                }
            }
        }
        catch (SQLException e)
        {
        	log.error("Error occured in getting passwords for user with bannerId: " +  bannerID + " due to: " + e.getMessage());
			e.printStackTrace();
            return passwordList;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return passwordList;
    }
}

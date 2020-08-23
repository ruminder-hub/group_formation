package com.advancesd.group17.users.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancesd.group17.auth.dao.AuthDaoImpl;
import com.advancesd.group17.database.DatabaseConfig;

public class UserDaoImpl implements UserDao{
	
	public static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public String getuserrolebybannerid(String bannerid) {

		String rolename = null;

		try
		(
			Connection conn = createDbConnection();
		    CallableStatement st = conn.prepareCall("{CALL getuserrolebybannerid(?)}");
		)
		{
			st.setString(1, bannerid);
	    	ResultSet rs = st.executeQuery();
	    	
	    	if(rs.next())
	    	{
		    	rolename = rs.getString("role_name");
			    st.close();
		    	return rolename;
			}
		}
		catch (Exception ex) {
            ex.printStackTrace();
        }
        
		return rolename;
	}
	
	Connection createDbConnection() {
		Connection connection = null;
		try {
			connection = DatabaseConfig.getInstance().getConnection();
			if (connection == null) {
				log.info("Connection null");
			} else {
				log.info("Connection established");
			}
		} catch (Exception e) {
			log.error("Error occured: " + e);
			e.printStackTrace();
		}
		return connection;
		
	}

}

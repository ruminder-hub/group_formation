package com.advancesd.group17.auth.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancesd.group17.course.dao.CourseDaoImpl;
import com.advancesd.group17.database.DatabaseConfig;
import com.advancesd.group17.users.model.User;


//Class to interact with database for authenticating user
public class AuthDaoImpl implements AuthDao {

	public static Logger log = LoggerFactory.getLogger(AuthDaoImpl.class);

	@Override
	public boolean loginAuthentication(User usr) {
		log.info("Entered AuthDaoImpl.loginAuthentication");
		try (Connection conn = createDbConnection();
				CallableStatement st = conn.prepareCall("{CALL userauthentication(?,?)}");) {
			st.setString(1, usr.getBannerId());
			st.setString(2, usr.getPassword());

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				st.close();
				return true;
			} else {
				st.close();
				return false;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isAlreadyUser(User u) {
		log.info("Entered AuthDaoImpl.isalreadyuser");
		try (Connection conn = createDbConnection();
				CallableStatement st = conn.prepareCall("{CALL isalreadyuser(?)}");) {
			st.setString(1, u.getBannerId());

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				st.close();
				return true;
			} else {
				st.close();
				return false;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean registerUser(User u) {
		log.info("Entered AuthDaoImpl.registerUser");
		try (Connection conn = createDbConnection();
				CallableStatement st = conn.prepareCall("{CALL createuser(?,?,?,?,?)}");) {
			st.setString(1, u.getBannerId());
			st.setString(2, u.getFirstName());
			st.setString(3, u.getLastName());
			st.setString(4, u.getEmail());
			st.setString(5, u.getPassword());

			st.executeQuery();

			st.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean checkUserByEmail(User u) {
		log.info("Entered AuthDaoImpl.checkUserByEmail");
		try (Connection conn = createDbConnection();
				CallableStatement st = conn.prepareCall("{CALL checkuserbyemail(?)}");) {
			st.setString(1, u.getEmail());

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				st.close();
				return true;
			} else {
				st.close();
				return false;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public User getUserCred(String banner) {
		log.info("Entered AuthDaoImpl.getUserCred");
		User u = new User();

		try (Connection conn = createDbConnection();
				CallableStatement st = conn.prepareCall("{CALL getusercred(?)}");) {
			st.setString(1, banner);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				u.setEmail(rs.getString("user_email"));
				u.setPassword(rs.getString("user_password"));
				st.close();
			}
			st.close();
			return u;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return u;
		}
	}
	
	
	//Creating DB connection
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
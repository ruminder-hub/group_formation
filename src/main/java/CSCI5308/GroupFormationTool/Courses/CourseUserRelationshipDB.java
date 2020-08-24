package CSCI5308.GroupFormationTool.Courses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.AccessControlAbstractFactory;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class CourseUserRelationshipDB implements ICourseUserRelationshipPersistence
{
	private Logger log = LoggerFactory.getLogger(CourseUserRelationshipDB.class);

	public List<IUser> findAllUsersWithoutCourseRole(Role role, long courseID)
	{
		log.info("Finding all Users without Course Role in database with courseId: " + courseID);
		List<IUser> users = new ArrayList<>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindUsersWithoutCourseRole(?, ?)");
			proc.setParameter(1, role.toString());
			proc.setParameter(2,  courseID);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long userID = results.getLong(1);
					String bannerID = results.getString(2);
					String firstName = results.getString(3);
					String lastName = results.getString(4);
					IUser u = AccessControlAbstractFactory.instance().getUser();
					u.setID(userID);
					u.setBannerID(bannerID);
					u.setFirstName(firstName);
					u.setLastName(lastName);
					users.add(u);
				}
			}
		}
		catch (SQLException e)
		{
			log.error("Error occured while finding all users without course role: " + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return users;
	}

	public List<IUser> findAllUsersWithCourseRole(Role role, long courseID)
	{
		log.info("Finding all Users with Course Role in database with courseId: " + courseID);
		List<IUser> users = new ArrayList<>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindUsersWithCourseRole(?, ?)");
			proc.setParameter(1, role.toString());
			proc.setParameter(2,  courseID);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long userID = results.getLong(1);
					IUser u = AccessControlAbstractFactory.instance().getUser();
					u.setID(userID);
					users.add(u);
				}
			}
		}
		catch (SQLException e)
		{
			log.error("Error occured while finding all users with course role: " + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return users;
	}
	
	public boolean enrollUser(ICourse course, IUser user, Role role)
	{
		log.info("Enrolling user: " + user.getID() + " to course with courseId: " + course.getId() + " and role " + role.toString());
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spEnrollUser(?, ?, ?)");
			proc.setParameter(1, course.getId());
			proc.setParameter(2, user.getID());
			proc.setParameter(3, role.toString());
			proc.execute();
		}
		catch (SQLException e)
		{
			log.error("Error occured in enrollUser: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return true;
	}

	public List<Role> loadUserRolesForCourse(ICourse course, IUser user)
	{
		log.info("Loading Roles for  user: " + user.getID() + " in course with courseId: " + course.getId());
		List<Role> roles = new ArrayList<Role>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadUserRolesForCourse(?, ?)");
			proc.setParameter(1, course.getId());
			proc.setParameter(2, user.getID());
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					Role role = Role.valueOf(results.getString(1).toUpperCase());
					roles.add(role);
				}
			}
		}
		catch (SQLException e)
		{
			log.error("Error occured in while loading User roles for Course: " + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return roles;
	}
}

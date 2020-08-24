package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.AccessControl.*;

public class CourseUserRelationship implements ICourseUserRelationship
{
    private Logger log = LoggerFactory.getLogger(CourseUserRelationship.class);
    
	public boolean userHasRoleInCourse(IUser user, Role role, ICourse course)
	{
		if (null == user || (user.isValidUser() == false))
		{
			return false;
		}
		if (null == role)
		{
			return false;
		}
		if (null == course)
		{
			return false;
		}
		log.info("Checking user role for user " + user.getId() + " for course: " + course.getId());

		ICourseUserRelationshipPersistence userCourseRelationshipDB = CoursesAbstractFactory.instance().getCourseUserRelationshipDB();
		List<Role> roles = userCourseRelationshipDB.loadUserRolesForCourse(course, user);

		if (null != roles && roles.contains(role))
		{
			return true;
		}
		return false;
	}

	public List<Role> loadAllRoluesForUserInCourse(IUser user, ICourse course)
	{
		ICourseUserRelationshipPersistence userCourseRelationshipDB = CoursesAbstractFactory.instance().getCourseUserRelationshipDB();
		List<Role> roles = userCourseRelationshipDB.loadUserRolesForCourse(course, user);
		return roles;
	}

	public boolean enrollUserInCourse(IUser user, ICourse course, Role role)
	{
		ICourseUserRelationshipPersistence userCourseRelationshipDB = CoursesAbstractFactory.instance().getCourseUserRelationshipDB();
		return userCourseRelationshipDB.enrollUser(course, user, role);
	}
}

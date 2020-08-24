package CSCI5308.GroupFormationTool.Courses;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import CSCI5308.GroupFormationTool.Security.SecurityAbstractFactory;
import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public class StudentCSVImport implements IStudentCSVImport
{
	private List<String> successResults;
	private List<String> failureResults;
	private ICourse course;
	private IUserPersistence userDB;
	private IPasswordEncryption passwordEncryption;
	private IStudentCSVParser parser;
	private List<IUser> studentList;
	private Logger log = LoggerFactory.getLogger(StudentCSVImport.class);

	public StudentCSVImport(IStudentCSVParser parser, ICourse course)
	{
		this.course = course;
		successResults = new ArrayList<String>();
		failureResults = new ArrayList<String>();
		userDB = AccessControlAbstractFactory.instance().getUserDB();
		passwordEncryption = SecurityAbstractFactory.instance().getPasswordEncryption();
		this.parser = parser;
		enrollStudentFromRecord();
	}

	public void enrollStudentFromRecord()
	{
		log.info("Enroll students from record");
		this.studentList = parser.parseCSVFile(failureResults);
		for(IUser u : this.studentList)
		{
			String bannerID = u.getBanner();
			String firstName = u.getFirstName();
			String lastName = u.getLastName();
			String email = u.getEmail();
			String userDetails = bannerID + " " + firstName + " " + lastName +" " + email;
			IUser user = AccessControlAbstractFactory.instance().getUser();
			userDB.loadUserByBannerID(bannerID, user);

			if (user.isValidUser() == false)
			{
				user.setBannerID(bannerID);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				IUserNotifications userNotifications = AccessControlAbstractFactory.instance().getUserNotifications();

				log.info("Creating user with bannerId: "+ bannerID + " firstName: " + firstName + " lastName: " + lastName + " email:" + email);
				if (user.createUser(userDB, passwordEncryption, userNotifications))
				{
					log.info("User created successfully");
					successResults.add("Created: " + userDetails);
					userDB.loadUserByBannerID(bannerID, user);
				}
				else
				{
					failureResults.add("Unable to save this user to DB: " + userDetails);
				}
			}
			
			if (course.enrollUserInCourse(Role.STUDENT, user))
			{
				log.info("User enrolled successfully as student to course with id: " + course.getId());
				successResults.add("User enrolled in course: " + userDetails);
			}
			else
			{
				log.error("Failed to add user to course " + course.getId());
				failureResults.add("Unable to enroll user in course: " + userDetails);
			}
		}
	}

	public List<IUser> getNewStudents()
	{
		List<IUser> newStudents= new ArrayList();
		IUserPersistence userDB =  AccessControlAbstractFactory.instance().getUserDB();
		this.studentList = parser.parseCSVFile(failureResults);
		for (IUser student : this.studentList)
		{
			if(userDB.isAlreadyUser(student.getBannerID())==false)
			{
				log.info("User " + student.getId() + "is a new student ");
				newStudents.add(student);
			}
		}
		return newStudents;
	}
	
	public List<String> getSuccessResults()
	{
		return successResults;
	}
	
	public List<String> getFailureResults()
	{
		return failureResults;
	}
}

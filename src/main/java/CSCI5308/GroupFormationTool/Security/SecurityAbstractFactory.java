package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Courses.CourseDB;
import CSCI5308.GroupFormationTool.Courses.CourseUserRelationshipDB;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Database.DefaultDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Database.IDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.QuestionDB;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.SurveyDB;

public class SecurityAbstractFactory implements ISecurityAbstractFactory
{
	private static SecurityAbstractFactory uniqueInstance = null;
	private IPasswordEncryption passwordEncryption;

	private SecurityAbstractFactory()
	{
		passwordEncryption = new BCryptPasswordEncryption();
	}

	public static SecurityAbstractFactory instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new SecurityAbstractFactory();
		}
		return uniqueInstance;
	}

	public IPasswordEncryption getPasswordEncryption()
	{
		return passwordEncryption;
	}
}

package CSCI5308.GroupFormationTool.Database;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Courses.CourseDB;
import CSCI5308.GroupFormationTool.Courses.CourseUserRelationshipDB;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.QuestionDB;
import CSCI5308.GroupFormationTool.Security.BCryptPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.SurveyDB;

public class DatabaseAbstractFactory implements IDatabaseAbstractFactory
{
	private static DatabaseAbstractFactory uniqueInstance = null;
	private IDatabaseConfiguration databaseConfiguration;

	private DatabaseAbstractFactory()
	{
		databaseConfiguration = new DefaultDatabaseConfiguration();
	}

	public static DatabaseAbstractFactory instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new DatabaseAbstractFactory();
		}
		return uniqueInstance;
	}

	public IDatabaseConfiguration getDatabaseConfiguration()
	{
		return databaseConfiguration;
	}
}

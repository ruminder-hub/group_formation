package CSCI5308.GroupFormationTool.Survey;

public class SurveyAbstractFactory implements ISurveyAbstractFactory
{
	private static SurveyAbstractFactory uniqueInstance = null;
	private ISurveyPersistence surveyDB;

	private SurveyAbstractFactory()
	{
		surveyDB = new SurveyDB();
	}

	public static SurveyAbstractFactory instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new SurveyAbstractFactory();
		}
		return uniqueInstance;
	}

	public ISurveyPersistence getSurveyDB()
	{
		return surveyDB;
	}

	public ISurveyQuestions getSurveyQuestions()
	{
		return new SurveyQuestions();
	}

	public IResponse getResponse()
	{
		return new Response();
	}

	public IGroup getGroup()
	{
		return new Group();
	}

	public IGroupCreationResponse getGroupCreationResponse()
	{
		return new GroupCreationResponse();
	}
}

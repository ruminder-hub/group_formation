package CSCI5308.GroupFormationTool.SurveyTest;

import CSCI5308.GroupFormationTool.Survey.*;

public class SurveyAbstractFactoryMock
{
	private static SurveyAbstractFactoryMock uniqueInstance = null;
	private ISurveyPersistence surveyDB;

	private SurveyAbstractFactoryMock()
	{
		surveyDB = new SurveyDBMock();
	}

	public static SurveyAbstractFactoryMock instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new SurveyAbstractFactoryMock();
		}
		return uniqueInstance;
	}

	public ISurveyPersistence getSurveyDBMock()
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

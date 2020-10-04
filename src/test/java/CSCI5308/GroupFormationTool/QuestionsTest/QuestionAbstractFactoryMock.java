package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Question.*;

public class QuestionAbstractFactoryMock
{
	private static QuestionAbstractFactoryMock uniqueInstance = null;
	private IQuestionPersistence questionDB;

	private QuestionAbstractFactoryMock()
	{
		questionDB = new QuestionDbMock();
	}

	public static QuestionAbstractFactoryMock instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new QuestionAbstractFactoryMock();
		}
		return uniqueInstance;
	}

	public IQuestionPersistence getQuestionDBMock()
	{
		return questionDB;
	}

	public IQuestion getQuestion()
	{
		return new Question();
	}

	public IOption getOption()
	{
		return new Option();
	}
}

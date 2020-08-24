package CSCI5308.GroupFormationTool.Question;

public class QuestionAbstractFactory implements IQuestionAbstractFactory
{
	private static QuestionAbstractFactory uniqueInstance = null;
	private IQuestionPersistence questionDB;

	private QuestionAbstractFactory()
	{
		questionDB = new QuestionDB();
	}

	public static QuestionAbstractFactory instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new QuestionAbstractFactory();
		}
		return uniqueInstance;
	}

	public IQuestionPersistence getQuestionDB()
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

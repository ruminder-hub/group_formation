package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDbMock implements IQuestionPersistence {

	private List<List<String>> questionList = new ArrayList<List<String>>();

	public QuestionDbMock()
	{
		List<String> mockList = new ArrayList<String>();
		mockList.add("123456");
		mockList.add("Mock Question Title");
		mockList.add("Mock Question Text");
		mockList.add("Mock Question Type");
		mockList.add("Mock Date Created");
		questionList.add(mockList);
	}

	@Override
	public Boolean saveQuestion(IQuestion question, String id)
	{
		question.setTitle("Tell us more");
		question.setType("FREE_TEXT");
		return true;
	}

	@Override
	public Integer getQuestionIdByTitleTextType(IQuestion question)
	{
		question.setId(3);
		return 3;
	}

	@Override
	public List<List<String>> getQuestionsByInstructorID(String instructorId, String order)
	{
		if (instructorId.equals("B-444444") && order != null)
		{
			return questionList;
		}
		return null;
	}

	@Override
	public boolean removeQuestionFromDatabase(String questionID)
	{
		if (questionID != null && questionID.equals("123456"))
		{
			return true;
		}
		return false;
	}
}

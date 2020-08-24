package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Question.QuestionAbstractFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
@SuppressWarnings("deprecation")
public class QuestionDBMockTest
{
	@Test
	public void saveQuestionTest()
	{
		IQuestionPersistence questionPersistence = QuestionAbstractFactoryMock.instance().getQuestionDBMock();
		IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
		question.setTitle("How many hours of credits required");
		question.setType("NUMERIC");
		questionPersistence.saveQuestion(question, "B-000000");
		Assert.isTrue(question.getType().equals("FREE_TEXT"));
		Assert.isTrue(question.getTitle().equals("Tell us more"));
	}

	@Test
	public void getQuestionIdByTitleTextTypeText()
	{
		IQuestionPersistence questionPersistence = QuestionAbstractFactoryMock.instance().getQuestionDBMock();
		IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
		question.setTitle("How many hours of credits required");
		question.setType("NUMERIC");
		questionPersistence.getQuestionIdByTitleTextType(question);
		Assert.isTrue(question.getId() == 3);
	}

	@Test
	public void getQuestionsByInstructorID()
	{
		IQuestionPersistence questionPersistence = QuestionAbstractFactoryMock.instance().getQuestionDBMock();
		String instructorId = "B-444444";
		String order = "Mock Order";
		List<List<String>> questionList = questionPersistence.getQuestionsByInstructorID(instructorId, order);
		Assert.isTrue(questionList.size() == 1);
	}

	@Test
	public void removeQuestionFromDatabase()
	{
		IQuestionPersistence questionPersistence = QuestionAbstractFactoryMock.instance().getQuestionDBMock();
		String questionId = "123456";
		boolean isQuestionDeleted = questionPersistence.removeQuestionFromDatabase(questionId);
		Assert.isTrue(isQuestionDeleted);
	}
}

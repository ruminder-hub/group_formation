package CSCI5308.GroupFormationTool.QuestionsTest;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Question.*;
import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@SuppressWarnings("deprecation")
public class QuestionTest {
	
	@Test
	public void ConstructorTests() 
	{
		IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
		Assert.isTrue(question.getId() == -1);
		Assert.isTrue(question.getTitle().isEmpty());
		Assert.isTrue(question.getQuestion().isEmpty());
		Assert.isTrue(question.getType().isEmpty());
		Assert.isTrue(CollectionUtils.isEmpty(question.getAnswerOptions()));
	}

	@Test
	public void setIdTest() 
	{
		IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
		question.setId(1);
		Assert.isTrue(question.getId() == 1);
	}

	@Test
	public void getIdTest() 
	{
		IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
		question.setId(2);
		Assert.isTrue(question.getId() == 2);
	}
	
	@Test
	public void setTitleTest() 
	{
		IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
		String questionTitle = "General Question";
		
		question.setTitle(questionTitle);
		Assert.isTrue(question.getTitle().equals(questionTitle));
	}
	
	@Test
	public void getTitleTest() 
	{
		IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
		String questionTitle = "Java";
		
		question.setTitle(questionTitle);
		Assert.isTrue(question.getTitle().equals(questionTitle));
	}
	
	@Test
	public void setQuestionTest() 
	{
		IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
		String questionText = "How many credits you are taking?";
		
		question.setQuestion(questionText);
		Assert.isTrue(question.getQuestion().equals(questionText));
	}
	
	@Test
	public void getQuestionTest() 
	{
		IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
		String questionText = "Indication role you want to perform?";
		
		question.setQuestion(questionText);
		Assert.isTrue(question.getQuestion().equals(questionText));
	}
	
	@Test
	public void setTypeTest() 
	{
		IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
		String questionType = "Numeric";
		
		question.setType(questionType);
		Assert.isTrue(question.getType().equals(questionType));
	}

	@Test
	public void getTypeTest() 
	{
		IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
		String questionType = "Free Text";
		
		question.setType(questionType);
		Assert.isTrue(question.getType().equals(questionType));
	}
	
	@Test
	public void setAnswerOptionsTest() 
	{
		IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
		IOption option = QuestionAbstractFactoryMock.instance().getOption();
		option.setText("Java");
		option.setValue("1");
		List<IOption> optionList = new ArrayList<>();
		optionList.add(option);
		question.setAnswerOptions(optionList);
		Assert.isTrue(!question.getAnswerOptions().isEmpty());
	}
	
	@Test
	public void getAnswerOptionsTest() 
	{
		IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
		IOption option = QuestionAbstractFactoryMock.instance().getOption();
		option.setText("PHP");
		option.setValue("2");
		List<IOption> optionList = new ArrayList<>();
		optionList.add(option);
		question.setAnswerOptions(optionList);
		Assert.isTrue(!question.getAnswerOptions().isEmpty());
	}
	
	@Test
	public void saveQuestionTest()
	{
		IQuestionPersistence questionPersistence = new QuestionDbMock();
		Question question = new Question();
		question.setTitle("How many hours of credits required");
		question.setType("NUMERIC");
		questionPersistence.saveQuestion(question, "B-000000");
		Assert.isTrue(question.getType().equals("FREE_TEXT"));
		Assert.isTrue(question.getTitle().equals("Tell us more"));
	}
}

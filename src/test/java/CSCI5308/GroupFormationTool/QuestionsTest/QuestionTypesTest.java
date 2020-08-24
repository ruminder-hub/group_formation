package CSCI5308.GroupFormationTool.QuestionsTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.Question.QuestionTypes;

@SpringBootTest
@SuppressWarnings("deprecation")
public class QuestionTypesTest 
{
	@Test
	public void getNameTest()
	{
		String questionType = "NUMERIC";
		Assert.isTrue(QuestionTypes.NUMERIC.name().equals(questionType));
	}
	
	@Test
	public void getValueTest()
	{
		String questionTypeValue = "Multiple Choice Choose 1";
		Assert.isTrue(QuestionTypes.MULTIPLE_CHOICE_CHOOSE_ONE.getValue().equals(questionTypeValue));
	}
}

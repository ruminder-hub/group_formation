package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Question.IOption;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.Question.Option;
import CSCI5308.GroupFormationTool.Question.Question;

@SpringBootTest
@SuppressWarnings("deprecation")
public class OptionTest
{
	@Test
	public void ConstructorTests() 
	{
		IOption option = QuestionAbstractFactoryMock.instance().getOption();
		Assert.isTrue(option.getText().isEmpty());
		Assert.isTrue(option.getValue().isEmpty());		
	}
	
	@Test
	public void setTextTest() 
	{
		IOption option = QuestionAbstractFactoryMock.instance().getOption();
		String optionText = "Java";
		
		option.setText(optionText);
		Assert.isTrue(option.getText().equals(optionText));
	}
	
	@Test
	public void getTextTest() 
	{
		IOption option = QuestionAbstractFactoryMock.instance().getOption();
		String optionText = "PHP";
		
		option.setText(optionText);
		Assert.isTrue(option.getText().equals(optionText));
	}
	
	@Test
	public void setValueTest() 
	{
		IOption option = QuestionAbstractFactoryMock.instance().getOption();
		String optionValue = "1";
		
		option.setValue(optionValue);
		Assert.isTrue(option.getValue().equals(optionValue));
	}
	
	@Test
	public void getValuTest() 
	{
		IOption option = QuestionAbstractFactoryMock.instance().getOption();
		String optionValue = "3";
		
		option.setValue(optionValue);
		Assert.isTrue(option.getValue().equals(optionValue));
	}
}

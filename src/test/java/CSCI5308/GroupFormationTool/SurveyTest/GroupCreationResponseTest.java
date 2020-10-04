package CSCI5308.GroupFormationTool.SurveyTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.Survey.GroupCreationResponse;

@SpringBootTest
@SuppressWarnings("deprecation")
public class GroupCreationResponseTest {
	
	@Test
	public void ConstructorTests() 
	{
		GroupCreationResponse groupCreationResponse = new GroupCreationResponse();
		Assert.isTrue(groupCreationResponse.getId() == -1);
		Assert.isTrue(groupCreationResponse.getResponse() == -1);
		Assert.isTrue(groupCreationResponse.getxValue() == -1);
		Assert.isTrue(groupCreationResponse.isIncludeMinOneWithValueGreaterThanX() == false);
		Assert.isTrue(groupCreationResponse.isIncludeMinOneWithValueLessThanX() == false);
	}
	
	@Test
	public void setIdTest() 
	{
		GroupCreationResponse groupCreationResponse = new GroupCreationResponse();
		groupCreationResponse.setId(1);
		Assert.isTrue(groupCreationResponse.getId() == 1);
	}

	@Test
	public void getIdTest() 
	{
		GroupCreationResponse groupCreationResponse = new GroupCreationResponse();
		groupCreationResponse.setId(2);
		Assert.isTrue(groupCreationResponse.getId() == 2);
	}
	
	@Test
	public void setResponseTest() 
	{
		GroupCreationResponse groupCreationResponse = new GroupCreationResponse();
		groupCreationResponse.setResponse(1);
		Assert.isTrue(groupCreationResponse.getResponse() == 1);
	}

	@Test
	public void getResponseTest() 
	{
		GroupCreationResponse groupCreationResponse = new GroupCreationResponse();
		groupCreationResponse.setResponse(2);
		Assert.isTrue(groupCreationResponse.getResponse() == 2);
	}
	
	@Test
	public void setxValueTest() 
	{
		GroupCreationResponse groupCreationResponse = new GroupCreationResponse();
		groupCreationResponse.setxValue(4);
		Assert.isTrue(groupCreationResponse.getxValue() == 4);
	}

	@Test
	public void getxValueTest() 
	{
		GroupCreationResponse groupCreationResponse = new GroupCreationResponse();
		groupCreationResponse.setxValue(3);
		Assert.isTrue(groupCreationResponse.getxValue() == 3);
	}
	
	@Test
	public void setIncludeMinOneWithValueGreaterThanXTest() 
	{
		GroupCreationResponse groupCreationResponse = new GroupCreationResponse();
		groupCreationResponse.setIncludeMinOneWithValueGreaterThanX(true);
		Assert.isTrue(groupCreationResponse.isIncludeMinOneWithValueGreaterThanX());
	}

	@Test
	public void isIncludeMinOneWithValueGreaterThanXTest() 
	{
		GroupCreationResponse groupCreationResponse = new GroupCreationResponse();
		groupCreationResponse.setIncludeMinOneWithValueGreaterThanX(false);
		Assert.isTrue(groupCreationResponse.isIncludeMinOneWithValueGreaterThanX() == false);
	}
	
	@Test
	public void setIncludeMinOneWithValueLessThanXTest() 
	{
		GroupCreationResponse groupCreationResponse = new GroupCreationResponse();
		groupCreationResponse.setIncludeMinOneWithValueLessThanX(true);
		Assert.isTrue(groupCreationResponse.isIncludeMinOneWithValueLessThanX());
	}

	@Test
	public void isIncludeMinOneWithValueLessThanXTest() 
	{
		GroupCreationResponse groupCreationResponse = new GroupCreationResponse();
		groupCreationResponse.setIncludeMinOneWithValueLessThanX(false);
		Assert.isTrue(groupCreationResponse.isIncludeMinOneWithValueLessThanX() == false);
	}
	
	

}

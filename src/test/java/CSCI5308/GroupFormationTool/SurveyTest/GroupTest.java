package CSCI5308.GroupFormationTool.SurveyTest;

import java.util.LinkedList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.Survey.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.AccessControl.User;

@SpringBootTest
@SuppressWarnings("deprecation")
public class GroupTest {

	@Test
	public void ConstructorTests() 
	{
		IGroup group = SurveyAbstractFactoryMock.instance().getGroup();
		Assert.isTrue(group.getGroupId() == -1);
		Assert.isTrue(group.getThreshold() == -1);
		Assert.isTrue(group.getCurrentSize() == -1);
		Assert.isTrue(group.getUserResponses() == null);
		Assert.isTrue(group.getUsers() == null);
	}
	
	@Test
	public void setGroupIdTest() 
	{
		IGroup group = SurveyAbstractFactoryMock.instance().getGroup();
		group.setGroupId(1);
		Assert.isTrue(group.getGroupId() == 1);
	}

	@Test
	public void getGroupIdTest() 
	{
		IGroup group = SurveyAbstractFactoryMock.instance().getGroup();
		group.setGroupId(2);
		Assert.isTrue(group.getGroupId() == 2);
	}
	
	@Test
	public void setThresholdTest() 
	{
		IGroup group = SurveyAbstractFactoryMock.instance().getGroup();
		group.setThreshold(90);
		Assert.isTrue(group.getThreshold() == 90);
	}

	@Test
	public void getThresholdTest() 
	{
		IGroup group = SurveyAbstractFactoryMock.instance().getGroup();
		group.setThreshold(80);
		Assert.isTrue(group.getThreshold() == 80);
	}
	
	@Test
	public void setCurrentSizeTest() 
	{
		IGroup group = SurveyAbstractFactoryMock.instance().getGroup();
		group.setCurrentSize(3);
		Assert.isTrue(group.getCurrentSize() == 3);
	}

	@Test
	public void getCurrentSizeTest() 
	{
		IGroup group = SurveyAbstractFactoryMock.instance().getGroup();
		group.setCurrentSize(2);
		Assert.isTrue(group.getCurrentSize() == 2);
	}
	
	@Test
	public void setUserResponsesTest() 
	{
		IGroup group = SurveyAbstractFactoryMock.instance().getGroup();
		List<List<IResponse>> userResponses = new LinkedList<List<IResponse>>();
		group.setUserResponses(userResponses);
		Assert.isTrue(group.getUserResponses().size() == 0);
	}

	@Test
	public void getUserResponsesTest() 
	{
		IGroup group = SurveyAbstractFactoryMock.instance().getGroup();
		List<List<IResponse>> userResponses = new LinkedList<List<IResponse>>();
		group.setUserResponses(userResponses);
		Assert.isTrue(group.getUserResponses() != null);
	}
	
	@Test
	public void setUsersTest() 
	{
		IGroup group = SurveyAbstractFactoryMock.instance().getGroup();
		List<IUser> users = new LinkedList<IUser>();
		group.setUsers(users);
		Assert.isTrue(group.getUsers().size() == 0);
	}

	@Test
	public void getUsersTest() 
	{
		IGroup group = SurveyAbstractFactoryMock.instance().getGroup();
		List<IUser> users = new LinkedList<IUser>();
		group.setUsers(users);
		Assert.isTrue(group.getUsers() != null);
	}
}

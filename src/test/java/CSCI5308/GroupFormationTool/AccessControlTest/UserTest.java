package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.PasswordPolicy.Context;
import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicyContextListBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@SuppressWarnings("deprecation")
public class UserTest
{
	@Test
	public void ConstructorTests()
	{
		IUser u = AccessControlAbstractFactoryMock.instance().getUser();
		Assert.isTrue(u.getBannerID().isEmpty());
		Assert.isTrue(u.getFirstName().isEmpty());
		Assert.isTrue(u.getLastName().isEmpty());
		Assert.isTrue(u.getEmail().isEmpty());
		
		IUserPersistence userDBMock = AccessControlAbstractFactoryMock.instance().getUserDBMock();
		u = new User(1, userDBMock);
		Assert.isTrue(u.getBannerID().equals("B00000000"));
		
		u = new User("B00000000", userDBMock);
		Assert.isTrue(u.getBannerID().equals("B00000000"));
	}
	
	@Test
	public void setIDTest()
	{
		IUser u = AccessControlAbstractFactoryMock.instance().getUser();
		u.setID(10);
		Assert.isTrue(10 == u.getID());
	}
	
	@Test
	public void getIDTest()
	{
		IUser u = AccessControlAbstractFactoryMock.instance().getUser();
		u.setID(10);
		Assert.isTrue(10 == u.getID());
	}
	
	@Test
	public void setBannerIDTest()
	{
		IUser u = AccessControlAbstractFactoryMock.instance().getUser();
		u.setBannerID("B00000000");
		Assert.isTrue(u.getBannerID().equals("B00000000"));
	}
	
	@Test
	public void getBannerIDTest()
	{
		IUser u = AccessControlAbstractFactoryMock.instance().getUser();
		u.setBannerID("B00000000");
		Assert.isTrue(u.getBannerID().equals("B00000000"));
	}
	
	@Test
	public void setFirstNameTest()
	{
		IUser u = AccessControlAbstractFactoryMock.instance().getUser();
		u.setFirstName("Rob");
		Assert.isTrue(u.getFirstName().equals("Rob"));
	}
	
	@Test
	public void getFirstNameTest()
	{
		IUser u = AccessControlAbstractFactoryMock.instance().getUser();
		u.setFirstName("Rob");
		Assert.isTrue(u.getFirstName().equals("Rob"));
	}

	@Test
	public void setLastNameTest()
	{
		IUser u = AccessControlAbstractFactoryMock.instance().getUser();
		u.setLastName("Hawkey");
		Assert.isTrue(u.getLastName().equals("Hawkey"));
	}

	@Test
	public void getLastNameTest()
	{
		IUser u = AccessControlAbstractFactoryMock.instance().getUser();
		u.setLastName("Hawkey");
		Assert.isTrue(u.getLastName().equals("Hawkey"));
	}
	
	@Test
	public void setEmailTest()
	{
		IUser u = AccessControlAbstractFactoryMock.instance().getUser();
		u.setEmail("rhawkey@dal.ca");
		Assert.isTrue(u.getEmail().equals("rhawkey@dal.ca"));
	}
	
	@Test
	public void getEmailTest()
	{
		IUser u = AccessControlAbstractFactoryMock.instance().getUser();
		u.setEmail("rhawkey@dal.ca");
		Assert.isTrue(u.getEmail().equals("rhawkey@dal.ca"));
	}
	
	@Test
	public void createUser()
	{		
		IUserPersistence userDB = AccessControlAbstractFactoryMock.instance().getUserDBMock();
		IUser user = AccessControlAbstractFactoryMock.instance().getUser();
		userDB.createUser(user);
		Assert.isTrue(user.getId() == 0);
		Assert.isTrue(user.getBannerID().equals("B00000000"));
	}

	@Test
	public void isBannerIDValidTest()
	{
		IUser user = AccessControlAbstractFactoryMock.instance().getUser();
		Assert.isTrue(user.isBannerIDValid("B000000000"));
		Assert.isTrue(!user.isBannerIDValid(null));
		Assert.isTrue(!user.isBannerIDValid(""));
	}
		
	@Test
	public void isFirstNameValidTest()
	{
		IUser user = AccessControlAbstractFactoryMock.instance().getUser();
		Assert.isTrue(user.isFirstNameValid("rob"));
		Assert.isTrue(!user.isFirstNameValid(null));
		Assert.isTrue(!user.isFirstNameValid(""));
	}
	
	@Test
	public void isLastNameValidTest()
	{
		IUser user = AccessControlAbstractFactoryMock.instance().getUser();
		Assert.isTrue(user.isLastNameValid("hawkey"));
		Assert.isTrue(!user.isLastNameValid(null));
		Assert.isTrue(!user.isLastNameValid(""));
	}
	
	@Test
	public void isEmailValidTest()
	{
		IUser user = AccessControlAbstractFactoryMock.instance().getUser();
		Assert.isTrue(user.isEmailValid("rhawkey@dal.ca"));
		Assert.isTrue(!user.isEmailValid(null));
		Assert.isTrue(!user.isEmailValid(""));
		Assert.isTrue(!user.isEmailValid("@dal.ca"));
		Assert.isTrue(!user.isEmailValid("rhawkey@"));
	}

	@Test
	public void failedPasswordValidationListTest()
	{
		IUser user = AccessControlAbstractFactoryMock.instance().getUser();
		IPasswordPolicyContextListBuilder listBuilder = new PasswordPolicyContextListBuilderMock();

		user.setPassword("abcd");
		user.setBannerID("B00123456");
		List<Context> allPasswordPolicyList = listBuilder.createAllPasswordPolicyList(user);

		List<String> failedValidationCriteriaList = new ArrayList<>();

		for (int i=0; i<allPasswordPolicyList.size(); i++)
		{
			if(allPasswordPolicyList.get(i).executeStrategy(user.getPassword()) == false)
			{
				failedValidationCriteriaList.add(allPasswordPolicyList.get(i).getStrategy().getValidationCriteria());
			}
		}
		Assertions.assertEquals(3,failedValidationCriteriaList.size());

		user.setPassword("abcde@Ghi");
		user.setBannerID("B00123456");
		allPasswordPolicyList = listBuilder.createAllPasswordPolicyList(user);

		failedValidationCriteriaList = new ArrayList<>();

		for (int i=0; i<allPasswordPolicyList.size(); i++)
		{
			if(allPasswordPolicyList.get(i).executeStrategy(user.getPassword()) == false)
			{
				failedValidationCriteriaList.add(allPasswordPolicyList.get(i).getStrategy().getValidationCriteria());
			}
		}
		Assertions.assertEquals(0,failedValidationCriteriaList.size());
	}
}

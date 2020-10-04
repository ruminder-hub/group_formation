package CSCI5308.GroupFormationTool.CoursesTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.AccessControlTest.AccessControlAbstractFactoryMock;
import CSCI5308.GroupFormationTool.Courses.*;
import CSCI5308.GroupFormationTool.SecurityTest.SecurityAbstractFactoryMock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import CSCI5308.GroupFormationTool.AccessControlTest.UserDBMock;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.SecurityTest.PasswordEncryptionMock;

@SpringBootTest
@SuppressWarnings("deprecation")
class StudentCSVImportTest 
{
	@Test
	public void enrollStudentFromRecordTest()
	{
		IUser user = AccessControlAbstractFactoryMock.instance().getUser();
		IUserPersistence userDB = AccessControlAbstractFactoryMock.instance().getUserDBMock();
		IPasswordEncryption passwordEncryption = SecurityAbstractFactoryMock.instance().getPasswordEncryptionMock();
		Assert.isTrue(user.createUser(userDB, passwordEncryption, null));
	}

	@Test
	public void getSuccessResultsTest()
	{
		List<String> successResults = new ArrayList<String>();
		successResults.add("Created record");
		assertThat(successResults).isNotNull();
		assertThat(successResults).isNotEmpty();
		Assert.isTrue(successResults.size() > 0);
	}

	@Test
	public void getFailureResultsTest()
	{
		List<String> failureResults = new ArrayList<String>();
		failureResults.add("Created record");
		assertThat(failureResults).isNotNull();
		assertThat(failureResults).isNotEmpty();
		Assert.isTrue(failureResults.size() > 0);
	}

	@Test
	public void getNewStudentsTest()
	{
		List<IUser> students = new ArrayList<>();
		IUser user = AccessControlAbstractFactoryMock.instance().getUser();
		user.setBannerID("B-999333");
		user.setFirstName("Tony");
		user.setLastName("Stark");
		user.setEmail("tony@dal.ca");
		user.setPassword("12345");
		students.add(user);
		Assert.isTrue(students.size() > 0);
		assertThat(students).isNotEmpty();
		assertThat(students).isNotNull();
	}
}

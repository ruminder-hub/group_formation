package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.AccessControl.IUser;

import java.util.List;
import java.util.Map;

public interface IGroup
{
	public List<IGroup> createGroups(List<IGroupCreationResponse> questionResponseList, long courseId, int groupSize, ISurveyPersistence surveyDB);

	public void setDefaults();

	public int getGroupId();

	public void setGroupId(int groupId);

	public int getThreshold();

	public void setThreshold(int threshold);

	public List<IUser> getUsers();

	public void setUsers(List<IUser> users);

	public List<List<IResponse>> getUserResponses();

	public void setUserResponses(List<List<IResponse>> userResponses);

	public int getCurrentSize();

	public void setCurrentSize(int currentSize);

	public int createGroupFromUserResponse(Map<String, List<IResponse>> studentResponses, List<IGroup> grpList, List<IGroupCreationResponse> questionResponseList, Integer groupSize, Integer noOfGroups, Integer initialThreshold);

	public double matchSimilarContent(String []grpUserResponse, String []currUserResponse);

	public int matchDissimilarContent(String []grpUserResponse, String []currUserResponse);

	public int checkStringMatch(String str1, String str2);

	public int checkItemsInArrMatched(String []arr1, String []arr2);

	public  Map<String, List<IResponse>> fetchUserResponseForQuestions(List<String> studentList, long courseId, List<IGroupCreationResponse> questions, ISurveyPersistence surveyDB);
}

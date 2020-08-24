package CSCI5308.GroupFormationTool.Survey;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.AccessControl.AccessControlAbstractFactory;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Group implements IGroup
{
	private Logger log = LoggerFactory.getLogger(Group.class);
	
	private int groupId;
	private int threshold;
	private List<IUser> users;
	private List<List<IResponse>> userResponses;
	private int currentSize;
	
	public Group()
	{
		setDefaults();
	}
	
	public void setDefaults()
	{
		groupId = -1;
		threshold = -1;
		users = null;
		userResponses = null;
		currentSize = -1;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public List<IUser> getUsers() {
		return users;
	}

	public void setUsers(List<IUser> users) {
		this.users = users;
	}

	public List<List<IResponse>> getUserResponses() {
		return userResponses;
	}

	public void setUserResponses(List<List<IResponse>> userResponses) {
		this.userResponses = userResponses;
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public void setCurrentSize(int currentSize) {
		this.currentSize = currentSize;
	}

	public List<IGroup> createGroups(List<IGroupCreationResponse> questionResponseList, long courseId, int groupSize, ISurveyPersistence surveyDB)
	{
		int initialThreshold = 90;
		List<String> studentBannerIdList = surveyDB.getStudentBannersWhoFilledSurvey(courseId);
		log.info("No of students who filled survey: " + studentBannerIdList);
		int noOfGroups = studentBannerIdList.size() / groupSize;
		if (studentBannerIdList.size() % groupSize != 0)
		{
			noOfGroups++;
		}
		
		List<IGroup> grpList = new LinkedList<>();
		for (int i = 0; i < noOfGroups; i++)
		{
			IGroup group = SurveyAbstractFactory.instance().getGroup();
			group.setCurrentSize(0);
			grpList.add(group);
		}
		
		Map<String, List<IResponse>> studentResponses = fetchUserResponseForQuestions(studentBannerIdList, courseId, questionResponseList, surveyDB);
		
		while(true)
		{
			int studentAddedToGroup = createGroupFromUserResponse(studentResponses, grpList, questionResponseList, groupSize, noOfGroups, initialThreshold);
			log.info("Students added to different Groups: " + studentAddedToGroup + " with matching criteria threshold of: " + initialThreshold);
			if (studentAddedToGroup == studentBannerIdList.size())
			{
				break;
			} 
			else {
				initialThreshold = initialThreshold - 5;
				grpList = new LinkedList<>();
				for (int i = 0; i < noOfGroups; i++)
				{
					IGroup group = SurveyAbstractFactory.instance().getGroup();
					group.setCurrentSize(0);
					grpList.add(group);
				}
			}
		}
		log.info("Groups created successfully");
		return grpList;
		
	}
	
	public int createGroupFromUserResponse(Map<String, List<IResponse>> studentResponses, List<IGroup> grpList, List<IGroupCreationResponse> questionResponseList, Integer groupSize, Integer noOfGroups, Integer initialThreshold)
	{
		int studentAddedToGroup = 0;
		int groupNumber = 1;
		for (Map.Entry<String, List<IResponse>> studentResponse : studentResponses.entrySet())
		{
			for(int i = 0; i < noOfGroups; i++)
			{
				IGroup currGrp = grpList.get(i);
				int currGroupSize = currGrp.getCurrentSize();
				if(currGroupSize < groupSize)
				{
					if (currGroupSize == 0)
					{
						IUser user = AccessControlAbstractFactory.instance().getUser();
						user.setBannerID(studentResponse.getKey());
						List<IUser> userList = new LinkedList<>();
						userList.add(user);
						
						currGrp.setUsers(userList);
						currGrp.setCurrentSize(1);
						currGrp.setGroupId(groupNumber++);
						if (currGrp.getUserResponses() == null)
						{
							List<List<IResponse>> responseList = new LinkedList<List<IResponse>>();
							responseList.add(studentResponse.getValue());
							currGrp.setUserResponses(responseList);
						}
						studentAddedToGroup++;
						log.info("User added to new Group");
						break;
					} else 
					{
						double percentageMatched = 0;
					
						for(List<IResponse> responeOfGrpUser : currGrp.getUserResponses())
						{
							int currUserMatched = 0;
							for(int j = 0; j < responeOfGrpUser.size(); j++)
							{
								double currQuesPercentMatched = 0;
								if (questionResponseList.get(j).getResponse() == 1)
								{
									currQuesPercentMatched = matchSimilarContent(responeOfGrpUser.get(j).getResponseList(), studentResponse.getValue().get(j).getResponseList());
								} 
								else if (questionResponseList.get(j).getResponse() == 2)
								{
									currQuesPercentMatched = matchDissimilarContent(responeOfGrpUser.get(j).getResponseList(), studentResponse.getValue().get(j).getResponseList());
								}
								currUserMatched += currQuesPercentMatched;
							}
							currUserMatched = currUserMatched / responeOfGrpUser.size();
							percentageMatched += currUserMatched;
						}
						double currUserMatchWithGrp = percentageMatched / currGrp.getCurrentSize();
						log.debug("Value of Current USer similarity " + currUserMatchWithGrp);
						if (currUserMatchWithGrp >= initialThreshold)
						{
							IUser user = AccessControlAbstractFactory.instance().getUser();
							user.setBannerID(studentResponse.getKey());
							currGrp.getUsers().add(user);
							currGrp.setCurrentSize(currGrp.getCurrentSize() + 1);
							currGrp.getUserResponses().add(studentResponse.getValue());
							studentAddedToGroup++;
							break;
							
						}
					}
				}
			}
		}
		return studentAddedToGroup;
		
	}
	
	public double matchSimilarContent(String []grpUserResponse, String []currUserResponse)
	{
		log.debug("Matching question for similar content");
		double currQuesPercentMatched = 0;
		if (grpUserResponse.length > 1 || currUserResponse.length > 1)
		{
			log.debug("Matching Multiple Choice Choose Many Question");
			int maxOptionsSelected = grpUserResponse.length;
			if (maxOptionsSelected < currUserResponse.length)
			{
				maxOptionsSelected = currUserResponse.length;
			}
			int matched = checkItemsInArrMatched(currUserResponse, grpUserResponse);
			currQuesPercentMatched = (matched * 100) / maxOptionsSelected;
		} 
		else
		{
			if (grpUserResponse[0].contains(" "))
			{
				log.debug("Mathcing Free Text question for similarity");
				currQuesPercentMatched = checkStringMatch(grpUserResponse[0], currUserResponse[0]);
			} 
			else
			{
				log.debug("Mathcing Numeric or Mulitiple Choice Choose One question for similarity");
				if (grpUserResponse[0].equals(currUserResponse[0])) {
					currQuesPercentMatched = 100;
				} 
				else
				{
					currQuesPercentMatched = 0;
				}
			}
			
		}
		return currQuesPercentMatched;
	}
	
	public int matchDissimilarContent(String []grpUserResponse, String []currUserResponse)
	{
		int currQuesPercentDisMatched = 0;
		log.debug("Matching question for dissimilar content");
		if (grpUserResponse.length > 1 || currUserResponse.length > 1)
		{
			log.debug("Matching Multiple Choice Choose Many Question");
			int maxOptionsSelected = grpUserResponse.length;
			if (maxOptionsSelected < currUserResponse.length)
			{
				maxOptionsSelected = currUserResponse.length;
			}
			int matched = checkItemsInArrMatched(currUserResponse, grpUserResponse);
			int disMatched = maxOptionsSelected - matched;
			currQuesPercentDisMatched = disMatched * 100 / maxOptionsSelected;
		} 
		else
		{
			if (grpUserResponse[0].contains(" "))
			{
				log.debug("Matching Free Text question for similarity");
				int perCentOfSimilarity = checkStringMatch(grpUserResponse[0], currUserResponse[0]);
				currQuesPercentDisMatched = 100 - perCentOfSimilarity;
			} 
			else
			{
				log.debug("Matching Numeric or Multiple Choice Choose One question for similarity");
				if (grpUserResponse[0].equals(currUserResponse[0])) {
					currQuesPercentDisMatched = 0;
				} 
				else
				{
					currQuesPercentDisMatched = 100;
				}
			}
			
		}
		return currQuesPercentDisMatched;
	}
	
	public int checkStringMatch(String str1, String str2)
	{
		log.debug("Entered String match for string: " + str1 + " and string: " + str2);
		String []str1Arr = str1.split(" ");
		String []str2Arr = str2.split(" ");
		int matched = checkItemsInArrMatched(str1Arr, str2Arr);
		int maxWords = str1Arr.length;
		if (maxWords < str2Arr.length)
		{
			maxWords = str2Arr.length;
		}
		
		return matched / maxWords * 100;
	}
	
	public int checkItemsInArrMatched(String []arr1, String []arr2)
	{
		log.debug("Entered checkItemsInArrMatched for matching string array");
		int matched = 0;
		for(int k = 0; k < arr1.length; k++)
		{
			for(String str : arr2)
			{
				if (str.equalsIgnoreCase(arr1[k]))
				{
					matched++;
				}
			}
		}
		log.debug("No of matched items: " + matched);
		return matched;
	}
	
	public Map<String, List<IResponse>> fetchUserResponseForQuestions(List<String> studentList, long courseId, List<IGroupCreationResponse> questions, ISurveyPersistence surveyDB)
	{
		log.info("Entered fetchUserResponseForQuestions fetching the student responses for the survey questions");
		Map<String, List<IResponse>> studentResponses = new HashMap<String, List<IResponse>>();
		for (String studentId: studentList)
		{
			List<IResponse> responseList = new LinkedList<>();
			for (IGroupCreationResponse question : questions)
			{
				IResponse response = surveyDB.getStudentResponseCorrespondingToQuestion(question.getId(), courseId, studentId);
				responseList.add(response);
			}
			studentResponses.put(studentId, responseList);
		}
		return studentResponses;
	}
}
